- all this code was to explain how DispatcherServlet works.
- you won't be coding like this in real-world application
- in real-world, `@RestController` and `@Controller` will be sufficed.

## Flow Chart
```mermaid
flowchart TD;
    A((Incoming Request)) --> B[Determine `HandlerMethod` from `HandlerMapping`]
    B --> S[Derive `MethodExecutionChain` from `HandlerMethod`]
    S --> C{`HandlerExecutionChain` found ?}
    C -- NO --> D[Send 404]
    D --> E((Failure Response))
    C -- YES --> F{...more... Interceptors available ?}
    F --> |YES| G[Interceptor `preHandle` the request]
    G --> H{request passed ?}
    H -- YES --> F
    H -- NO --> I[Send 400]
    I --> E
    F -- NO --> J[Determine `HandlerAdapter`]
    J --> K{`HandlerAdapter` found ?}
    K -- NO --> L[Throw `ServletException`]
    L --> E
    K -- YES --> M[process request and generate response]
    M --> N{ModelAndView returned ?}
    N -- NO --> O[Interceptors `postHandle` the request]
    O --> P((Success Response))
    N -- YES --> Q{ModelAndView has View ?}
    Q -- NO --> O
    Q -- YES --> R[RequestToViewNameTranslator to get template file name]
    R --> T[set template in response]
    T --> O
```