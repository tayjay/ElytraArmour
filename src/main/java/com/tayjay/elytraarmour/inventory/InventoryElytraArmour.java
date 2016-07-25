package com.tayjay.elytraarmour.inventory;

import com.tayjay.elytraarmour.capabilities.IElytraDataProvider;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;

/**
 * Created by tayjay on 2016-07-24.
 */
public class InventoryElytraArmour implements IItemHandlerModifiable
{
    public final ItemStack ELYTRA;
    private final IItemHandlerModifiable compose;

    public InventoryElytraArmour(IElytraDataProvider provider, ItemStack stack)
    {
        ELYTRA = stack;
        this.compose = (IItemHandlerModifiable)provider.getArmourSlot();
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack)
    {
        compose.setStackInSlot(slot,stack);
    }



    @Override
    public int getSlots()
    {
        return compose.getSlots();
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        try
        {
            return compose.getStackInSlot(slot);
        }catch (Exception e)
        {

        }
        return null;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
    {
        if(!(stack.getItem() instanceof ItemArmor)&& ((ItemArmor)stack.getItem()).armorType == EntityEquipmentSlot.CHEST)
            return stack; //Only armour allowed
        else
            return compose.insertItem(slot,stack,simulate);
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate)
    {
        return compose.extractItem(slot,amount,simulate);
    }
}
