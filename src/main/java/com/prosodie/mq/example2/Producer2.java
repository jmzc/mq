package com.prosodie.mq.example2;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;


@Component
public class Producer2
{

	@Autowired 
	JmsTemplate jmsTemplate;



	public Producer2()
	{
		super();

	}

	// JMS  createTopic/createQueue
	// Note that this method is not for creating the physical topic. 
	// The physical creation of topics is an administrative task and is not to be initiated by the JMS API
	// The one exception is the creation of temporary topics, which is accomplished with the createTemporaryTopic method.
	
	public void send(final String login, final String text)
	{
		try
		{

			  this.jmsTemplate.send( new MessageCreator() 
			  {
		            public Message createMessage(Session session) throws JMSException 
		            {
		            	TextMessage txtMessage = session.createTextMessage();
		    			txtMessage.setStringProperty("LOGIN", login);
		    			txtMessage.setText(text);
		                return txtMessage;
		                
		            }
		          });
		
			  

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		

	}

}
