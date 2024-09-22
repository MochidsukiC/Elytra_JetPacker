package jp.houlab.mochidsuki.elytra_jetpacker;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import static jp.houlab.mochidsuki.elytra_jetpacker.Main.config;
import static jp.houlab.mochidsuki.elytra_jetpacker.Main.plugin;

/**
 * プレイヤーをジェットパックにて降下させるエフェクト、物理演算を司るクラス。
 * @author Mochidsuki
 */
public class Flying extends BukkitRunnable {
    /**
     * 実行
     */
    @Override
    public void run() {
        for (Player player : plugin.getServer().getOnlinePlayers()){
            if(player.isGliding()&&player.getScoreboardTags().contains("JetPack")){
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,5,0,false,false,false));

                Location loc = player.getLocation().clone();

                loc.add(loc.getDirection().multiply(-1));

                player.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, loc, 50, 0.1, 0.1, 0.1, 0.2);
                player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc, 50, 0.2, 0.2, 0.2, 0);
                player.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, loc, 50, 0.2, 0.2, 0.2, 0);
                player.getWorld().spawnParticle(Particle.SMOKE_NORMAL, loc, 50, 0.2, 0.2, 0.2, 0);

                player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 0.5f, 0);

                if (player.getLocation().getPitch() > 0) {
                    player.setVelocity(player.getLocation().getDirection().normalize().multiply(config.getDouble("elytra_x_vector")));
                    Vector v = player.getVelocity();
                    v.add(new Vector(0, config.getDouble("elytra_y_vector"), 0));
                    player.setVelocity(v);
                } else {
                    player.getLocation().setPitch(-1f);
                    player.setVelocity(player.getLocation().getDirection().normalize().multiply(config.getDouble("elytra_x_vector") / 4));
                    Vector v = player.getVelocity();
                    v.add(new Vector(0, config.getDouble("elytra_y_vector"), 0));
                    player.setVelocity(v);
                }

            }
        }
    }
}
