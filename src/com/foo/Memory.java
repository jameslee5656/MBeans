package com.foo;

import java.util.Set;

import javax.management.openmbean.CompositeData;
import org.json.JSONException;
import org.json.JSONObject;

public class Memory implements Parts{
	protected CompositeData HeapMemoryUsage;
	protected CompositeData NonHeapMemoryUsage;
	protected long ObjectPendingFinalizationCount;
	protected boolean Verbose;
	
	// setting variables
	public void setHeapMemoryUsage(CompositeData HeapMemoryUsage) {
		this.HeapMemoryUsage =  HeapMemoryUsage;
	}
	public void setNonHeapMemoryUsage(CompositeData NonHeapMemoryUsage) {
		this.NonHeapMemoryUsage = NonHeapMemoryUsage;
	}
	public void setObjectPendingFinalizationCount(long ObjectPendingFinalizationCount) {
		this.ObjectPendingFinalizationCount = ObjectPendingFinalizationCount;
	}
	public void setVerbose(boolean Verbose) {
		this.Verbose = Verbose;
	}
	
	// getting variables
	public CompositeData getHeapMemoryUsage() {
		return HeapMemoryUsage;
	}
	public CompositeData getNonHeapMemoryUsage() {
		return NonHeapMemoryUsage;
	}
	public long getObjectPendingFinalizationCount() {
		return ObjectPendingFinalizationCount;
	}
	public boolean getVerbose() {
		return Verbose;
	}
	public JSONObject putValueInJson(JSONObject json) throws JSONException {
		Set<String> keys = HeapMemoryUsage.getCompositeType().keySet();
		JSONObject tempt = new JSONObject();
		for (String item:keys) {
			tempt.put(item, HeapMemoryUsage.get(item));
		}
		json.put("HeapMemoryUsage",tempt);
		keys = NonHeapMemoryUsage.getCompositeType().keySet();
		
		tempt = new JSONObject();
		for (String item:keys) {
			tempt.put(item, NonHeapMemoryUsage.get(item));
		}
		json.put("NonHeapMemoryUsage",tempt);
		json.put("ObjectPendingFinalizationCount", ObjectPendingFinalizationCount);
		json.put("Verbose",Verbose);
		return json;
	}
	
}
