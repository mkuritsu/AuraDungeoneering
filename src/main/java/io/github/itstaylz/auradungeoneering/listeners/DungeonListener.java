package io.github.itstaylz.auradungeoneering.listeners;

import dev.aurelium.auraskills.api.AuraSkillsApi;
import dev.aurelium.auraskills.api.source.SkillSource;
import io.github.itstaylz.auradungeoneering.AuraCustomContentLoader;
import io.github.itstaylz.auradungeoneering.sources.CompleteDungeonSource;
import net.playavalon.mythicdungeons.api.events.dungeon.PlayerFinishDungeonEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DungeonListener implements Listener {

    private final AuraCustomContentLoader contentLoader;
    private final AuraSkillsApi auraSkills;

    public DungeonListener(AuraCustomContentLoader contentLoader) {
        this.contentLoader = contentLoader;
        this.auraSkills = AuraSkillsApi.get();
    }

    @EventHandler
    private void onFinish(PlayerFinishDungeonEvent event) {
        Player player = event.getPlayer();
        SkillSource<CompleteDungeonSource> skillSource = this.auraSkills.getSourceManager().getSingleSourceOfType(CompleteDungeonSource.class);
        if (skillSource != null) {
            CompleteDungeonSource dungeonSource = skillSource.source();
            double xp = dungeonSource.getXp();
            this.auraSkills.getUser(player.getUniqueId()).addSkillXp(this.contentLoader.getSkill().getSkill(), xp, dungeonSource);
        }
    }
}
