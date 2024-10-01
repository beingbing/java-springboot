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

# security vulnerabilities
## denial of service (DoS attack)
- happens on server
- server is bombarded with too many requests
- solution: rate limiting on requests received from a particular IP.
- but attacker may change IP as well, hence companies have ML algos to limit these type of cases
## CSRF
- happens on browser
- Cross Site Request Forgery
- A user sent you link of a script, you clicks and it opens in browser.
- It has JS code to transfer fund from your account, it only needs your token
- which it takes from your `localstorage` and then will make the call.
- solution 1/2: banks keep token expiry time very small
- solution 2/2: two-factor authentication for payment.
- HW: find how it is prevented
## CORS
- happens on browser
- Cross Origin Resource Sharing
- browsers are designed to prevent it.
- before browser makes a request, it tries to get valid origins list from servers from which server wants to entertain requests.
- because servers can refuse to entertain requests from unknown source/origin
- once browser gets the list of valid origins from server, it checks if current website is present in that list or not.
- If present, then API call will be made, otherwise not
- scammers can still do CORS via proxy. unauthorized FE --> calls an unauthorized BE --> BE makes the call. As browser checks origin in the list but servers doesn't.

# Spring Security
add spring-boot-starter-security dependency to use spring-security -
```xml
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
    <version>3.3.3</version>
</dependency>
```
- as soon as the dependency is included in project structure
- spring-security will implement a password based authentication and prevent all api calls and start throwing `401 Unauthorized`
- only those calls will be allowed which are validated using the generated password under a default username `user`
- the details you have to send in request headers under name `Authorization` after selecting `Basic Authroization` from the dropdown
- it will send a base64 encrypted request of your user-name and password
- once a user is authenticated, `Authenticate` object is set in spring-context for controller/interceptors to build authorization logic on the basis of authorities assigned to user.

## Architecture
- The request is intercepted by authentication-filter interface (analogous to tomcat filters)
- Authentication responsibility is delegated to authentication-manager interface
- The authentication-manager is a thin layer devoid of any authentication logic and uses the authentication-provider which implements authentication logic.
- The authentication-provider finds the user with a user-details-service and validates the password using a password-encoder
- it contains all the information about user
- it contains userName, password, list of authorities, list of roles, etc.
- the responsibility of password-encoder is just of encoding and password matching logic hence validating the token
- authentication-provider propagates result back to AM, which sends it back to AF
- the result of authentication is returned to the authentication-filter
- AF stores details about the authenticated entity into security-context
- security-filter chain gets executed after TomCat filters or custom filters that we wrote
- the new flow after introducing spring-security is -
  - request --> goes through TomCat -> Filter1 --> Filter2 ... -> Filter n -> our custom servlet filter-chain -> `DefaultSecurityFilterChain` -> DispatcherServlet
