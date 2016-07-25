package com.tayjay.elytraarmour.client.gui;

import com.tayjay.elytraarmour.ElytraArmourMod;
import com.tayjay.elytraarmour.inventory.ContainerElytraArmour;
import com.tayjay.elytraarmour.inventory.InventoryElytraArmour;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by tayjay on 2016-07-24.
 */
public class GuiElytraArmour extends GuiContainer
{
    private static final ResourceLocation texture = new ResourceLocation(ElytraArmourMod.modId.toLowerCase(),"textures/gui/elytraArmour.png");


    public GuiElytraArmour(InventoryPlayer inventoryPlayer, InventoryElytraArmour inventoryElytraArmour)
    {
        super(new ContainerElytraArmour(inventoryPlayer,inventoryElytraArmour));
        this.xSize = 196;
        this.ySize = 177;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GL11.glColor4f(1f,1f,1f,1f);

        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        this.drawTexturedModalRect((width-xSize)/2,(height-ySize)/2,0,0,xSize,ySize);


        ContainerElytraArmour container = (ContainerElytraArmour)inventorySlots;
        int posX = guiLeft+16;
        int posY = guiTop+17;
        RenderHelper.enableGUIStandardItemLighting();
        itemRender.renderItemIntoGUI(container.inventoryElytraArmour.ELYTRA, posX,posY);
        RenderHelper.disableStandardItemLighting();
        if(mouseX > posX && mouseX <=posX+16 && mouseY > posY && mouseY <=posY+16)
        {
            drawHoveringText(container.inventoryElytraArmour.ELYTRA.getTooltip(Minecraft.getMinecraft().thePlayer,false),mouseX,mouseY);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        fontRendererObj.drawString("Elytra Armour",5,5,0);
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);


    }
}
