package com.oni.masks.shards.types;

import com.oni.masks.shards.Shard;
import com.oni.masks.shards.SinShardType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class PrideShard extends Shard {

    public PrideShard(final Player player) {
        super(SinShardType.PRIDE, player);
    }

    @Override
    public void applyPassiveEffects() {
        addPotionEffect(PotionEffectType.STRENGTH, Integer.MAX_VALUE, 0);
    }

    @Override
    public void removePassiveEffects() {
        removePotionEffect(PotionEffectType.STRENGTH);
    }
}
