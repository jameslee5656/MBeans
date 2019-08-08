package com.foo;

import java.util.HashMap;
import java.util.Map;

//import java.lang.management.ManagementFactory;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class Main extends MFunc {
    public static void main(String[] args) throws Exception {
        String mission = "";
        String parts   = "";
        String host = "";
        String port = "";
        String JBOSS_ADMIN = "";
        String JBOSS_PASSWORD = "";
        String howLong = ""; 
        String howMuch = "";
		try {
	        mission = args[0];
	        host    = args[1]; 
	        port    = args[2];
	        JBOSS_ADMIN = args[3];
	        JBOSS_PASSWORD = args[4];
	        parts   = args[5];
	        howLong = args[6];
	        howMuch = args[7];
	    }
	    catch (ArrayIndexOutOfBoundsException e){
//	        System.out.println("ArrayIndexOutOfBoundsException caught");
	    }
	    finally {
	    }
		// This part will be arguments reference
		if ((mission.equals("help")) || (mission.equals("h"))) {
			System.out.println("|**************************************************************|");
			System.out.println("|This is a small table for you to reference this program       |");
			System.out.println("|1 argument: What kind of mission you need                     |");
			System.out.println("|    1.1 help (h)     : Print out this table                   |");
			System.out.println("|    1.2 view  (v): To view the attributes once                |");
			System.out.println("|    1.3 export(e): Export a json file                         |");
			System.out.println("|2 argument: The IP you are going to connect                   |");
			System.out.println("|3 argument: The Port you are going to use                     |");
			System.out.println("|4 argument: The JBOSS_ADMIN                                   |");
			System.out.println("|5 argument: The JBOSS_PASSWORD                                |");
			System.out.println("|6 argument: Which part you want?                              |");
			System.out.println("|    6.1 all  (a) : Every parts                                |");
			System.out.println("|      (also any form of M,R,O,T will be all too)              |");
			System.out.println("|    6.2 M    (m) : Memory                                     |");
			System.out.println("|    6.3 R    (r) : RunTime                                    |");
			System.out.println("|    6.4 O    (o) : Operating System                           |");
			System.out.println("|    6.5 T    (t) : Threading                                  |");
			System.out.println("|    (Multiple like you need Memory and Runtime just type RM)  |");
			System.out.println("|    (The arrangement of every UpperCase Letter doesn't matter)|");
			System.out.println("|7 argument: How many milliseconds for one sample              |");
			System.out.println("|8 argument: The number of samples you take                    |");
			System.out.println("***************************************************************|");
			System.out.println("| |----------|----------|----------| ... 3 times   (the 8 argu)|");
			System.out.println("|  5 millisec 5 millisec 5 millisec  ... 5 millisec(the 7 argu)|");
			System.out.println("***************************************************************|");
//			System.out.println("||");
			System.exit(0);
		}

		System.out.println("Starting lookup ...");  
		if(host.isEmpty()) {
			host = "9.32.165.55";// host
		}
		if(port.isEmpty()) {
			port = "9990";// management-native port  
		} 
		String urlString = System.getProperty("jmx.service.url","service:jmx:remote+http://" + host + ":" + port);   
			   
		//for passing credentials for password 
		Map<String, String[]> env = new HashMap<>();
		if(JBOSS_ADMIN.isEmpty()) {
			JBOSS_ADMIN = System.getenv("JBOSS_ADMIN");
		}
		if(JBOSS_PASSWORD.isEmpty()) {
			JBOSS_PASSWORD = System.getenv("JBOSS_PASSWORD");
		}
		String[] credentials = {JBOSS_ADMIN, JBOSS_PASSWORD};
		env.put(JMXConnector.CREDENTIALS, credentials);
			   
		JMXServiceURL serviceURL = new JMXServiceURL(urlString);    
		JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, env);
		MFunc mfunc = new MFunc();
		
		if ((mission.equals("view")) || (mission.equals("v"))) {
			boolean printout = true;
			if ((mission.equals("export")) || (mission.equals("e"))){
				printout = false;
			}
			if(parts.contains("a")||parts.equals("all")||parts.contains("M")||parts.contains("m")) {
				System.out.println("Memory:");
				mfunc.getMemoryAttr(jmxConnector,printout);
			}
			if(parts.contains("a")||parts.equals("all")||parts.contains("R")||parts.contains("r")) {
				System.out.println("Runtime:");
				mfunc.getRuntimeAttr(jmxConnector,printout);
			}
			if(parts.contains("a")||parts.equals("all")||parts.contains("O")||parts.contains("o")) {
				System.out.println("Operating System:");
				mfunc.getOSAttr(jmxConnector,printout);
			}
			if(parts.contains("a")||parts.equals("all")||parts.contains("T")||parts.contains("t")) {
				System.out.println("Threading:");
				mfunc.getThreadsAttr(jmxConnector,printout);
			}
		}
		mfunc.writeJson();
		
//		memory.sampleMemoryUsage(attributeName, jmxConnector, 5, 1000);
 
    }
}