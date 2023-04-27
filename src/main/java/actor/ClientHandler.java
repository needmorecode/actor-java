package actor;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Netty客户端handler
 */
public class ClientHandler extends SimpleChannelInboundHandler<Message> {
    

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
    	ActorSystem.send(msg.getDestNode(), msg.getDestActor(), msg.getCommand(), msg.getParams());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
