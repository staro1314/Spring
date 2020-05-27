package com.example.spring.nio.netty.embedded;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: Staro
 * @date: 2020/4/10 14:46
 * @Description:
 */
public class FixedLengthFrameDecoderTest {

    @Test
    public void testFrameDecoded(){
        ByteBuf byteBuf=Unpooled.buffer();
        for (int i=0;i<9;i++){
            byteBuf.writeByte(i);
        }
        ByteBuf input = byteBuf.duplicate();

        EmbeddedChannel channel=new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        Assert.assertFalse(channel.writeInbound(input.readBytes(2)));
        Assert.assertTrue(channel.writeInbound(input.readBytes(7)));

        Assert.assertTrue(channel.finish());
        ByteBuf read=channel.readInbound();
        Assert.assertEquals(byteBuf.readSlice(3),read);
        read.release();

        read=channel.readInbound();
        Assert.assertEquals(byteBuf.readSlice(3),read);
        read.release();

        read=channel.readInbound();
        Assert.assertEquals(byteBuf.readSlice(3),read);
        read.release();

        Assert.assertNull(channel.readInbound());
        byteBuf.release();
    }
}
