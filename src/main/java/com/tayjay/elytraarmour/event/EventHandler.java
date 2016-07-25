package com.tayjay.elytraarmour.event;

import com.tayjay.elytraarmour.ElytraArmourMod;
import com.tayjay.elytraarmour.capabilities.ElytraDataImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.items.IItemHandler;

/**
 * Created by tayjay on 2016-07-23.
 */
public class EventHandler
{
    @SubscribeEvent
    public void attachCaps(AttachCapabilitiesEvent.Item event)
    {
        if(event.getItem() == Items.ELYTRA)
        {
            event.addCapability(ElytraDataImpl.Provider.NAME,new ElytraDataImpl.Provider());
        }
    }

    @SubscribeEvent
    public void renderPlayer(RenderPlayerEvent event)
    {
        ItemStack elytra;
        if (event.getEntityPlayer().getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null && event.getEntityPlayer().getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.ELYTRA)
        {
            elytra = event.getEntityPlayer().getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            if(elytra.hasCapability(ElytraArmourMod.ELYTRA_DATA_CAPABILITY,null))
            {
                IItemHandler armourSlot = elytra.getCapability(ElytraArmourMod.ELYTRA_DATA_CAPABILITY,null).getArmourSlot();

            }
        }
    }

    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event)
    {
        ItemStack elytra;
        if (event.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null && event.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.ELYTRA)
        {
            elytra = event.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
            if(elytra.hasCapability(ElytraArmourMod.ELYTRA_DATA_CAPABILITY,null))
            {
                ItemStack armour = elytra.getCapability(ElytraArmourMod.ELYTRA_DATA_CAPABILITY,null).getArmourSlot().getStackInSlot(0);
                if(armour==null)
                    return;
                armour.getItem().onArmorTick(event.player.worldObj,event.player,armour);

            }
        }
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event)
    {
        if(event.getItemStack().getItem() == Items.ELYTRA)
        {
            IItemHandler armourSlot = event.getItemStack().getCapability(ElytraArmourMod.ELYTRA_DATA_CAPABILITY,null).getArmourSlot();
            if(armourSlot.getStackInSlot(0)!=null)
            {
                //event.getToolTip().add("Attached armour:");
                event.getToolTip().addAll(armourSlot.getStackInSlot(0).getTooltip(event.getEntityPlayer(),false));
            }
            else
            {
                event.getToolTip().add("No armour attached.");
            }
        }
    }

    @SubscribeEvent
    public void renderGameOverlay(RenderGameOverlayEvent.Post event)
    {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution res = event.getResolution();
        int left_height = 39;
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();
        if(event.getType() != RenderGameOverlayEvent.ElementType.HEALTH)
            return;
        //event.setCanceled(true);
        int level = ForgeHooks.getTotalArmorValue(Minecraft.getMinecraft().thePlayer);
        if(level<20)
        {
            GlStateManager.enableBlend();
            int left = width / 2 - 91;
            int top = height - left_height-10;

            if(Minecraft.getMinecraft().thePlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST)!=null &&
                    Minecraft.getMinecraft().thePlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.ELYTRA &&
                    Minecraft.getMinecraft().thePlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getCapability(ElytraArmourMod.ELYTRA_DATA_CAPABILITY,null).getArmourSlot().getStackInSlot(0)!=null)
                level+=((ItemArmor)Minecraft.getMinecraft().thePlayer.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getCapability(ElytraArmourMod.ELYTRA_DATA_CAPABILITY,null).getArmourSlot().getStackInSlot(0).getItem()).damageReduceAmount;
            for (int i = 1; level > 0 && i < 20; i += 2)
            {
                if (i < level)
                {
                    Gui.drawModalRectWithCustomSizedTexture(left, top, 34, 9, 9, 9,256,256);
                }
                else if (i == level)
                {
                    Gui.drawModalRectWithCustomSizedTexture(left, top, 25, 9, 9, 9,256,256);
                }
                else if (i > level)
                {
                    Gui.drawModalRectWithCustomSizedTexture(left, top, 16, 9, 9, 9,256,256);
                }
                left += 8;
            }
            left_height += 10;

            GlStateManager.disableBlend();
        }

    }

    @SubscribeEvent
    public void playerDamaged(LivingHurtEvent event)
    {
        if(event.getEntityLiving() instanceof EntityPlayer)
        {
            ItemStack elytra;
            if (event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null && event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.ELYTRA)
            {
                elytra = event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.CHEST);
                if(elytra.hasCapability(ElytraArmourMod.ELYTRA_DATA_CAPABILITY,null))
                {
                    ItemStack armourStack = elytra.getCapability(ElytraArmourMod.ELYTRA_DATA_CAPABILITY,null).getArmourSlot().getStackInSlot(0);
                    if(armourStack!=null)
                    {
                        ItemArmor armour = (ItemArmor) armourStack.getItem();
                        event.setAmount(ISpecialArmor.ArmorProperties.applyArmor(event.getEntityLiving(),new ItemStack[]{armourStack},event.getSource(),event.getAmount()));
                        //event.setAmount(applyArmorCalculations(event.getSource(),event.getAmount(), (EntityPlayer) event.getEntityLiving(),armourStack));
                    }
                }
            }
        }
    }

    /**
     * Reduces damage, depending on armor
     * From EntityLivingBase
     */
    protected float applyArmorCalculations(DamageSource source, float damage,EntityPlayer player, ItemStack elytraArmour)
    {
        if (!source.isUnblockable())
        {
            elytraArmour.damageItem((int) damage,player);
            damage = CombatRules.getDamageAfterAbsorb(damage, ((ItemArmor) elytraArmour.getItem()).damageReduceAmount, ((ItemArmor) elytraArmour.getItem()).toughness);
        }

        return damage;
    }
}
