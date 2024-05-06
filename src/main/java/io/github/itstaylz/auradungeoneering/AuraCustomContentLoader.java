package io.github.itstaylz.auradungeoneering;

import dev.aurelium.auraskills.api.AuraSkillsApi;
import dev.aurelium.auraskills.api.registry.NamespacedRegistry;
import io.github.itstaylz.auradungeoneering.skills.DungeoneeringSkill;
import io.github.itstaylz.auradungeoneering.sources.CompleteDungeonSource;
import io.github.itstaylz.auradungeoneering.stats.DungeoneeringStat;
import io.github.itstaylz.auradungeoneering.traits.MythicDungeonLootBonusTrait;
import io.github.itstaylz.auradungeoneering.traits.MythicMobBossAttackBonusTrait;
import io.github.itstaylz.auradungeoneering.traits.MythicMobBossDefenseBonusTrait;
import io.github.itstaylz.auradungeoneering.traits.MythicMobDefenseBonusTrait;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class AuraCustomContentLoader {

    private final MythicDungeonLootBonusTrait dungeonLootBonusTrait;
    private final MythicMobBossAttackBonusTrait bossAttackBonusTrait;
    private final MythicMobBossDefenseBonusTrait bossDefenseBonusTrait;
    private final MythicMobDefenseBonusTrait mobDefenseBonusTrait;
    private final DungeoneeringStat stat;
    private final DungeoneeringSkill skill;
    private final JavaPlugin plugin;

    public AuraCustomContentLoader(JavaPlugin plugin, AuraDungeoneeringConfig config) {
        this.plugin = plugin;
        this.dungeonLootBonusTrait = new MythicDungeonLootBonusTrait(plugin, config);
        this.bossAttackBonusTrait = new MythicMobBossAttackBonusTrait(plugin, config);
        this.bossDefenseBonusTrait = new MythicMobBossDefenseBonusTrait(plugin, config);
        this.mobDefenseBonusTrait = new MythicMobDefenseBonusTrait(plugin, config);
        this.stat = new DungeoneeringStat(plugin, Arrays.asList(this.dungeonLootBonusTrait, this.bossAttackBonusTrait,
                this.bossDefenseBonusTrait, this.mobDefenseBonusTrait), config);
        this.skill = new DungeoneeringSkill(plugin, config);
    }

    public void register() {
        AuraSkillsApi auraSkills = AuraSkillsApi.get();
        NamespacedRegistry registry = auraSkills.useRegistry(this.plugin.getName().toLowerCase(), this.plugin.getDataFolder());
        registry.registerSourceType("completing_dungeon", (source, context) -> new CompleteDungeonSource(context.parseValues(source)));
        registry.registerTrait(this.dungeonLootBonusTrait.getTrait());
        registry.registerTrait(this.bossAttackBonusTrait.getTrait());
        registry.registerTrait(this.bossDefenseBonusTrait.getTrait());
        registry.registerTrait(this.mobDefenseBonusTrait.getTrait());
        registry.registerStat(this.stat.getStat());
        registry.registerSkill(this.skill.getSkill());
    }

    public DungeoneeringStat getStat() {
        return stat;
    }

    public DungeoneeringSkill getSkill() {
        return skill;
    }

    public MythicDungeonLootBonusTrait getDungeonLootBonusTrait() {
        return dungeonLootBonusTrait;
    }

    public MythicMobBossAttackBonusTrait getBossAttackBonusTrait() {
        return bossAttackBonusTrait;
    }

    public MythicMobBossDefenseBonusTrait getBossDefenseBonusTrait() {
        return bossDefenseBonusTrait;
    }

    public MythicMobDefenseBonusTrait getMobDefenseBonusTrait() {
        return mobDefenseBonusTrait;
    }
}
