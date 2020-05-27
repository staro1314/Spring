package com.example.spring.nio.netty.embedded;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: Staro
 * @date: 2020/4/10 15:29
 * @Description:
 */
public class AbsIntegerEncoderTest {
    @Test
    public void testEncoded() {
        ByteBuf byteBuf = Unpooled.buffer();
        for (int i = 1; i < 10; i++) {
            byteBuf.writeByte(i * -1);
        }
        EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());
        Assert.assertTrue(channel.writeOutbound(byteBuf));

        Assert.assertTrue(channel.finish());
        for (int i = 1; i < 10; i++) {
            Assert.assertEquals(i, java.util.Optional.ofNullable(channel.readOutbound()));
        }
        Assert.assertNull(channel.readOutbound());
    }
}
