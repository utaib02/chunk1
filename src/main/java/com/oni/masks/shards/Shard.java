package com.oni.masks.shards;

import com.oni.masks.OniMasksPlugin;
import com.oni.masks.player.PlayerData;
import com.oni.masks.player.PlayerDataManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Getter
@RequiredArgsConstructor
public abstract class Shard {

    protected final SinShardType shardType;
    protected final Player player;

    private final OniMasksPlugin plugin = OniMasksPlugin.getInstance();
    private final PlayerDataManager playerDataManager = this.plugin.getPlayerDataManager();

    public abstract void applyPassiveEffects();

    public abstract void removePassiveEffects();

    public void onEquip() {
    }

    protected void addPotionEffect(final PotionEffectType effectType, final int duration, final int amplifier) {
        final PotionEffect effect = new PotionEffect(effectType, duration, amplifier, false, false);
        this.player.addPotionEffect(effect);
    }

    protected void removePotionEffect(final PotionEffectType effectType) {
        this.player.removePotionEffect(effectType);
    }
}