- so, spring-security sits at the level of TomCat filters, by creating its own filter-chain which runs after all TomCat filters ran.
- if that is through, then request is handed over to spring-mvc
- the advantage we have in spring-security over TomCat filter is, the only drawback we had with servlet filters is got resolved.
- as we know there was no way to customize servlet filter to run on requests which came over a specific path, they used to run over every request.
- spring-security filter-chain can be customized to be invoked only on specific routes.
```mermaid
graph TB
    A[Client Request] -->|HTTP Request| B[Authentication Filter]
    B <--> C[Authentication Manager]
    C <--> D[Authentication Provider]
    B --> E[Security Context]
    D -.-> F[User Details Service]
    D -.-> G[Password Encoder]
```
```mermaid
graph TD
    A[Client Request] -->|HTTP Request| B[Spring Security Filter Chain]
    B --> C[SecurityContextPersistenceFilter]
    C --> D[UsernamePasswordAuthenticationFilter]
    D --> E[AuthenticationManager]
    E -->|Delegates| F[AuthenticationProvider]
    F --> G[UserDetailsService]
    G --> H[UserDetails]
    
    D -->|Authenticated| I[SecurityContextHolder]
    I -->|Holds| J[SecurityContext]
    
    E -->|Authenticated| K[AccessDecisionManager]
    K --> L[Security Metadata Source]
    
    K -->|Grants Access| M[Controller/Resource]

    A -.->|Denied| N[AccessDeniedHandler]
    N -.-> A[Client Request]
    
    M -->|Response| A[Client Request]
    
    B -.-> O[LogoutFilter]
    O -.-> A[Client Request]
```
## Write custom `UserDetailsService` and `PasswordEncoder`
- `InMemoryUserDetailsManager` is one of the five `UserDetailsService` implementation which is used by default
- `UserDetailsService` interface has only one method defined - `loadUserByUsername(String username)`
- `InMemoryUserDetailsManager` keeps all the detail in RAM
- `InMemoryUserDetailsManager` keeps a map of userName:userDetails
- it has multiple constructors, one of them takes a `Collection` of users and puts all of them in map.
- the details it keeps is of type `MutableUser` which implements `MutableUserDetails` and has `password` as one of the components of details
- `InMemoryUserDetailsManager` has many methods related to username and password
- `InMemoryUserDetailsManager` implementation of `loadUserByUsername(String username)` is simple, it gets the user from map, if not found throws an error.
- loadUserByUserName("user") will give all the details of creds made by spring-security by default for you.
- `InMemoryUserDetailsManager` while creating a set of default creds also create other details for it, like authorities provided under `GrantedAuthority`
- `NoOpPasswordEncoder` is the implementation of `PasswordEncoder` which is used by default with password validation along with `InMemoryUserDetailsManager`
- similarly, we can create a bean of our own custom `UserDetailsService` and `PasswordEncoder` using `@Configuration/@Bean` annotations
- but as soon as we create a custom bean of `UserDetailsService`, spring-security stops making default bean of `UserDetailsService`, `PasswordEncoder` and `AuthenticationProvider` and then we need to define custom implementation for all three of them otherwise there will be no way for HTTP requests to get authenticated.
- and spring-security still keeps the architecture in place, so we need to create creds as well along with custom bean otherwise we won't be able to log in.
```java
@Bean
public UserDetailsService inMemoryCustomUserDetailsService() {
    List<UserDetails> userDetails = new ArrayList<>(); // it will store user authentication details in RAM
    userDetails.add(User.withUsername("samar").password("samar-taj").authorities("read").build());
    userDetails.add(User.withUsername("Maheen").password("maheen-samar").authorities("read", "write").build());
    userDetails.add(User.withUsername("rubab").password("rubab-samar").roles("ADMIN").build());
    return new InMemoryUserDetailsManager(userDetails);
}
```
- but this alone won't work, we need an implementation of `PasswordEncoder` as well.
```java
@Bean
public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
}
```
- Now, use your custom creds, they will work.

## UserDetailsService
- interface contains only `loadUserByUsername()` which doesn't seem enough to provide us all the features that should be allowed to be performed on a users meta information.
- As it do not contain any user update capabilities like creating a new user, updating an existing user, deleting a user, changing password or any other kind of operation on user. It only gives us an existing user object, so that behavior must be passed on to it and already present in parent.
- When we checked, `UserDetailsService` has a child interface `UserDetailsManager` which holds all the features related to write/update.
- This is an example of Interface Segregation Principle as write related features are in user-details-manager and read related are in user-details-service.
- So, if directly implementing `UserDetailsService`, instead i implements `UserDetailsManger` then i will be having more features.
- `InMemoryUserDetailsManager` also extends `UserDetailsManager`

