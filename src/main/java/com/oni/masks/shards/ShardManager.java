package com.oni.masks.shards;

import com.oni.masks.OniMasksPlugin;
import com.oni.masks.player.PlayerData;
import com.oni.masks.player.PlayerDataManager;
import com.oni.masks.shards.types.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class ShardManager {

    private final OniMasksPlugin plugin = OniMasksPlugin.getInstance();
    private final PlayerDataManager playerDataManager = this.plugin.getPlayerDataManager();

    public Shard createShard(final Player player, final SinShardType shardType) {
        return switch (shardType) {
            case LUST -> new LustShard(player);
            case WRATH -> new WrathShard(player);
            case PRIDE -> new PrideShard(player);
            case ENVY -> new EnvyShard(player);
            case GLUTTONY -> new GluttonyShard(player);
            case SLOTH -> new SlothShard(player);
            case GREED -> new GreedShard(player);
        };
    }

    public void assignShard(final Player player, final SinShardType shardType) {
        final PlayerData playerData = this.playerDataManager.getPlayerData(player.getUniqueId());

        if (playerData.getCurrentShard() != null) {
            player.sendMessage(Component.text("You already have a shard equipped! Cannot stack shards.", NamedTextColor.RED));
            return;
        }

        if (playerData.getCurrentShard() != null) {
            playerData.getCurrentShard().removePassiveEffects();
        }

        final Shard newShard = this.createShard(player, shardType);
        playerData.setCurrentShard(newShard);
        playerData.setShardType(shardType);

        newShard.applyPassiveEffects();
        newShard.onEquip();

        player.sendMessage(Component.text("Shard applied! Your mask is now enhanced.", NamedTextColor.GOLD));

        this.plugin.getSoundManager().playMaskAssignSound(player);
        this.playerDataManager.savePlayerData(player.getUniqueId());
    }

    public void removeShard(final Player player) {
        final PlayerData playerData = this.playerDataManager.getPlayerData(player.getUniqueId());

        if (playerData.getCurrentShard() != null) {
            playerData.getCurrentShard().removePassiveEffects();
            playerData.setCurrentShard(null);
            playerData.setShardType(null);

            player.sendMessage(Component.text("Shard removed.", NamedTextColor.GREEN));
            this.playerDataManager.savePlayerData(player.getUniqueId());
        }
    }
}
