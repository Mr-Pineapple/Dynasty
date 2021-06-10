package co.uk.mrpineapple.dynasty.common.entity.neutral;

import co.uk.mrpineapple.dynasty.common.ai.guard.DefendVillageGoal;
import co.uk.mrpineapple.dynasty.common.ai.guard.GuardAttackGoal;
import co.uk.mrpineapple.dynasty.common.ai.guard.LookAtTalkingToGuard;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.swing.Timer;
import java.util.concurrent.ThreadLocalRandom;

public class GuardEntity extends CreatureEntity {

    public String chat = null;
    public boolean nameActive = false;

    public GuardEntity(EntityType<? extends GuardEntity> guardEntity, World world) {
        super(guardEntity, world);
        }

    @Override
    protected void registerGoals() {
          this.populateDefaultEquipmentSlots();
            this.goalSelector.addGoal(1, new GuardAttackGoal(this, 1.0D, true));
            this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
            this.goalSelector.addGoal(1, new LookAtTalkingToGuard(this));
            this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
            this.goalSelector.addGoal(2, new ReturnToVillageGoal(this, 0.6D, false));
            this.goalSelector.addGoal(4, new PatrolVillageGoal(this, 0.6D));
            this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
            this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
            this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
            this.targetSelector.addGoal(1, new DefendVillageGoal(this));
            this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (p_234199_0_) -> {
                return p_234199_0_ instanceof IMob && !(p_234199_0_ instanceof CreeperEntity);
            }));
    }

    private void populateDefaultEquipmentSlots() {
        this.canHoldItem(new ItemStack(Items.IRON_SWORD));
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
        this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.SHIELD));

    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 50.0D).add(Attributes.MOVEMENT_SPEED, 0.35D).add(Attributes.ATTACK_DAMAGE, 8.0D);
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
    public ActionResultType mobInteract(PlayerEntity playerEntity, Hand hand) {
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
            javax.swing.Timer timer = new Timer(2000, arg0 -> removeChat());
            timer.setRepeats(false);
            timer.start();
        }
        return ActionResultType.sidedSuccess(this.level.isClientSide);

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

    @Override
    @Nullable
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected void playStepSound(BlockPos p_180429_1_, BlockState p_180429_2_) {

    }
}
