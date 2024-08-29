package jp.houlab.mochidsuki.elytra_jetpacker;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Plugin plugin;
    public static FileConfiguration config;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new Listener(),this);
        plugin = this;
        getCommand("jumppad").setExecutor(new CommandListener());
        getCommand("elytrajetpacker").setExecutor(new CommandListener());

        new Flying().runTaskTimer(this,1,1);
        new JumpPad().runTaskTimer(this,1,1);


        saveDefaultConfig();
        config = getConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
