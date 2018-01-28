package de.luc1412.eai.manager;

import de.luc1412.eai.EasyAntiInsult;
import de.luc1412.eai.utils.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Luc1412 on 05.12.2017 at 14:16
 */
public class AntiSpamManager extends Manager {

	private static AntiSpamManager instance;
	private Map<Player, String> lastMessage;

	public AntiSpamManager() {
		instance = this;
	}

	public static AntiSpamManager getInstance() {
		return instance;
	}

	@Override
	public void init() {
		lastMessage = new HashMap<>();
	}

	@Override
	public boolean isForbidden(Player player, String message) {
		boolean isSpam = false;
		message = message.toLowerCase();

		if (lastMessage.containsKey(player)) {
			String oldMessage = lastMessage.get(player);

			if (message.equalsIgnoreCase(oldMessage)) {
				return true;
			}

			lastMessage.put(player, message);
		}

		lastMessage.put(player, message.toLowerCase());

		return false;
	}

	@Override
	public void punishPlayer(Player player) {
		int count = EasyAntiInsult.getDataManager().getData(player, DataManager.DataType.SPAM);
		List<String> commands = EasyAntiInsult.getDefaultConfig().getStringList("Ant");
		String command = commands.get(count - 1);
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
	}

	@Override
	public void disable() {
		lastMessage = null;
	}

	@Override
	public void reload() {
		init();
	}
}
