/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.curlymaple.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.curlymaple.server.message.LogicManager;

/**
 * Handles a server-side channel.
 */
@Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String> {

	private static final Logger logger = Logger.getLogger("server");

	private AttributeKey<MemoryData> key = new AttributeKey<MemoryData>("u");

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.pipeline().channel();
		MemoryData md = new MemoryData(channel);
		ctx.attr(key).set(md);
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, String msg)
			throws Exception {
		try {
			// logger.info(msg);
			if (null == msg || "".equals(msg)) {
				return;
			}
			JSONObject json = JSONObject.fromObject(msg);
			IC2SCommand command = MessageDispatcher.dispatcher(json);
			if (null == command) {
				return;
			}
			MemoryData md = ctx.attr(key).get();
			JSONObject param = JSONObject.fromObject(json.get("p"));
			command.execute(param, md);
		} catch (Exception e) {
			ctx.pipeline().channel().close();
			ctx.pipeline().channel().disconnect();
			logger.error("messageReceived", e);
		}
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		MemoryData md = ctx.attr(key).get();
		LogicManager.getInstance().removeMemoryData(md);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		boolean isIOException = cause instanceof IOException;
		if (isIOException) {
			logger.info(cause.getMessage());
		} else {
			logger.error("exception:", cause);
		}
		ctx.close();
	}

}