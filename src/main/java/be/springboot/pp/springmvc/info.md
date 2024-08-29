### Q. What is HTTP ?
- application layer protocol
- stands for HyperText Transfer Protocol
- HyperText = Structured-Text + Links
- An HTTP request contains some sort of links/url/path. Like, 'GET /book/anything.html'
- Text present in an HTTP request has distinct components with unique objective.
- stateless protocol: server do not keep any context about client in between requests.

### Q. If HTTP is stateless then how recommendation system works ?
- using cookies/tokens
- It goes as a part of HTTP request and helps server form a unique response tailored for you using info stored in your cookies/tokens

## Components of a Request
- Method: GET, POST, PUT, PATCH, etc.
- URL: Uniform Resource Locator
- Version: 
- Headers: It contains additional information. Like,
  - Content-Type: 
- Body: (Optional)
- request-path:
- request-params:

## Components of a Response
- Status-code:
- Status-message:
- Header:
- Body:
