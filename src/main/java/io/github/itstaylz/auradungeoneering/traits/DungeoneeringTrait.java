package io.github.itstaylz.auradungeoneering.traits;

import dev.aurelium.auraskills.api.bukkit.BukkitTraitHandler;
import dev.aurelium.auraskills.api.registry.NamespacedId;
import dev.aurelium.auraskills.api.trait.CustomTrait;
import dev.aurelium.auraskills.api.trait.Trait;
import dev.aurelium.auraskills.api.user.SkillsUser;
import io.github.itstaylz.auradungeoneering.AuraDungeoneeringConfig;
import io.github.itstaylz.auradungeoneering.DungeoneeringCustomContent;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class DungeoneeringTrait implements BukkitTraitHandler, Listener {

    private final CustomTrait trait;
    private final DungeoneeringCustomContent content;

    protected DungeoneeringTrait(JavaPlugin plugin, AuraDungeoneeringConfig config, String key, DungeoneeringCustomContent content) {
        this.trait = CustomTrait
                .builder(NamespacedId.of(plugin.getName().toLowerCase(), key))
                .displayName(config.trait(key).getString("name"))
                .build();
        this.content = content;

    }

    @Override
    public double getBaseLevel(Player player, Trait trait) {
        return 0;
    }

    @Override
    public Trait[] getTraits() {
        return new Trait[] { this.trait };
    }

    @Override
    public void onReload(Player player, SkillsUser user, Trait trait) {

    }

    public CustomTrait getTrait() {
        return trait;
    }

    protected DungeoneeringCustomContent getContent() {
        return content;
    }
}
