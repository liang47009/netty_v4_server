package com.curlymaple.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ChatServer {
	public void run(String ip, int port) throws Exception {
		System.setProperty("io.netty.noUnsafe", "true");
		System.setProperty("io.netty.noJavassist", "false");
		System.setProperty("io.netty.noPreferDirect", "true");
		System.setProperty("io.netty.noResourceLeakDetection", "false");
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChatServerInitializer());
			b.bind(ip, port).sync().channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		new ChatServer().run("192.168.1.132", 8888);
	}
}