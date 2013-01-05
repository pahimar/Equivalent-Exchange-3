package com.pahimar.ee3.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

/**
 * BlockEE
 * 
 * Parent block class for Equivalent Exchange blocks
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public abstract class BlockEE extends BlockContainer {

    public BlockEE(int id, Material material) {

        super(id, material);
    }

}
