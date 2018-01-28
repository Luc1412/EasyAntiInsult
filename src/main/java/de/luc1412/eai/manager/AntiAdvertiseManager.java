package de.luc1412.eai.manager;

import de.luc1412.eai.EasyAntiInsult;
import de.luc1412.eai.utils.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class AntiAdvertiseManager extends Manager {

	public static AntiAdvertiseManager instance;

	private List<String> whitelisted;
	private String[] blacklisted;

	public AntiAdvertiseManager() {
		instance = this;
	}

	public static AntiAdvertiseManager getInstance() {
		return instance;
	}

	@Override
	public void init() {
		whitelisted = EasyAntiInsult.getWhitelistedURLsConfig().getWhitelisted();

		blacklisted = new String[]{
				".de", ".d e", ". de", ". d e",
				".eu", ".e u", ". eu", ". e u",
				".com", ". com", ". c om", ". co m", ".c om", ".co m",
				".org", ". org", ". o rg", ". or g", ".o rg", ".or g",
				".net", ". net", ". n et", ". ne t", ".n et", ".ne t",
				".tk", ". tk", ".t k", ". t k",
				".ml", ". ml", ".m l", ". m l",
				".ga", ". ga", ".g a", ". g a",
				".cf", ". cf", ".c f", ". c f",
				".gq", ". gq", ".g q", ". g q",
				".at", ".a t", ". at", ". a t",
				".ch", ".c h", ". ch", ". c h",
				".me", ".m e", ". me", ". m e",
				".us", ".u s", ". us", ". u s",
				".tv", ".t v", ". tv", ". t v"};
	}

	@Override
	public boolean isForbidden(Player player, String message) {
		message = message.toLowerCase();
		boolean isAd = false;

		for (String whitelistedString : whitelisted) {
			message = whitelistedString.replaceAll(whitelistedString, "");
			System.out.println(whitelistedString);
		}

		for (String blacklistedString : blacklisted) {
			System.out.println(blacklistedString);
			System.out.println(message);
			if (message.contains(blacklistedString)) {
				System.out.println("is ad");
				isAd = true;
			}
		}
		return isAd;
	}

	@Override
	public void punishPlayer(Player player) {
		int count = EasyAntiInsult.getDataManager().getData(player, DataManager.DataType.ADS);
		List<String> commands = EasyAntiInsult.getDefaultConfig().getStringList("AntiAdvertise.Punishment.PunishmentCommands");
		String command = commands.get(count - 1);
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
	}

	@Override
	public void disable() {
		whitelisted = null;
		blacklisted = null;
	}

	@Override
	public void reload() {
		init();
	}

}
