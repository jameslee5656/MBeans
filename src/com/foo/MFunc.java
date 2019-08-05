package com.foo;

import java.io.IOException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnector;

public class MFunc{	
	private CompositeData cd = null;
	private Object memoryMbean = null;
	private Object osMbean = null;
	// This function is for printing out Memory Usage
	public void getMemoryAttr(String attributeName,JMXConnector jmxConnector) 
			throws MalformedObjectNameException, AttributeNotFoundException, InstanceNotFoundException, MBeanException,
				ReflectionException, IOException {	
		long init;
		long max;
		long used;
		long committed;
		//get an instance of the HeapMemoryUsage Mbean
    	memoryMbean = jmxConnector.getMBeanServerConnection().getAttribute(
    										new ObjectName("java.lang:type=Memory"), "HeapMemoryUsage");
    	
    	cd = (CompositeData) memoryMbean;
		committed = Long.parseLong(cd.get("committed").toString());
		init = Long.parseLong(cd.get("init").toString());
		max = Long.parseLong(cd.get("max").toString());
		used = Long.parseLong(cd.get("used").toString());
		System.out.println("committed:" + Long.toString(committed));
		System.out.println("Init     :" + Long.toString(init));
		System.out.println("Max      :" + Long.toString(max));
		System.out.println("Used     :" + Long.toString(used));
	}
	public void getRuntimeAttr(String attributeName,JMXConnector jmxConnector) 
			throws MalformedObjectNameException, AttributeNotFoundException, InstanceNotFoundException, MBeanException,
				ReflectionException, IOException {	
		long startTime;
		long Uptime;
		//get an instance of the HeapMemoryUsage Mbean
    	memoryMbean = jmxConnector.getMBeanServerConnection().getAttribute(
    										new ObjectName("java.lang:type=Runtime"), "StartTime");
		startTime = Long.parseLong(memoryMbean.toString());
		
		memoryMbean = jmxConnector.getMBeanServerConnection().getAttribute(
				new ObjectName("java.lang:type=Runtime"), "Uptime");
		Uptime = Long.parseLong(memoryMbean.toString());
//		max = Long.parseLong(cd.get("max").toString());
//		used = Long.parseLong(cd.get("used").toString());
		System.out.println("startTime:" + Long.toString(startTime));
		System.out.println("Uptime   :" + Long.toString(Uptime));
//		System.out.println("Max      :" + Long.toString(max));
//		System.out.println("Used     :" + Long.toString(used));
	}
	public void sampleMemoryUsage(String attributeName, JMXConnector jmxConnector, int sampleCount, 
										long sampleMilliSecond) 
			throws AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException,
				MBeanException, ReflectionException, IOException, InterruptedException {
		long cpuBefore = 0;
		long tempMemory = 0;
		//get an instance of the HeapMemoryUsage Mbean
    	memoryMbean = jmxConnector.getMBeanServerConnection().getAttribute(
    										new ObjectName("java.lang:type=Memory"), "HeapMemoryUsage");
    	cd = (CompositeData) memoryMbean;
    	
    	// Create a loop to get values every second
    	for (int i = 0; i < sampleCount; i++) {
    		// get an instance of the OperatingSystem Mbean
	    	osMbean = jmxConnector.getMBeanServerConnection().getAttribute(
	    							new ObjectName("java.lang:type=OperatingSystem"),"ProcessCpuTime");
			if (i == 0) {
	    		cpuBefore = Long.parseLong(osMbean.toString());
	    	}
			System.out.println("Used memory: " + " " + cd.get("used") + " Used cpu: " + osMbean); //print memory usage
	    	tempMemory = tempMemory + Long.parseLong(cd.get("used").toString());
	    	Thread.sleep(sampleMilliSecond); //delay for one second
    	}
    	//get system time and cpu time from last poll
    	long cpuAfter = Long.parseLong(osMbean.toString());
    	long cpuDiff = cpuAfter - cpuBefore; //find cpu time between our first and last jmx poll
    	System.out.println("Cpu diff in milli seconds: " + cpuDiff / 1000000); //print cpu time in miliseconds
    	System.out.println("average memory usage is: " + tempMemory / sampleCount);//print average memory usage
    }
}
//Object attrVal = connection.getAttribute(mBeanName, attributeName);
//System.out.println("Value via JMX: " + new Date((Long) attrVal));
//	   
//mBeanName = new ObjectName("JMImplementation:type=MBeanServerDelegate");
//attrVal = connection.getAttribute(mBeanName, "MBeanServerId");
//System.out.println(attrVal);
//	   
//mBeanName = new ObjectName("java.lang:type=Memory");
//attrVal = connection.getAttribute(mBeanName, "ObjectPendingFinalizationCount");
//System.out.println(attrVal);
//	   
//mBeanName = new ObjectName("java.lang:type=Memory");
//attrVal = connection.getAttribute(mBeanName, "HeapMemoryUsage");
//System.out.println("HeapMemoryUsage   :" + attrVal);
//	   
//mBeanName = new ObjectName("java.lang:type=Memory");
//attrVal = connection.getAttribute(mBeanName, "NonHeapMemoryUsage");
//System.out.println("NonHeapMemoryUsage:" + attrVal);
//	   
//mBeanName = new ObjectName("java.lang:type=Memory");
//attrVal = connection.getAttribute(mBeanName, "Verbose");
//System.out.println("Verbose: " + attrVal);
//
//// Output Object name
//mBeanName = new ObjectName("java.lang:type=Memory");
//attrVal = connection.getAttribute(mBeanName, "ObjectName");
//System.out.println("ObjectName: 	" + attrVal);
//
//// call the garbage collector
////jmxc.getMBeanServerConnection().invoke(new ObjectName("java.lang:type=Memory"), "gc", null, null);
//
//mBeanName = new ObjectName("java.lang:type=OperatingSystem");
//attrVal = connection.getAttribute(mBeanName, "ObjectName");
//System.out.println("NonHeapMemoryUsage" + attrVal);

