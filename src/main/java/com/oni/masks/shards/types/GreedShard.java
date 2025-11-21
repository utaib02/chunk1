package com.oni.masks.shards.types;

import com.oni.masks.shards.Shard;
import com.oni.masks.shards.SinShardType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class GreedShard extends Shard {

    public GreedShard(final Player player) {
        super(SinShardType.GREED, player);
    }

    @Override
    public void applyPassiveEffects() {
        addPotionEffect(PotionEffectType.RESISTANCE, Integer.MAX_VALUE, 0);
    }

    @Override
    public void removePassiveEffects() {
        removePotionEffect(PotionEffectType.RESISTANCE);
    }
}
