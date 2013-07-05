package com.prosodie.mq.example2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OLSMS
{


	public static void main(String[] args)
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("context2.xml");
		Producer2 producer = ctx.getBean(Producer2.class);
		producer.send("JMZC","MENSAJE ENVIADO DESDE OLSMS 2");

	}

}
