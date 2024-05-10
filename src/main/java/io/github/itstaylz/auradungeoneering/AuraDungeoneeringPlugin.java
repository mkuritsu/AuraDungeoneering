package io.github.itstaylz.auradungeoneering;

import io.github.itstaylz.auradungeoneering.listeners.DungeonListener;
import io.github.itstaylz.auradungeoneering.manager.BossManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AuraDungeoneeringPlugin extends JavaPlugin implements Listener {

    private AuraDungeoneeringConfig config;
    private DungeoneeringCustomContent contentLoader;

    @Override
    public void onEnable() {
        saveResource("sources/dungeoneering.yml", false);
        saveResource("rewards/dungeoneering.yml", false);
        saveResource("skills.yml", false);
        saveResource("stats.yml", false);
        this.config = new AuraDungeoneeringConfig(this);
        this.config.getBosses().forEach(BossManager.getInstance()::addBoss);
        this.contentLoader = new DungeoneeringCustomContent(this, config);
        this.contentLoader.register();
        registerEvents();
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new DungeonListener(this.contentLoader, this.config), this);
    }
}
