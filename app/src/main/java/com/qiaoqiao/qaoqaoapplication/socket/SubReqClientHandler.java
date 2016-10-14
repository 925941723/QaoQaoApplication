package com.qiaoqiao.qaoqaoapplication.socket;

import com.serial.java.protobuf.SubscribeReqProto;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 作者：VULCAN
 * 版本：1.0
 * 创建日期：2016/9/22
 * 描述：
 * 修订历史：
 */

public class SubReqClientHandler extends ChannelInboundHandlerAdapter {
    public SubReqClientHandler() {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(subscribeReq(1));
    }

    private SubscribeReqProto.SubscribeReq subscribeReq(int i){
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(i)
                .setUserName("John")
                .setProductName("QAO")
                .setAddress("GuangZhou");
        return builder.build();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
//        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
