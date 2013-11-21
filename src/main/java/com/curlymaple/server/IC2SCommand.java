package com.curlymaple.server;

import net.sf.json.JSONObject;

public interface IC2SCommand {
	void execute(JSONObject param, MemoryData memoryData);
}
