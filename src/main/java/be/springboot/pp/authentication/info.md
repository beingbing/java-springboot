# Authentication

- one way is to pass username+password everytime we send a request (of course, we can encode it to send over the internet, example base64 encoding)
- but passing sensitive details over the wire repeatedly might risk decoding by malicious users, they will figure it out eventually and mimic the users without them being aware.
- instead when the first time login happen we can provide FE with a token to send in further requests.
- we will persist the token in DB along with an expiry date, and validate to allow the request or reject it.
- drawback here is that for every api call we need to talk to DB.
- DB interaction is expensive, so avoid it to be done everytime.
- instead we can use JWT(JSON Web Token)
- JWT structure is 3 strings separated by 2 dots (.)
- structure: basic_info.payload(username, expiryDate, etc...).signature
- 