package com.infamous.minibloom.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

@SuppressWarnings("EntityConstructor")
public class AbstractDwarfCowEntity extends CowEntity {

    public AbstractDwarfCowEntity(EntityType<? extends CowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public boolean isChild() {
        return true;
    }

    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        this.setChild(true);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public int getGrowingAge() {
        if(!this.world.isRemote){
            return 0;
        }
        else{
            return super.getGrowingAge();
        }
    }

    @Override
    public boolean canBreed() {
        return false;
    }

    @Override
    public boolean canMateWith(AnimalEntity otherAnimal) {
        return false;
    }

    @Override
    public void ageUp(int growthSeconds, boolean updateForcedAge) {
        // NO-OP
    }

    @Override
    public void addGrowth(int growth) {
        // NO-OP
    }

    @Override
    public void func_234177_a_(ServerWorld world, AnimalEntity animalEntity) {
        // NO-OP
    }

    @Override
    public boolean isInLove() {
        return false;
    }

    @Override
    public void setInLove(@Nullable PlayerEntity player) {
        // NO-OP
    }

    @Override
    public void setInLove(int ticks) {
        // NO-OP
    }

    @Override
    public ActionResultType func_230254_b_(PlayerEntity playerEntity, Hand hand) {
        return ActionResultType.func_233537_a_(this.world.isRemote);
    }
}
