package com.prosodie.mq.example1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class C2C
{


	public static void main(String[] args)
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("context1.xml");
		Consumer1 consumer = ctx.getBean(Consumer1.class);
		consumer.consume("LOGIN = 'JMZC'");

	}

}
