/*******************************************************************************
 * Copyright (c) 2012 cpw.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors:
 *     cpw - initial API and implementation
 ******************************************************************************/
package chestup.client;

import chestup.ContainerIronChest;
import chestup.IronChestType;
import chestup.TileEntityIronChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GUIChest extends GuiContainer {
    private GUI type;
    private String mName;

    private GUIChest(GUI type, IInventory player, IInventory chest) {
        super(type.makeContainer(player, chest));
        this.type = type;
        this.xSize = type.xSize;
        this.ySize = type.ySize;
        this.allowUserInput = false;
    }

    public int getRowLength() {
        return type.mainType.getRowLength();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        switch (this.type) {
            case WRIRON:
                this.mName = "Wrought Iron Chest";
                break;
            case STEEL:
                this.mName = "Steel Chest";
                break;
            case ALUMINIUM:
                this.mName = "Aluminium Chest";
                break;
            case HSLA:
                this.mName = "HSLA Chest";
                break;
            case TITANIUM:
                this.mName = "Titanium Chest";
                break;
            case WOLFRAM:
                this.mName = "Tungsten Steel Chest";
                break;
            case CHROME:
                this.mName = "Chrome Chest";
                break;
            case IRIDIUM:
                this.mName = "Iridium Chest";
                break;
            case OSMIUM:
                this.mName = "Osmium Chest";
                break;
            case NEUTRONIUM:
                this.mName = "Neutronium Chest";
        }
        fontRendererObj.drawString(this.mName, 8, 7, 0);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
//        if (type == GUI.WRIRON || type == GUI.STEEL || type == GUI.ALUMINIUM || type == GUI.HSLA) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.getTextureManager().bindTexture(type.guiResourceList.location);
            Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(guiLeft, guiTop, 0, 0.0, 0.0);
            tessellator.addVertexWithUV(guiLeft, guiTop + 500, 0, 0.0, 1.0);
            tessellator.addVertexWithUV(guiLeft + 500, guiTop + 500, 0, 1.0, 1.0);
            tessellator.addVertexWithUV(guiLeft + 500, guiTop, 0, 1.0, 0.0);
            tessellator.draw();
//        } else {
//            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//            // new "bind tex"
//            this.mc.getTextureManager().bindTexture(type.guiResourceList.location);
//            int x = (width - xSize) / 2;
//            int y = (height - ySize) / 2;
//            drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
//        }
    }

    public enum ResourceList {
        WRIRON(new ResourceLocation("chestup", "textures/gui/wriron.png")),
        STEEL(new ResourceLocation("chestup", "textures/gui/steel.png")),
        ALUMINIUM(new ResourceLocation("chestup", "textures/gui/aluminium.png")),
        HSLA(new ResourceLocation("chestup", "textures/gui/hsla.png")),
        TITANIUM(new ResourceLocation("chestup", "textures/gui/titanium.png")),
        WOLFRAM(new ResourceLocation("chestup", "textures/gui/wolfram.png")),
        CHROME(new ResourceLocation("chestup", "textures/gui/chrome.png")),
        IRIDIUM(new ResourceLocation("chestup", "textures/gui/iridium.png")),
        OSMIUM(new ResourceLocation("chestup", "textures/gui/osmium.png")),
        NEUTRONIUM(new ResourceLocation("chestup", "textures/gui/neutronium.png"));


        public final ResourceLocation location;

        private ResourceList(ResourceLocation loc) {
            this.location = loc;
        }
    }

    public enum GUI {
        WRIRON(177, 190, ResourceList.WRIRON, IronChestType.WRIRON),
        STEEL(177, 256, ResourceList.STEEL, IronChestType.STEEL),
        ALUMINIUM(177, 312, ResourceList.ALUMINIUM, IronChestType.ALUMINIUM),
        HSLA(213, 312, ResourceList.HSLA, IronChestType.HSLA),
        TITANIUM(249, 312, ResourceList.TITANIUM, IronChestType.TITANIUM),
        WOLFRAM(285, 312, ResourceList.WOLFRAM, IronChestType.WOLFRAM),
        CHROME(321, 312, ResourceList.CHROME, IronChestType.CHROME),
        IRIDIUM(357, 312, ResourceList.IRIDIUM, IronChestType.IRIDIUM),
        OSMIUM(393, 312, ResourceList.OSMIUM, IronChestType.OSMIUM),
        NEUTRONIUM(429, 312, ResourceList.NEUTRONIUM, IronChestType.NEUTRONIUM);

        private int xSize;
        private int ySize;
        private ResourceList guiResourceList;
        private IronChestType mainType;

        private GUI(int xSize, int ySize, ResourceList guiResourceList, IronChestType mainType) {
            this.xSize = xSize;
            this.ySize = ySize;
            this.guiResourceList = guiResourceList;
            this.mainType = mainType;

        }

        public static GUIChest buildGUI(IronChestType type, IInventory playerInventory, TileEntityIronChest chestInventory) {
            return new GUIChest(values()[chestInventory.getType().ordinal()], playerInventory, chestInventory);
        }

        protected Container makeContainer(IInventory player, IInventory chest) {
            return new ContainerIronChest(player, chest, mainType, xSize, ySize);
        }
    }
}
