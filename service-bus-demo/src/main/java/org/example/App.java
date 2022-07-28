package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
//        Sender.sendMessage();
        Sender.sendMessageBatch();
        // this method throws an interupted exception:
        Receive.receiveMessages();
    }
}
