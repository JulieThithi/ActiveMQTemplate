package receiver;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyReceiver {

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
			
			// Open a session
			//QueueSession session = connection.createQueueSession(true, Session.AUTO_ACKNOWLEDGE) ;
			TopicSession session = connection.createTopicSession(true, Session.AUTO_ACKNOWLEDGE) ;
			
			// start the connection	
			connection.start();
			
			// Create a receive	
			//QueueReceiver receiver = session.createReceiver(queue);
			TopicSubscriber receiver = session.createSubscriber(topic) ;
			
			// Receive the message
			Message m = receiver.receive();
			
			System.out.println(m + "JULIE ");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
