package jp.houlab.mochidsuki.elytra_jetpacker;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * ジェットパック中であることを指すタグを削除する
 */
public class RemoveTag extends BukkitRunnable {
    private Player player;
    public RemoveTag(Player player) {
        this.player = player;
    }

    /**
     * 実行
     */
    @Override
    public void run() {
        if(!player.getLocation().clone().add(0,-0.2,0).getBlock().getType().equals(Material.AIR)) {
            player.removeScoreboardTag("JetPack");
            cancel();
        }
    }
}
