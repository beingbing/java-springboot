# Request Journey
Q. What happens as soon as a server receives an HTTP web request ? 
authentication/authorisation happens when request reaches application layer.

Q. What you receive on harwdware from internet wires, does that know about request methods GET/PUT/POST/... ?
hardware can just receive raw bytes. The device on our machine which actually receives a request is called NIC (Network Interface Card). It is the hardware that connects our machine to the world of internet.
Every request that comes to NIC is handed over to OS once received.

Q. How hardware inform anything to OS ?
Interrupts: signals like mouse click/keyboard key press, and all such events from hardware are called interrupts.

NIC sends Interrupt to OS. Whenever NIC  receives a request (packet of raw bytes), it emits Interrupt to OS. When OS receives Interrupt, it become alert and take action.
The action OS takes is it copies those raw bytes from NIC to kernel space. There are two types of spaces: kernel space and application space. Kernel space is used by OS for chores, this space is not accessible to user application. But our Spring Application is running in Application/User space.

Next, OS analyzes the raw request and as soon as OS concludes request to be a TCP (HTTP is built on top of TCP) request then OS kicks in all the TCP protocols. TCP protocol says every data that is sent, should be received and should be reordered in a certain order. After this, OS establishes a TCP connection with request sender. But OS keeps a TCP connection queue, if that queue is full then the recently received request will be dropped. So, once a TCP connection is established and all TCP protocols ran on raw packets received then OS transfers refined request to the application layer.

Q. What is server?
Hypothetically, Server is nothing but an infinite while loop, a Thread which never dies and due to which JVM do not exits. The server is running in user/application space.

Every incoming request received by NIC has an IP address and a port details in it. Once OS is through with the request, it checks the IP and port and transfers the request on that port in application space. Before doing so, OS translates the TCP request into an HTTP request and sends it. 

Q. What is Port ?
It is an abstraction. OS keeps a mapping of port number and application running on it.

Now the user is sent to user space from kernel space in proper HTTP format after identifying the port. So OS as soon as is done with first identifying the port and second finished with converting the request to HTTP hands the request over to application server (in our case, it is Apache TomCat).

Server converts the request into HTTPServletRequet type and forwards it to  a Spring module called Spring MVC. Now, MVC maps the request to a route in controller and execute methods established on that route. In this process, request keeps on getting deserialized/transformed into suitable input types.

So,
NIC ⇋ OS  ⇋ kernel space ⇋ port ⇋ application space ⇋ server ⇋ Spring MVC ⇋ DB

Note: UDP packets can be missed so OS may refuse to accept a UDP request.

response -> [MVC] -> serialize -> [TomCat] -> HTTP -> [OS] -> TCP -> [NIC] -> raw packets

Q. What's the difference between TCP and HTTP ?
TCP is a stream of bytes, it doesn't know demarcations or wat section of bytes are together and which packets are of different requests, it's just a stream. HTTP gives them demarcation and classification into headers/body/etc.