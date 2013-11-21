package com.curlymaple.chat.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@Sharable
public class ProtocolEncoder extends MessageToByteEncoder<ISendable> {

	@Override
	protected void encode(ChannelHandlerContext ctx, ISendable msg, ByteBuf out)
			throws Exception {
		byte[] byteArray = msg.toByteArray();
		if (null == byteArray) {
			throw new NullPointerException("bytearray can't be null!");
		}
		out.writeInt(byteArray.length);
		out.writeBytes(byteArray);
	}

}