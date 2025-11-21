package com.oni.masks.shards.types;

import com.oni.masks.shards.Shard;
import com.oni.masks.shards.SinShardType;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class LustShard extends Shard {

    public LustShard(final Player player) {
        super(SinShardType.LUST, player);
    }

    @Override
    public void applyPassiveEffects() {
        addPotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0);

        if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH) != null) {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(22.0);
        }
    }

    @Override
    public void removePassiveEffects() {
        removePotionEffect(PotionEffectType.SPEED);

        if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH) != null) {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
        }
    }
}
