package io.github.itstaylz.auradungeoneering.stats;

import dev.aurelium.auraskills.api.item.ItemContext;
import dev.aurelium.auraskills.api.registry.NamespacedId;
import dev.aurelium.auraskills.api.stat.CustomStat;
import io.github.itstaylz.auradungeoneering.traits.DungeooneringTrait;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class DungeoneeringStat {

    private final CustomStat stat;

    public DungeoneeringStat(JavaPlugin plugin, List<DungeooneringTrait> traits) {
        CustomStat.CustomStatBuilder builder = CustomStat
                .builder(NamespacedId.of(plugin.getName().toLowerCase(), "dungeoneering_expertise"))
                .displayName("Dungeoneering Expertise")
                .color("<green>")
                .description("How much expert you are in dungeons")
                .item(ItemContext.builder()
                        .group("lower")
                        .material("emerald")
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
