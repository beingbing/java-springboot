# Authentication
- understand authentication as identity verification you do while entering an airport. It do not make you eligible to board a flight, for which you need to pass authorization. But you can still atleast enter the airport.
- the easiest way for a client to query for an authentication is by sending required credentials in header under `Authorization` key.
- above is the brute force way, definitely in this approach user needs to pass username+password everytime client send a request (of course, client can encode to send over the internet, example base64 encoding)
- but passing sensitive details over the wire repeatedly might risk decoding by malicious users, they will figure it out eventually and mimic the users without them being aware.
- Also, keeping a DB interaction in authentication layer will heavily slow down the process, suppose if we have cache layer after authentication, then the purpose of serving client faster, is why we keep cache, and if authentication slow down our request to even reach to the cache layer then it defeats the purpose.
- instead when the first time login happen we can provide FE with an encrypted token to send in further requests.
- we will persist the decrypted token against that user in DB along with an expiry date, and validate to allow the request or reject it.
- although it is better than the previous solution, in terms of safety of our credentials, our sensitive data will be hard to capture and decode.
- drawback here is that still for every api call we need to talk to DB.
- DB interaction is expensive, so avoid it to be done everytime.
- instead we can use JWT(JSON Web Token)

# JWT
- JWT structure is 3 strings separated by 2 dots (.) - strA.strB.strC
- strA: basic info: { "alg": "SHA256", ... }
- strB: payload (usrname, expiryDate, etc...)
- strC: signature (unique secret comprises of normally 256 bits)
- structure: basic_info.payload(username, expiryDate, etc...).signature
- secretKey is created using encrypting algorithm like SHA256
- FE  -- usrnm + pswd --> BE (basicInfo --encode--> strA, payload --base64 encode --> strB, basicInfo+payload+secretKey --encode--> strC) --> give it back to FE
- didn't store anything in DB but FE store it now in browser.
- now validation -
- FE -- token --> BE (strA --decrypt--> basicInfo, strB --decrypt--> payload, basicInfo+payload+secretKey --encrypt--> candidate). if candidate == signature then authenticated.

## drawback
- what if expiry time is too small that usr gets frequently locked out then user needs to relogin in which DB calls for fetching creds and generating payload will be too frequent. Which will defeat the purpose.
- solution: along with token (jwt), we will also create refresh_token (jwt) with higher expiryTime and save it in DB.

so, FE -- token + refreshToken --> BE (token expired)
                                    |
                                   BE (fetch refresh_token from DB and compare)
                                    |
FE <--response + RT + new token <-- BE (if refresh_token is validated, create new token and send back to user)

If refresh token expires then do the relogin.

to add dependencies JWT -
```xml
<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.12.6</version>
</dependency>

<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.12.6</version>
    <scope>runtime</scope>
</dependency>

<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.12.6</version>
    <scope>runtime</scope>
</dependency>
```
- you can check basic JWT token generation and validation in `JwtUtils`

## Exercise 1 (minor)
- create `/sign-up`, `/log-in`, `/log-out` and authentication on a controller route using login token
- for `/sign-up` make username unique and send with password, and once it is done, generate and hand the token
- if token expires, the redirect to `/log-in`, ask for username and password again. If matches, generate and return a new token.
- for other apis, a mandatory token should be received.
- for `/logout` first check whether the token is valid, then, instruct FE to delete token from `localstorage`.
- or for `/logout` first check whether the token is valid, then, generate a new token with almost no life and instruct FE to update the token, as soon as next replace will come, user will get redirected to `/login`.
- so in case of `/logout`, server do not need to do anything, just validate current token and strategize on evicting the token from FE end.

## Exercise 2 (major)
- 