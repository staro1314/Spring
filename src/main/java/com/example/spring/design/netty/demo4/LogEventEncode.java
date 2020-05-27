package com.example.spring.design.netty.demo4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author: Staro
 * @date: 2020/4/14 14:46
 * @Description:
 */
public class LogEventEncode extends MessageToMessageEncoder<LogEvent> {
    private final InetSocketAddress source;

    public LogEventEncode(InetSocketAddress source) {
        this.source = source;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, LogEvent logEvent, List<Object> list) throws Exception {
        byte[] file = logEvent.getLogfile().getBytes(CharsetUtil.UTF_8);
        byte[] msg = logEvent.getMsg().getBytes(CharsetUtil.UTF_8);
        ByteBuf buffer = channelHandlerContext.alloc().buffer(file.length + msg.length + 1);
        buffer.writeBytes(file);
        buffer.writeByte(LogEvent.SEPARATOR);
        buffer.writeBytes(msg);
        list.add(new DatagramPacket(buffer.array(),0,file.length + msg.length + 1,source));
    }
}
