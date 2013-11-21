package com.curlymaple.chat.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.MessageList;
import io.netty.handler.codec.MessageToMessageDecoder;

@Sharable
public class ProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg,
			MessageList<Object> out) throws Exception {
		if (null != msg) {
			if (msg instanceof ByteBuf) {
				ByteBuf temp = (ByteBuf) msg;
				if (temp.readableBytes() < 4) {
					// 长度不对
					return;
				}
				temp.markReaderIndex();
				int length = temp.readInt();
				if (temp.readableBytes() < length) {
					// 可读长度与消息说明的长度不符
					temp.resetReaderIndex();
					return;
				}
				temp = temp.readBytes(length);
				out.add(temp);
			}
		}
	}

}
