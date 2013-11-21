package com.curlymaple.server;

import org.apache.log4j.Logger;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

	private static final Logger logger = Logger.getLogger("server");

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
					.childHandler(new ServerInitializer());
			b.bind(ip, port).sync().channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		String ip = Config.getProperty(Config.IP);
		String temp = Config.getProperty(Config.PORT);
		int port = Integer.valueOf(temp);
		logger.info("Server ip:" + ip + ", port:" + port);
		new Server().run(ip, port);
	}
}