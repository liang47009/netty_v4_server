package com.curlymaple.server;

import java.util.HashMap;
import java.util.Map;

import com.curlymaple.server.message.NameChange_C2S;
import com.curlymaple.server.message.YYUserInfo_C2S;

import net.sf.json.JSONObject;

public class MessageDispatcher {
	private static final Map<String, IC2SCommand> COMMANDS = new HashMap<String, IC2SCommand>();
	private static final String YYUSERINFO = "yyuserInfo";
	private static final String NAMECHANGE = "nameChange";

	static {
		COMMANDS.put(YYUSERINFO, new YYUserInfo_C2S());
		COMMANDS.put(NAMECHANGE, new NameChange_C2S());
	}

	public static IC2SCommand dispatcher(JSONObject json) throws Exception {
		String command = json.getString("m");
		if (null == command) {
			return null;
		}
		return getCommand(command);
	}

	private static IC2SCommand getCommand(String key) {
		return COMMANDS.get(key);
	}

}
