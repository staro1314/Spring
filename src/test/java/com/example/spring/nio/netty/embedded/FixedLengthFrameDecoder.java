package com.example.spring.nio.netty.embedded;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author: Staro
 * @date: 2020/4/10 14:40
 * @Description:
 */
public class FixedLengthFrameDecoder extends ByteToMessageDecoder {//继承 ByteToMessageDecoder 用来处理入站的字节并将他们解码为消息
    private final int frameLength;

    public FixedLengthFrameDecoder(int frameLength) {//指定产出的帧的长度
        if (frameLength<=0){
            throw new IllegalArgumentException("frameLength must be a positive integer: "+frameLength);
        }
        this.frameLength = frameLength;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes()>=frameLength){//检查是否有足够的字节用于读到下个帧
            ByteBuf buf = byteBuf.readBytes(frameLength);//从 ByteBuf 读取新帧
            list.add(buf);//添加帧到解码好的消息 List
        }
    }


}
