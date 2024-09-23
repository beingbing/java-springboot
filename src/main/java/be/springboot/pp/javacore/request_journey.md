# The Journey of a Request

When a server receives an HTTP web request, a series of steps and components work together to process and respond to it. From the moment the request is received as raw bytes on the hardware to its final processing in the application layer, understanding the journey of a request can give valuable insight into how web servers work.

### 1. Receiving a Request: From Hardware to OS
The journey of an HTTP request begins when the server’s hardware, specifically the **Network Interface Card (NIC)**, receives raw bytes from the internet. The NIC is responsible for connecting the machine to the internet and for receiving all incoming network traffic. However, at this stage, the NIC only handles raw data and does not understand higher-level protocols like HTTP.

#### 1.1 Interrupts: Hardware to OS Communication
When the NIC receives a request (in the form of raw packets), it sends an **interrupt** signal to the **Operating System (OS)**, alerting it that new data has arrived. An interrupt is simply a signal sent by hardware (such as the NIC, keyboard, or mouse) to notify the OS that attention is required.

Upon receiving this interrupt, the OS copies the raw bytes from the NIC to **kernel space**, a memory space reserved for the OS and inaccessible to user applications. This separation ensures the security and stability of the system.

### 2. OS Processing: TCP Protocols and Connection Management
Once the OS has the raw data in kernel space, it begins analyzing it. The OS recognizes that the incoming request is a **TCP** request, as **HTTP** is built on top of TCP. The OS now follows TCP protocols, ensuring that all packets are received in the correct order and any lost packets are retransmitted. This step ensures data integrity.

After processing the TCP request, the OS establishes a **TCP connection** with the request sender. However, the OS maintains a TCP connection queue, and if the queue is full, the newly received request may be dropped.

### 3. Passing to Application Layer: Ports and Protocols
Once the OS has established a TCP connection and ordered the incoming data correctly, it checks the IP address and **port** number embedded in the request. The port is an abstraction used by the OS to map requests to specific applications. Different applications or services run on different port numbers.

The OS translates the TCP request into an **HTTP request** and forwards it to the designated application via the appropriate port. In our case, this request is sent to the **application server**, which is typically something like **Apache Tomcat**.

### 4. Application Layer: The Role of the Server
The server, often represented as an infinite loop or a thread that never dies, listens for incoming requests on a specific port. When a request arrives, the server converts the raw HTTP request into an object that the application can understand—such as an **HttpServletRequest** object in Java applications.

The server then passes the request to the **Spring MVC** framework.

### 5. Request Handling in Spring MVC
**Spring MVC** is responsible for mapping the incoming request to the appropriate controller route in the application. It identifies the corresponding method in the controller and executes it. During this process, the incoming request is deserialized and transformed into suitable input types based on the controller method’s parameters.

For example, if the request is a `GET` request to fetch a resource, the corresponding method in the controller is called, and the response is generated.

### 6. Response Generation: From Application Back to Hardware
Once the controller generates a response (e.g., fetching data from the database or processing business logic), the following steps occur:
1. The response is serialized into a format like JSON or XML.
2. The server (e.g., Tomcat) wraps the response in an HTTP format and sends it to the OS.
3. The OS follows the TCP protocols to send the response back through the NIC as raw packets over the internet.

The path for the response mirrors that of the request:
```
[Spring MVC] -> Serialize -> [Tomcat] -> HTTP -> [OS] -> TCP -> [NIC] -> Raw Packets
```

### 7. TCP vs. HTTP
A key difference between **TCP** and **HTTP** lies in their responsibilities:
- **TCP** is a low-level protocol that handles the delivery of bytes between two machines. It guarantees that the data arrives intact but does not concern itself with the structure of the data.
- **HTTP**, built on top of TCP, is a higher-level protocol that adds structure to the raw bytes, organizing them into headers, body, and status codes. It enables web clients and servers to communicate in a standardized way.

### Summary of the Request Journey
1. **NIC** receives raw data from the internet.
2. **NIC** sends an interrupt to the **OS**.
3. **OS** processes the raw data, establishes a **TCP** connection, and checks the **port**.
4. **OS** translates the TCP data into an **HTTP request** and forwards it to the **application server**.
5. The **application server** converts the request into an appropriate object and forwards it to **Spring MVC**.
6. **Spring MVC** maps the request to a controller method, processes it, and generates a response.
7. The **response** travels back through the OS, TCP, NIC, and over the internet.

Understanding this flow helps in troubleshooting issues, optimizing performance, and ensuring that requests are handled efficiently from the hardware level all the way to the application layer.
