package com.oni.masks.commands;

import com.oni.masks.OniMasksPlugin;
import com.oni.masks.masks.Mask;
import com.oni.masks.sins.Sin; 
import com.oni.masks.player.PlayerData;
import com.oni.masks.player.PlayerDataManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AbilityCommand implements CommandExecutor {
    
    private final OniMasksPlugin plugin = OniMasksPlugin.getInstance();
    private final PlayerDataManager playerDataManager = this.plugin.getPlayerDataManager();
    
    @Override
    public boolean onCommand(@NotNull final CommandSender sender, @NotNull final Command command, 
                            @NotNull final String label, @NotNull final String[] args) {
        
        if (!(sender instanceof Player)) {
            sender.sendMessage(Component.text("This command can only be used by players!", NamedTextColor.RED));
            return true;
        }
        
        final Player player = (Player) sender;
        final PlayerData playerData = this.playerDataManager.getPlayerData(player.getUniqueId());
        final Mask mask = playerData.getCurrentMask();
        final Sin sin = playerData.getCurrentSin();

        if (mask == null) {
            player.sendMessage(Component.text("You don't have a mask equipped!", NamedTextColor.RED));
            return true;
        }

        final String cmdName = command.getName().toLowerCase();

        if (cmdName.equals("ability1")) {
            if (mask.getAbilities().isEmpty()) {
                player.sendMessage(Component.text("Your mask doesn't have ability1!", NamedTextColor.RED));
                return true;
            }

            mask.getAbilities().get(0).use();
            playerData.applyTriangleCooldowns("ability1");

        } else if (cmdName.equals("ability2")) {
            if (mask.getAbilities().size() < 2) {
                player.sendMessage(Component.text("Your mask doesn't have ability2!", NamedTextColor.RED));
                return true;
            }

            mask.getAbilities().get(1).use();
            playerData.applyTriangleCooldowns("ability2");

        } else if (cmdName.equals("ability3")) {
            if (sin == null) {
                player.sendMessage(Component.text("You need a Sin Item equipped to use ability3!", NamedTextColor.RED));
                return true;
            }

            if (sin.getAbilities().isEmpty()) {
                player.sendMessage(Component.text("Your sin doesn't have an ability!", NamedTextColor.RED));
                return true;
            }

            sin.getAbilities().get(0).use();
            playerData.applyTriangleCooldowns("ability3");
        }
        
        return true;
    }
}
