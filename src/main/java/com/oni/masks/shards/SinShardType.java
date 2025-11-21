package com.oni.masks.shards;

import com.oni.masks.sins.SinType;
import lombok.Getter;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

@Getter
public enum SinShardType {
    LUST(SinType.LUST, "Lust", TextColor.color(255, 105, 180),
        "Creates 1.5 heart pink healing pulse", "Adds soft pink trail + 1 bonus heart heal"),
    WRATH(SinType.WRATH, "Wrath", NamedTextColor.RED,
        "Adds flame burst around player (4-block)", "Adds lava spark explosion + 1 heart damage"),
    PRIDE(SinType.PRIDE, "Pride", TextColor.color(218, 165, 32),
        "Purple dragon breath flare", "Adds knockback + armor shred"),
    ENVY(SinType.ENVY, "Envy", TextColor.color(50, 205, 50),
        "1.2s blindness to target", "Adds poison tick (0.5 hearts)"),
    GLUTTONY(SinType.GLUTTONY, "Gluttony", TextColor.color(139, 69, 19),
        "Applies Hunger II", "Applies Hunger II + Weakness I"),
    SLOTH(SinType.SLOTH, "Sloth", NamedTextColor.GRAY,
        "Creates 0.5s slow field", "Adds mining fatigue pulse"),
    GREED(SinType.GREED, "Greed", NamedTextColor.GOLD,
        "Mark target → drops 1 gold nugget", "Adds gold explosion + 1 heart damage");

    private final SinType sinType;
    private final String displayName;
    private final TextColor color;
    private final String ability1Buff;
    private final String ability2Buff;

    SinShardType(final SinType sinType, final String displayName, final TextColor color,
                 final String ability1Buff, final String ability2Buff) {
        this.sinType = sinType;
        this.displayName = displayName;
        this.color = color;
        this.ability1Buff = ability1Buff;
        this.ability2Buff = ability2Buff;
    }
}
