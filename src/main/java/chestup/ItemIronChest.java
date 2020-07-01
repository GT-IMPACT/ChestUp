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

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class ItemIronChest extends ItemBlock {

    public ItemIronChest(Block block) {
        super(block);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int i) {
        return IronChestType.validateMeta(i);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return "tile.chestup:" + IronChestType.values()[itemstack.getItemDamage()].name();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addInformation(ItemStack item, EntityPlayer par2, List aList, boolean b) {
        super.addInformation(item, par2, aList, b);
        switch (item.getItemDamage()) {
            case 0:
                aList.add("Size: " + EnumChatFormatting.YELLOW + "45");
                break;
            case 1:
                aList.add("Size: " + EnumChatFormatting.YELLOW + "63");
                break;
            case 2:
                aList.add("Size: " + EnumChatFormatting.YELLOW + "81");
                break;
            case 3:
                aList.add("Size: " + EnumChatFormatting.YELLOW + "99");
                break;
            case 4:
                aList.add("Size: " + EnumChatFormatting.YELLOW + "117");
                break;
            case 5:
                aList.add("Size: " + EnumChatFormatting.YELLOW + "135");
                break;
            case 6:
                aList.add("Size: " + EnumChatFormatting.YELLOW + "153");
                break;
            case 7:
                aList.add("Size: " + EnumChatFormatting.YELLOW + "171");
                break;
            case 8:
                aList.add("Size: " + EnumChatFormatting.YELLOW + "189");
                break;
            case 9:
                aList.add("Size: " + EnumChatFormatting.YELLOW + "207");
                break;
        }
        aList.add("Author: " + EnumChatFormatting.YELLOW + "4gname");
    }
}
