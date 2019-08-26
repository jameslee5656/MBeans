package com.foo;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class Threads implements Parts{
	private boolean ThreadAllocatedMemorySupported;
	private boolean ThreadAllocatedMemoryEnabled;
	private boolean ThreadContentionMonitoringSupported;
	private boolean CurrentThreadCpuTimeSupported;
	private boolean ObjectMonitorUsageSupported;
	private boolean SynchronizerUsageSupported;
	private boolean ThreadContentionMonitoringEnabled;
	private boolean ThreadCpuTimeEnabled;
	private long PeakThreadCount;
	private long DaemonThreadCount;
	private long ThreadCount;
	private long TotalStartedThreadCount;
	// This one should be array but need more research how to do it
	private Map<String, Object> AllThreadIds;
	private long CurrentThreadCpuTime;
	private long CurrentThreadUserTime;
	private boolean ThreadCpuTimeSupported;
	public void setThreadAllocatedMemorySupported(boolean ThreadAllocatedMemorySupported) {
		this.ThreadAllocatedMemorySupported = ThreadAllocatedMemorySupported;
	}
	public void setThreadAllocatedMemoryEnabled(boolean ThreadAllocatedMemoryEnabled) {
		this.ThreadAllocatedMemoryEnabled = ThreadAllocatedMemoryEnabled;
	}
	public void setThreadContentionMonitoringSupported(boolean ThreadContentionMonitoringSupported) {
		this.ThreadContentionMonitoringSupported = ThreadContentionMonitoringSupported;
	}
	public void setCurrentThreadCpuTimeSupported(boolean CurrentThreadCpuTimeSupported) {
		this.CurrentThreadCpuTimeSupported = CurrentThreadCpuTimeSupported;
	}
	public void setObjectMonitorUsageSupported(boolean ObjectMonitorUsageSupported) {
		this.ObjectMonitorUsageSupported = ObjectMonitorUsageSupported;
	}
	public void setSynchronizerUsageSupported(boolean SynchronizerUsageSupported) {
		this.SynchronizerUsageSupported = SynchronizerUsageSupported;
	}
	public void setThreadContentionMonitoringEnabled(boolean ThreadContentionMonitoringEnabled) {
		this.ThreadContentionMonitoringEnabled = ThreadContentionMonitoringEnabled;
	}
	public void setThreadCpuTimeEnabled(boolean ThreadCpuTimeEnabled) {
		this.ThreadCpuTimeEnabled = ThreadCpuTimeEnabled;
	}
	public void setPeakThreadCount(long PeakThreadCount) {
		this.PeakThreadCount = PeakThreadCount;
	}
	public void setDaemonThreadCount(long DaemonThreadCount) {
		this.DaemonThreadCount = DaemonThreadCount;
	}
	public void setThreadCount(long ThreadCount) {
		this.ThreadCount = ThreadCount;
	}
	public void setTotalStartedThreadCount(long TotalStartedThreadCount) {
		this.TotalStartedThreadCount = TotalStartedThreadCount;
	}
	public void setAllThreadIds(Map<String, Object> AllThreadIds) {
		this.AllThreadIds = AllThreadIds;
	}
	public void setCurrentThreadCpuTime(long CurrentThreadCpuTime) {
		this.CurrentThreadCpuTime = CurrentThreadCpuTime;
	}
	public void setCurrentThreadUserTime(long CurrentThreadUserTime)
	{
		this.CurrentThreadUserTime = CurrentThreadUserTime;
	}
	public void setThreadCpuTimeSupported(boolean ThreadCpuTimeSupported) {
		this.CurrentThreadCpuTimeSupported = ThreadCpuTimeSupported;
	}
	public JSONObject putValueInJson(JSONObject json) throws JSONException {
		json.put("ThreadAllocatedMemorySupported",ThreadAllocatedMemorySupported);
		json.put("ThreadAllocatedMemoryEnabled", ThreadAllocatedMemoryEnabled);
		json.put("ThreadContentionMonitoringSupported",ThreadContentionMonitoringSupported);
		json.put("CurrentThreadCpuTimeSupported",CurrentThreadCpuTimeSupported);
		json.put("ObjectMonitorUsageSupported", ObjectMonitorUsageSupported);
		json.put("SynchronizerUsageSupported", SynchronizerUsageSupported);
		json.put("ThreadContentionMonitoringEnabled",ThreadContentionMonitoringEnabled);
		json.put("ThreadCpuTimeEnabled",ThreadCpuTimeEnabled);
		json.put("PeakThreadCount",PeakThreadCount);
		json.put("DaemonThreadCount",DaemonThreadCount);
		json.put("ThreadCount",ThreadCount);
		json.put("TotalStartedThreadCount",TotalStartedThreadCount);
		// This one should be array but need more research how to do it
		json.put("AllThreadIds", AllThreadIds);
		json.put("CurrentThreadCpuTime",CurrentThreadCpuTime);
		json.put("CurrentThreadUserTime",CurrentThreadUserTime);
		json.put("ThreadCpuTimeSupported",ThreadCpuTimeSupported);
		return json;
	}
}