## AuthenticationProvider
- if we do not write a custom `AuthenticationProvider` then a default will be used.
- it has two methods declared, `supports(Class<?> authentication)` and `authenticate()`
- `supports()` takes as input a random clas type because there are different kinds of authentication that exist in real-world. Like, username + password, username + OTP, user + fingerPrint, etc.
- Hence, to write a custom `AuthenticationProvider` we will first need to specify what kind of authentication we will be supporting via this method.
- `authenticate(Authentication authentication)` which returns `Authentication`
- it returns a new instance of `Authentication` object containing authorities which the user is entitled to.
- hence, the authentication object consumed by authenticate() is different from the object it returns, and the difference is mostly because of presence of authority.
- custom `AuthenticationProvider` implementation needs not be registered, as a bean of it will be created during `ApplicationContext` creation and will be added in the list of `AuthenticationProvider` 

### Authentication
- `Authentication` instance contains all details against which a user got authenticated.
- it contains, creds details, authorities, principal, isAuthenticated, and other details.
- `UsernamePasswordAuthenticationToken` is the implementation of `Authentication` used by default with `InMemoryUserDetailsManager` and `NoOpPasswordEncoder`
- hence, we can infer that `Authentication` is an interface implementing token properties
- so, `authenticate()` implementation takes in the token that came in request, validates/verifies it, and generates a token corresponding to it for internal use.
- if internal-token says to `AuthenticationFilter` that everything is good, then spring-security filter-chain is passed on otherwise request is failed.
- it extends `Principal` interface.
- `Principal` interface do not come packaged with spring-security it is a part of core Java Security package.
- `Principal` can be used to represent any entity, such as an individual, a corporation, and a login id, etc.
- here, `Principal` represents the secured creds that a user have.
- when a user is conceptualized, it is thought of having some attributes, those attributes are said to be user's principals that are bound to it.
- The only behavior spring-security uses out of it is `getName()`. But keep a complete `Principal` object as part of `Authentication`
- `Principal` represents security attributes of a user that's contained in `Authentication` object.
- but `Authentication` only deals with security attribute, hence `Principal` can be thought of as a representation of a user whose identity is its name.
- the getter `Object getPrincipal` returns you the principal field value, which is of type `Object`.
- principal type is kept as `Object` because what if the authentication implementation we are using keeps userImage + voiceAudio as the authentication properties. In this case Principal will simply contain a ByteArray representing Image.
- `Authentication` also has a getter for collection of authorities that a user can have.
- each element of collection extends `GrantedAuthority`
- `GrantedAuthority` is capable of keeping only a single `String` field representing the authority constant which a user gives clearance of.
- authority can be thought of as a permission. Example, read permission, create permission, update permission, delete permission, etc.
- one of its implementation is `SimpleGrantedAuthority`, which keeps an extra key as `role`.
- `Authentication`, similar to `Principal`, also has a getter which returns an `Object` and named `getCredentials()`
- Because of the same reason as `Principal`, credential is also of type `Object`. For example, it can contain an audio file recording of voice as well.
- the third `Object` field, `detials` is kept for holding extra details which implementer wants authentication layer to keep in internal-token
- `isAuthenticated` flag is true if the authentication process is complete, if it is false then it shows authentication process is still undergoing.

#### Example - `UsernamePasswordAuthenticationToken`
- it keeps username as `Principal` object of type `Object`
- and password as credentials, also of type `Object`
- by default, it do not sets authorities list and set authenticated as false if a user fails authentication.
- on the contrary, it by defaults sets an authorities list along with setting authenticated as true if a user succeeds in authentication.
- when `AuthenticationFilter` encounters a username/password in request, it builds an instance of `UsernamePasswordAuthenticationToken` and pass it down to `AuthenticationManager`
- but at the level of AF we can customize to create an instance of another type instead of this as well.

