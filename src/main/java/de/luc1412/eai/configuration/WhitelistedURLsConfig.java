package de.luc1412.eai.configuration;

import de.luc1412.eai.EasyAntiInsult;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luc1412 on 29.10.2017 at 23:25
 */
public class WhitelistedURLsConfig {

	private File file = new File(EasyAntiInsult.getInstance().getDataFolder(), "WhitelistedURLs.yml");
	private YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

	public WhitelistedURLsConfig() {
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
						"# #################################### URL WHITELIST CONFIG ###################################\n" +
						"# #############################################################################################\n" +
						"#\n" +
						"Please enter here the URLs wich are whitelisted and didn't get blocked/punished.\n" +
						"You must enable this whitelist in the Main Config!\n" +
						"# "
		).copyHeader(true);

		List<String> cfgDefaults = new ArrayList<>();
		cfgDefaults.add("Luc1412.tk");
		cfgDefaults.add("Google.de");
		cfgDefaults.add("Google.com");

		cfg.addDefault("WhitelistedURLs", cfgDefaults);

		cfg.options().copyDefaults(true);

		cfg.save(file);
	}

	public List<String> getWhitelisted() {
		return cfg.getStringList("WhitelistedURLs");
	}

	public void relaodConfig() {
		cfg = YamlConfiguration.loadConfiguration(file);
	}

}
