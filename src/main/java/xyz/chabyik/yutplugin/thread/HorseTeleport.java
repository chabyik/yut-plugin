package xyz.chabyik.yutplugin.thread;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import xyz.chabyik.yutplugin.data.YutData;

import java.util.HashMap;
import java.util.Map;

public class HorseTeleport extends Thread {
    public Map<Player, Double> distances = new HashMap<>();
    public Map<Player, Entity> holding = new HashMap<>();

    @Override
    public void run() {
        if (YutData.isEnabled) {
            for (Player player : holding.keySet()) {
                Location loc = player.getLocation();
                Vector dir = loc.getDirection();
                dir.normalize();
                dir.multiply(distances.get(player));
                loc.add(dir);
                holding.get(player).teleport(loc);
            }
        }
    }
}
