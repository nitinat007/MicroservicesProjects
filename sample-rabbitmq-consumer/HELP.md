# Read Me First
Sample consumer for a rabbitMQ 

# Start docker container of rabbitMQ :
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.10-management 

*  Login to RabbitMQ Management UI with cred : guest/guest
*  Create Exchange: sample_exchange 
*  Create Queue: sample_queue
*  Create Binding to sample_queue with binding 'sample-routing-key'
*  Publish Message in this Exchange manually and see that the message is queued in the sample_queue
# Start Consumer: 
*  mvn spring-boot:run
*  And observe that the message is printed /consumed in the console. 

Concepts of RabbitMQ

*  Message Broker: a piece of software, which enables services and applications to communicate with each other using messages.
*  Producer: responsible for queueing up new messages
*  Consumer: takes a message off the queue
*  Broker: acts as a middleman for various services.
*  Message: Information that is sent from the producer to a consumer through RabbitMQ. It contains a payload (body) and one or more message properties (headers)
*  Connection:A TCP connection between your application and the RabbitMQ broker.
*  Channel:A virtual connection inside a connection.
*  Exchange: An exchange accepts the messages from the producer and routes them to correct message queues with the help of bindings and routing keys (like address of the message)
*  Binding: A binding is a link between a queue and an exchange.
*  queues:  A buffer that stores messages.
*  AMQP:Advanced Message Queuing Protocol is the protocol used by RabbitMQ
*  Vhost, virtual host:Provides a way to segregate applications using the same RabbitMQ instance.


