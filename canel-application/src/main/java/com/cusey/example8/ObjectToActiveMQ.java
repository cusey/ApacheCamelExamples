package com.cusey.example8;

import java.util.Date;

import javax.jms.ConnectionFactory;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class ObjectToActiveMQ {

	public static void main(String[] args) throws Exception {
		
		CamelContext context = new DefaultCamelContext();
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		
		context.addRoutes( new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				 from("direct:start")
				 .to("activemq:queue:my_queue");
				 
			}
		
		});
		
		context.start();
		
		ProducerTemplate producerTemplate = context.createProducerTemplate();
		
		producerTemplate.sendBody("direct:start", new Date());

	}

}
