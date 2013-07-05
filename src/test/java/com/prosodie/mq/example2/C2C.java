package com.prosodie.mq.example2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class C2C
{


	public static void main(String[] args)
	{
		try
		{
			ApplicationContext ctx = new ClassPathXmlApplicationContext("context2.xml");
			Consumer2 consumer = ctx.getBean(Consumer2.class);
			consumer.consume("LOGIN = 'JMZC'");

			/*
			while(true)
			{
				Thread.sleep(2000);
			}
			*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
