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

import static net.minecraft.util.StatCollector.translateToLocal;

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
        int[] metaSize = {45, 63, 81, 99, 117, 135, 153, 171, 189, 207};
        aList.add(translateToLocal("item.chestup.size") + ": " + EnumChatFormatting.YELLOW + metaSize[item.getItemDamage()]);
    }
}