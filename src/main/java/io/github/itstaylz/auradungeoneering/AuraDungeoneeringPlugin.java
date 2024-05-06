package io.github.itstaylz.auradungeoneering;

import io.github.itstaylz.auradungeoneering.listeners.DungeonListener;
import io.github.itstaylz.auradungeoneering.listeners.MythicMobsListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AuraDungeoneeringPlugin extends JavaPlugin implements Listener {

    private final AuraDungeoneeringConfig config = new AuraDungeoneeringConfig(this);
    private final AuraCustomContentLoader contentLoader = new AuraCustomContentLoader(this, config);

    @Override
    public void onEnable() {
        saveResource("sources/dungeoneering.yml", false);
        saveResource("rewards/dungeoneering.yml", false);
        this.contentLoader.register();
        registerEvents();
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new DungeonListener(this.contentLoader), this);
        pm.registerEvents(new MythicMobsListener(), this);
    }
}
