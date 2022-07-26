## Messaging Pattern
- as the name implies, the messaging patten relates to how 2 different parts of an application communicate with each other
- communication channel
- ideally loose coupling, don't want the different parts to know much about the other parts, only how to receive messages
- asynchronous messaging

## Publisher-Subscriber Pattern
- using a single queue for each customer doesn't scale well
- also, some consumers might only care about a certain amount of messages
- the sender, or publisher, packages information into a message and sends it through the communication channel
- there is one output channel per consumer, or subscriber
- ![diagram](https://docs.microsoft.com/en-us/azure/architecture/patterns/_images/publish-subscribe.png)
### Benefits
- decoupling - systems don't really need to know about each other, they can still communicate
- scalability - only one input, forwarded to many outputs
- reliability - asynchronous mesaging allows for smooth operation even under increased loads

## Azure Service Bus
- message queues
- publish-service topics
- decouple applications and services from each other
- message - a container decorated with metadata and contains data
    - can be JSON, XML, plain text, etc.
- subscribers can subscribe to a publisher and get messages from the published message stream
- PaaS, so don't worry about hardware failuers, OS patches, logs, backups


### Queues
- messages are ordered, durable
- delivered in pull mode, or only delivered when requested

### Topics
- topics are useful in publish/subscribe scenarios
- topics can have multiple subscriptions, act like queues from the receicing side
- a subscription can have a filter, or a condition for the message to be brought into the subscription
- subscription can also have an action that can modify the metadata of the message

### Namespaces
- a namespace is a container for all messaging components (queues and topics)
- namespaces often serve as application containers

### Advanced Features
- to make FIFO guaranteed, use sessions
- dead-letter queue holds messages that cannot be delivered to any receiver
- Read more about Azure Service Bus [here](https://docs.microsoft.com/en-us/azure/service-bus-messaging/service-bus-messaging-overview)

## Demo


### Domain Driven Design
- looking at software from top down
- focus should be on business, rather than technology
- domain is the business we're trying to solve
    - banking, insurance, e-commerce
- models of the domain, make our software conform to that
- closing te gap between business reality and code
- ex: Windows 8, well made and looks good, but didn't add features that were helpful/wanted
- Domain Driven Design is not inherently related to OOP, but in practice it makes use of OOP concepts
- [Wiki Page](https://en.wikipedia.org/wiki/Domain-driven_design)
