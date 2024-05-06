package io.github.itstaylz.auradungeoneering.traits;

import dev.aurelium.auraskills.api.registry.NamespacedId;
import dev.aurelium.auraskills.api.trait.CustomTrait;
import io.github.itstaylz.auradungeoneering.AuraDungeoneeringConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class MythicMobBossDefenseBonusTrait implements DungeooneringTrait {

    private static final String KEY = "mythic_boss_defense_bonus";

    private final CustomTrait trait;

    public MythicMobBossDefenseBonusTrait(JavaPlugin plugin, AuraDungeoneeringConfig config) {
        this.trait = CustomTrait
                .builder(NamespacedId.of(plugin.getName().toLowerCase(), KEY))
                .displayName(config.trait(KEY).getString("name"))
                .build();
    }

    public CustomTrait getTrait() {
        return trait;
    }

    @Override
    public double getModifier() {
        return 1;
    }
}
