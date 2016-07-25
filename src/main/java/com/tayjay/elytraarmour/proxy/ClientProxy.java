package com.tayjay.elytraarmour.proxy;

import com.tayjay.elytraarmour.client.handler.KeyInputHandler;
import com.tayjay.elytraarmour.client.render.LayerElytraArmour;
import com.tayjay.elytraarmour.client.settings.Keybindings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import java.util.Map;

/**
 * Created by tayjay on 2016-07-24.
 */
public class ClientProxy extends CommonProxy
{
    public void initRendering()
    {
        Map<String,RenderPlayer> skinMap = Minecraft.getMinecraft().getRenderManager().getSkinMap();
        RenderPlayer render;
        //Get Steve Render
        render = skinMap.get("default");
        render.addLayer(new LayerElytraArmour(render));

        //Get Alex Render
        render = skinMap.get("slim");
        render.addLayer(new LayerElytraArmour(render));
    }

    public void init(){registerKeyBindings();}

    public void registerKeyBindings()
    {
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
        for(Keybindings key : Keybindings.values())
        {
            ClientRegistry.registerKeyBinding(key.getKeybind());
        }
    }
}
