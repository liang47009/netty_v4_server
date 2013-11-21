package com.curlymaple.chat.message;

import net.sf.json.JSONObject;
import io.netty.channel.Channel;

import com.curlymaple.chat.IS2CCommand;

public class NameChange_S2C implements IS2CCommand {

	private String m = "nameChange";
	private Object p;

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public Object getP() {
		return p;
	}

	public void setP(Object p) {
		this.p = p;
	}

	public void send(int uid, Channel channel) {
		this.setP(new Uid(uid));
		JSONObject json = JSONObject.fromObject(this);
		String msg = json.toString();
		channel.write(msg);
	}

}