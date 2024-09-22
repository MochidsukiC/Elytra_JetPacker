package jp.houlab.mochidsuki.elytra_jetpacker;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static jp.houlab.mochidsuki.elytra_jetpacker.Main.plugin;

/**
 * コマンドリスナー
 * @author Mochidsuki
 */
public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(s.equalsIgnoreCase("jumppad")){
            try{
                BlockCommandSender block = (BlockCommandSender) commandSender;
                Location loc = block.getBlock().getLocation();

                Integer[] i = new Integer[2];
                i[0] = Integer.valueOf(strings[0]);
                i[1] = Integer.valueOf(strings[1]);

                JumpPad.JumpPadLocation.put(loc,i);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        if(s.equalsIgnoreCase("elytrajetpacker")){
            if(strings[0].equalsIgnoreCase("reload")){
                JumpPad.JumpPadLocation.clear();
            }
            if(strings[0].equalsIgnoreCase("openjet")){
                for(Player player : plugin.getServer().getOnlinePlayers()){
                    player.setGliding(true);
                    player.addScoreboardTag("JetPack");
                    new RemoveTag(player).runTaskTimer(plugin,0L,1L);
                }
            }
        }
        return false;
    }
}
