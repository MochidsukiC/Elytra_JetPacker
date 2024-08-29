package jp.houlab.mochidsuki.elytra_jetpacker;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

import static jp.houlab.mochidsuki.elytra_jetpacker.Main.plugin;

public class JumpPad extends BukkitRunnable {
    static public HashMap<Location,Integer[]> JumpPadLocation = new HashMap<>();
    @Override
    public void run() {
        for(Location loc : JumpPadLocation.keySet()) {
            for(Player player : loc.getWorld().getPlayers()) {
                if(player.getLocation().distance(loc) <= 3) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,JumpPadLocation.get(loc)[0]*20, JumpPadLocation.get(loc)[1],true,false,false));
                    player.addScoreboardTag("JetPack");
                    new RemoveTag(player).runTaskTimer(plugin,1,1);
                    new JumpEffect(JumpPadLocation.get(loc)[0]*20,player).runTaskTimer(plugin,1,1);
                }
            }
        }
    }
}

class JumpEffect extends BukkitRunnable {
    int time;
    Player player;
    public JumpEffect(int time,Player player){
       this.time = time;
       this.player = player;
    }
    @Override
    public void run() {
        player.getWorld().spawnParticle(Particle.SPIT, player.getLocation(), 50, 0.1, 0.1, 0.1, 0.2);
        player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, player.getLocation(), 50, 0.2, 0.2, 0.2, 0);
        player.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, player.getLocation(), 50, 0.2, 0.2, 0.2, 0);
        player.getWorld().spawnParticle(Particle.SMOKE_NORMAL, player.getLocation(), 50, 0.2, 0.2, 0.2, 0);

        time--;
        if(time <= 0) {
            cancel();
        }
    }
}

class RemoveTag extends BukkitRunnable {
    private Player player;
    public RemoveTag(Player player) {
        this.player = player;
    }
    @Override
    public void run() {
        if(!player.getLocation().clone().add(0,-0.2,0).getBlock().getType().equals(Material.AIR)) {
            player.removeScoreboardTag("JetPack");
            cancel();
        }
    }
}