package jp.houlab.mochidsuki.elytra_jetpacker;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * メインクラス
 */
public final class Main extends JavaPlugin {

    /**
     * 起動時の初期化処理
     */
    public static Plugin plugin;
    public static FileConfiguration config;
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getCommand("jumppad").setExecutor(new CommandListener());
        getCommand("elytrajetpacker").setExecutor(new CommandListener());

        new Flying().runTaskTimer(this,1,1);
        new JumpPad().runTaskTimer(this,1,1);


        saveDefaultConfig();
        config = getConfig();
    }

    /**
     * 終了
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
