package io.github.itstaylz.auradungeoneering.stats;

import dev.aurelium.auraskills.api.item.ItemContext;
import dev.aurelium.auraskills.api.registry.NamespacedId;
import dev.aurelium.auraskills.api.stat.CustomStat;
import io.github.itstaylz.auradungeoneering.AuraDungeoneeringConfig;
import io.github.itstaylz.auradungeoneering.traits.DungeooneringTrait;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class DungeoneeringStat {

    private static final String KEY = "dungeoneering_expertise";

    private final CustomStat stat;

    public DungeoneeringStat(JavaPlugin plugin, List<DungeooneringTrait> traits, AuraDungeoneeringConfig config) {
        CustomStat.CustomStatBuilder builder = CustomStat
                .builder(NamespacedId.of(plugin.getName().toLowerCase(), KEY))
                .displayName(config.stat(KEY).getString("name"))
                .color(config.stat(KEY).getString("color"))
                .description(config.stat(KEY).getString("description"))
                .item(ItemContext.builder()
                        .group("lower")
                        .material(config.stat(KEY).getString("material"))
                        .order(2)
                        .build());
        for (DungeooneringTrait trait : traits) {
            builder.trait(trait.getTrait(), trait.getModifier());
        }
        this.stat = builder.build();
    }

    public CustomStat getStat() {
        return stat;
    }
}
