package com.prosodie.mq.example1;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Producer1
{

	PooledConnectionFactory connectionFactory;

	private int ack = Session.AUTO_ACKNOWLEDGE;
	private String topic = "OLSMS.TO.C2C";

	private MessageProducer producer;

	

	@Autowired
	public Producer1(PooledConnectionFactory connectionFactory)
	{
		super();

		this.connectionFactory = connectionFactory;

	}

	// JMS  createTopic/createQueue
	// Note that this method is not for creating the physical topic. 
	// The physical creation of topics is an administrative task and is not to be initiated by the JMS API
	// The one exception is the creation of temporary topics, which is accomplished with the createTemporaryTopic method.
	
	public void send(String login, String text)
	{
		
		Connection connection = null;
		try
		{
			connection = connectionFactory.createConnection();
						
	
			Session session = connection.createSession(false, ack);
			
			/*
			    Client side destination objects are not the same as server side resources
				Note that the ActiveMQ Broker will only create server side resources for destinations when messages are actually sent to them. 
				So you can create as many instances of ActiveMQTopic and ActiveMQQueue on a client without any real overhead until you actually send messages to them on a broker. So a JMS client creating a new ActiveMQQueue POJO does not mean you are creating server side queue resources.
				Think of the ActiveMQQueue and ActiveMQTopic classes as like java.net.URL. 
				They are just names which refer to server side resources which are auto-created when they are used.
				
				This means that different clients creating different ActiveMQQueue instances will communicate 
				with the same physical queue on a JMS broker if the name is the same.
			 */

			this.producer = session.createProducer(session.createTopic(topic));

			// The main difference is that if you are using persistent delivery, messages are persisted to disk/database so that they will survive a broker restart. 
			// When using non-persistent delivery, if you kill a broker then you will lose all in-transit messages.
			// Delivery mode is set to PERSISTENT by default.
			// Persistence is a property of a an individual message.
			
			// There are 2 main ways to disable persistence:
			// 1>Set the NON_PERSISTENT message delivery flag on your MessageProducer
			// 2>Set the persistent=false flag in the <broker/> element of the Xml Configuration or on the property BrokerService
			
			//this.producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

	
			TextMessage txtMessage = session.createTextMessage();
			txtMessage.setStringProperty("LOGIN", login);
			txtMessage.setText(text);
			

			this.producer.send(txtMessage);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
			try
			{
				/*
				if (producer != null) 
					producer.close();
				*/
				
				
				if (connection != null)
					connection.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
	

	}

}
