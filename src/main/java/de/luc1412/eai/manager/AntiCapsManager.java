package de.luc1412.eai.manager;

import de.luc1412.eai.EasyAntiInsult;
import de.luc1412.eai.utils.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class AntiCapsManager extends Manager {

	private static AntiCapsManager instance;
	private int maxUpperCasePercent;

	public AntiCapsManager() {
		instance = this;
	}

	public static AntiCapsManager getInstance() {
		return instance;
	}

	@Override
	public void init() {
		maxUpperCasePercent = EasyAntiInsult.getDefaultConfig().getInt("AntiCapsLock.MaxCapsPercent");
	}

	@Override
	public boolean isForbidden(Player player, String message) {
		return message.length() > 4 && getUpperCasePercent(message) > maxUpperCasePercent;
	}

	@Override
	public void punishPlayer(Player player) {
		int count = EasyAntiInsult.getDataManager().getData(player, DataManager.DataType.CAPS);
		List<String> commands = EasyAntiInsult.getDefaultConfig().getStringList("AntiCapsLock.Punishment.PunishmentCommands");
		String command = commands.get(count - 1);
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
	}

	@Override
	public void disable() {
		maxUpperCasePercent = -1;
	}

	@Override
	public void reload() {
		init();
	}

	private int getUpperCasePercent(String message) {
		int stringLenght = message.length();
		int capsLenght = 0;

		for (int i = 0; i < message.length(); i++) {
			if (Character.isUpperCase(message.charAt(i))) {
				capsLenght++;
			}
		}
		return 100 / stringLenght * capsLenght;
	}
}
