package com.cusey.example3;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/*
 * --DIRECT
 * The Direct component provides direct, synchronous invocation of
 *  any consumers when a producer sends a message exchange.
 * This endpoint can be used to connect existing routes in the 
 * same camel context.
 * https://camel.apache.org/components/3.4.x/direct-component.html
 * 
 * -- Staged Event Driven Architecture (SEDA)
 * The SEDA component provides asynchronous SEDA behavior, so that 
 * messages are exchanged on a BlockingQueue and consumers are invoked 
 * in a separate thread from the producer.
 * https://camel.apache.org/components/3.4.x/seda-component.html
 */

public class ProducerAndConsumer {

	public static void main(String[] args) throws Exception {
		
		
		CamelContext context = new DefaultCamelContext();
		
		
		context.addRoutes( new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				from("direct:start")
				.to("seda:end");
			}
			
		});
		
		context.start();

		ProducerTemplate producerTemplate = context.createProducerTemplate();
		
		producerTemplate.sendBody("direct:start", "Hello Everyone");
		
		
		ConsumerTemplate consumerTemplate = context.createConsumerTemplate();
		
		String message = consumerTemplate.receiveBody("seda:end", String.class);
		
		System.out.println(message);
		
	}

}
