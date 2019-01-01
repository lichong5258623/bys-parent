package com.chong.bys.bystest.netty.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author lichong
 * 2018/10/14 22:01
 * @version 1
 * @since 1.0
 */
public class FirstServer {

    public static void main(String[] args) throws Exception {

        //定义一对线程组
        NioEventLoopGroup parentGroup = new NioEventLoopGroup();
        NioEventLoopGroup childGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(parentGroup,childGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new FirstServerInitlializer());
            ChannelFuture future = serverBootstrap.bind(20001).sync();
            future.channel().closeFuture().sync();
        }finally {
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }
    }
}
