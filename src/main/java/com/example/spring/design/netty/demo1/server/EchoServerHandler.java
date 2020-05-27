package com.example.spring.design.netty.demo1.server;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author: Staro
 * @date: 2020/3/20 8:46
 * @Description:
 */
@ChannelHandler.Sharable  //1.表示这类的实例之间可以在channel里面共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server received:" + in.toString(CharsetUtil.UTF_8)); //2.日志信息输出到控制台
        ctx.write(in);   //3.将所接受的信息返回给发送者。注意，这里还没有冲刷数据
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE); //4.冲刷所有待审消息到远程节点。关闭通道后，操作完成
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace(); //5.打印异常堆栈异常
        ctx.close(); //6.关闭通道
    }
}
