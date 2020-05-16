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
package chestup;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum IronChestType {
    WRIRON(45, 9, 5, true, "Wrougth Iron Chest", "0.png", 0, Arrays.asList("plateWrougthIron"), TileEntityIronChest.class),
    STEEL(63, 9, 7, true, "Steel Chest", "1.png", 1, Arrays.asList("plateSteel"), TEsteel.class),
    ALUMINIUM(81, 9, 9, true, "Aluminium Chest", "2.png", 2, Arrays.asList("plateAluminium"), TEaluminium.class),
    HSLA(99, 11, 9, true, "HSLA Chest", "3.png", 3, Arrays.asList("plateHSLA"), TEhsla.class),
    TITANIUM(117, 13, 9, true, "Titanium Chest", "4.png", 4, Arrays.asList("plateTitanium"), TEtitanium.class),
    WOLFRAM(135, 15, 9, true, "Tungsten Steel Chest", "5.png", 5, Arrays.asList("plateTungstenSteel"), TEwolfram.class),
    CHROME(153, 17, 9, true, "Chrome Chest", "6.png", 6, Arrays.asList("plateChrome"), TEchrome.class),
    IRIDIUM(171, 19, 9, true, "Iridium Chest", "7.png", 7, Arrays.asList("plateIridium"), TEiridium.class),
    OSMIUM(189, 21, 9, true, "Osmium Chest", "8.png", 8, Arrays.asList("plateOsmium"), TEosmium.class),
    NEUTRONIUM(207, 23, 9, true, "Neutronium Chest", "9.png", 9, Arrays.asList("plateNeutronium"), TEneutronium.class),
    WOOD(0, 0, false, "", "", -1, Arrays.asList("plankWood"), null);
    private static String[] sideNames = {"top", "front", "side"};
    private static int[] sideMapping = {0, 0, 2, 1, 2, 2, 2};
    public String friendlyName;
    public Class<? extends TileEntityIronChest> clazz;
    int size;
    private int rowLength, rowY;
    private boolean tieredChest;
    private String modelTexture;
    private int textureRow;
    private String[] recipes;
    private ArrayList<String> matList;
    private Item itemFilter;
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    IronChestType(int size, int rowX, boolean tieredChest, String friendlyName, String modelTexture, int textureRow, List<String> mats,
                  Class<? extends TileEntityIronChest> clazz) {
        this(size, rowX, tieredChest, friendlyName, modelTexture, textureRow, mats, clazz, (Item) null);
    }

    IronChestType(int size, int rowX, int rowY, boolean tieredChest, String friendlyName, String modelTexture, int textureRow, List<String> mats,
                  Class<? extends TileEntityIronChest> clazz, String... recipes) {
        this(size, rowX, rowY, tieredChest, friendlyName, modelTexture, textureRow, mats, clazz, (Item) null);
    }

    IronChestType(int size, int rowLength, boolean tieredChest, String friendlyName, String modelTexture, int textureRow, List<String> mats,
                  Class<? extends TileEntityIronChest> clazz, Item itemFilter) {
        this.size = size;
        this.rowLength = rowLength;
        this.tieredChest = tieredChest;
        this.friendlyName = friendlyName;
        this.modelTexture = modelTexture;
        this.textureRow = textureRow;
        this.clazz = clazz;
        this.itemFilter = itemFilter;
        this.matList = new ArrayList<String>();
        matList.addAll(mats);
    }

    IronChestType(int size, int rowX, int rowY, boolean tieredChest, String friendlyName, String modelTexture, int textureRow, List<String> mats,
                  Class<? extends TileEntityIronChest> clazz, Item itemFilter) {
        this.size = size;
        this.rowLength = rowX;
        this.rowY = rowY;
        this.tieredChest = tieredChest;
        this.friendlyName = friendlyName;
        this.modelTexture = modelTexture;
        this.textureRow = textureRow;
        this.clazz = clazz;
        this.itemFilter = itemFilter;
        this.recipes = recipes;
        this.matList = new ArrayList<String>();
        matList.addAll(mats);
    }

    public static TileEntityIronChest makeEntity(int metadata) {
        // Compatibility
        int chesttype = validateMeta(metadata);
        if (chesttype == metadata) {
            try {
                TileEntityIronChest te = values()[chesttype].clazz.newInstance();
                return te;
            } catch (InstantiationException e) {
                // unpossible
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // unpossible
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void registerBlocksAndRecipes(BlockIronChest blockResult) {
        Object previous = "chestWood";
        for (IronChestType typ : values()) {
            ItemStack chest = new ItemStack(blockResult, 1, typ.ordinal());
            if (typ.isValidForCreativeMode()) GameRegistry.registerCustomItemStack(typ.friendlyName, chest);
            if (typ.tieredChest) previous = chest;
        }
    }

    public static int validateMeta(int i) {
        if (i < values().length && values()[i].size > 0) {
            return i;
        } else {
            return 0;
        }
    }

    public String getModelTexture() {
        return modelTexture;
    }

    public int getTextureRow() {
        return textureRow;
    }

    public int getRowCount() {
        return size / rowLength;
    }

    public int getRowLength() {
        return rowLength;
    }

    public int getRowY() {
        return rowY;
    }

    public boolean isTransparent() {
        return this == NEUTRONIUM;
    }

    public List<String> getMatList() {
        return matList;
    }

    public boolean isValidForCreativeMode() {
        return validateMeta(ordinal()) == ordinal();
    }

    public boolean isExplosionResistant() {
        return this == NEUTRONIUM;
    }

    @SideOnly(Side.CLIENT)
    public void makeIcons(IIconRegister par1IconRegister) {
        if (isValidForCreativeMode()) {
            icons = new IIcon[3];
            int i = 0;
            for (String s : sideNames) {
                icons[i++] = par1IconRegister.registerIcon(String.format("chestup:%s_%s", name().toLowerCase(), s));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side) {

        return icons[sideMapping[side]];
    }

    public Slot makeSlot(IInventory chestInventory, int index, int x, int y) {
        return new ValidatingSlot(chestInventory, index, x, y, this);
    }

    public boolean acceptsStack(ItemStack itemstack) {
        return itemFilter == null || itemstack == null || itemstack.getItem() == itemFilter;
    }

    public void adornItemDrop(ItemStack item) {
    }
}
