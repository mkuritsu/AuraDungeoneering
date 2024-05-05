package io.github.itstaylz.auradungeoneering;

import dev.aurelium.auraskills.api.AuraSkillsApi;
import dev.aurelium.auraskills.api.item.ItemContext;
import dev.aurelium.auraskills.api.registry.NamespacedId;
import dev.aurelium.auraskills.api.registry.NamespacedRegistry;
import dev.aurelium.auraskills.api.skill.CustomSkill;
import dev.aurelium.auraskills.api.source.LevelerContext;
import dev.aurelium.auraskills.api.source.SkillSource;
import dev.aurelium.auraskills.api.source.SourceType;
import dev.aurelium.auraskills.api.source.XpSource;
import dev.aurelium.auraskills.api.stat.CustomStat;
import dev.aurelium.auraskills.api.trait.CustomTrait;
import io.lumine.mythic.bukkit.events.MythicDamageEvent;
import io.lumine.mythic.bukkit.events.MythicPlayerAttackEvent;
import net.playavalon.mythicdungeons.api.events.dungeon.PlayerFinishDungeonEvent;
import net.playavalon.mythicdungeons.dungeons.Dungeon;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AuraDungeoneeringPlugin extends JavaPlugin implements Listener {

    private static final CustomSkill SKILL = CustomSkill
            .builder(NamespacedId.of("auradungeoneering", "dungeoneering"))
            .displayName("Dungeoneering")
            .description("Complete dungeons to earn XP")
            .item(ItemContext.builder()
                    .material("skeleton_skull")
                    .pos("4,4")
                    .build())
            .build();

    private static final CustomTrait TEST_TRAIT = CustomTrait
            .builder(NamespacedId.of("auradungeoneering", "test_trait"))
            .displayName("TEST TRAIT")
            .build();

    private static final CustomStat TEST_STAT = CustomStat
            .builder(NamespacedId.of("auradungeoneering", "test_stat"))
            .trait(TEST_TRAIT, 0.5)
            .color("<green>")
            .symbol("")
            .displayName("Test stat")
            .description("AAAAAAAAAAAAaaaaa")
            .item(ItemContext.builder()
                    .material("skeleton_skull")
                    .group("lower")
                    .order(2)
                    .build())
            .build();

    private static AuraSkillsApi auraSkillsApi;

    @Override
    public void onEnable() {
        saveResource("sources/dungeoneering.yml", true);
        Bukkit.getPluginManager().registerEvents(this, this);
        auraSkillsApi = AuraSkillsApi.get();
        NamespacedRegistry registry = auraSkillsApi.useRegistry(getName(), getDataFolder());

        SourceType dungeonSource = registry.registerSourceType("completing_dungeon",
                (source, context) -> new DungeonSource(context.parseValues(source)));
        registry.registerTrait(TEST_TRAIT);
        registry.registerStat(TEST_STAT);
        registry.registerSkill(SKILL);

        getLogger().info("ID: " + dungeonSource.getId());

        getLogger().info("SOURCE ENABLED: " + dungeonSource.isEnabled());
    }

    @EventHandler
    private void onEgg(PlayerEggThrowEvent event) {
        Player player = event.getPlayer();
        AuraSkillsApi api = AuraSkillsApi.get();
        SkillSource<DungeonSource> dss = auraSkillsApi.getSourceManager().getSingleSourceOfType(DungeonSource.class);
        double xp = dss.source().getXp();
//        player.sendMessage("XP: " + dss.source().getXp());
        api.getUser(player.getUniqueId()).addSkillXp(SKILL, xp, dss.source());
    }

    @EventHandler
    private void onDungeonFinish(PlayerFinishDungeonEvent event) {
        Player player = event.getPlayer();
        Dungeon dungeon = event.getDungeon();
        if (dungeon.hasPlayerCompletedDungeon(player)) {
            player.sendMessage("COMPLETE DUNGEON");
        }
    }

    @EventHandler
    private void onAttack(MythicDamageEvent event) {
        Bukkit.broadcastMessage("DAMAGE");
    }
}
