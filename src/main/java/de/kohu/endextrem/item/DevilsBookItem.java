package de.kohu.endextrem.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.core.Vec3i;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static de.kohu.endextrem.item.ModItemInit.siegel;

public class DevilsBookItem extends Item {

    public DevilsBookItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!world.isClientSide) {
            Vec3 aim = player.getLookAngle();
            SmallFireball fireball = new SmallFireball(world, player, aim.x, aim.y, aim.z);
            fireball.setPos(player.getX(), player.getY(0.5), player.getZ());
            fireball.shoot(aim.x, aim.y, aim.z, 1.5F, 1.0F);
            world.addFreshEntity(fireball);

            if (player.getOffhandItem().getItem().equals(siegel.get())) {
                MobEffect[] effects = {MobEffects.JUMP, MobEffects.ABSORPTION,
                        MobEffects.FIRE_RESISTANCE, MobEffects.WATER_BREATHING,
                        MobEffects.NIGHT_VISION, MobEffects.HUNGER, MobEffects.WEAKNESS,};
                Random random = new Random();
                MobEffect randomEffect = effects[random.nextInt(effects.length)];
                player.addEffect(new MobEffectInstance(randomEffect, 2400, 1));
            } else {
                MobEffect[] effects = {MobEffects.JUMP, MobEffects.ABSORPTION,
                        MobEffects.FIRE_RESISTANCE, MobEffects.WATER_BREATHING, MobEffects.INVISIBILITY,
                        MobEffects.NIGHT_VISION, MobEffects.HUNGER, MobEffects.WEAKNESS,
                        MobEffects.POISON};
                Random random = new Random();
                MobEffect randomEffect = effects[random.nextInt(effects.length)];
                player.addEffect(new MobEffectInstance(randomEffect, 2400, 1));
            }
            itemstack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
        }

        return InteractionResultHolder.success(itemstack);
    }
}