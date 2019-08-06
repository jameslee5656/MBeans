package com.foo;

import java.io.IOException;
import java.util.Set;

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
	private Object Mbean = null;
	private Object osMbean = null;
	// This function do output and return output
	public CompositeData outputCDAttr(JMXConnector jmxConnector,String ObjectNameStr,String attributeName) throws AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, IOException {
		CompositeData tempt = null;
		//get an instance of the Mbean
		Mbean = jmxConnector.getMBeanServerConnection().getAttribute(
				new ObjectName(ObjectNameStr), attributeName);
		cd = (CompositeData) Mbean;
		Set<String> keys = cd.getCompositeType().keySet();
		for (String item:keys) {
			System.out.format("%-35s:" ,attributeName + "(" + item + ")");
			System.out.println(cd.get(item).toString());
		}
		return cd;
	}
	// This function is for returning Long Attributes one 
	public long outputLongAttr(JMXConnector jmxConnector,String ObjectNameStr,String attributeName) throws AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, IOException {
		long tempt;
		Mbean = jmxConnector.getMBeanServerConnection().getAttribute(
				new ObjectName(ObjectNameStr), attributeName);
		tempt = Long.parseLong(Mbean.toString());
		System.out.format("%-35s:" , attributeName);
		System.out.println(Long.toString(tempt));
		return tempt;
	}
	// This function is for returning float Attributes one 
	public float outputFloatAttr(JMXConnector jmxConnector,String ObjectNameStr,String attributeName) throws AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, IOException {
		float tempt;
		//get an instance of the Mbean
		Mbean = jmxConnector.getMBeanServerConnection().getAttribute(
				new ObjectName(ObjectNameStr), attributeName);
		tempt = Float.parseFloat(Mbean.toString());
		System.out.format("%-35s:" , attributeName);
		System.out.println(Float.toString(tempt));
		return tempt;
	}
	// This function is for returning String Attributes one 
	public String outputStrAttr(JMXConnector jmxConnector,String ObjectNameStr,String attributeName) throws AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, IOException {
		String tempt;
		//get an instance of the Mbean
		Mbean = jmxConnector.getMBeanServerConnection().getAttribute(
				new ObjectName(ObjectNameStr), attributeName);
		tempt = Mbean.toString();
		System.out.format("%-35s:" , attributeName);
		System.out.println(tempt);
		return tempt;
	}
