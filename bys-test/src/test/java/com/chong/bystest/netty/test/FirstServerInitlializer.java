package com.chong.bystest.netty.test;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author lichong
 * 2018/10/14 22:20
 * @version 1
 * @since 1.0
 */
public class FirstServerInitlializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        //获取SocketChannel对应的管道
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        pipeline.addLast("firstHandler",new FirstHandler());


    }
}
