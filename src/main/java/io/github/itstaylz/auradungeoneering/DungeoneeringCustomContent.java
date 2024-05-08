package io.github.itstaylz.auradungeoneering;

import dev.aurelium.auraskills.api.AuraSkillsApi;
import dev.aurelium.auraskills.api.item.ItemContext;
import dev.aurelium.auraskills.api.registry.NamespacedId;
import dev.aurelium.auraskills.api.registry.NamespacedRegistry;
import dev.aurelium.auraskills.api.skill.CustomSkill;
import dev.aurelium.auraskills.api.stat.CustomStat;
import dev.aurelium.auraskills.api.trait.CustomTrait;
import io.github.itstaylz.auradungeoneering.skills.DungeoneeringSkill;
import io.github.itstaylz.auradungeoneering.sources.CompletingDungeonSource;
import io.github.itstaylz.auradungeoneering.traits.*;
import org.bukkit.plugin.java.JavaPlugin;

public class DungeoneeringCustomContent {

    private CustomTrait bossAttackBonusTrait;
    private CustomTrait bossDefenseBonusTrait;
    private CustomTrait mobAttackBonusTrait;
    private CustomTrait mobDefenseBonusTrait;
    private CustomTrait dungeonLootBonusTrait;
    private final CustomStat bossAttackBonusStat;
    private final CustomStat bossDefenseBonusStat;
    private final CustomStat mobAttackBonusStat;
    private final CustomStat mobDefenseBonusStat;
    private final CustomStat dungeonLootBonusStat;
    private final CustomSkill dungeoneeringSkill;
    private final JavaPlugin plugin;
    private final AuraDungeoneeringConfig config;

    public DungeoneeringCustomContent(JavaPlugin plugin, AuraDungeoneeringConfig config) {
        this.plugin = plugin;
        this.config = config;
        this.bossAttackBonusStat = createCustomStat("mythic_boss_attack_bonus", "0,2");
        this.bossDefenseBonusStat = createCustomStat("mythic_boss_defense_bonus", "0,3");
        this.dungeonLootBonusStat = createCustomStat("mythic_dungeon_loot_bonus", "0,4");
        this.mobDefenseBonusStat = createCustomStat("mythic_mob_defense_bonus", "0,5");
        this.mobAttackBonusStat = createCustomStat("mythic_mob_attack_bonus", "0,6");
        this.dungeoneeringSkill = new DungeoneeringSkill(plugin, config).getSkill();
    }

    public void register() {
        AuraSkillsApi auraSkills = AuraSkillsApi.get();
        NamespacedRegistry registry = auraSkills.useRegistry(this.plugin.getName().toLowerCase(), this.plugin.getDataFolder());
        this.bossAttackBonusTrait = registerTrait(auraSkills, registry, new BossAttackBonusTrait(this.plugin, this.config, this));
        this.bossDefenseBonusTrait = registerTrait(auraSkills, registry, new BossDefenseBonusTrait(this.plugin, this.config, this));
        this.dungeonLootBonusTrait = registerTrait(auraSkills, registry, new DungeonLootBonusTrait(this.plugin, this.config, this));
        this.mobDefenseBonusTrait = registerTrait(auraSkills, registry, new MobDefenseBonusTrait(this.plugin, this.config, this));
        this.mobAttackBonusTrait = registerTrait(auraSkills, registry, new MobAttackBonusTrait(this.plugin, this.config, this));
        registry.registerStat(this.bossDefenseBonusStat);
        registry.registerStat(this.bossAttackBonusStat);
        registry.registerStat(this.mobAttackBonusStat);
        registry.registerStat(this.mobDefenseBonusStat);
        registry.registerStat(this.dungeonLootBonusStat);
        registry.registerSkill(this.dungeoneeringSkill);

        registry.registerSourceType("completing_dungeon", (source, context) -> new CompletingDungeonSource(context.parseValues(source)));
    }

    private CustomStat createCustomStat(String key, String pos) {
        return CustomStat.builder(NamespacedId.of(this.plugin.getName().toLowerCase(), key))
                .displayName(this.config.stat(key).getString("name"))
                .color(this.config.stat(key).getString("color"))
                .description(this.config.stat(key).getString("description"))
                .symbol(this.config.stat(key).getString("symbol"))
                .item(ItemContext.builder()
                        .material(this.config.stat(key).getString("material"))
                        .pos(pos)
                        .build())
                .build();
    }

    private CustomTrait registerTrait(AuraSkillsApi auraSkills, NamespacedRegistry registry, DungeoneeringTrait trait) {
        registry.registerTrait(trait.getTrait());
        auraSkills.getHandlers().registerTraitHandler(trait);
        return trait.getTrait();
    }

    public CustomSkill getDungeoneeringSkill() {
        return dungeoneeringSkill;
    }

    public CustomStat getMobAttackBonusStat() {
        return mobAttackBonusStat;
    }

    public CustomStat getBossAttackBonusStat() {
        return bossAttackBonusStat;
    }

    public CustomStat getBossDefenseBonusStat() {
        return bossDefenseBonusStat;
    }

    public CustomStat getDungeonLootBonusStat() {
        return dungeonLootBonusStat;
    }

    public CustomStat getMobDefenseBonusStat() {
        return mobDefenseBonusStat;
    }

    public CustomTrait getBossAttackBonusTrait() {
        return bossAttackBonusTrait;
    }

    public CustomTrait getBossDefenseBonusTrait() {
        return bossDefenseBonusTrait;
    }

    public CustomTrait getDungeonLootBonusTrait() {
        return dungeonLootBonusTrait;
    }

    public CustomTrait getMobAttackBonusTrait() {
        return mobAttackBonusTrait;
    }

    public CustomTrait getMobDefenseBonusTrait() {
        return mobDefenseBonusTrait;
    }
}

