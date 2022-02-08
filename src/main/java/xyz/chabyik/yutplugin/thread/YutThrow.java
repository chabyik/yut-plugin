package xyz.chabyik.yutplugin.thread;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import xyz.chabyik.yutplugin.data.YutData;

import java.time.Duration;
import java.util.Random;

public class YutThrow extends Thread {
    @Override
    public void run() {
        YutData.isThrew = true;

        try {
            Title.Times time = Title.Times.of(Duration.ofSeconds(0), Duration.ofSeconds(1), Duration.ofSeconds(1));
            String[] yut = { "도", "개", "걸", "윷", "모", "백도" };
            int num = new Random().nextInt(6);

            if (YutData.turningPoint != null) yut = new String[] { "윷", "모" };
            if (YutData.dobak != null) yut = new String[] { "모", "백도" };
            if (YutData.turningPoint != null || YutData.dobak != null) num = new Random().nextInt(2);

            if (YutData.dobak == null || YutData.turningPoint == null) {
                for (int i = 60; i >= 1; i--) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        String subTitle = "";
                        if (YutData.turningPoint != null) subTitle = YutData.turningPoint + " - 인생역전";
                        if (YutData.dobak != null) subTitle = YutData.dobak + " - 도박";

                        all.showTitle(Title.title(Component.text("[ " + yut[num] + " ]"), Component.text(subTitle), time));
                        all.playSound(all, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f);
                    }

                    if (YutData.dobak == null && YutData.turningPoint == null) {
                        if (num == 5) num = 0;
                        else num++;
                    } else {
                        num = num == 0 ? 1 : 0;
                    }
                    Thread.sleep(Math.round((1.0 / i) * 1000));
                }
            }

            for (Player all : Bukkit.getOnlinePlayers()) {
                if (YutData.dobak != null && YutData.turningPoint != null) num = 0;

                boolean yutMo = yut[num].equals("윷") || yut[num].equals("모");
                boolean isNarak = YutData.narak != null;
                String subTitle = "";
                if (yutMo && isNarak) subTitle = YutData.narak + " - 나락 (무효)";
                if (yutMo && !isNarak) subTitle = "한 번 더!";
                if (YutData.dobak != null && YutData.turningPoint != null) subTitle = YutData.dobak + " - 도박, " + YutData.turningPoint + " - 인생역전, 한 번 더!";
                if (YutData.dobak != null && YutData.turningPoint != null && isNarak) subTitle = YutData.dobak + " - 도박, " + YutData.turningPoint + " - 인생역전, " + YutData.narak + " - 나락 (무효)";

                all.showTitle(Title.title(Component.text((yutMo && isNarak ? ChatColor.RED : ChatColor.GREEN) + "[ " + yut[num] + " ]"), Component.text(subTitle), time));
                all.playSound(all, yutMo && isNarak ? Sound.ITEM_TOTEM_USE : Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 1f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        YutData.dobak = null;
        YutData.turningPoint = null;
        YutData.narak = null;
        YutData.isThrew = false;
    }
}
