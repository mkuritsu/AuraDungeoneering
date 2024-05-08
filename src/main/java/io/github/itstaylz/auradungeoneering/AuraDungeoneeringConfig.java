package io.github.itstaylz.auradungeoneering;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AuraDungeoneeringConfig {

    private final YamlConfiguration config;
    private final Map<String, Double> weekdaysXpMultipliers = new HashMap<>();

    public AuraDungeoneeringConfig(JavaPlugin plugin) {
        plugin.saveResource("config.yml", false);
        this.config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "config.yml"));
        ConfigurationSection section = this.config.getConfigurationSection("weekdays_xp_multiplier");
        for (String weekday : section.getKeys(false)) {
            this.weekdaysXpMultipliers.put(weekday.toLowerCase(), section.getDouble(weekday));
        }
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

    public double getWeekdayXpMultiplier(int weekday) {
        return switch (weekday) {
            case Calendar.SUNDAY -> this.weekdaysXpMultipliers.getOrDefault("sunday", 1.0);
            case Calendar.MONDAY -> this.weekdaysXpMultipliers.getOrDefault("monday", 1.0);
            case Calendar.TUESDAY -> this.weekdaysXpMultipliers.getOrDefault("tuesday", 1.0);
            case Calendar.WEDNESDAY -> this.weekdaysXpMultipliers.getOrDefault("wednesday", 1.0);
            case Calendar.THURSDAY -> this.weekdaysXpMultipliers.getOrDefault("thursday", 1.0);
            case Calendar.FRIDAY -> this.weekdaysXpMultipliers.getOrDefault("friday", 1.0);
            case Calendar.SATURDAY -> this.weekdaysXpMultipliers.getOrDefault("saturday", 1.0);
            default -> 1.0;
        };
    }

}
