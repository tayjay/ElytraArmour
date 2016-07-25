package com.tayjay.elytraarmour.capabilities;

import com.tayjay.elytraarmour.ElytraArmourMod;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

/**
 * Created by tayjay on 2016-07-23.
 */
public class ElytraDataImpl
{
    public static void init()
    {
        CapabilityManager.INSTANCE.register(IElytraDataProvider.class,
                new Capability.IStorage<IElytraDataProvider>()
                {
                    @Override
                    public NBTBase writeNBT(Capability<IElytraDataProvider> capability, IElytraDataProvider instance, EnumFacing side)
                    {
                        return null;
                    }

                    @Override
                    public void readNBT(Capability<IElytraDataProvider> capability, IElytraDataProvider instance, EnumFacing side, NBTBase nbt)
                    {

                    }
                },DefaultImpl.class);
    }

    private static class DefaultImpl implements IElytraDataProvider
    {
        private IItemHandler armourSlot = new ItemStackHandler(1);
        private final String TAG_NAME = "elytra_armour";

        @Override
        public IItemHandler getArmourSlot()
        {
            return armourSlot;
        }

        private NBTTagCompound writeNBT()
        {
            NBTTagCompound tag = new NBTTagCompound();
            NBTBase inv = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().writeNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,armourSlot,null);
            tag.setTag(TAG_NAME,inv);
            return tag;
        }

        @Override
        public NBTTagCompound serializeNBT()
        {
            return writeNBT();
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt)
        {
            if(nbt.hasKey(TAG_NAME))
            {
                IItemHandler inv = new ItemStackHandler(1);
                CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().readNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,inv,null,nbt.getTag(TAG_NAME));
                armourSlot = inv;
            }
        }

        @Override
        public void sync(EntityPlayerMP playerMP)
        {

        }
    }

    public static class Provider implements ICapabilitySerializable<NBTTagCompound>
    {
        public static final ResourceLocation NAME = new ResourceLocation("elytraArmour","elytra_armour");
        private final IElytraDataProvider cap = new DefaultImpl();

        @Override
        public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
        {
            return capability == ElytraArmourMod.ELYTRA_DATA_CAPABILITY;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
        {
            if(capability == ElytraArmourMod.ELYTRA_DATA_CAPABILITY)
                return ElytraArmourMod.ELYTRA_DATA_CAPABILITY.cast(cap);
            return null;
        }

        @Override
        public NBTTagCompound serializeNBT()
        {
            return cap.serializeNBT();
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt)
        {
            cap.deserializeNBT(nbt);
        }
    }
    private ElytraDataImpl(){}
}
