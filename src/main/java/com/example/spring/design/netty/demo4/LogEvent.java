package com.example.spring.design.netty.demo4;

import lombok.Getter;
import lombok.Setter;

import java.net.InetSocketAddress;

/**
 * @author: Staro
 * @date: 2020/4/14 14:40
 * @Description:
 */
@Setter
@Getter
public class LogEvent {
    public static final byte SEPARATOR = (byte) ':';

    private final InetSocketAddress source;
    private final String logfile;
    private final String msg;
    private final long received;

    public LogEvent(String logfile, String msg) {
        this(null, -1, logfile, msg);
    }

    public LogEvent(InetSocketAddress source, long received, String logfile, String msg) {
        this.source = source;
        this.received = received;
        this.logfile = logfile;
        this.msg = msg;
    }
}
