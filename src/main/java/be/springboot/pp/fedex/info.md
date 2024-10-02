# Fedex
- allows sending and receiving parcel
- think of yourself as a Product Manager, scan Fedex website, understand its offerings and come up with some set of requirements

## functional requirement
- A customer should be able to login/register etc.
- a customer should be able to place an order, e.g. -
```json
{
  "item": "A little life",
  "type": "book",
  "weight": "480gms",
  "dimensions": "4 x 5 x 7",
  "pickUpAddress": "...",
  "deliveryAddress": "...",
  "...": "..."
}
```
- figure out optimal delivery route possible between pickup and delivery address
- upon placing an order, the system should internally generate some delivery plan for the package, e.g. -
```json
{
  "pickUpDateAndTime": "...",
  "pickUpPersonDetails": "...",
  "routePlanning": "Del -> agra -> indore -> bombay"
}
```
- pickUp details should be asynchronously sent to the customer via comms
- the pickup guy's job is to simply take the parcel to its first fulfilment center. Next the parcel moves from one FC to another FC via truck. FCs are just like nodes and roads are directed edges
- every truck has some weight and volume limitation. When at some FC parcels can move in or out of truck if constraints satisfy
- a truck driver's app can always update the current state of the truck (e.g.- at BOM, travelling from BOM to BGL, etc)
- assume that each truck has a defined set of routes. Also, when a truck is at an FC, it can start loading, once loaded fully, the driver has to be notified to immediately leave.
- a customer should get a notification once parcel reaches closest FC
- at the final FC, a delivery agent should be assigned and a message containing delivery details should be sent to the receiver.
- it should be able to track the shipment based on some ID
- we need a payment API as well for order to be placed
- admin should have access to APIs like adding a truck, FC, etc. There should be robust authentication and authorization here.

## approach
- list of services: Auth-service, order-service, payment-service, route-planner, admin-service, comms-service, DB to store ordered details
- apart from saving order details in DB, it should also be stored in kafka message-queue so that route-planner can listen
- route-planner after formulating path store it back in DB
.
- .
- .

- consider them different maven projects on different ports
- explore rest-client
- we can use graph DS
- create in-memory based route planner graph
- 