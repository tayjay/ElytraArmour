package com.tayjay.elytraarmour.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Created by tayjay on 2016-07-24.
 */
public class SlotElytraArmour extends SlotItemHandler
{
    InventoryElytraArmour invAugments;
    public SlotElytraArmour(IItemHandler itemHandler, int index, int xPosition, int yPosition)
    {
        super(itemHandler, index, xPosition, yPosition);
        invAugments = (InventoryElytraArmour)itemHandler;
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() instanceof ItemArmor && ((ItemArmor)stack.getItem()).armorType == EntityEquipmentSlot.CHEST;
    }

    @Override
    public boolean canTakeStack(EntityPlayer playerIn)
    {
        return true;
    }

    @Override
    public int getItemStackLimit(ItemStack stack)
    {
        return 1;
    }
}