## AuthenticationManager
- it maintains a list of `AuthenticationProvider` to iterate on
- `AuthenticationManager` before calling `AuthenticationProvider` calls `supports()` of `AuthenticationProvider` to check which one out of the list of providers supports the current authentication. If all rejects then at the end a default provider is kept which handles the authentication.
- if all providers fails the authentication, request is failed and return flow is initiated, in this scenario, request never reaches DispatcherServlet.
- but if `authenticate()` returns null to manager, then it represents that the authentication operation is inconclusive, hence manager will still look for the provider in the list who can conclusively either return true or false.
- so, either provider gives false from supports or null from authenticate(), both represents the same thing to manager.
- so going back to the request flow -
  - request --> servlet filter 1 --> servlet filter 2 --> ... --> servlet filter n --> authentication filter 1 --> 2 --> ... authentication filter n --> DispatcherServlet
  - if at any point before reaching the DispatcherServlet authentication fails then return journey starts with setting 401/403.
  - 401: authentication failure, 403: authorisation failure

## SecurityFilterChain
- We can custom which requests will go through which provider via `SecurityFilterChain`
- as already establish, spring-security filter-chain has obvious advantages over servlet filter-chain is that we can make routes skip an authentication or implement some authentication only at a specific route through it.

## SecurityContextHolder
- spring-mvc can get hold of `Authentication` object kept in `SecurityContext` after successful authentication via `SecurityContextHolder` using static `getContext()`
- using authority/role/credential details stored in `Authentication` object controllers/interceptors can decide on what a request is capable of executing
- `getContext()` gives the controller `Authentication` only of that request which it is currently invoked upon.
- But at a given time, due to multi-threading there will be multiple security-context object residing in the `SecurityContextHolder`, then how `SecurityContextHolder` decide which security-context was associated with which request ?
- One of the way of doing that is, every Thread has a ThreadLocal. SecurityContextHolder uses `SecurityContextHolderStrategy` based on Strategy design pattern to define a holder-strategy for every request. `ThreadLocalSecurityContextHolderStrategy` is one of the implementation in which, as every request is assigned to a Thread, and Threads have unique id, hence security-context is saved against a thread id. Hence, we can decide which security-context was associated with the given request using ThreadLocal.

## AuthenticationFilter
- `AuthenticationFilter` is an implementation of servlet filter. It uses servlet filter under the hood.
- `AuthenticationFilter` class extends `OncePerRequestFilter` abstract class which extends abstract class `GenericFilterBean`, which implements java (jakarta) `Filter`
- `OncePerRequestFilter` defines `doFilter()` of `Filter`
- while writing we learned to define filters and a chain already gets created, here, we will design complete filter-chain under the wrapper of security-filter-chain
- `SecurityFilterChain` has `getFilters()` which returns list of all the filters.
- `BasicAuthenticationFilter` is the major filter which works behind the scene for spring-security.
- triggering point of all `AuthenticationManager` / `AuthenticationProvider` / .... is `BasicAuthenticationFilter`
- `BasicAuthenticationFilter` extracts `Authentication` object from request
- it is also responsible for setting `Authentication` object after authentication into `SecurityContext` and then in `SecurityContextHolder`
- it denotes `Authentication` object extracted from request as authRequest and `Authentication` object created after successful authentication as authResponse
- it calls `authenticate()` on `AuthenticationManager` send authRequest to it to get the authResponse
```java
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    // .
    // .
    // .
    if (this.authenticationIsRequired(username)) {
        Authentication authResult = this.authenticationManager.authenticate(authRequest);
        SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authResult);
        this.securityContextHolderStrategy.setContext(context);
        // .
        // .
        // .
    }
    // .
    // .
    // .
}
```
- to introduce our own custom implementation of `AuthenticationFilter` in `SecurityFilterChain` we have mainly 3 points
  - add a filter before `BasicAuthenticationFilter` (before attempting authentication)
  - add a filter at `BasicAuthenticationFilter`
  - add a filter after `BasicAuthenticationFilter` (if authentication succeeded and a `SecurityContext` is set)
- `shouldNotFilter()` can be defined for paths on which we do not wish current filter to get executed.
