package de.luc1412.eai.configuration;

import de.luc1412.eai.EasyAntiInsult;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SettingsConfig {

	private File file = new File(EasyAntiInsult.getInstance().getDataFolder(), "Settings.yml");
	private YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

	public SettingsConfig() {
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
						"# ###################################### SETTINGS CONFIG ######################################\n" +
						"# #############################################################################################\n" +
						"#\n" +
						"PLEASE DONT CHANGE ANYTHING HERE!"
		).copyHeader(true);

		cfg.addDefault("ReplaceInsults", "");

		cfg.options().copyDefaults(true);

		cfg.save(file);
	}

	public void setValue(String path, Object value) {
		cfg.set(path, value);
		saveConfig();
	}

	public Object getValue(String path) {
		return cfg.get(path);
	}

	public void saveConfig() {
		try {
			cfg.save(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void relaodConfig() {
		cfg = YamlConfiguration.loadConfiguration(file);
	}

}
