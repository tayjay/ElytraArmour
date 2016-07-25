package com.tayjay.elytraarmour;

import com.tayjay.elytraarmour.capabilities.ElytraDataImpl;
import com.tayjay.elytraarmour.capabilities.IElytraDataProvider;
import com.tayjay.elytraarmour.event.EventHandler;
import com.tayjay.elytraarmour.client.handler.GuiHandler;
import com.tayjay.elytraarmour.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by tayjay on 2016-07-23.
 */
@Mod(modid = ElytraArmourMod.modId,name = ElytraArmourMod.name,version = ElytraArmourMod.version)
public class ElytraArmourMod
{
    public static final String modId = "elytraArmour";
    public static final String name = "ElytraArmour";
    public static final String version = "0.0.0.0";

    @Mod.Instance(ElytraArmourMod.modId)
    public static ElytraArmourMod instance;

    @SidedProxy(serverSide = "com.tayjay.elytraarmour.proxy.CommonProxy",clientSide = "com.tayjay.elytraarmour.proxy.ClientProxy")
    public static CommonProxy proxy;


    @CapabilityInject(IElytraDataProvider.class)
    public static final Capability<IElytraDataProvider> ELYTRA_DATA_CAPABILITY = null;

    @Mod.EventHandler
    public void priInit(FMLPreInitializationEvent event)
    {
        ElytraDataImpl.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(instance,new GuiHandler());

        proxy.initRendering();
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
}
