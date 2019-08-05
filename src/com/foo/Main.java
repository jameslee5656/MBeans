package com.foo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.openmbean.CompositeData;
//import java.lang.management.ManagementFactory;
//import java.util.Queue;
//import java.util.concurrent.ArrayBlockingQueue;
//import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class Main extends MFunc {
    public static void main(String[] args) throws Exception {
		try {
	        String mission = args[0];
	        String howLong = args[1];
	        String howMuch = args[1];
	    }
	    catch (ArrayIndexOutOfBoundsException e){
	        System.out.println("ArrayIndexOutOfBoundsException caught");
	    }
	    finally {
	    	System.out.println("No argument");
	    }
		System.out.println("Starting lookup ...");  
		String attributeName = "StartTime";  
		String host = "9.32.165.55";    
		int port = 9990;  // management-native port  
		String urlString = System.getProperty("jmx.service.url","service:jmx:remote+http://" + host + ":" + port);   
			   
		//for passing credentials for password 
		Map<String, String[]> env = new HashMap<>();
		String[] credentials = {System.getenv("JBOSS_ADMIN"), System.getenv("JBOSS_PASSWORD")};
		env.put(JMXConnector.CREDENTIALS, credentials);
			   
		JMXServiceURL serviceURL = new JMXServiceURL(urlString);    
		JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, env);
		
		
		attributeName = new String("HeapMemoryUsage");
		MFunc memory = new MFunc();
		System.out.println("Memory:");
		memory.getMemoryAttr(attributeName, jmxConnector);
		System.out.println("Runtime:");
		memory.getRuntimeAttr(attributeName, jmxConnector);
		
//		memory.sampleMemoryUsage(attributeName, jmxConnector, 5, 1000);
 
    }
}