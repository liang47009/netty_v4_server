package com.curlymaple.chat.message;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.curlymaple.chat.MemoryData;
import com.curlymaple.chat.User;

public class LogicManager {
	private static final LogicManager INSTANCE = new LogicManager();
	private static final Logger logger = Logger.getLogger("LogicManager");
	private LogicManager() {
	}

	private static final int MA = 200;// 鍏ㄩ閬撶鐞嗗憳(MA) 榛勯┈ 200
	private static final int VP = 230;// 棰戦亾鎬荤鐞�VP) 姗欓┈ 230
	private static final int OW = 255;// 棰戦亾鎵�湁鑰�OW) 绱┈ 255

	public static final LogicManager getInstance() {
		return INSTANCE;
	}

	private Map<Integer, Map<Integer, MemoryData>> superMemoryData = new ConcurrentHashMap<Integer, Map<Integer, MemoryData>>(
			32);

	// private Map<Integer, List<MemoryData>> normalMemoryData = new
	// ConcurrentHashMap<Integer, List<MemoryData>>(
	// 32);

	/**
	 * 鐢ㄦ埛淇℃伅
	 * 
	 * @param param
	 * @param memoryData
	 */
	public void yyUserInfo(JSONObject param, MemoryData memoryData) {
		if (null == memoryData.getUser()) {
			User user = new User();
			memoryData.setUser(user);
			int uid = param.getInt("uid");
			int role = param.getInt("role");
			int ch_top = param.getInt("channel");
			user.setUid(uid);
			user.setRole(role);
			user.setCh_top(ch_top);
			if (role == MA || role == VP || role == OW) {
				Map<Integer, MemoryData> userMap = superMemoryData.get(ch_top);
				if (userMap == null) {
					userMap = new ConcurrentHashMap<Integer, MemoryData>();
					superMemoryData.put(ch_top, userMap);
				}
				userMap.put(uid, memoryData);
			}
		}
	}

	/**
	 * 鐢ㄦ埛鏀瑰彉鍚嶇О
	 * {"method.name":"nameChange","method.param":{"uid":789012307,"role":
	 * 25,"channel":81910520}}
	 * 
	 * @param param
	 * @param memoryData
	 */
	public void nameChange(JSONObject param, MemoryData memoryData) {
		User user = memoryData.getUser();
		if (null != user) {
			int uid = param.getInt("uid");
			// int role = param.getInt("role");
			int ch_top = param.getInt("channel");
			Map<Integer, MemoryData> list = this.superMemoryData.get(ch_top);
			if (null != list && !list.isEmpty()) {
				for (MemoryData mem : list.values()) {
					String msg = "{\"m\":\"nameChange\",\"p\":{\"uid\":" + uid
							+ "}}";
					mem.getChannel().write(msg);
					logger.info(Calendar.getInstance().getTimeInMillis()+""+msg);
					// nc.send(uid, mem.getChannel());
				}
			}
		}
	}

	// private NameChange_S2C nc = new NameChange_S2C();
	/**
	 * 涓嬬嚎娓呯悊
	 * 
	 * @param md
	 */
	public void removeMemoryData(MemoryData md) {
		User user = md.getUser();
		if (null == user) {
			return;
		}
		Map<Integer, MemoryData> map = superMemoryData.get(user.getCh_top());
		if (null == map) {
			return;
		}
		map.remove(user.getUid());
	}

}