//	This one apparently have some problem == > need more research on it
	public String[] outputStrarrAttr(JMXConnector jmxConnector,String ObjectNameStr,String attributeName) throws AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, IOException {
		String [] tempt = null;
		//get an instance of the Mbean
		Mbean = jmxConnector.getMBeanServerConnection().getClass();
		cd = (CompositeData) Mbean;
		System.out.println("Debug:" + Mbean.toString());
//		System.out.format("%-35s:" , attributeName);
		return tempt;
	}
	// This function is for returning Boolean Attributes one 
	public boolean outputBoolAttr(JMXConnector jmxConnector,String ObjectNameStr,String attributeName) throws AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, IOException {
		boolean tempt;
		//get an instance of the Mbean
		Mbean = jmxConnector.getMBeanServerConnection().getAttribute(
				new ObjectName(ObjectNameStr), attributeName);
		tempt = Boolean.parseBoolean(Mbean.toString());
		System.out.format("%-35s:" , attributeName);
		System.out.println(tempt);
		return tempt;
	}
	// ------------------------------------------------
	// This function is for printing out Memory Attributes
	public void getMemoryAttr(String attributeName,JMXConnector jmxConnector) 
			throws MalformedObjectNameException, AttributeNotFoundException, InstanceNotFoundException, MBeanException,
				ReflectionException, IOException {	
		//get an HeapMemoryUsage instance ... this is a CompositeData
		CompositeData HeapMemoryUsage = outputCDAttr(jmxConnector,"java.lang:type=Memory","HeapMemoryUsage");
		CompositeData NonHeapMemoryUsage = outputCDAttr(jmxConnector,"java.lang:type=Memory","NonHeapMemoryUsage");
		long ObjectPendingFinalizationCount = outputLongAttr(jmxConnector,"java.lang:type=Memory","ObjectPendingFinalizationCount");
		boolean Verbose = outputBoolAttr(jmxConnector,"java.lang:type=Memory","Verbose");
	}
	
	public void getThreadsAttr(JMXConnector jmxConnector) throws AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, IOException {
		boolean ThreadAllocatedMemorySupported =  outputBoolAttr(jmxConnector,"java.lang:type=Threading","ThreadAllocatedMemorySupported");
		boolean ThreadAllocatedMemoryEnabled = outputBoolAttr(jmxConnector,"java.lang:type=Threading","ThreadAllocatedMemoryEnabled");
		boolean ThreadContentionMonitoringSupported = outputBoolAttr(jmxConnector,"java.lang:type=Threading","ThreadContentionMonitoringSupported");
		boolean CurrentThreadCpuTimeSupported = outputBoolAttr(jmxConnector,"java.lang:type=Threading","CurrentThreadCpuTimeSupported");
		boolean ObjectMonitorUsageSupported = outputBoolAttr(jmxConnector,"java.lang:type=Threading","ObjectMonitorUsageSupported");
		boolean SynchronizerUsageSupported = outputBoolAttr(jmxConnector,"java.lang:type=Threading","SynchronizerUsageSupported");
		boolean ThreadContentionMonitoringEnabled = outputBoolAttr(jmxConnector,"java.lang:type=Threading","ThreadContentionMonitoringEnabled");
		boolean ThreadCpuTimeEnabled = outputBoolAttr(jmxConnector,"java.lang:type=Threading","ThreadCpuTimeEnabled");
		long PeakThreadCount = outputLongAttr(jmxConnector,"java.lang:type=Threading","PeakThreadCount");
		long DaemonThreadCount = outputLongAttr(jmxConnector,"java.lang:type=Threading","DaemonThreadCount");
		long ThreadCount = outputLongAttr(jmxConnector,"java.lang:type=Threading","ThreadCount");
		long TotalStartedThreadCount = outputLongAttr(jmxConnector,"java.lang:type=Threading","TotalStartedThreadCount");
		// This one should be array but need more research how to do it
		String AllThreadIds = outputStrAttr(jmxConnector,"java.lang:type=Threading","AllThreadIds");
		long CurrentThreadCpuTime = outputLongAttr(jmxConnector,"java.lang:type=Threading","CurrentThreadCpuTime");
		long CurrentThreadUserTime = outputLongAttr(jmxConnector,"java.lang:type=Threading","CurrentThreadUserTime");
		boolean ThreadCpuTimeSupported = outputBoolAttr(jmxConnector,"java.lang:type=Threading","ThreadCpuTimeSupported");
	}
	public void getRuntimeAttr(JMXConnector jmxConnector) 
			throws MalformedObjectNameException, AttributeNotFoundException, InstanceNotFoundException, MBeanException,
				ReflectionException, IOException {	
		String VmName = outputStrAttr(jmxConnector,"java.lang:type=Runtime","VmName");
		String VmVendor = outputStrAttr(jmxConnector,"java.lang:type=Runtime","VmVendor");
		String VmVersion = outputStrAttr(jmxConnector,"java.lang:type=Runtime","VmVersion");
		String SpecName = outputStrAttr(jmxConnector,"java.lang:type=Runtime","SpecName");
		String ManagementSpecVersion = outputStrAttr(jmxConnector,"java.lang:type=Runtime","ManagementSpecVersion");
		String LibraryPath = outputStrAttr(jmxConnector,"java.lang:type=Runtime","LibraryPath");
		boolean BootClassPathSupported = outputBoolAttr(jmxConnector,"java.lang:type=Runtime","BootClassPathSupported");
		String BootClassPath = outputStrAttr(jmxConnector,"java.lang:type=Runtime","BootClassPath");
		String InputArguments = outputStrAttr(jmxConnector,"java.lang:type=Runtime","InputArguments");
		long Uptime = outputLongAttr(jmxConnector,"java.lang:type=Runtime","Uptime");
		String SpecVersion = outputStrAttr(jmxConnector,"java.lang:type=Runtime","SpecVersion");
		String SpecVendor = outputStrAttr(jmxConnector,"java.lang:type=Runtime","SpecVendor");
		long startTime = outputLongAttr(jmxConnector,"java.lang:type=Runtime","StartTime");
	}
	public void getOSAttr(String attributeName,JMXConnector jmxConnector) 
			throws MalformedObjectNameException, AttributeNotFoundException, InstanceNotFoundException, MBeanException,
				ReflectionException, IOException {	
		long OpenFileDescriptorCount = outputLongAttr(jmxConnector,
				"java.lang:type=OperatingSystem","OpenFileDescriptorCount");
		long MaxFileDescriptorCount =outputLongAttr(jmxConnector,
				"java.lang:type=OperatingSystem","MaxFileDescriptorCount");
		long CommittedVirtualMemorySize=outputLongAttr(jmxConnector,
				"java.lang:type=OperatingSystem","CommittedVirtualMemorySize");
		long TotalSwapSpaceSize=outputLongAttr(jmxConnector,
				"java.lang:type=OperatingSystem","TotalSwapSpaceSize");
		long FreeSwapSpaceSize=outputLongAttr(jmxConnector,
				"java.lang:type=OperatingSystem","FreeSwapSpaceSize");
		long ProcessCpuTime=outputLongAttr(jmxConnector,
				"java.lang:type=OperatingSystem","ProcessCpuTime");
		long FreePhysicalMemorySize=outputLongAttr(jmxConnector,
				"java.lang:type=OperatingSystem","FreePhysicalMemorySize");
		long TotalPhysicalMemorySize=outputLongAttr(jmxConnector,
				"java.lang:type=OperatingSystem","TotalPhysicalMemorySize");
		float SystemCpuLoad=outputFloatAttr(jmxConnector,
				"java.lang:type=OperatingSystem","SystemCpuLoad");
		float ProcessCpuLoad=outputFloatAttr(jmxConnector,
				"java.lang:type=OperatingSystem","ProcessCpuLoad");
		String Version=outputStrAttr(jmxConnector,
				"java.lang:type=OperatingSystem","Version");
		String AvailableProcessors=outputStrAttr(jmxConnector,
				"java.lang:type=OperatingSystem","AvailableProcessors");
		String Arch=outputStrAttr(jmxConnector,
				"java.lang:type=OperatingSystem","Arch");
		String SystemLoadAverage=outputStrAttr(jmxConnector,
				"java.lang:type=OperatingSystem","SystemLoadAverage");
	}
	public void sampleMemoryUsage(String attributeName, JMXConnector jmxConnector, int sampleCount, 
										long sampleMilliSecond) 
			throws AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException,
				MBeanException, ReflectionException, IOException, InterruptedException {
		long cpuBefore = 0;
		long tempMemory = 0;
		//get an instance of the HeapMemoryUsage Mbean
    	Mbean = jmxConnector.getMBeanServerConnection().getAttribute(
    										new ObjectName("java.lang:type=Memory"), "HeapMemoryUsage");
    	cd = (CompositeData) Mbean;
    	
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


