package com.cusey.example4;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ProcessorMain {
	
	public static void main(String[] args) throws Exception {
		
		
		CamelContext context = new DefaultCamelContext();
		
		
		context.addRoutes( new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				from("direct:start")
				.process(new Processor() {

					public void process(Exchange exchange) throws Exception {
						
						System.out.println("I am the processor.....");						
					}
					
				})
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
