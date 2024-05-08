package io.github.itstaylz.auradungeoneering.traits;

import dev.aurelium.auraskills.api.trait.Trait;
import io.github.itstaylz.auradungeoneering.AuraDungeoneeringConfig;
import io.github.itstaylz.auradungeoneering.DungeoneeringCustomContent;
import io.lumine.mythic.bukkit.events.MythicMobLootDropEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Locale;

public class DungeonLootBonusTrait extends DungeoneeringTrait {

    private static final String KEY = "mythic_dungeon_loot_bonus";

    public DungeonLootBonusTrait(JavaPlugin plugin, AuraDungeoneeringConfig config, DungeoneeringCustomContent content) {
        super(plugin, config, KEY, content);
    }

    @Override
    public String getMenuDisplay(double value, Trait trait, Locale locale) {
        return super.getMenuDisplay(value, trait, locale) + "%";
    }

    @EventHandler
    private void onLoot(MythicMobLootDropEvent event) {
        event.getDrops().getDrops().forEach(drop -> {
            event.getKiller().sendMessage(drop.getLine());
        });
        event.getDrops().setLootTable(Arrays.asList()); // this works
//        event.getPhysicalDrops().clear();
//        event.getDrops().getDrops().clear();
        Bukkit.broadcastMessage("drops cleared!");
    }

}
