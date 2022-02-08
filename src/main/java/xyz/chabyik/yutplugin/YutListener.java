package xyz.chabyik.yutplugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import xyz.chabyik.yutplugin.data.YutData;
import xyz.chabyik.yutplugin.thread.YutThrow;

import java.util.Arrays;

public class YutListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (
                (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                e.getHand() == EquipmentSlot.HAND &&
                e.getItem() != null &&
                YutData.isEnabled
        ) {
            if (e.getItem().getType() == Material.STICK && !YutData.isThrew) {
                Bukkit.broadcast(Component.text(e.getPlayer().getName() + "님이 윷을 던졌습니다!"));
                YutThrow yutThrow = new YutThrow();
                yutThrow.start();
            } else if (e.getItem().getType() == Material.ARROW) {
                Player player = e.getPlayer();
                Entity entity = player.getTargetEntity(50, true);

                if (entity != null && entity.hasMetadata("yut_horse") && !(YutData.horseTeleport.holding.containsKey(player))) {
                    YutData.horseTeleport.holding.put(player, entity);
                    YutData.horseTeleport.distances.put(player, player.getLocation().distance(entity.getLocation()));
                } else if (YutData.horseTeleport.holding.containsKey(player)) {
                    YutData.horseTeleport.holding.remove(player);
                    YutData.horseTeleport.distances.remove(player);
                }
            } else if (e.getItem().getType() == Material.PAPER && e.getItem().getItemMeta() != null && e.getItem().getItemMeta().displayName() != null) {
                TextComponent nameComp = (TextComponent) e.getItem().getItemMeta().displayName();
                String[] items = { "도박", "인생역전", "나락" };
                String playerName = e.getPlayer().getName();
                String name = ChatColor.stripColor(nameComp.content());

                if (Arrays.asList(items).contains(name)) {
                    switch (name) {
                        case "도박" -> YutData.dobak = playerName;
                        case "인생역전" -> YutData.turningPoint = playerName;
                        case "나락" -> YutData.narak = playerName;
                    }

                    Bukkit.broadcast(Component.text(playerName + "님이 [ " + name + " ] 을(를) 사용했습니다!"));
                    e.getItem().setAmount(e.getItem().getAmount() - 1);
                }
            }
        }
    }
}
