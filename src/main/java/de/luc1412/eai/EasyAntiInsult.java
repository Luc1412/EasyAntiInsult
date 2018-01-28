package de.luc1412.eai;

import de.luc1412.eai.commands.CommandCooldownManager;
import de.luc1412.eai.commands.EasyAntiInsultCommand;
import de.luc1412.eai.configuration.DefaultConfig;
import de.luc1412.eai.configuration.InsultReplaceConfig;
import de.luc1412.eai.configuration.StatsConfig;
import de.luc1412.eai.configuration.WhitelistedURLsConfig;
import de.luc1412.eai.listener.ChatListener;
import de.luc1412.eai.listener.JoinListener;
import de.luc1412.eai.manager.*;
import de.luc1412.eai.utils.DataManager;
import de.luc1412.eai.utils.DatabaseManager;
import de.luc1412.eai.utils.Installer;
import de.luc1412.eai.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luc1412 on 26.10.2017 at 14:23
 */
public class EasyAntiInsult extends JavaPlugin {

	public static final String PREFIX = "§1§lEasy§4§lAnti§6§lInsult §r";
	private static final List<Manager> managers = new ArrayList<>();
	public static int DATA_SAVING_MODE = -1;
	private static WhitelistedURLsConfig whitelistedURLsConfig;
	private static EasyAntiInsult instance;
	private static DefaultConfig defaultConfig;
	private static InsultReplaceConfig insultReplaceConfig;
	private static DatabaseManager databaseManager;
	private static Utils utils;
	private static CommandCooldownManager commandCooldownManager;
	private static DataManager dataManager;
	private static StatsConfig statsConfig;
	private boolean isFirstStart = false;

	public static void init() {
		defaultConfig = new DefaultConfig();
		getUtils().log("Initialize DefaultConfig...");

		whitelistedURLsConfig = new WhitelistedURLsConfig();
		getUtils().log("Initialize WhitelistedURLsConfig...");

		insultReplaceConfig = new InsultReplaceConfig();
		getUtils().log("Initialize InsultReplaceConfig...");

		if (defaultConfig.getBoolean("DataSaving.Enabled")) {
			getUtils().log("Data Saving is Enabled!");
			String mode = defaultConfig.getString("DataSaving.Mode");
			if (mode.equalsIgnoreCase("mysql")) {
				DATA_SAVING_MODE = 1;
				getUtils().log("Set the Data Saving Mode to MySQL");
				databaseManager = new DatabaseManager(defaultConfig.getString("DataSaving.MySQLMode.Host"), defaultConfig.getInt("DataSaving.MySQLMode.Port"), defaultConfig.getString("DataSaving.MySQLMode.Database"), defaultConfig.getString("DataSaving.MySQLMode.Username"), defaultConfig.getString("DataSaving.MySQLMode.Password"));
				getUtils().log("Initialize DataManager and connect to the Database...");
			} else if (mode.equalsIgnoreCase("file")) {
				DATA_SAVING_MODE = 0;
				getUtils().log("Set the Data Saving Mode to File");
				statsConfig = new StatsConfig();
			} else utils.sendError("THE DATA-SAVING MODE IS INVALID!");
		}

		EasyAntiInsult.getInstance().getCommand("easyantiinsult").setExecutor(new EasyAntiInsultCommand());
		getUtils().log("Register Commands...");

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new ChatListener(), EasyAntiInsult.getInstance());
		pm.registerEvents(new JoinListener(), EasyAntiInsult.getInstance());
		getUtils().log("Register Listener...");

