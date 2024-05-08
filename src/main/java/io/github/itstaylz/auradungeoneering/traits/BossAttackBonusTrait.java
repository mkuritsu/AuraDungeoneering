package io.github.itstaylz.auradungeoneering.traits;

import dev.aurelium.auraskills.api.AuraSkillsApi;
import dev.aurelium.auraskills.api.trait.Trait;
import dev.aurelium.auraskills.api.user.SkillsUser;
import io.github.itstaylz.auradungeoneering.AuraDungeoneeringConfig;
import io.github.itstaylz.auradungeoneering.DungeoneeringCustomContent;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

public class BossAttackBonusTrait extends DungeoneeringTrait {

    private static final String KEY = "mythic_boss_attack_bonus";

    public BossAttackBonusTrait(JavaPlugin plugin, AuraDungeoneeringConfig config, DungeoneeringCustomContent content) {
        super(plugin, config, KEY, content);
    }

    @Override
    public String getMenuDisplay(double value, Trait trait, Locale locale) {
        return super.getMenuDisplay(value, trait, locale) + "%";
    }

    // TODO: check boss
    @EventHandler
    private void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player && MythicBukkit.inst().getMobManager().isMythicMob(event.getEntity())) {
            AuraSkillsApi auraSkills = AuraSkillsApi.get();
            SkillsUser user = auraSkills.getUser(player.getUniqueId());
            double bonus = user.getBonusTraitLevel(getContent().getBossAttackBonusTrait());
            event.setDamage(event.getDamage() + event.getDamage() * bonus);
        }
    }
}
