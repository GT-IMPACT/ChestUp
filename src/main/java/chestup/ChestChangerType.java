/*******************************************************************************
 * Copyright (c) 2012 cpw. All rights reserved. This program and the accompanying materials are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: cpw - initial API and implementation
 ******************************************************************************/
package chestup;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.config.Configuration;

import static chestup.IronChestType.*;

public enum ChestChangerType {
    WOODWRIRON(WOOD, WRIRON, "WOODWRIRONUpgrade", "Wood to Wrought Iron Chest Upgrade"),
    WRIRONSTEEL(WRIRON, STEEL, "WRIRONSTEELUpgrade", "Wrought Iron to Steel Chest Upgrade"),
    STEELALUMINIUM(STEEL, ALUMINIUM, "STEELALUMINIUMUpgrade", "Steel to Aluminium Chest Upgrade"),
    ALUMINIUMHSLA(ALUMINIUM, HSLA, "ALUMINIUMHSLAUpgrade", "Aluminium to HSLA Chest Upgrade"),
    HSLATITANIUM(HSLA, TITANIUM, "HSLATITANIUMUpgrade", "HSLA to Titanium Chest Upgrade"),
    TITANIUMWOLFRAM(TITANIUM, WOLFRAM, "TITANIUMWOLFRAMUpgrade", "Titanium to Tungsten Steel Chest Upgrade"),
    WOLFRAMCHROME(WOLFRAM, CHROME, "WOLFRAMCHROMEUpgrade", "Tungsten Steel to Chrome Chest Upgrade"),
    CHROMEIRIDIUM(CHROME, IRIDIUM, "CHROMEIRIDIUMUpgrade", "Chrome to Iridium Chest Upgrade"),
    IRIDIUMOSMIUM(IRIDIUM, OSMIUM, "IRIDIUMOSMIUMUpgrade", "Iridium to Osmium Chest Upgrade"),
    OSMIUMNEUTRONIUM(OSMIUM, NEUTRONIUM, "OSMIUMNEUTRONIUMUpgrade", "Osmium to Neutronium Chest Upgrade");

    public String itemName;
    public String descriptiveName;
    private IronChestType source;
    private IronChestType target;
    private ItemChestChanger item;

    private ChestChangerType(IronChestType source, IronChestType target, String itemName, String descriptiveName) {
        this.source = source;
        this.target = target;
        this.itemName = itemName;
        this.descriptiveName = descriptiveName;
    }

    public static void buildItems(Configuration cfg) {
        for (ChestChangerType type : values()) {
            type.buildItem(cfg);
        }
    }

    public boolean canUpgrade(IronChestType from) {
        return from == this.source;
    }

    public int getTarget() {
        return this.target.ordinal();
    }

    public ItemChestChanger buildItem(Configuration cfg) {
        item = new ItemChestChanger(this);
        //GameRegistry.registerItem(item, itemName);
        return item;
    }
}
