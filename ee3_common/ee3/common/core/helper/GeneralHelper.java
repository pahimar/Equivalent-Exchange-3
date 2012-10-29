package ee3.common.core.helper;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.EntityBlaze;
import net.minecraft.src.EntityCaveSpider;
import net.minecraft.src.EntityCreeper;
import net.minecraft.src.EntityDragon;
import net.minecraft.src.EntityEnderman;
import net.minecraft.src.EntityGhast;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMagmaCube;
import net.minecraft.src.EntityPigZombie;
import net.minecraft.src.EntitySilverfish;
import net.minecraft.src.EntitySkeleton;
import net.minecraft.src.EntitySlime;
import net.minecraft.src.EntitySpider;
import net.minecraft.src.EntityWitch;
import net.minecraft.src.EntityWither;
import net.minecraft.src.EntityZombie;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

/**
 * GeneralHelper
 * 
 * General helper methods for EE3
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class GeneralHelper {

    public static ItemStack convertObjectToItemStack(Object obj) {
        if (obj instanceof Item) {
            return new ItemStack((Item) obj);
        } else if (obj instanceof Block) {
            return new ItemStack((Block) obj);
        } else if (obj instanceof ItemStack) {
            return (ItemStack) obj;
        } else {
            return null;
        }
    }

    public static Object[] convertSingleStackToPluralStacks(ItemStack stack) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        ItemStack currentStack;

        for (int i = 0; i < stack.stackSize; i++) {
            currentStack = new ItemStack(stack.itemID, 1, stack.getItemDamage());
            list.add(currentStack);
        }

        return list.toArray();
    }
    
    public static boolean isHostileEntity(EntityLiving entity) {
        if ((entity instanceof EntityBlaze) || 
            (entity instanceof EntityCaveSpider) ||
            (entity instanceof EntityCreeper) ||
            (entity instanceof EntityDragon) ||
            (entity instanceof EntityEnderman) ||
            (entity instanceof EntityGhast) ||
            (entity instanceof EntityMagmaCube) ||
            (entity instanceof EntityPigZombie) ||
            (entity instanceof EntitySilverfish) ||
            (entity instanceof EntitySkeleton) ||
            (entity instanceof EntitySlime) ||
            (entity instanceof EntitySpider) ||
            (entity instanceof EntityWitch) ||
            (entity instanceof EntityWither) ||
            (entity instanceof EntityZombie)) {
            return true;
        }
        else {
            return false;
        }
    }

}
