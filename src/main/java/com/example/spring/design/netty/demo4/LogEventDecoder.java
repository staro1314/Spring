package com.example.spring.design.netty.demo4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author: Staro
 * @date: 2020/4/14 15:28
 * @Description:
 */
public class LogEventDecoder extends MessageToMessageDecoder<DatagramPacket> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket, List<Object> list) throws Exception {
        byte[] content = datagramPacket.getData();
        ByteBuf data = Unpooled.copiedBuffer(content);
        int i = data.indexOf(0, data.readableBytes(), LogEvent.SEPARATOR);
        String fileName = data.slice(0, i).toString(CharsetUtil.UTF_8);
        String logMsg = data.slice(i + 1, data.readableBytes()).toString(CharsetUtil.UTF_8);

        LogEvent logEvent = new LogEvent((InetSocketAddress) datagramPacket.getSocketAddress(), System.currentTimeMillis(), fileName, logMsg);
        list.add(logEvent);
    }
}
