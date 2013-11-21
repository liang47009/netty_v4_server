package com.curlymaple.server.message;

import net.sf.json.JSONObject;

import com.curlymaple.server.IC2SCommand;
import com.curlymaple.server.MemoryData;

public class NameChange_C2S implements IC2SCommand {

	public void execute(JSONObject param, MemoryData memoryData) {
		LogicManager.getInstance().nameChange(param, memoryData);
	}

}
