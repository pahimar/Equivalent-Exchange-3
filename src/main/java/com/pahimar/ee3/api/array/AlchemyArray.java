package com.pahimar.ee3.api.array;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

// TODO Switch bare Strings to String constants
public class AlchemyArray implements Comparable<AlchemyArray> {

    private ResourceLocation texture;
    private String unlocalizedName;
    private String className;
    private int lightLevel;
    private int chalkPerBlockCost;

    private AlchemyArray() {
    }

    public AlchemyArray(ResourceLocation texture, String unlocalizedName) {
        this.texture = texture;
        this.unlocalizedName = unlocalizedName;
        this.chalkPerBlockCost = 1;
        this.lightLevel = 0;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public void setTexture(ResourceLocation texture) {
        this.texture = texture;
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public void setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
    }

    @SideOnly(Side.CLIENT)
    public String getDisplayName() {
        return I18n.format(unlocalizedName);
    }

    public int getChalkCostPerBlock() {
        return chalkPerBlockCost;
    }

    public void setChalkPerBlockCost(int chalkPerBlockCost) {
        this.chalkPerBlockCost = Math.abs(chalkPerBlockCost);
    }

    public int getLightLevel() {
        return lightLevel;
    }

    public String getClassName() {
        return className;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        if (nbtTagCompound != null) {

            if (nbtTagCompound.hasKey("textureDomain") && nbtTagCompound.hasKey("texturePath")) {
                this.texture = new ResourceLocation(nbtTagCompound.getString("textureDomain"), nbtTagCompound.getString("texturePath"));
            }
            else {
                this.texture = new ResourceLocation("");
            }

            if (nbtTagCompound.hasKey("unlocalizedName")) {
                this.unlocalizedName = nbtTagCompound.getString("unlocalizedName");
            }
            else {
                this.unlocalizedName = "";
            }

            if (nbtTagCompound.hasKey("className")) {
                this.className = nbtTagCompound.getString("className");
            }
            else {
                this.className = "";
            }

            if (nbtTagCompound.hasKey("lightLevel")) {
                this.lightLevel = nbtTagCompound.getInteger("lightLevel");
            }
            else {
                this.lightLevel = 0;
            }
        }
        else {
            this.texture = new ResourceLocation("");
            this.unlocalizedName = "";
            this.className = "";
            this.lightLevel = 0;
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setString("textureDomain", texture.getResourceDomain());
        nbtTagCompound.setString("texturePath", texture.getResourcePath());
        nbtTagCompound.setString("unlocalizedName", unlocalizedName);
        nbtTagCompound.setString("className", this.getClass().getCanonicalName());
        nbtTagCompound.setInteger("lightLevel", lightLevel);
    }

    public static AlchemyArray readArrayFromNBT(NBTTagCompound nbtTagCompound) {

        AlchemyArray alchemyArray = new AlchemyArray();
        alchemyArray.readFromNBT(nbtTagCompound);
        return alchemyArray;
    }

    public void onArrayPlacedBy(World world, BlockPos eventPos, BlockPos arrayPos, EntityLivingBase entityLiving, ItemStack itemStack) {
    }

    public void onArrayActivated(World world, BlockPos eventPos, BlockPos arrayPos, IBlockState arrayState, EntityPlayer entityPlayer, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
    }

    public void onArrayClicked(World world, BlockPos eventPos, BlockPos arrayPos, EntityPlayer entityPlayer) {
    }

    public void onArrayDestroyedByExplosion(World world, BlockPos eventPos, BlockPos arrayPos, Explosion explosion) {
    }

    public void onArrayDestroyedByPlayer(World world, BlockPos eventPos, BlockPos arrayPos, IBlockState arrayState) {
    }

    public void onEntityCollidedWithArray(World world, BlockPos eventPos, BlockPos arrayPos, IBlockState arrayState, Entity entity) {
    }

    public void onArrayFallenUpon(World world, BlockPos eventPos, BlockPos arrayPos, Entity entity, float fallDistance) {
    }

    public void onUpdate(World world, BlockPos arrayPos, int tickCount) {
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof AlchemyArray && this.compareTo((AlchemyArray) object) == 0;
    }

    @Override
    public int compareTo(AlchemyArray alchemyArray) {

        if (this.texture.getResourceDomain().equalsIgnoreCase(alchemyArray.getTexture().getResourceDomain())) {

            if (this.texture.getResourcePath().equalsIgnoreCase(alchemyArray.getTexture().getResourcePath())) {
                return 0;
            }
            else {
                return this.texture.getResourcePath().compareToIgnoreCase(alchemyArray.getTexture().getResourcePath());
            }
        }
        else {
            return this.texture.getResourceDomain().compareToIgnoreCase(alchemyArray.getTexture().getResourceDomain());
        }
    }
}
