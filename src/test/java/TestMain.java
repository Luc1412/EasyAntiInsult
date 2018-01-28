import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luc1412 on 28.10.2017 at 19:05
 */
public class TestMain extends JavaPlugin {

	File file = new File(this.getDataFolder(), "test.yml");
	YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

	@Override
	public void onEnable() {
		if (!file.exists()) try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<String> testList = new ArrayList<>();

		testList.add("test1");
		testList.add("€");
		testList.add("^°\"§$%&/()=?`´²³{[]}ß\\+*~#'-_.:,;<>|µ@");

		cfg.set("test.test1.Liste", testList);

		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