		if (getDefaultConfig().getBoolean("AntiAdvertise.Enabled")) {
			managers.add(new AntiAdvertiseManager());
			getUtils().log("Initialize Anti-Advertise-Manager...");
		}
		if (getDefaultConfig().getBoolean("AntiCapsLock.Enabled")) {
			managers.add(new AntiCapsManager());
			getUtils().log("Initialize Anti-Caps-Manager...");
		}
		if (getDefaultConfig().getBoolean("AntiInsult.Enabled")) {
			managers.add(new AntiInsultManager());
			getUtils().log("Initialize Anti-Insult-Manager...");
		}
		if (getDefaultConfig().getBoolean("AntiSpam.Enabled")) {
			managers.add(new AntiSpamManager());
			getUtils().log("Initialize Anti-Spam-Manager...");
		}
		if (getDefaultConfig().getBoolean("AntiUnicode.Enabled")) {
			managers.add(new AntiUnicodeManager());
			getUtils().log("Initialize Anti-Unicode-Manager...");
		}

		for (Manager manager : managers) {
			manager.init();
		}
		getUtils().log("All Manager where successfully initialized!");
	}

	public static WhitelistedURLsConfig getWhitelistedURLsConfig() {
		return whitelistedURLsConfig;
	}

	public static InsultReplaceConfig getInsultReplaceConfig() {
		return insultReplaceConfig;
	}

	public static EasyAntiInsult getInstance() {
		return instance;
	}

	public static DefaultConfig getDefaultConfig() {
		return defaultConfig;
	}

	public static void setDefaultConfig(DefaultConfig defaultConfig) {
		EasyAntiInsult.defaultConfig = defaultConfig;
	}

	public static StatsConfig getStatsConfig() {
		return statsConfig;
	}

	public static DatabaseManager getDatabaseManager() {
		return databaseManager;
	}

	public static Utils getUtils() {
		return utils;
	}

	public static CommandCooldownManager getCommandCooldownManager() {
		return commandCooldownManager;
	}

	public static DataManager getDataManager() {
		return dataManager;
	}

	@Override
	public void onLoad() {
		instance = this;
		Bukkit.getConsoleSender().sendMessage(PREFIX + "§aThe Plugin was successfully loaded!");
	}

	@Override
	public void onEnable() {

		/*
		------------------------ UTILS ------------------------

		------------------ DEFAULT - CONFIG -------------------
		 */
		utils = new Utils();

		if (!getUtils().isDefaultConfigExists()) {
			isFirstStart = true;
		} else {
			defaultConfig = new DefaultConfig();
			getUtils().log("Initialize Utils...");
			getUtils().log("Initialize DefaultConfig...");
		}


		/*
		-------------------- DONATOR-CHECK --------------------
		 */
		dataManager = new DataManager();
		getUtils().log("Initialize DataManager...");

		/*
		-------------- COMMAND COOLDOWN MANGER ----------------
		 */
		commandCooldownManager = new CommandCooldownManager();
		getUtils().log("Initialize CommandCooldownManager");

		/*
		---------------------- INSTALLER ----------------------
		 */
		if (isFirstStart) {
			new Installer();
		} else {
			init();
		}

		Bukkit.getConsoleSender().sendMessage("\n" +
				"§9|§7~~~~~~~~~~~~~~~~~~~~~~§1Easy§4Anti§6Insult§7~~~~~~~~~~~~~~~~~~~~~§9|\n" +
				"§9|§1Easy§5Anti§dInsult §e" + this.getDescription().getVersion() + " §aBY LUC1412 WAS SUCCESSFULLY ENABLED!§9|\n" +
				"§9|§4ALL RIGHTS RESERVED BY LUC1412!                          §9|\n" +
				"§9|§4YOU ARE NOT ALLOWED TO DECOMPILE THIS PLUGIN!            §9|\n" +
				"§9|§7~~~~~~~~~~~~~~~~~~~~~~§1Easy§4Anti§6Insult§7~~~~~~~~~~~~~~~~~~~~~§9|");

		Bukkit.broadcastMessage("§a§lThe Update has been successfully installed!");
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.playSound(all.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1000, 0.1F);
		}
	}

	@Override
	public void onDisable() {

		for (Manager manager : managers) {
			manager.disable();
		}
		getUtils().log("All Manager where successfully disabled!");
	}
}
