package com.foo;

import org.json.JSONException;
import org.json.JSONObject;

public class OS implements Parts{
	private long OpenFileDescriptorCount;
	private long MaxFileDescriptorCount;
	private long CommittedVirtualMemorySize;
	private long TotalSwapSpaceSize;
	private long FreeSwapSpaceSize;
	private long ProcessCpuTime;
	private long FreePhysicalMemorySize;
	private long TotalPhysicalMemorySize;
	private float SystemCpuLoad;
	private float ProcessCpuLoad;
	private String Version;
	private String AvailableProcessors;
	private String Arch;
	private String SystemLoadAverage;
	public void setOpenFileDescriptorCount (long OpenFileDescriptorCount){
		this.OpenFileDescriptorCount=OpenFileDescriptorCount;
	}
	public void setMaxFileDescriptorCount (long MaxFileDescriptorCount){
		this.MaxFileDescriptorCount=MaxFileDescriptorCount;
	}
	public void setCommittedVirtualMemorySize (long CommittedVirtualMemorySize){
		this.CommittedVirtualMemorySize=CommittedVirtualMemorySize;
	}
	public void setTotalSwapSpaceSize (long TotalSwapSpaceSize){
		this.TotalSwapSpaceSize=TotalSwapSpaceSize;
	}
	public void setFreeSwapSpaceSize (long FreeSwapSpaceSize){
		this.FreeSwapSpaceSize=FreeSwapSpaceSize;
	}
	public void setProcessCpuTime (long ProcessCpuTime){
		this.ProcessCpuTime=ProcessCpuTime;
	}
	public void setFreePhysicalMemorySize (long FreePhysicalMemorySize){
		this.FreePhysicalMemorySize=FreePhysicalMemorySize;
	}
	public void setTotalPhysicalMemorySize (long TotalPhysicalMemorySize){
		this.TotalPhysicalMemorySize=TotalPhysicalMemorySize;
	}
	public void setSystemCpuLoad (float SystemCpuLoad){
		this.SystemCpuLoad=SystemCpuLoad;
	}
	public void setProcessCpuLoad (float ProcessCpuLoad){
		this.ProcessCpuLoad=ProcessCpuLoad;
	}
	public void setVersion (String Version){
		this.Version=Version;
	}
	public void setAvailableProcessors (String AvailableProcessors){
		this.AvailableProcessors=AvailableProcessors;
	}
	public void setArch (String Arch){
		this.Arch=Arch;
	}
	public void setSystemLoadAverage (String SystemLoadAverage){
		this.SystemLoadAverage=SystemLoadAverage;
	}
	public JSONObject putValueInJson(JSONObject json) throws JSONException {
		json.put("OpenFileDescriptorCount", OpenFileDescriptorCount);
		json.put("MaxFileDescriptorCount", MaxFileDescriptorCount);
		json.put("CommittedVirtualMemorySize", CommittedVirtualMemorySize);
		json.put("TotalSwapSpaceSize", TotalSwapSpaceSize);
		json.put("FreeSwapSpaceSize", FreeSwapSpaceSize);
		json.put("ProcessCpuTime", ProcessCpuTime);
		json.put("FreePhysicalMemorySize", FreePhysicalMemorySize);
		json.put("TotalPhysicalMemorySize", TotalPhysicalMemorySize);
		json.put("SystemCpuLoad", SystemCpuLoad);
		json.put("ProcessCpuLoad", ProcessCpuLoad);
		json.put("Version", Version);
		json.put("AvailableProcessors", AvailableProcessors);
		json.put("Arch", Arch);
		json.put("SystemLoadAverage", SystemLoadAverage);
		return json;
	}
}
