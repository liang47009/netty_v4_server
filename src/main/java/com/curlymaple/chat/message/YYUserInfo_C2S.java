package com.curlymaple.chat.message;

import net.sf.json.JSONObject;

import com.curlymaple.chat.IC2SCommand;
import com.curlymaple.chat.MemoryData;

public class YYUserInfo_C2S implements IC2SCommand {

	public void execute(JSONObject param, MemoryData memoryData) {
		LogicManager.getInstance().yyUserInfo(param, memoryData);
	}

}
