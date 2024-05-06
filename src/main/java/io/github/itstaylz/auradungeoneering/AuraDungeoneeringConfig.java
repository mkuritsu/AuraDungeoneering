package io.github.itstaylz.auradungeoneering;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class AuraDungeoneeringConfig {

    private final YamlConfiguration config;

    public AuraDungeoneeringConfig(JavaPlugin plugin) {
        plugin.saveResource("config.yml", false);
        this.config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "config.yml"));
    }

    public ConfigurationSection skill(String name) {
        return this.config.getConfigurationSection("skills." + name);
    }

    public ConfigurationSection trait(String name) {
        return this.config.getConfigurationSection("traits." + name);
    }

    public ConfigurationSection stat(String name) {
        return this.config.getConfigurationSection("stats." + name);
    }

}
