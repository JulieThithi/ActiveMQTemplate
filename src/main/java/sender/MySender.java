package sender;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.QueueConnectionFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//Is a queue able to receive message from many senders? Yes !
//Is a queue able to send message to many receivers? No ! (but a Topic can)

public class MySender {

	public static void main(String[] args) {
		
		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			//QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");
			
			//Queue queue = (Queue) applicationContext.getBean("queue");
			Topic topic = (Topic) applicationContext.getBean("topic");
			
			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html	
			//QueueConnection connection = factory.createQueueConnection() ;
			TopicConnection connection = factory.createTopicConnection() ;
			
			// Open a session without transaction and acknowledge automatic
			//QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE) ;
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE) ;
			
			// Start the connection
			connection.start();
			
			// Create a sender
			//QueueSender sender = session.createSender(queue) ;
			TopicPublisher sender = session.createPublisher(topic) ;
			
			// Create a message
			TextMessage m = session.createTextMessage("yo");
			
			// Send the message
			sender.send( m );
			
			// Close the session
			session.close();
			
			// Close the connection
			connection.close();

		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
