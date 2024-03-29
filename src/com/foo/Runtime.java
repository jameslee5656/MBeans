package com.foo;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class Runtime implements Parts{
	private String VmName;
	private String VmVendor;
	private String VmVersion;
	private String SpecName;
	private String ManagementSpecVersion;
	private String LibraryPath;
	private boolean BootClassPathSupported;
	private String BootClassPath;
	private Map<String, Object> InputArguments;
	private long Uptime;
	private String SpecVersion;
	private String SpecVendor;
	private long startTime;
	public void setVmName (String VmName) {
		this.VmName = VmName;
	}
	public void setVmVendor (String VmName) {
		this.VmName=VmName;
	}
	public void setVmVersion (String VmVersion) {
		this.VmVersion=VmVersion;
	}
	public void setSpecName (String SpecName) {
		this.SpecName=SpecName;
	}
	public void setManagementSpecVersion (String ManagementSpecVersion) {
		this.ManagementSpecVersion=ManagementSpecVersion;
	}
	public void setLibraryPath (String LibraryPath) {
		this.LibraryPath=LibraryPath;
	}
	public void setBootClassPathSupported (boolean BootClassPathSupported) {
		this.BootClassPathSupported=BootClassPathSupported;
	}
	public void setBootClassPath (String BootClassPath) {
		this.BootClassPath=BootClassPath;
	}
	public void setInputArguments (Map<String, Object> InputArguments) {
		this.InputArguments=InputArguments;
	}
	public void setUptime (long Uptime) {
		this.Uptime=Uptime;
	}
	public void setSpecVersion (String SpecVersion) {
		this.SpecVersion = SpecVersion;
	}
	public void setSpecVendor (String SpecVendor) {
		this.SpecVendor=SpecVendor;
	}
	public void setstartTime (long startTime) {
		this.startTime=startTime;
	}
	public JSONObject putValueInJson(JSONObject json) throws JSONException {
		json.put("VmName",VmName);
		json.put("VmVendor",VmVendor);
		json.put("VmVersion",VmVersion);
		json.put("SpecName",SpecName);
		json.put("ManagementSpecVersion",ManagementSpecVersion);
		json.put("LibraryPath",LibraryPath);
		json.put("BootClassPathSupported",BootClassPathSupported);
		json.put("BootClassPath",BootClassPath);
		json.put("InputArguments",InputArguments);
		json.put("Uptime",Uptime);
		json.put("SpecVersion",SpecVersion);
		json.put("SpecVendor",SpecVendor);
		json.put("startTime",startTime);
		return json;
	}
}
