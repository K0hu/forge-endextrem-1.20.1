package de.kohu.endextrem.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class HellBranchItem extends Item {
    public HellBranchItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Vec3 aim = player.getLookAngle();

        double distance = 10.0;
        Vec3 currentPosition = player.position();
        Vec3 teleportPosition = currentPosition.add(aim.scale(distance));

        BlockPos targetBlockPos = new BlockPos((int) teleportPosition.x, (int) teleportPosition.y, (int) teleportPosition.z);
        world = player.getCommandSenderWorld();
        BlockState targetBlockState = world.getBlockState(targetBlockPos);

        if (targetBlockState.isAir()) {
            player.teleportTo(teleportPosition.x, teleportPosition.y, teleportPosition.z);
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 500, 1));
            itemstack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
            return InteractionResultHolder.success(itemstack);
        } else {
            // Return fail if the block is not passable
            return InteractionResultHolder.fail(itemstack);
        }
    }

}