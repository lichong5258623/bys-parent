package com.chong.bys.bystest.netty.demo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author lichong
 * 2018/10/28 16:42
 * @version 1
 * @since 1.0
 */
public class Server {


    public static void main(String[] args) throws InterruptedException {

        //定义一对线程组
        NioEventLoopGroup parentGroup = new NioEventLoopGroup();
        NioEventLoopGroup childGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(parentGroup,childGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerInitlializer());
            ChannelFuture future = serverBootstrap.bind(20001).sync();
            future.channel().closeFuture().sync();
        }finally {
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }
    }
}
