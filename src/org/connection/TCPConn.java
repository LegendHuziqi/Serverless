package org.connection;

import java.util.concurrent.Executors;

import io.netty.bootstrap.Bootstrap;
import  io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.apache.log4j.Logger;

public class TCPConn {
    private static Logger log = Logger.getLogger(TCPConn.class);

    public void TCPServer() throws Exception {
        Thread inbound = new Thread(new Runnable() {
            @Override
            public void run() {
                TCPServerUtils serverIn = new TCPServerUtils();
                try {
                    serverIn.startinbound(8888);
                } catch (Exception e) {
                    log.error("Inbound Server crash!!!", e);
                    System.exit(1);
                }
            }
        });
        log.debug("aaaa");
        inbound.start();
    }

    public void TCPClient(String host, int port) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer() {
                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ch.pipeline().addLast(new ClientIntHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}