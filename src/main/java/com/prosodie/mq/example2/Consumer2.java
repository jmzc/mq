package com.prosodie.mq.example2;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class Consumer2 implements MessageListener
{




	@Autowired 
	DefaultMessageListenerContainer listener;
	
	
	public Consumer2()
	{
		super();
		
	}
	
	/*
	 * By default, a Consumer's session will dispatch messages to the consumer in a separate thread. 
	 * If you are using Consumers with auto acknowledge, you can increase throughput by passing messages 
	 * straight through the Session to the Consumer by setting the alwaysSessionAsync property on the ActiveMQ ConnectionFactory to be false
			
		CONNECTION			
			----
				|
				  SESSION1
	*/ 
	
	 /*
	 * The maximum number of messages that ActiveMQ will push to a Consumer 
	 * without the Consumer processing a message is set by the pre-fetch size.
	 */

	public void consume(String selector)
	{

		
		try
		{
	
			this.listener.start();
			this.listener.setMessageSelector(selector);
			this.listener.setMessageListener(this);
			
		
			

		}
		catch (Exception e)
		{

			e.printStackTrace();
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
				System.out.println("*** RECEIVED C2C 2:" + textMessage.getText() + "***");
			}
			catch (final JMSException e)
			{
				e.printStackTrace();
			}
		}

	}

}
