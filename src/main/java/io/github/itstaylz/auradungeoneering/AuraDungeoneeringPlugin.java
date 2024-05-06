package io.github.itstaylz.auradungeoneering;

import io.github.itstaylz.auradungeoneering.listeners.DungeonListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AuraDungeoneeringPlugin extends JavaPlugin implements Listener {

    private final AuraCustomContentLoader contentLoader = new AuraCustomContentLoader(this);

    @Override
    public void onEnable() {
        saveResource("sources/dungeoneering.yml", true);
        this.contentLoader.register();
        registerEvents();
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents( new DungeonListener(this.contentLoader), this);
    }
}
