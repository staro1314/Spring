package com.example.spring.design.netty.demo3;

import org.jboss.netty.channel.ChannelUpstreamHandler;
import org.jboss.netty.handler.codec.spdy.SpdyOrHttpChooser;

import javax.net.ssl.SSLEngine;

/**
 * @author: Staro
 * @date: 2020/4/14 11:03
 * @Description:
 */
public class DefaultSpdyOrHttpChooser extends SpdyOrHttpChooser {
    protected DefaultSpdyOrHttpChooser(int maxSpdyContentLength, int maxHttpContentLength) {
        super(maxSpdyContentLength, maxHttpContentLength);
    }

    @Override
    protected SelectedProtocol getProtocol(SSLEngine sslEngine) {

        return null;
    }

    @Override
    protected ChannelUpstreamHandler createHttpRequestHandlerForHttp() {
        return null;
    }
}
