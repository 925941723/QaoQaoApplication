package com.qiaoqiao.qaoqaoapplication.socket;

import com.qiaoqiao.qaoqaoapplication.config.Constants;
import com.serial.java.protobuf.SubscribeReqProto;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * 作者：VULCAN
 * 版本：1.0
 * 创建日期：2016/9/21
 * 描述：
 * 修订历史：
 */

public class QAOClient {

    private static final QAOClient qaoClient = new QAOClient();
    private ChannelFuture channelFuture = null;

    public static QAOClient getQaoClient(){
        return qaoClient;
    }

    public void connect(){
            EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new ProtobufDecoder(SubscribeReqProto.SubscribeReq.getDefaultInstance()))
                                    .addLast(new ProtobufEncoder())
                                    .addLast(new SubReqClientHandler());

//                            socketChannel.pipeline().addLast(new TimeClientHandler());
                        }
                    });
            channelFuture = bootstrap.connect(Constants.IP, Constants.PORT).sync();

            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public void send(String data){
        byte[] bytes = data.getBytes();
        if (null!=channelFuture) {
            ByteBuf byteBuf = channelFuture.channel().alloc().buffer();
            byteBuf.writeBytes(bytes);
            channelFuture.channel().writeAndFlush(byteBuf);
        }
    }
}
