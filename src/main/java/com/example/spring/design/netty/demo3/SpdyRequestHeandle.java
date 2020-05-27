package com.example.spring.design.netty.demo3;

import io.netty.channel.ChannelHandler;

/**
 * @author: Staro
 * @date: 2020/4/14 10:56
 * @Description:
 */
@ChannelHandler.Sharable
public class SpdyRequestHeandle extends HttpRequestHandler{ //继承 HttpRequestHandler 这样就能共享相同的逻辑

    @Override
    protected String getContect() {
        return "This contect is transmitted via SPDY\r\n"; //生产内容写到 payload。这个重写了 HttpRequestHandler 的 getContent() 的实现
    }
}
