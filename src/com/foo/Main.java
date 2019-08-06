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
        String mission = "";
        String howLong;
        String howMuch;
		try {
	        mission = args[0];
	        howLong = args[1];
	        howMuch = args[1];
	    }
	    catch (ArrayIndexOutOfBoundsException e){
//	        System.out.println("ArrayIndexOutOfBoundsException caught");
	    }
	    finally {
	    }
		// This part will be arguments reference
		if ((mission.equals("help")) || (mission.equals("h"))) {
			System.out.println("*************************************************************");
			System.out.println("*This is a small table for you to reference this program    *");
			System.out.println("*1 argument: What kind of mission you need                  *");
			System.out.println("*    1.1 help (h)   : Print out this table                  *");
			System.out.println("*    1.2 overall (o): To look at all of the attributes once *");
			System.out.println("*2 argument: The IP you are going to connect                *");
			System.out.println("*3 argument: The JBOSS_ADMIN                                *");
			System.out.println("*4 argument: The JBOSS_PASSWORD                             *");
			System.out.println("*5 argument: How many milliseconds for one sample           *");
			System.out.println("*6 argument: The number of samples you take                 *");
			System.out.println("*************************************************************");
//			System.out.println("**");
			System.exit(0);
		}
		if ((mission.equals("help")) || (mission.equals("o")))
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
		MFunc mfunc = new MFunc();
		System.out.println("Memory:");
		mfunc.getMemoryAttr(attributeName, jmxConnector);
		System.out.println("Runtime:");
		mfunc.getRuntimeAttr(jmxConnector);
		System.out.println("OS:");
		mfunc.getOSAttr(attributeName, jmxConnector);
		System.out.println("Threading:");
		mfunc.getThreadsAttr(jmxConnector);
		
//		memory.sampleMemoryUsage(attributeName, jmxConnector, 5, 1000);
 
    }
}