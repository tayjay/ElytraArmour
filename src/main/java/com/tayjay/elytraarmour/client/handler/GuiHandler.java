package com.tayjay.elytraarmour.client.handler;

import com.tayjay.elytraarmour.ElytraArmourMod;
import com.tayjay.elytraarmour.client.gui.GuiElytraArmour;
import com.tayjay.elytraarmour.inventory.ContainerElytraArmour;
import com.tayjay.elytraarmour.inventory.InventoryElytraArmour;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by tayjay on 2016-07-24.
 */
public class GuiHandler implements IGuiHandler
{
    public enum GuiIDs{
        ELYTRA_ARMOUR
    }
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (GuiIDs.values()[ID])
        {
            case ELYTRA_ARMOUR:
                if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.ELYTRA)
                    return new ContainerElytraArmour(player.inventory,new InventoryElytraArmour(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getCapability(ElytraArmourMod.ELYTRA_DATA_CAPABILITY,null),player.getItemStackFromSlot(EntityEquipmentSlot.CHEST)));
                return null;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (GuiIDs.values()[ID])
        {
            case ELYTRA_ARMOUR:
                if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.ELYTRA)
                    return new GuiElytraArmour(player.inventory, new InventoryElytraArmour(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getCapability(ElytraArmourMod.ELYTRA_DATA_CAPABILITY,null),player.getItemStackFromSlot(EntityEquipmentSlot.CHEST)));
                Minecraft.getMinecraft().thePlayer.addChatMessage(new TextComponentString("Not Wearing Elytra."));
                return null;
        }
        return null;
    }
}
