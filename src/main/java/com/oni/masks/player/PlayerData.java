package com.oni.masks.player;

import com.oni.masks.masks.Mask;
import com.oni.masks.masks.MaskType;
import com.oni.masks.sins.Sin;
import com.oni.masks.sins.SinType;
import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Data
public class PlayerData {
    
    private UUID playerId;
    private MaskType maskType;
    private transient Mask currentMask; // Don't serialize the actual mask object
    private SinType sinType;
    private transient Sin currentSin; // Don't serialize the actual sin object
    private boolean hasJoinedBefore;
    private Set<UUID> trustedPlayers;
    private Map<String, Long> abilityCooldowns;
    private Map<String, Long> abilityTriangleDelays;
    private int currentEventStage;
    private int eventStageXP;
    private int playerKills;
    private int maskTier;
    private MaskType previousMaskType; // Store mask before event
    private long lastRerollTime; // Anti-spam for reroll items
    private Set<UUID> uniqueKills; // Track unique player kills for tier system
    private int tierLevel; // Current tier (0, 1, 2)
    
    public PlayerData(final UUID playerId) {
        this.playerId = playerId;
        this.hasJoinedBefore = false;
        this.trustedPlayers = new HashSet<>();
        this.abilityCooldowns = new HashMap<>();
        this.currentEventStage = 1;
        this.eventStageXP = 0;
        this.playerKills = 0;
        this.maskTier = 0;
        this.previousMaskType = null;
        this.lastRerollTime = 0;
        this.uniqueKills = new HashSet<>();
        this.tierLevel = 0;
        this.abilityTriangleDelays = new HashMap<>();
    }
    
    public boolean isTrusted(final UUID otherPlayerId) {
        return this.trustedPlayers.contains(otherPlayerId);
    }
    
    public void trustPlayer(final UUID otherPlayerId) {
        this.trustedPlayers.add(otherPlayerId);
    }
    
    public void untrustPlayer(final UUID otherPlayerId) {
        this.trustedPlayers.remove(otherPlayerId);
    }
    
    public boolean isAbilityOnCooldown(final String abilityName) {
        final long currentTime = System.currentTimeMillis();

        final Long cooldownEnd = this.abilityCooldowns.get(abilityName);
        if (cooldownEnd != null && currentTime < cooldownEnd) {
            return true;
        }

        final Long triangleDelayEnd = this.abilityTriangleDelays.get(abilityName);
        return triangleDelayEnd != null && currentTime < triangleDelayEnd;
    }
    
    public void setCooldown(final String abilityName, final long durationMs) {
        this.abilityCooldowns.put(abilityName, System.currentTimeMillis() + durationMs);
    }
    
    public long getRemainingCooldown(final String abilityName) {
        final long currentTime = System.currentTimeMillis();

        final Long cooldownEnd = this.abilityCooldowns.get(abilityName);
        final Long triangleDelayEnd = this.abilityTriangleDelays.get(abilityName);

        long regularRemaining = cooldownEnd != null ? Math.max(0, cooldownEnd - currentTime) : 0;
        long triangleRemaining = triangleDelayEnd != null ? Math.max(0, triangleDelayEnd - currentTime) : 0;

        return Math.max(regularRemaining, triangleRemaining);
    }
    
    public void incrementEventStageXP() {
        this.eventStageXP++;
    }
    
    public void resetEventProgress() {
        this.currentEventStage = 1;
        this.eventStageXP = 0;
    }
    
    public boolean canUseReroll() {
        return System.currentTimeMillis() - this.lastRerollTime > 20000; // 20 seconds
    }
    
    public boolean addUniqueKill(final UUID victimId) {
        if (this.uniqueKills.add(victimId)) {
            // Check for tier upgrades
            final int kills = this.uniqueKills.size();
            int newTier = 0;
            
            if (kills >= 4) {
                newTier = 2;
            } else if (kills >= 2) {
                newTier = 1;
            }
            
            if (newTier > this.tierLevel) {
                this.tierLevel = newTier;
                return true; // Tier upgraded
            }
        }
        return false; // No tier change
    }
    
    public int getUniqueKillCount() {
        return this.uniqueKills.size();
    }

    public void applyTriangleCooldowns(final String usedAbility) {
        final long delayMs = 5000;
        final long currentTime = System.currentTimeMillis();

        if (!usedAbility.equals("ability1")) {
            final Long existingDelay = this.abilityTriangleDelays.get("ability1");
            if (existingDelay == null || existingDelay < currentTime) {
                this.abilityTriangleDelays.put("ability1", currentTime + delayMs);
            }
        }

        if (!usedAbility.equals("ability2")) {
            final Long existingDelay = this.abilityTriangleDelays.get("ability2");
            if (existingDelay == null || existingDelay < currentTime) {
                this.abilityTriangleDelays.put("ability2", currentTime + delayMs);
            }
        }

        if (!usedAbility.equals("ability3")) {
            final Long existingDelay = this.abilityTriangleDelays.get("ability3");
            if (existingDelay == null || existingDelay < currentTime) {
                this.abilityTriangleDelays.put("ability3", currentTime + delayMs);
            }
        }
    }
}