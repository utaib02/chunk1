package com.oni.masks.listeners;

import com.oni.masks.OniMasksPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftingListener implements Listener {
    
    private final OniMasksPlugin plugin = OniMasksPlugin.getInstance();
    
    @EventHandler
    public void onCraftItem(final CraftItemEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        final Player player = (Player) event.getWhoClicked();
        final ItemStack result = event.getRecipe().getResult();

        if (this.plugin.getItemManager().isRerollItem(result)) {
            this.plugin.getLogger().info(event.getWhoClicked().getName() + " crafted a Mask Reroll Token");
        }

        if (this.plugin.getItemManager().isShardItem(result)) {
            this.plugin.getLogger().info(player.getName() + " crafted a Sin Shard");
        }
    }
}