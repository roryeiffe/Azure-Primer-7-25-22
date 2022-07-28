package org.example;

import com.azure.messaging.servicebus.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Receive {
    // first , set up our connection string:
    private static String connectionString = "Endpoint=sb://rory-demo.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=bG06AWpymtmax+cMEJXVI6S+Zd4EWU40pc90qpEQmGs=";

    // specify which queue we want to interact with:
    private static String queueName = "hr";

    static void receiveMessages() throws InterruptedException {
        // pause all threads until it's counted down
        final CountDownLatch countDownLatch = new CountDownLatch(1);


        ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .processor()
                .queueName(queueName)
                .processMessage(Receive::processMessage)
                .processError(context -> processError(context, countDownLatch))
                .buildProcessorClient();

        // start the processor:
        System.out.println("Starting the processor");
        processorClient.start();

        TimeUnit.SECONDS.sleep(10);
        System.out.println("Stopping and closing the processor");
        processorClient.close();
    }

    // method to process the messages:
    private static void processMessage(ServiceBusReceivedMessageContext context) {
        // first we receive the message from the context
        ServiceBusReceivedMessage message = context.getMessage();
        // print out some information about the message:
        System.out.printf("Processing message. Session %s, Sequence %s, Contents: %s%n", message.getMessageId(), message.getSequenceNumber(), message.getBody());
    }

    // method to process the error:
    private static void processError(ServiceBusErrorContext context, CountDownLatch countDownLatch) {
        // for this method, we can just print out that an error happened:
        System.out.println("Something went wrong when processing the message");


        // NOTE: This code is new (not covered in class). But the jist of it is that it goes through each possible error type
        // and does something different for each:
        // first, print out the general error message:
        System.out.printf("Error when receiving messages from namespace: '%s'. Entity: '%s'%n",
                context.getFullyQualifiedNamespace(), context.getEntityPath());

        // check if it is NOT a service bus exception:
        if(!(context.getException() instanceof ServiceBusException)) {
            System.out.printf("Non-ServiceBusException occured: %s%n", context.getException());
            return;
        }

        // parse out the exception and reason:
        ServiceBusException exception = (ServiceBusException) context.getException();
        ServiceBusFailureReason reason = exception.getReason();

        // do different action for each reason:
        if(reason == ServiceBusFailureReason.MESSAGING_ENTITY_DISABLED
                || reason == ServiceBusFailureReason.MESSAGING_ENTITY_NOT_FOUND
                || reason == ServiceBusFailureReason.UNAUTHORIZED) {
            System.out.printf("Unrecoverable error. Reason %s: %s%n", reason, exception.getMessage());
            // release threads
            countDownLatch.countDown();
        } else if (reason == ServiceBusFailureReason.MESSAGE_LOCK_LOST) {
            System.out.printf("Message lock lost for message: %s%n", context.getException());
        } else if (reason == ServiceBusFailureReason.SERVICE_BUSY) {
            try {
                // Choosing an arbitrary amount of time to wait until trying again.
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.err.println("Unable to sleep for period of time");
            }
        } else {
            System.out.printf("Error source %s, reason %s, message: %s%n", context.getErrorSource(),
                    reason, context.getException());
        }
    }
}
