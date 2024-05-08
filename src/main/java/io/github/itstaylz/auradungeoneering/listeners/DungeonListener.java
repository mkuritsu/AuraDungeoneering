package io.github.itstaylz.auradungeoneering.listeners;

import dev.aurelium.auraskills.api.AuraSkillsApi;
import dev.aurelium.auraskills.api.source.SkillSource;
import io.github.itstaylz.auradungeoneering.AuraDungeoneeringConfig;
import io.github.itstaylz.auradungeoneering.DungeoneeringCustomContent;
import io.github.itstaylz.auradungeoneering.sources.CompletingDungeonSource;
import net.playavalon.mythicdungeons.api.events.dungeon.PlayerFinishDungeonEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Calendar;

public class DungeonListener implements Listener {

    private final DungeoneeringCustomContent contentLoader;
    private final AuraDungeoneeringConfig config;
    private final AuraSkillsApi auraSkills;

    public DungeonListener(DungeoneeringCustomContent contentLoader, AuraDungeoneeringConfig config) {
        this.contentLoader = contentLoader;
        this.auraSkills = AuraSkillsApi.get();
        this.config = config;
    }

    @EventHandler
    private void onFinish(PlayerFinishDungeonEvent event) {
        Player player = event.getPlayer();
        SkillSource<CompletingDungeonSource> skillSource = this.auraSkills.getSourceManager().getSingleSourceOfType(CompletingDungeonSource.class);
        if (skillSource != null) {
            CompletingDungeonSource dungeonSource = skillSource.source();
            double xp = dungeonSource.getXp();
            double multiplier = this.config.getWeekdayXpMultiplier(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
            this.auraSkills.getUser(player.getUniqueId()).addSkillXp(this.contentLoader.getDungeoneeringSkill(), xp * multiplier, dungeonSource);
        }
    }
}
