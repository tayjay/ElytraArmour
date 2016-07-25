package com.tayjay.elytraarmour.capabilities;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;

/**
 * Created by tayjay on 2016-07-23.
 */
public interface IElytraDataProvider extends INBTSerializable<NBTTagCompound>
{
    IItemHandler getArmourSlot();

    void sync(EntityPlayerMP playerMP);
}
