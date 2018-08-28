package org.connection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import org.apache.log4j.Logger;
import org.reflaction.clientReflact;
import org.reflaction.slaveReflact;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

//@ChannelHandler.Sharable
public class HttpSlaveInboundHandler extends ChannelInboundHandlerAdapter {

    private static Logger log = Logger.getLogger(HttpSlaveInboundHandler.class);

    private HttpRequest request;
    private String uri ="";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        try {
            log.info("HttpServerInboundHandler.channelRead");
            FullHttpRequest fhr = (FullHttpRequest) msg;
            ByteBuf buf = fhr.content();
            String message = buf.toString(io.netty.util.CharsetUtil.UTF_8);
            buf.release();
            JSONObject jsonObject = JSON.parseObject(message);
            slaveReflact rs=new slaveReflact();
            System.out.println(jsonObject.getString("service"));
            System.out.println(jsonObject.getString("args"));
            String res = rs.reflact(jsonObject.getString("service"),jsonObject.getString("args"));
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                    OK, Unpooled.wrappedBuffer(res.getBytes("UTF-8")));
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