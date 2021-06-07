package co.uk.mrpineapple.dynasty.common.entity.passive;


import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.*;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.annotation.Nullable;
import javax.swing.Timer;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class BasicVillagerEntity extends GolemEntity {

    public String chat = null;
    boolean nameActive = false;
    protected static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.defineId(IronGolemEntity.class, DataSerializers.BYTE);

    public BasicVillagerEntity(EntityType<? extends GolemEntity> p_i50267_1_, World world) {
        super(p_i50267_1_, world);
    }



    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(2, new ReturnToVillageGoal(this, 0.6D, false));
        this.goalSelector.addGoal(4, new PatrolVillageGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.setCustomNameVisible(false);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
    }


    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.35D);
    }

    @Override
    protected int decreaseAirSupply(int air) {
        return air;
    }

    @Override
    protected void doPush(Entity entity) {
        if (entity instanceof IMob && !(entity instanceof CreeperEntity) && this.getRandom().nextInt(20) == 0) {
            this.setTarget((LivingEntity)entity);
        }

        super.doPush(entity);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (getHorizontalDistanceSqr(this.getDeltaMovement()) > (double)2.5000003E-7F && this.random.nextInt(5) == 0) {
            int i = MathHelper.floor(this.getX());
            int j = MathHelper.floor(this.getY() - (double)0.2F);
            int k = MathHelper.floor(this.getZ());
            BlockPos pos = new BlockPos(i, j, k);
            BlockState blockstate = this.level.getBlockState(pos);
            if (!blockstate.isAir(this.level, pos)) {
              //  this.level.addParticle(new BlockParticleData(ParticleTypes.BLOCK, blockstate).setPos(pos), this.getX() + ((double)this.random.nextFloat() - 0.5D) * (double)this.getBbWidth(), this.getY() + 0.1D, this.getZ() + ((double)this.random.nextFloat() - 0.5D) * (double)this.getBbWidth(), 4.0D * ((double)this.random.nextFloat() - 0.5D), 0.5D, ((double)this.random.nextFloat() - 0.5D) * 4.0D);
            }
        }

    }

    @Override
    public boolean hurt(DamageSource source, float p_70097_2_) {
        boolean flag = super.hurt(source, p_70097_2_);
        this.playSound(SoundEvents.PLAYER_ATTACK_NODAMAGE, 1.0F, 1.0F);
        return flag;
    }


    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.PLAYER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.PLAYER_DEATH;
    }

    @Override
    protected ActionResultType mobInteract(PlayerEntity playerEntity, Hand hand) {
        if(this.nameActive == false) {
            this.nameActive = true;
            int randomChat = ThreadLocalRandom.current().nextInt(1, 3 + 1);
            switch (randomChat) {
                case 1:
                    chat = "dialogue.dynasty.basicvillager.hello";
                    break;

                case 2:
                    chat = "dialogue.dynasty.basicvillager.lovelyday";
                    break;

                case 3:
                    chat = "dialogue.dynasty.basicvillager.meet";
                    break;
            }

            this.setCustomName(new TranslationTextComponent(chat).withStyle(TextFormatting.GOLD).withStyle(TextFormatting.BOLD));
            this.setCustomNameVisible(true);
            Timer timer = new Timer(2000, arg0 -> removeChat());
            timer.setRepeats(false);
            timer.start();
        }
            return ActionResultType.sidedSuccess(this.level.isClientSide);

    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack p_190948_1_, @Nullable IBlockReader p_190948_2_, List<ITextComponent> p_190948_3_, ITooltipFlag p_190948_4_) {
    }

    public void removeChat()
    {
        String chat = "";
        this.setCustomName(new TranslationTextComponent(chat));
        this.setCustomNameVisible(false);
        this.nameActive = false;

    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
    }

    public boolean isUnderWaterConverting() {
        return false;
    }

}
