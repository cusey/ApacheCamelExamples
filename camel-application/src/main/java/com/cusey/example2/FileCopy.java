package com.cusey.example2;

/*
 * -- from and to 
 * Copying file from one file to another file
 * 
 * https://camel.apache.org/components/3.4.x/file-component.html
 * -- file:directoryName[?options]
 * The File component provides access to file systems, allowing files to be 
 * processed by any other Camel Components or messages from other components 
 * to be saved to disk.
 * 
 *  -- option noop
 * If true, the file is not moved or deleted in any way. 
 * This option is good for readonly data, or for ETL type 
 * requirements. If noop=true, Camel will set idempotent=true
 *  as well, to avoid consuming the same files over and over again.
 */

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


public class FileCopy {
	
	public static void main(String[] args) throws Exception {
		
		CamelContext context = new DefaultCamelContext();
		
		context.addRoutes( new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				from("file:input_box?noop=true")
				.to("file:ouput_box");
				
			}
			
		});
		
		while(true) {
			context.start();
		}
		
	}
}
			
		


