package com.qiaoqiao.qaoqaoapplication.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 作者：VULCAN
 * 版本：1.0
 * 创建日期：2016/9/21
 * 描述：
 * 修订历史：
 */

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    public TimeClientHandler() {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        System.out.println(msg);
//        ByteBuf byteBuf = (ByteBuf) msg;
//        try {
//            byte[] req = new byte[byteBuf.readableBytes()];
//            byteBuf.readBytes(req);
//            String string = new String(req, "UTF-8");
//            System.out.println(string);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } finally {
//            byteBuf.release();
//        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
