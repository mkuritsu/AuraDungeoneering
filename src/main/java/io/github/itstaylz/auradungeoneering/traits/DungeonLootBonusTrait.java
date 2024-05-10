package io.github.itstaylz.auradungeoneering.traits;

import dev.aurelium.auraskills.api.AuraSkillsApi;
import dev.aurelium.auraskills.api.trait.Trait;
import dev.aurelium.auraskills.api.user.SkillsUser;
import io.github.itstaylz.auradungeoneering.AuraDungeoneeringConfig;
import io.github.itstaylz.auradungeoneering.DungeoneeringCustomContent;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.events.MythicMobLootDropEvent;
import io.lumine.mythic.core.drops.Drop;
import io.lumine.mythic.core.drops.DropTable;
import io.lumine.mythic.core.drops.LootBag;
import io.lumine.mythic.core.mobs.ActiveMob;
import net.playavalon.mythicdungeons.MythicDungeons;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class DungeonLootBonusTrait extends DungeoneeringTrait {

    private static final String KEY = "mythic_dungeon_loot_bonus";
    private static final Random RANDOM = new Random();

    private final AuraDungeoneeringConfig config;

    public DungeonLootBonusTrait(JavaPlugin plugin, AuraDungeoneeringConfig config, DungeoneeringCustomContent content) {
        super(plugin, config, KEY, content);
        this.config = config;
    }

    @Override
    public String getMenuDisplay(double value, Trait trait, Locale locale) {
        return super.getMenuDisplay(value, trait, locale) + "%";
    }

    @EventHandler
    private void onLoot(MythicMobLootDropEvent event) {
        if (event.getKiller() instanceof Player player && MythicDungeons.inst().isPlayerInDungeon(player)) {
            AuraSkillsApi auraSkills = AuraSkillsApi.get();
            SkillsUser user = auraSkills.getUser(player.getUniqueId());
            double bonus = user.getBonusTraitLevel(getContent().getDungeonLootBonusTrait());
            double chance = RANDOM.nextDouble() * 100;
            if (chance <= bonus) {
                ActiveMob mob = event.getMob();
                String dropTableName = this.config.getLootTableKey(mob.getType().getInternalName());
                if (dropTableName == null) {
                    return;
                }
                Optional<DropTable> optDropTable = MythicBukkit.inst().getDropManager().getDropTable(dropTableName);
                optDropTable.ifPresent(dropTable -> {
                    List<Drop> defaultLootTable = event.getDrops().getLootTable();
                    LootBag bonusBag = dropTable.generate();
                    defaultLootTable.addAll(bonusBag.getLootTable());
                    event.getDrops().setLootTable(defaultLootTable);
                });
            }

        }
    }
}
