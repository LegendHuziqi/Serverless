package org.connection;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.log4j.Logger;

public class TCPConn_Slave {
    private static Logger log = Logger.getLogger(TCPConn_Slave.class);

    public void TCPServer() throws Exception {
        Thread inbound = new Thread(new Runnable() {
            @Override
            public void run() {
                TCPSlaveUtils serverIn = new TCPSlaveUtils();
                try {
                    serverIn.startinbound(8888);
                } catch (Exception e) {
                    log.error("Inbound Server crash!!!", e);
                    System.exit(1);
                }
            }
        });
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