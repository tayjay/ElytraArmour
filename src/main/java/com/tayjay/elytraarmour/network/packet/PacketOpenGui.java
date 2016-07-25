package com.tayjay.elytraarmour.network.packet;

import com.tayjay.elytraarmour.ElytraArmourMod;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by tayjay on 2016-07-24.
 */
public class PacketOpenGui extends PacketRunnable<PacketOpenGui>
{
    private int guiId;

    public PacketOpenGui(){}

    public PacketOpenGui(int guiId)
    {
        this.guiId = guiId;
    }

    @Override
    public Runnable getServerRunnable(final PacketOpenGui message, final MessageContext ctx)
    {
        return new Runnable()
        {
            @Override
            public void run()
            {
                ctx.getServerHandler().playerEntity.openGui(ElytraArmourMod.instance,message.guiId,ctx.getServerHandler().playerEntity.worldObj,0,0,0);
            }
        };
    }

    @Override
    public Runnable getClientRunnable(PacketOpenGui message, MessageContext ctx)
    {
        return null;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.guiId = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.guiId);
    }
}
