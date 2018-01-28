package de.luc1412.eai.configuration;

import de.luc1412.eai.EasyAntiInsult;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luc1412 on 30.10.2017 at 18:49
 */
public class InsultReplaceConfig {

	private File file = new File(EasyAntiInsult.getInstance().getDataFolder(), "ReplaceInsults.yml");
	private YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

	public InsultReplaceConfig() {
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
						"# ################################### INSULT REPLACE CONFIG ###################################\n" +
						"# #############################################################################################\n" +
						"#\n" +
						"Please enter here the Insults wich are blacklisted.\n" +
						"You must edit this config if you use the REPLACE mode.\n" +
						"You must format the values that the Plugin know what is the blacklisted word and with wich word\n" +
						"will replaced. Format:\n" +
						"blacklisted Word:ReplaceWord" +
						"# "
		).copyHeader(true);

		List<String> cfgDefaults = new ArrayList<>();
		cfgDefaults.add("fuck:nice");
		cfgDefaults.add("hacker:skiller");
		cfgDefaults.add("hacks:skills");
		cfgDefaults.add("fuck you:love you <3");
		cfgDefaults.add("asshole:nice dude");


		cfg.addDefault("ReplaceInsults", cfgDefaults);

		cfg.options().copyDefaults(true);

		cfg.save(file);
	}

	public List<String> getBlacklisted() {
		return cfg.getStringList("ReplaceInsults");
	}

	public void relaodConfig() {
		cfg = YamlConfiguration.loadConfiguration(file);
	}

}
