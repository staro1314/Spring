package com.example.spring.design.netty.demo4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: Staro
 * @date: 2020/4/14 15:48
 * @Description:
 */
public class LogEventHandler extends SimpleChannelInboundHandler<LogEvent> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogEvent logEvent) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(logEvent.getReceived());
        sb.append("[");
        sb.append(logEvent.getSource().toString());
        sb.append("][");
        sb.append(logEvent.getLogfile());
        sb.append("]:");
        sb.append(logEvent.getMsg());
        System.out.println(sb.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
