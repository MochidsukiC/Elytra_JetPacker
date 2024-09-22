package jp.houlab.mochidsuki.elytra_jetpacker;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

import static jp.houlab.mochidsuki.elytra_jetpacker.Main.plugin;

/**
 * ジャンプパッドを実行するクラス
 * @author Mochidsuki
 */
public class JumpPad extends BukkitRunnable {
    static public HashMap<Location,Integer[]> JumpPadLocation = new HashMap<>();

    /**
     * 実行
     */
    @Override
    public void run() {
        for(Location loc : JumpPadLocation.keySet()) {
            for(Player player : loc.getWorld().getPlayers()) {
                int x = player.getLocation().getBlockX();
                int z = player.getLocation().getBlockZ();
                if(player.getLocation().distance(loc) <= 3 && x == loc.getBlockX() && z == loc.getBlockZ()) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,JumpPadLocation.get(loc)[0]*20, JumpPadLocation.get(loc)[1],true,false,false));
                    player.addScoreboardTag("JetPack");
                    new RemoveTag(player).runTaskTimer(plugin,20L,1);
                    new JumpEffect(JumpPadLocation.get(loc)[0]*20,player).runTaskTimer(plugin,1,1);
                }
            }
        }
    }
}

