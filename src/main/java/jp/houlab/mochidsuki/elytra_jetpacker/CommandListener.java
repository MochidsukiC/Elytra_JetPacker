package jp.houlab.mochidsuki.elytra_jetpacker;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
        }
        return false;
    }
}
