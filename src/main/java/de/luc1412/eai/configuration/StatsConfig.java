package de.luc1412.eai.configuration;

import de.luc1412.eai.EasyAntiInsult;
import de.luc1412.eai.utils.DataManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * Created by Luc1412 on 03.11.2017 at 22:47
 */
public class StatsConfig {

	private File file = new File(EasyAntiInsult.getInstance().getDataFolder(), "stats.yml");
	private YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

	public StatsConfig() {
		try {
			createConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createConfig() throws IOException {
		if (!EasyAntiInsult.getInstance().getDataFolder().exists())
			EasyAntiInsult.getInstance().getDataFolder().mkdir();
		if (!file.exists()) file.createNewFile();

		cfg.options().header(
				"# #############################################################################################\n" +
						"# #################################### THANK YOU FOR USING ####################################\n" +
						"# ################################# EasyAntiInsult BY LUC1412 #################################\n" +
						"# #############################################################################################\n" +
						"# #################################### STATS CONFIG ###########################################\n" +
						"# #############################################################################################\n" +
						"#\n"
		).copyHeader(true);

		cfg.set("Data", null);

		saveConfig();
	}

	public void addPlayer(Player player) {
		System.out.println(1);
		if (!isPlayerExists(player)) {
			System.out.println(2);
			cfg.set("Data." + player.getUniqueId().toString() + ".Global.GlobalCount", 0);
			cfg.set("Data." + player.getUniqueId().toString() + ".Ads.AdsCount", 0);
			cfg.set("Data." + player.getUniqueId().toString() + ".Caps.CapsCount", 0);
			cfg.set("Data." + player.getUniqueId().toString() + ".Insults.InsultsCount", 0);
			cfg.set("Data." + player.getUniqueId().toString() + ".Spam.SpamCount", 0);
			cfg.set("Data." + player.getUniqueId().toString() + ".Unicode.UnicodeCount", 0);
			cfg.set("Data." + player.getUniqueId().toString() + ".Info.Name", player.getName());
			cfg.set("Data." + player.getUniqueId().toString() + ".Info.LastAddress", player.getAddress().getHostName());

			saveConfig();
		}
	}

	public int getData(Player player, DataManager.DataType type) {
		if (isPlayerExists(player)) {
			return cfg.getInt("Data." + player.getUniqueId().toString() + "." + type.getValue() + "." + type.getValue() + "Count");
		}
		return -1;
	}

	public void setData(Player player, DataManager.DataType type, int data) {
		if (isPlayerExists(player)) {
			cfg.set("Data." + player.getUniqueId().toString() + "." + type.getValue() + "." + type.getValue() + "Count", data);
			saveConfig();
		}
	}

	private void saveConfig() {
		try {
			cfg.save(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private boolean isPlayerExists(Player player) {
		System.out.println(cfg.getString("Data." + player.getUniqueId().toString() + ".Info.Name"));
		return cfg.getString("Data." + player.getUniqueId().toString() + ".Info.Name") != null;
	}

}
