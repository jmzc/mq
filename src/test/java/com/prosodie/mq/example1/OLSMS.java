package com.prosodie.mq.example1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OLSMS
{


	public static void main(String[] args)
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("context1.xml");
		Producer1 producer = ctx.getBean(Producer1.class);
		producer.send("JMZC","MENSAJE ENVIADO DESDE OLSMS 1");

	}

}
