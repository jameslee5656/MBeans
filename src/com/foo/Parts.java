package com.foo;

import org.json.JSONException;
import org.json.JSONObject;

public interface Parts {
	public default JSONObject putValueInJson(JSONObject json) throws JSONException {
		return json;
	}

}
