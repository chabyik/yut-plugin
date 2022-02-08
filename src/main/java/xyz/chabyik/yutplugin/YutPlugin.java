package xyz.chabyik.yutplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.chabyik.yutplugin.data.Horse;
import xyz.chabyik.yutplugin.data.YutData;

public class YutPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new YutListener(), this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, YutData.horseTeleport, 0, 1);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("yut") && sender.isOp()) {
            if (args.length == 0) return false;

            if (args[0].equals("toggle")) {
                YutData.isEnabled = !YutData.isEnabled;
            } else if (args[0].equals("summon") && YutData.isEnabled) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    for (int i = 0; i < 3; i++) {
                        ArmorStand armor = (ArmorStand) all.getWorld().spawnEntity(all.getLocation(), EntityType.ARMOR_STAND);
                        armor.setGravity(false);
                        armor.setArms(true);
                        armor.setBasePlate(false);
                        armor.setMetadata("yut_horse", new Horse(this));

                        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
                        skullMeta.setOwningPlayer(all);
                        skull.setItemMeta(skullMeta);

                        armor.setItem(EquipmentSlot.HEAD, skull);
                        armor.setItem(EquipmentSlot.CHEST, new ItemStack(Material.LEATHER_CHESTPLATE));
                        armor.setItem(EquipmentSlot.LEGS, new ItemStack(Material.LEATHER_LEGGINGS));
                        armor.setItem(EquipmentSlot.FEET, new ItemStack(Material.LEATHER_BOOTS));
                    }
                }
            }
        }

        return true;
    }
}
