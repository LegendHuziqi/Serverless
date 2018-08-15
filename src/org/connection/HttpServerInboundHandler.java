package org.connection;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import org.apache.log4j.Logger;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

//@ChannelHandler.Sharable
public class HttpServerInboundHandler extends ChannelInboundHandlerAdapter {

    private static Logger log = Logger.getLogger(HttpServerInboundHandler.class);

    private HttpRequest request;
    private String uri ="";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        try {
            FullHttpRequest fhr = (FullHttpRequest) msg;

            ByteBuf buf = fhr.content();
            String message = buf.toString(io.netty.util.CharsetUtil.UTF_8);
            buf.release();
            String tt = fhr.uri() +message ;
            log.debug(tt);
            log.error("sadsadsadsadsad");
            System.out.println(fhr.uri());
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                    OK, Unpooled.wrappedBuffer(tt.getBytes("UTF-8")));
            response.headers().set(CONTENT_TYPE, "text/plain");
            response.headers().setInt(CONTENT_LENGTH,
                    response.content().readableBytes());
            if (HttpHeaderUtil.isKeepAlive(fhr)) {
                response.headers().set(CONNECTION, KEEP_ALIVE);
            }
            ctx.write(response);
            ctx.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
        log.debug("--------------------------------------");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error(cause.getMessage());
        ctx.close();
    }

}