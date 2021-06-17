package com.infamous.minibloom.entities;

import com.infamous.minibloom.blocks.ModBlocks;
import com.infamous.minibloom.config.MinibloomConfig;
import com.infamous.minibloom.items.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.EnumSet;
import java.util.Random;

public class MinibloomEntity extends AbstractDwarfCowEntity {
    private static final DataParameter<Boolean> SLEEPING = EntityDataManager.createKey(MinibloomEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(MinibloomEntity.class, DataSerializers.BOOLEAN);
    private static final Ingredient BREEDING_ITEMS = Ingredient.fromItems(Items.GLISTERING_MELON_SLICE);
    private int honeySproutCooldown;
    private int fertilizeCooldown;

    public MinibloomEntity(World worldIn) {
        super(ModEntityTypes.MINIBLOOM.get(), worldIn);
    }

    public MinibloomEntity(EntityType<? extends MinibloomEntity> type, World worldIn) {
        super(type, worldIn);
        this.lookController = new MinibloomEntity.LookHelperController();
        this.moveController = new MinibloomEntity.MoveHelperController();
        this.honeySproutCooldown = MinibloomConfig.COMMON.MINIBLOOM_HONEY_SPROUT_COOLDOWN.get();
        this.fertilizeCooldown = MinibloomConfig.COMMON.MINIBLOOM_FERTILIZE_COOLDOWN.get();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MinibloomEntity.SwimGoal());
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new TemptGoal());
        this.goalSelector.addGoal(3, new MinibloomEntity.FindShelterGoal(1.25D));
        this.goalSelector.addGoal(4, new MinibloomEntity.SleepGoal());
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookAtGoal(this, BeeEntity.class, 8.0F));
        //this.goalSelector.addGoal(8, new MinibloomEntity.SitAndLookGoal());
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return CowEntity.func_234188_eI_();
    }

    public static boolean canMinibloomSpawn(EntityType<? extends AnimalEntity> entityType, IWorld world, SpawnReason reason, BlockPos blockPos, Random random) {
        return world.getBlockState(blockPos.down()).getBlock() == Blocks.GRASS_BLOCK && world.getLightSubtracted(blockPos, 0) > 8;
    }

    @Override
    public void livingTick() {
        super.livingTick();
        /*
        if (this.world.isRemote && this.isSleeping()) {
            for(int i = 0; i < 2; ++i) {
                this.world.addParticle(ModParticleTypes.SLEEP.get(), this.getPosXRandom(0.5D), this.getPosYRandom() - 0.25D, this.getPosZRandom(0.5D), (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
            }
        }

         */
        if(!this.world.isRemote){
            if (this.honeySproutCooldown <= 0) {
                BlockState buttercupBlockState = ModBlocks.HONEY_SPROUT_BLOCK.get().getDefaultState();
                BlockPos blockpos = this.getPosition();
                boolean doCreateButtercup = this.rand.nextDouble() < MinibloomConfig.COMMON.MINIBLOOM_HONEY_SPROUT_CHANCE.get();
                boolean isValidPosition = buttercupBlockState.isValidPosition(this.world, blockpos);
                boolean positionIsEmpty = this.world.isAirBlock(blockpos);
                boolean isOnGround =  this.onGround;
                boolean allowMobGriefingEvent = ForgeEventFactory.getMobGriefingEvent(this.world, this);
                boolean allowMobGriefing = this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING);
                if (doCreateButtercup && isValidPosition && positionIsEmpty && isOnGround && (allowMobGriefing || allowMobGriefingEvent)) {
                    this.world.setBlockState(this.getPosition(), buttercupBlockState, 3);
                }
                this.honeySproutCooldown = MinibloomConfig.COMMON.MINIBLOOM_HONEY_SPROUT_COOLDOWN.get() * 20;
                this.fertilizeCooldown = MinibloomConfig.COMMON.MINIBLOOM_FERTILIZE_COOLDOWN.get() * 20;
            } else{
                this.honeySproutCooldown--;
            }
            if (this.fertilizeCooldown <= 0) {
                BlockPos blockpos = this.getPosition();
                BlockState targetBlockState = this.world.getBlockState(blockpos);
                boolean doFertilize = this.rand.nextDouble() < MinibloomConfig.COMMON.MINIBLOOM_FERTILIZE_CHANCE.get();
                boolean isOnGround =  this.onGround;
                boolean allowMobGriefingEvent = ForgeEventFactory.getMobGriefingEvent(this.world, this);
                boolean allowMobGriefing = this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING);
                if (targetBlockState.getBlock() instanceof IGrowable && doFertilize && isOnGround && (allowMobGriefing || allowMobGriefingEvent)) {
                    IGrowable growable = (IGrowable)targetBlockState.getBlock();
                    if (growable.canGrow(this.world, blockpos, targetBlockState, this.world.isRemote)) {
                        if (this.world instanceof ServerWorld) {
                            if (growable.canUseBonemeal(this.world, this.world.rand, blockpos, targetBlockState)) {
                                growable.grow((ServerWorld)this.world, this.world.rand, blockpos, targetBlockState);
                            }
                        }
                    }
                }
                this.fertilizeCooldown = MinibloomConfig.COMMON.MINIBLOOM_FERTILIZE_COOLDOWN.get() * 20;
                this.honeySproutCooldown = MinibloomConfig.COMMON.MINIBLOOM_HONEY_SPROUT_COOLDOWN.get() * 20;
            } else{
                this.fertilizeCooldown--;
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.isServerWorld()){
            boolean isInWater = this.isInWater();
            if (isInWater || this.getAttackTarget() != null || this.world.isThundering()) {
                this.setSleeping(false);
            }

            if (isInWater || this.getAttackTarget() != null || this.isSleeping()) {
                this.setSitting(false);
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        boolean attacked =  super.attackEntityFrom(source, amount);
        if(attacked){
            this.setMoving();
        }
        return attacked;
    }

    @Override
    protected void registerData() {
        this.dataManager.register(SLEEPING, false);
        this.dataManager.register(SITTING, false);
        super.registerData();
    }

    @Override
    public boolean isSleeping() {
        return this.dataManager.get(SLEEPING);
    }

    public boolean isSitting(){
        return this.dataManager.get(SITTING);
    }

    private void setSleeping(boolean isSleeping) {
        this.dataManager.set(SLEEPING, isSleeping);
    }

    private void setSitting(boolean isSitting) {
        this.dataManager.set(SITTING, isSitting);
    }

    private void setMoving() {
        this.setSitting(false);
        this.setSleeping(false);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("Sleeping", this.isSleeping());
        compound.putBoolean("Sitting", this.isSitting());
        compound.putInt("HoneySproutCooldown", this.honeySproutCooldown);
        compound.putInt("FertilizeCooldown", this.fertilizeCooldown);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setSleeping(compound.getBoolean("Sleeping"));
        this.setSitting(compound.getBoolean("Sitting"));
        this.honeySproutCooldown = (compound.getInt("HoneySproutCooldown"));
        this.fertilizeCooldown = (compound.getInt("FertilizeCooldown"));
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return BREEDING_ITEMS.test(stack) || stack.getItem().isIn(ModItems.MINIBLOOM_BREEDING_ITEMS);
    }

    @Override
    //createChild
    public MinibloomEntity func_241840_a(ServerWorld serverWorld, AgeableEntity p_241840_2_) {
        return ModEntityTypes.MINIBLOOM.get().create(serverWorld);
    }

    @Override
    public ActionResultType func_230254_b_(PlayerEntity playerEntity, Hand hand) {
        ItemStack interactStack = playerEntity.getHeldItem(hand);
        if(isBreedingItem(interactStack) && !this.isSleeping()){
            this.consumeItemFromStack(playerEntity, interactStack);
            this.setSitting(true);
            this.world.setEntityState(this, (byte) 18);
            if(interactStack.getItem().getFood() != null){
                int healingAmount = interactStack.getItem().getFood().getHealing();
                this.heal(healingAmount);
            }
        }
        else if(this.isSitting()){
            this.setMoving();
        }
        return super.func_230254_b_(playerEntity, hand);
    }

    public class LookHelperController extends LookController {
        public LookHelperController() {
            super(MinibloomEntity.this);
        }

        /**
         * Updates look
         */
        public void tick() {
            if (!MinibloomEntity.this.isSleeping()) {
                super.tick();
            }

        }

        protected boolean shouldResetPitch() {
            return !MinibloomEntity.this.isCrouching();
        }
    }

    private boolean canMove() {
        return !this.isSleeping() && !this.isSitting();
    }

    class MoveHelperController extends MovementController {
        public MoveHelperController() {
            super(MinibloomEntity.this);
        }

        public void tick() {
            if (MinibloomEntity.this.canMove()) {
                super.tick();
            }

        }
    }

    class TemptGoal extends net.minecraft.entity.ai.goal.TemptGoal {

        public TemptGoal() {
            super(MinibloomEntity.this, 1.25D, BREEDING_ITEMS, false);
        }

        @Override
        protected boolean isTempting(ItemStack stack) {
            return MinibloomEntity.this.isBreedingItem(stack);
        }
    }

    class SitAndLookGoal extends Goal {
        private double randomXLookChange;
        private double randomZLookChange;
        private int lookTimer;
        private int sitTimer;

        public SitAndLookGoal() {
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return MinibloomEntity.this.getRevengeTarget() == null
                    && MinibloomEntity.this.getRNG().nextFloat() < 0.02F
                    && !MinibloomEntity.this.isSleeping()
                    && MinibloomEntity.this.getAttackTarget() == null
                    && MinibloomEntity.this.getNavigator().noPath()
                    && !MinibloomEntity.this.isCrouching();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return this.sitTimer > 0;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.recalculateLookChange();
            this.sitTimer = 2 + MinibloomEntity.this.getRNG().nextInt(3);
            MinibloomEntity.this.setSitting(true);
            MinibloomEntity.this.getNavigator().clearPath();
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            MinibloomEntity.this.setSitting(false);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            --this.lookTimer;
            if (this.lookTimer <= 0) {
                --this.sitTimer;
                this.recalculateLookChange();
            }

            MinibloomEntity.this.getLookController()
                    .setLookPosition(
                            MinibloomEntity.this.getPosX() + this.randomXLookChange,
                            MinibloomEntity.this.getPosYEye(),
                            MinibloomEntity.this.getPosZ() + this.randomZLookChange,
                            (float)MinibloomEntity.this.getHorizontalFaceSpeed(),
                            (float)MinibloomEntity.this.getVerticalFaceSpeed());
        }

        private void recalculateLookChange() {
            double d0 = (Math.PI * 2D) * MinibloomEntity.this.getRNG().nextDouble();
            this.randomXLookChange = Math.cos(d0);
            this.randomZLookChange = Math.sin(d0);
            this.lookTimer = 80 + MinibloomEntity.this.getRNG().nextInt(20);
        }
    }

    class FindShelterGoal extends FleeSunGoal {
        private int cooldown = 100;

        public FindShelterGoal(double p_i50724_2_) {
            super(MinibloomEntity.this, p_i50724_2_);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            if (!MinibloomEntity.this.isSleeping() && this.creature.getAttackTarget() == null) {
                if (MinibloomEntity.this.world.isThundering()) {
                    return true;
                } else if (this.cooldown > 0) {
                    --this.cooldown;
                    return false;
                } else {
                    this.cooldown = 100;
                    BlockPos creaturePosition = this.creature.getPosition();
                    return MinibloomEntity.this.world.isNightTime()
                            && MinibloomEntity.this.world.canSeeSky(creaturePosition)
                            && !((ServerWorld)MinibloomEntity.this.world).isVillage(creaturePosition)
                            && this.isPossibleShelter();
                }
            } else {
                return false;
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            MinibloomEntity.this.setMoving();
            super.startExecuting();
        }
    }

    class SleepGoal extends Goal {

        public SleepGoal() {
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            if (MinibloomEntity.this.moveStrafing == 0.0F && MinibloomEntity.this.moveVertical == 0.0F && MinibloomEntity.this.moveForward == 0.0F) {
                return this.shouldSleep() || MinibloomEntity.this.isSleeping();
            } else {
                return false;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return this.shouldSleep();
        }

        private boolean shouldSleep() {
            return MinibloomEntity.this.world.isNightTime() ;
                    //&& this.hasShelter();
        }

        private boolean hasShelter() {
            BlockPos blockpos = new BlockPos(MinibloomEntity.this.getPosX(), MinibloomEntity.this.getBoundingBox().maxY, MinibloomEntity.this.getPosZ());
            return !MinibloomEntity.this.world.canSeeSky(blockpos) && MinibloomEntity.this.getBlockPathWeight(blockpos) >= 0.0F;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            MinibloomEntity.this.setMoving();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            MinibloomEntity.this.setSitting(false);
            MinibloomEntity.this.setJumping(false);
            MinibloomEntity.this.setSleeping(true);
            MinibloomEntity.this.getNavigator().clearPath();
            MinibloomEntity.this.getMoveHelper().setMoveTo(MinibloomEntity.this.getPosX(), MinibloomEntity.this.getPosY(), MinibloomEntity.this.getPosZ(), 0.0D);
        }
    }

    class SwimGoal extends net.minecraft.entity.ai.goal.SwimGoal {
        public SwimGoal() {
            super(MinibloomEntity.this);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            super.startExecuting();
            MinibloomEntity.this.setMoving();
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return MinibloomEntity.this.isInWater() && MinibloomEntity.this.func_233571_b_(FluidTags.WATER) > 0.25D || MinibloomEntity.this.isInLava();
        }
    }
}
