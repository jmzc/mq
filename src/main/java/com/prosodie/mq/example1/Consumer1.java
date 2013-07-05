package com.prosodie.mq.example1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer1 implements MessageListener
{

	ConnectionFactory connectionFactory;

	private int ack = Session.AUTO_ACKNOWLEDGE;
	private String topic = "OLSMS.TO.C2C";

	private MessageConsumer consumer;

	@Autowired
	public Consumer1(PooledConnectionFactory connectionFactory)
	{
		super();
		this.connectionFactory = connectionFactory;
	}

	public void consume(String selector)
	{

		Connection connection = null;
		try
		{

			/*
			 * A JMS client typically creates a connection, one or more
			 * sessions, and a number of message producers and consumers. When a
			 * connection is created, it is in stopped mode. That means that no
			 * messages are being delivered. It is typical to leave the
			 * connection in stopped mode until setup is complete (that is,
			 * until all message consumers have been created). At that point,
			 * the client calls the connection's start method, and messages
			 * begin arriving at the connection's consumers. This setup
			 * convention minimizes any client confusion that may result from
			 * asynchronous message delivery while the client is still in the
			 * process of setting itself up.
			 */
			connection = connectionFactory.createConnection();

			connection.start();

			/*
			 * connection.stop(); // --> NO PARA connection.close(); 
			 * 					  // --> NO CIERRA
			 * 
			 */

			Session session = connection.createSession(false, ack);

			this.consumer = session.createConsumer(session.createTopic(topic), selector);

			this.consumer.setMessageListener(this);

		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
		finally
		{
			try
			{
				try
				{
					/*
					if (connection != null) 
						connection.close();
					*/
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}



	}

	@Override
	public void onMessage(Message message)
	{

		if (message instanceof TextMessage)
		{
			final TextMessage textMessage = (TextMessage) message;
			try
			{
				System.out.println("*** RECEIVED EXAMPLE 1:" + textMessage.getText() + "***");
			}
			catch (final JMSException e)
			{
				e.printStackTrace();
			}
		}

	}

}
