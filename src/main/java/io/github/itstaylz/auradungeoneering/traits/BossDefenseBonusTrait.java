package io.github.itstaylz.auradungeoneering.traits;

import dev.aurelium.auraskills.api.AuraSkillsApi;
import dev.aurelium.auraskills.api.trait.Trait;
import dev.aurelium.auraskills.api.user.SkillsUser;
import io.github.itstaylz.auradungeoneering.AuraDungeoneeringConfig;
import io.github.itstaylz.auradungeoneering.DungeoneeringCustomContent;
import io.github.itstaylz.auradungeoneering.manager.BossManager;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;
import java.util.Optional;

public class BossDefenseBonusTrait extends DungeoneeringTrait {

    private static final String KEY = "mythic_boss_defense_bonus";

    public BossDefenseBonusTrait(JavaPlugin plugin, AuraDungeoneeringConfig config, DungeoneeringCustomContent content) {
        super(plugin, config, KEY, content);
    }

    @Override
    public String getMenuDisplay(double value, Trait trait, Locale locale) {
        return super.getMenuDisplay(value, trait, locale) + "%";
    }

    @EventHandler
    private void onAttack(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player player && MythicBukkit.inst().getMobManager().isMythicMob(event.getDamager())) {
            Optional<ActiveMob> optMob = MythicBukkit.inst().getMobManager().getActiveMob(event.getEntity().getUniqueId());
            optMob.ifPresent(mob -> {
                if (BossManager.getInstance().isBoss(mob)) {
                    AuraSkillsApi auraSkills = AuraSkillsApi.get();
                    SkillsUser user = auraSkills.getUser(player.getUniqueId());
                    double bonus = user.getBonusTraitLevel(getContent().getBossDefenseBonusTrait());
                    event.setDamage(event.getDamage() - event.getDamage() * bonus);
                }
            });
        }
    }
}
