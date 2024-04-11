package br.com.eduardo.ponciano.travel.payment;

import java.util.Enumeration;

import org.apache.activemq.ActiveMQConnectionFactory;

import jakarta.jms.Connection;
import jakarta.jms.Message;
import jakarta.jms.Queue;
import jakarta.jms.QueueBrowser;
import jakarta.jms.Session;

public class QueueInfo {
    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        String[] queueNames = {"queue.test"}; 
        for (String queueName : queueNames) {
            Queue queue = session.createQueue(queueName);
            QueueBrowser browser = session.createBrowser(queue);
            Enumeration<?> messages = browser.getEnumeration();
            int messageCount = 0;
            while (messages.hasMoreElements()) {
                Message message = (Message) messages.nextElement();
                messageCount++;
            }
            System.out.println("Queue: " + queueName + ", Size: " + messageCount);
            browser.close();
        }

        session.close();
        connection.close();
    }
}
