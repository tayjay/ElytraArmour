package com.tayjay.elytraarmour.network;

import com.tayjay.elytraarmour.ElytraArmourMod;
import com.tayjay.elytraarmour.network.packet.PacketOpenGui;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by tayjay on 2016-07-24.
 */
public class NetworkHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ElytraArmourMod.modId);

    static
    {
        int desc = 0;
        INSTANCE.registerMessage(PacketOpenGui.class,PacketOpenGui.class,desc++, Side.SERVER);
    }
}
