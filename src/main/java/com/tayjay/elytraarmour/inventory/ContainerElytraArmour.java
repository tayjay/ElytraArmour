package com.tayjay.elytraarmour.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

/**
 * Created by tayjay on 2016-07-24.
 */
public class ContainerElytraArmour extends Container
{
    public InventoryElytraArmour inventoryElytraArmour;

    public ContainerElytraArmour(InventoryPlayer invPlayer, InventoryElytraArmour invEA)
    {
        this.inventoryElytraArmour = invEA;
        this.addSlotToContainer(new SlotElytraArmour(inventoryElytraArmour,0,0,0));

        //Player Inventory
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 9; j++)
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 19 + j * 18, 100 + i * 18));

        //Player Hotbar
        for (int i = 0; i < 9; i++)
            this.addSlotToContainer(new Slot(invPlayer, i, 19 + i * 18, 158));
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }

    @Nullable
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        Slot slot = this.getSlot(index);

        if(slot==null || !slot.getHasStack())
            return null;

        ItemStack stack = slot.getStack();
        ItemStack newStack = stack.copy();

        if(index<inventoryElytraArmour.getSlots())
        {
            if(!this.mergeItemStack(stack, inventoryElytraArmour.getSlots(),this.inventorySlots.size(),true))
                return null;
            slot.onSlotChanged();
        }
        else if(!this.mergeItemStack(stack,0,inventoryElytraArmour.getSlots(),true))
            return null;

        if(stack.stackSize == 0)
            slot.putStack(null);
        else
            slot.onSlotChanged();

        slot.onPickupFromSlot(playerIn,newStack);
        return newStack;
    }
}
