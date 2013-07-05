package com.prosodie.mq.example2;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.springframework.stereotype.Component;

@Component
public class JMSExceptionListener implements ExceptionListener
{
	public void onException(final JMSException e)
	{
		e.printStackTrace();
	}
}