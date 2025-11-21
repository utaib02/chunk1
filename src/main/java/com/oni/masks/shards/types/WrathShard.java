package com.oni.masks.shards.types;

import com.oni.masks.shards.Shard;
import com.oni.masks.shards.SinShardType;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class WrathShard extends Shard {

    public WrathShard(final Player player) {
        super(SinShardType.WRATH, player);
    }

    @Override
    public void applyPassiveEffects() {
        addPotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0);

        if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH) != null) {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(24.0);
        }
    }

    @Override
    public void removePassiveEffects() {
        removePotionEffect(PotionEffectType.FIRE_RESISTANCE);

        if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH) != null) {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
        }
    }
}
