package com.oni.masks.shards.types;

import com.oni.masks.shards.Shard;
import com.oni.masks.shards.SinShardType;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class GluttonyShard extends Shard {

    public GluttonyShard(final Player player) {
        super(SinShardType.GLUTTONY, player);
    }

    @Override
    public void applyPassiveEffects() {
        addPotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0);

        if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH) != null) {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(26.0);
        }
    }

    @Override
    public void removePassiveEffects() {
        removePotionEffect(PotionEffectType.SATURATION);

        if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH) != null) {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
        }
    }
}
