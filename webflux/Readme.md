
## Spring WebFlux and Json RPC Example 



## Reactive Streams 
Reactive Streams is a means to  provide a standard for asynchronous stream processing with non-blocking back pressure
Reactive Streams specification is a standard and since Java 9, it is included in Java with the Flow API and supports interfaces for 
**Publisher**, **Subscriber**, **Subscription**, and **Processor** :

* **Publisher**: The publisher is the source that will send the data to one or more subscribers.
* **Subscriber**: A subscriber will subscribe itself to a publisher.
* **Subscription**: On the publisher side, a subscription will be created, which will be shared with a subscriber.
* **Processor**: A processor can be used to sit between a publisher and a subscriber, this way a transformation of the data can take place.
 


## Sepcial Reactor Types 

Project Reactor
The next step to take in our journey is Project Reactor. This project provides a Reactive library based on the Reactive Streams specification. In other words, an implementation of the specification. Basically, Reactor provides two types:

Mono: implements Publisher and returns 0 or 1 elements;
Flux: implements Publisher and returns N elements. 