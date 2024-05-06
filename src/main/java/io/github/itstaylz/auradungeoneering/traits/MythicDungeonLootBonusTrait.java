package io.github.itstaylz.auradungeoneering.traits;

import dev.aurelium.auraskills.api.registry.NamespacedId;
import dev.aurelium.auraskills.api.trait.CustomTrait;
import org.bukkit.plugin.java.JavaPlugin;

public class MythicDungeonLootBonusTrait implements DungeooneringTrait {

    private final CustomTrait trait;

    public MythicDungeonLootBonusTrait(JavaPlugin plugin) {
        this.trait = CustomTrait
                .builder(NamespacedId.of(plugin.getName().toLowerCase(), "mythic_dungeon_loot_bonus"))
                .displayName("Mythic Dungeon Loot Bonus")
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
