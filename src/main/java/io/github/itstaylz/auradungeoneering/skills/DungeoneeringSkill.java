package io.github.itstaylz.auradungeoneering.skills;

import dev.aurelium.auraskills.api.item.ItemContext;
import dev.aurelium.auraskills.api.registry.NamespacedId;
import dev.aurelium.auraskills.api.skill.CustomSkill;
import org.bukkit.plugin.java.JavaPlugin;

public class DungeoneeringSkill {

    private final CustomSkill skill;

    public DungeoneeringSkill(JavaPlugin plugin) {
        this.skill = CustomSkill
                .builder(NamespacedId.of(plugin.getName().toLowerCase(), "dungeoneering"))
                .displayName("Dungeoneering")
                .description("Complete dungeons to earn XP")
                .item(ItemContext.builder()
                        .material("skeleton_skull")
                        .pos("4,4")
                        .build())
                .build();
    }

    public CustomSkill getSkill() {
        return skill;
    }
}
