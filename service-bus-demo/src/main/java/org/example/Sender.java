package org.example;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusMessageBatch;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

import java.util.Arrays;
import java.util.List;

public class Sender {
    // first , set up our connection string:
    private static String connectionString = "Endpoint=sb://rory-demo.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=bG06AWpymtmax+cMEJXVI6S+Zd4EWU40pc90qpEQmGs=";

    // specify which queue we want to interact with:
    private static String queueName = "hr";

    // send a single message:
    static void sendMessage() {
        // create the sender client:
        ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName)
                .buildClient();

        // create a message using a string
        ServiceBusMessage message = new ServiceBusMessage("Hello, from Sender Class");

        // use the client to send the message:
        senderClient.sendMessage(message);

        System.out.println("Sent a simple message to the queue: " + queueName);
    }

    // this method returns a list of Service Bus Messages
    static List<ServiceBusMessage> createMessages() {
        Person person = new Person("Rory", "unemployed");
        ServiceBusMessage[] messages = {
                new ServiceBusMessage("HR meeting Friday at 3"),
                new ServiceBusMessage("Rory is fired"),
                new ServiceBusMessage(person.toString())
        };
        return Arrays.asList(messages);
    }

    static void sendMessageBatch() {
        // create the sender client:
        ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName)
                .buildClient();

        // first, we create an empty message batch
        ServiceBusMessageBatch messageBatch = senderClient.createMessageBatch();

        // print out max size:
        System.out.println("Max size: " + messageBatch.getMaxSizeInBytes());

        // get our messages from the method that we created
        List<ServiceBusMessage> messages = Sender.createMessages();

        // iterate through the messages:
        for(ServiceBusMessage message: messages) {
            // for each message, we try to add it to the batch:
            // if it works, successful, then we continue
            if(messageBatch.tryAddMessage(message)) continue;

            // the rest of this loop body is all about what happens when the batch is full

            // we want to send the batch
            senderClient.sendMessages(messageBatch);
            System.out.println("Sent a batch of messages to the queue: " + queueName);

            // then we want to reset our batch, make it empty
            messageBatch = senderClient.createMessageBatch();

            // still have our left-over message:
            // try to add the message to a now-empty batch
            if(!messageBatch.tryAddMessage(message)) {
                // print out that we can't send the message, even in an empty batch
                System.err.printf("Message is too large for an empty batch. Skipping. Max size: %s.", messageBatch.getMaxSizeInBytes());
            }
        }
        // send any messages that are still in the batch:
        if (messageBatch.getCount() > 0) {
            senderClient.sendMessages(messageBatch);
            System.out.println("Sent a batch of messages to the queue: " + queueName);
        }
    }

}
