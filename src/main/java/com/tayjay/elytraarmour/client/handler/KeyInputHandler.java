package com.tayjay.elytraarmour.client.handler;

import com.tayjay.elytraarmour.ElytraArmourMod;
import com.tayjay.elytraarmour.client.settings.Keybindings;
import com.tayjay.elytraarmour.network.NetworkHandler;
import com.tayjay.elytraarmour.network.packet.PacketOpenGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by tayjay on 2016-07-24.
 */
public class KeyInputHandler
{
    private Keybindings getPressedKey()
    {
        for(Keybindings key : Keybindings.values())
        {
            if(key.isPressed()) return key;
        }
        return null;
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        Keybindings key = getPressedKey();
        if (key != null)
        {
            switch (key)
            {
                case OPEN_ELYTRA_ARMOUR:
                    NetworkHandler.INSTANCE.sendToServer(new PacketOpenGui(GuiHandler.GuiIDs.ELYTRA_ARMOUR.ordinal()));
                    break;

            }
        }
    }
}
