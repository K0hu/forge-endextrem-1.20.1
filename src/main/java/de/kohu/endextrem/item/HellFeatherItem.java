package de.kohu.endextrem.item;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class HellFeatherItem extends Item {

    public HellFeatherItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!world.isClientSide) {
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 1200, 1));
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 1));
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 1200, 1));
            player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 1));

            itemstack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
        }

        // Return the item that was used
        return InteractionResultHolder.success(itemstack);
    }
}