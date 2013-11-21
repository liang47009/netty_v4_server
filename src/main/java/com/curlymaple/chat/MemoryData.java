package com.curlymaple.chat;

import io.netty.channel.Channel;

/**
 * 内存数据
 * 
 * @author CurlyMaple
 * 
 */
public class MemoryData {
	private User user;
	private Channel channel;

	public MemoryData(Channel channel) {
		this.channel = channel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

}