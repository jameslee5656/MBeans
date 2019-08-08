package com.foo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.Vector;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnector;

import org.json.JSONObject;

import com.foo.Memory;

public class MFunc{	
	private CompositeData cd = null;
	private Object Mbean = null;
	private Object osMbean = null;
	private Vector<Object> objVec = new Vector<Object>();
	
	// This is the place I store my variable;
	private Memory memory = new Memory();
	private Threads threads = new Threads();
	private Runtime runtime = new Runtime();
	private OS os = new OS();
	
	// This function do output and return output
	public CompositeData outputCDAttr(JMXConnector jmxConnector,String ObjectNameStr,String attributeName,boolean printout) throws AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, IOException {
		//get an instance of the Mbean
		Mbean = jmxConnector.getMBeanServerConnection().getAttribute(
				new ObjectName(ObjectNameStr), attributeName);
		cd = (CompositeData)Mbean;
		Set<String> keys = cd.getCompositeType().keySet();
		if (printout) { 
			for (String item:keys) {
				System.out.format("%-35s:" ,attributeName + "(" + item + ")");
				System.out.println(cd.get(item).toString());
			}
		}
		return cd;
	}
	// This function is for returning Long Attributes one 
	public long outputLongAttr(JMXConnector jmxConnector,String ObjectNameStr,String attributeName,boolean printout) throws AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, IOException {
		long tempt;
		Mbean = jmxConnector.getMBeanServerConnection().getAttribute(
				new ObjectName(ObjectNameStr), attributeName);
		tempt = Long.parseLong(Mbean.toString());
		if(printout) {
			System.out.format("%-35s:" , attributeName);
			System.out.println(Long.toString(tempt));
		}
		return tempt;
	}
	// This function is for returning float Attributes one 
	public float outputFloatAttr(JMXConnector jmxConnector,String ObjectNameStr,String attributeName,boolean printout) throws AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, IOException {
		float tempt;
		//get an instance of the Mbean
		Mbean = jmxConnector.getMBeanServerConnection().getAttribute(
				new ObjectName(ObjectNameStr), attributeName);
		tempt = Float.parseFloat(Mbean.toString());
		if(printout) {
			System.out.format("%-35s:" , attributeName);
			System.out.println(Float.toString(tempt));
		}
		return tempt;
	}
	// This function is for returning String Attributes one 
	public String outputStrAttr(JMXConnector jmxConnector,String ObjectNameStr,String attributeName,boolean printout) throws AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, IOException {
		String tempt;
		//get an instance of the Mbean
		Mbean = jmxConnector.getMBeanServerConnection().getAttribute(
				new ObjectName(ObjectNameStr), attributeName);
		tempt = Mbean.toString();
		if(printout) {
			System.out.format("%-35s:" , attributeName);
			System.out.println(tempt);
		}
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
	public boolean outputBoolAttr(JMXConnector jmxConnector,String ObjectNameStr,String attributeName,boolean printout) throws AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, IOException {
		boolean tempt;
		//get an instance of the Mbean
		Mbean = jmxConnector.getMBeanServerConnection().getAttribute(
				new ObjectName(ObjectNameStr), attributeName);
		tempt = Boolean.parseBoolean(Mbean.toString());
		if (printout) {
			System.out.format("%-35s:" , attributeName);
			System.out.println(tempt);
		}
		return Boolean.valueOf(tempt);
	}
	// This is for output json
	public void writeJson() throws Exception {
		JSONObject json = new JSONObject();
		for (Object item:objVec) {
			JSONObject jsonObj = new JSONObject();
			jsonObj = ((Parts) item).putValueInJson(jsonObj);
			json.put(item.getClass().toString(),jsonObj);
		}
        Files.write(Paths.get("output.json"), json.toString().getBytes());
    }
	// ------------------------------------------------
	// This function is for printing out Memory Attributes
	public void getMemoryAttr(JMXConnector jmxConnector,boolean printout) 
			throws Exception {	
		//Store what we get into Memory.java instance
		memory.setHeapMemoryUsage(outputCDAttr(jmxConnector,"java.lang:type=Memory","HeapMemoryUsage", printout));
		memory.setNonHeapMemoryUsage(outputCDAttr(jmxConnector,"java.lang:type=Memory","NonHeapMemoryUsage", printout));
		memory.setObjectPendingFinalizationCount(outputLongAttr(jmxConnector,"java.lang:type=Memory","ObjectPendingFinalizationCount", printout));
		memory.setVerbose(outputBoolAttr(jmxConnector,"java.lang:type=Memory","Verbose", printout));
		objVec.add(memory);
	}
	public void getThreadsAttr(JMXConnector jmxConnector, boolean printout) throws AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, IOException {
		threads.setThreadAllocatedMemoryEnabled(outputBoolAttr(jmxConnector,"java.lang:type=Threading","ThreadAllocatedMemorySupported",printout));
		threads.setThreadAllocatedMemoryEnabled(outputBoolAttr(jmxConnector,"java.lang:type=Threading","ThreadAllocatedMemoryEnabled",printout));
		threads.setThreadContentionMonitoringSupported(outputBoolAttr(jmxConnector,"java.lang:type=Threading","ThreadContentionMonitoringSupported",printout));
		threads.setCurrentThreadCpuTimeSupported(outputBoolAttr(jmxConnector,"java.lang:type=Threading","CurrentThreadCpuTimeSupported",printout));
		threads.setObjectMonitorUsageSupported(outputBoolAttr(jmxConnector,"java.lang:type=Threading","ObjectMonitorUsageSupported",printout));
		threads.setSynchronizerUsageSupported(outputBoolAttr(jmxConnector,"java.lang:type=Threading","SynchronizerUsageSupported",printout));
		threads.setThreadContentionMonitoringEnabled(outputBoolAttr(jmxConnector,"java.lang:type=Threading","ThreadContentionMonitoringEnabled",printout));
		threads.setThreadCpuTimeEnabled(outputBoolAttr(jmxConnector,"java.lang:type=Threading","ThreadCpuTimeEnabled",printout));
		threads.setPeakThreadCount(outputLongAttr(jmxConnector,"java.lang:type=Threading","PeakThreadCount",printout));
		threads.setDaemonThreadCount(outputLongAttr(jmxConnector,"java.lang:type=Threading","DaemonThreadCount",printout));
		threads.setThreadCount(outputLongAttr(jmxConnector,"java.lang:type=Threading","ThreadCount",printout));
		threads.setTotalStartedThreadCount(outputLongAttr(jmxConnector,"java.lang:type=Threading","TotalStartedThreadCount",printout));
		// This one should be array but need more research how to do it
		threads.setAllThreadIds(outputStrAttr(jmxConnector,"java.lang:type=Threading","AllThreadIds",printout));
		threads.setCurrentThreadCpuTime(outputLongAttr(jmxConnector,"java.lang:type=Threading","CurrentThreadCpuTime",printout));
		threads.setCurrentThreadUserTime(outputLongAttr(jmxConnector,"java.lang:type=Threading","CurrentThreadUserTime",printout));
		threads.setThreadCpuTimeSupported(outputBoolAttr(jmxConnector,"java.lang:type=Threading","ThreadCpuTimeSupported",printout));
		objVec.add(threads);
	}
	public void getRuntimeAttr(JMXConnector jmxConnector, boolean printout) 
			throws MalformedObjectNameException, AttributeNotFoundException, InstanceNotFoundException, MBeanException,
				ReflectionException, IOException {	
		runtime.setVmName(outputStrAttr(jmxConnector,"java.lang:type=Runtime","VmName",printout));
		runtime.setVmVendor(outputStrAttr(jmxConnector,"java.lang:type=Runtime","VmVendor",printout));
		runtime.setVmVersion(outputStrAttr(jmxConnector,"java.lang:type=Runtime","VmVersion",printout));
		runtime.setSpecName(outputStrAttr(jmxConnector,"java.lang:type=Runtime","SpecName",printout));
		runtime.setManagementSpecVersion(outputStrAttr(jmxConnector,"java.lang:type=Runtime","ManagementSpecVersion",printout));
		runtime.setLibraryPath(outputStrAttr(jmxConnector,"java.lang:type=Runtime","LibraryPath",printout));
		runtime.setBootClassPathSupported(outputBoolAttr(jmxConnector,"java.lang:type=Runtime","BootClassPathSupported",printout));
		runtime.setBootClassPath(outputStrAttr(jmxConnector,"java.lang:type=Runtime","BootClassPath",printout));
		runtime.setInputArguments(outputStrAttr(jmxConnector,"java.lang:type=Runtime","InputArguments",printout));
		runtime.setUptime(outputLongAttr(jmxConnector,"java.lang:type=Runtime","Uptime",printout));
		runtime.setSpecVersion(outputStrAttr(jmxConnector,"java.lang:type=Runtime","SpecVersion",printout));
		runtime.setSpecVendor(outputStrAttr(jmxConnector,"java.lang:type=Runtime","SpecVendor",printout));
		runtime.setstartTime(outputLongAttr(jmxConnector,"java.lang:type=Runtime","StartTime",printout));
		objVec.add(runtime);
	}
	public void getOSAttr(JMXConnector jmxConnector,boolean printout) 
			throws MalformedObjectNameException, AttributeNotFoundException, InstanceNotFoundException, MBeanException,
				ReflectionException, IOException {	
		os.setOpenFileDescriptorCount(outputLongAttr(jmxConnector,"java.lang:type=OperatingSystem","OpenFileDescriptorCount",printout));
		os.setMaxFileDescriptorCount(outputLongAttr(jmxConnector,"java.lang:type=OperatingSystem","MaxFileDescriptorCount",printout));
		os.setCommittedVirtualMemorySize(outputLongAttr(jmxConnector,"java.lang:type=OperatingSystem","CommittedVirtualMemorySize",printout));
		os.setTotalSwapSpaceSize(outputLongAttr(jmxConnector,"java.lang:type=OperatingSystem","TotalSwapSpaceSize",printout));
		os.setFreeSwapSpaceSize(outputLongAttr(jmxConnector,"java.lang:type=OperatingSystem","FreeSwapSpaceSize",printout));
		os.setProcessCpuTime(outputLongAttr(jmxConnector,"java.lang:type=OperatingSystem","ProcessCpuTime",printout));
		os.setFreePhysicalMemorySize(outputLongAttr(jmxConnector,"java.lang:type=OperatingSystem","FreePhysicalMemorySize",printout));
		os.setTotalPhysicalMemorySize(outputLongAttr(jmxConnector,"java.lang:type=OperatingSystem","TotalPhysicalMemorySize",printout));
		os.setSystemCpuLoad(outputFloatAttr(jmxConnector,"java.lang:type=OperatingSystem","SystemCpuLoad",printout));
		os.setProcessCpuLoad(outputFloatAttr(jmxConnector,"java.lang:type=OperatingSystem","ProcessCpuLoad",printout));
		os.setVersion(outputStrAttr(jmxConnector,"java.lang:type=OperatingSystem","Version",printout));
		os.setAvailableProcessors(outputStrAttr(jmxConnector,"java.lang:type=OperatingSystem","AvailableProcessors",printout));
		os.setArch(outputStrAttr(jmxConnector,"java.lang:type=OperatingSystem","Arch",printout));
		os.setSystemLoadAverage(outputStrAttr(jmxConnector,"java.lang:type=OperatingSystem","SystemLoadAverage",printout));
		objVec.add(os);
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


