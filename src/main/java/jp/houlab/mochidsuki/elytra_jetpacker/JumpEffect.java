package jp.houlab.mochidsuki.elytra_jetpacker;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * プレイヤーが発射されるエフェクトを司るクラス
 * @author Mochidsuki
 */
public class JumpEffect extends BukkitRunnable {
    int time;
    Player player;

    /**
     * コンストラクタ
     * @param time 上昇時間
     * @param player ターゲットのプレイヤー
     */
    public JumpEffect(int time,Player player){
       this.time = time;
       this.player = player;
    }

    /**
     * 実行
     */
    @Override
    public void run() {
        player.getWorld().spawnParticle(Particle.SPIT, player.getLocation(), 50, 0.1, 0.1, 0.1, 0.2);
        player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, player.getLocation(), 50, 0.2, 0.2, 0.2, 0);
        player.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, player.getLocation(), 50, 0.2, 0.2, 0.2, 0);
        player.getWorld().spawnParticle(Particle.SMOKE_NORMAL, player.getLocation(), 50, 0.2, 0.2, 0.2, 0);

        if(time%3 == 0){
            player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_GHAST_SHOOT, 0.5f, 0);
        }

        time--;
        if(time <= 0) {
            cancel();
        }
    }
}
