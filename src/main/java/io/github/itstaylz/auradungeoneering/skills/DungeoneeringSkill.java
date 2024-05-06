package io.github.itstaylz.auradungeoneering.skills;

import dev.aurelium.auraskills.api.item.ItemContext;
import dev.aurelium.auraskills.api.registry.NamespacedId;
import dev.aurelium.auraskills.api.skill.CustomSkill;
import io.github.itstaylz.auradungeoneering.AuraDungeoneeringConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class DungeoneeringSkill {

    private static final String KEY = "dungeoneering";

    private final CustomSkill skill;

    public DungeoneeringSkill(JavaPlugin plugin, AuraDungeoneeringConfig config) {
        this.skill = CustomSkill
                .builder(NamespacedId.of(plugin.getName().toLowerCase(), KEY))
                .displayName(config.skill(KEY).getString("name"))
                .description(config.skill(KEY).getString("description"))
                .item(ItemContext.builder()
                        .material(config.skill(KEY).getString("material"))
                        .pos(config.skill(KEY).getString("pos"))
                        .build())
                .build();
    }

    public CustomSkill getSkill() {
        return skill;
    }
}
