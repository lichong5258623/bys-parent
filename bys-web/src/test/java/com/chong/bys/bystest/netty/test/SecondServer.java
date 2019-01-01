package com.chong.bys.bystest.netty.test;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author lichong
 * 2018/10/16 20:05
 * @version 1
 * @since 1.0
 */
public class SecondServer {


    public static void main(String[] args) throws Exception {

        NioEventLoopGroup parentGroup = new NioEventLoopGroup();
        NioEventLoopGroup childGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(parentGroup,childGroup )
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new SecondServerInitlialzer());

            ChannelFuture future = server.bind(20001).sync();
            future.channel().closeFuture().sync();
        }finally {
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }





    }
}
