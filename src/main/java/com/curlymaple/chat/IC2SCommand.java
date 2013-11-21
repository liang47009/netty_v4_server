package com.curlymaple.chat;

import net.sf.json.JSONObject;

public interface IC2SCommand {
	void execute(JSONObject param, MemoryData memoryData);
}
