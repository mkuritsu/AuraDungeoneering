package io.github.itstaylz.auradungeoneering.traits;

import dev.aurelium.auraskills.api.registry.NamespacedId;
import dev.aurelium.auraskills.api.trait.CustomTrait;
import io.github.itstaylz.auradungeoneering.AuraDungeoneeringConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class MythicDungeonLootBonusTrait implements DungeooneringTrait {

    private static final String KEY = "mythic_dungeon_loot_bonus";

    private final CustomTrait trait;

    public MythicDungeonLootBonusTrait(JavaPlugin plugin, AuraDungeoneeringConfig config) {
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
