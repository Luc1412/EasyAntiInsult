package de.luc1412.eai.configuration;

import de.luc1412.eai.EasyAntiInsult;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by Luc1412 on 26.10.2017 at 21:03
 */
public class DefaultConfig {

	private static Map<String, Object> cfgValues;
	private File file = new File(EasyAntiInsult.getInstance().getDataFolder(), "config.yml");
	private YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

	public DefaultConfig() {
		loadConfigValues();
	}

	private void loadConfigValues() {
		cfgValues = cfg.getValues(true);
	}

	public int getInt(String path) {
		return (int) cfgValues.get(path);
	}

	public double getDouble(String path) {
		return (double) cfgValues.get(path);
	}

	public String getString(String path) {
		return (String) cfgValues.get(path);
	}

	public boolean getBoolean(String path) {
		return (boolean) cfgValues.get(path);
	}

	public List<String> getStringList(String path) {
		return (List<String>) cfgValues.get(path);
	}

	public void reloadConfig() {
		cfg = YamlConfiguration.loadConfiguration(file);
		loadConfigValues();
	}


}
