package com.chong.bystest.netty.demo.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.ServerSocketChannel;

/**
 * @author lichong
 * 2018/10/28 17:49
 * @version 1
 * @since 1.0
 */
public class ServerInitlializer extends ChannelInitializer<ServerSocketChannel> {

    @Override
    protected void initChannel(ServerSocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

    }
}
