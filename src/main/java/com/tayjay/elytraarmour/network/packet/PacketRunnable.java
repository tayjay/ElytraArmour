package com.tayjay.elytraarmour.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by tayjay on 2016-07-24.
 */
public abstract class PacketRunnable<REQ extends IMessage> implements IMessage, IMessageHandler<REQ,IMessage>
{
    public PacketRunnable(){}

    @Override
    public REQ onMessage(final REQ message, final MessageContext ctx)
    {
        if(ctx.side == Side.CLIENT)
            Minecraft.getMinecraft().addScheduledTask(getClientRunnable(message,ctx));
        else
            ctx.getServerHandler().playerEntity.getServerWorld().addScheduledTask(getServerRunnable(message,ctx));
        return null;
    }

    public abstract Runnable getServerRunnable(final REQ message,final MessageContext ctx);

    public abstract Runnable getClientRunnable(final REQ message,final MessageContext ctx);
}
