package de.luc1412.eai.utils;

import de.luc1412.eai.EasyAntiInsult;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DataLogger {

	Logger logger = Logger.getLogger("Offenses");

	public DataLogger() {
		try {
			FileHandler fileHandler = new FileHandler(EasyAntiInsult.getInstance().getDataFolder().toString() + "offenses.log");
			logger.addHandler(fileHandler);

			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(new SimpleFormatter());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void log(Player player, DataManager.DataType type) {
		logger.log(Level.INFO, type.getLogValue().replace("%player_name%", player.getName()).replace("%player_uuid%", player.getUniqueId().toString()));
	}
}
