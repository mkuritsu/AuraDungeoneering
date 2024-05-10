package io.github.itstaylz.auradungeoneering.manager;

import io.lumine.mythic.core.mobs.ActiveMob;

import java.util.HashSet;
import java.util.Set;

public final class BossManager {

    private static BossManager instance;

    private final Set<String> bosses = new HashSet<>();

    private BossManager() {
        if (instance != null)
            throw new UnsupportedOperationException("Cannot create instance of " + BossManager.class.getSimpleName());
    }

    public void addBoss(String name) {
        this.bosses.add(name);
    }

    public void removeBoss(String name) {
        this.bosses.remove(name);
    }

    public Set<String> getBosses() {
        return this.bosses;
    }

    public boolean isBoss(ActiveMob activeMob) {
        return this.bosses.contains(activeMob.getType().getInternalName());
    }

    public static synchronized BossManager getInstance() {
        if (instance == null)
            instance = new BossManager();
        return instance;
    }
}
