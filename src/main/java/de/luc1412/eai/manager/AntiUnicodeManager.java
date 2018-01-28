package de.luc1412.eai.manager;

import de.luc1412.eai.EasyAntiInsult;
import de.luc1412.eai.utils.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luc1412 on 05.12.2017 at 19:06
 */
public class AntiUnicodeManager extends Manager {

	private static AntiUnicodeManager instance;
	private List<Integer> whitelistedChars;

	public AntiUnicodeManager() {
		instance = this;
	}

	public static AntiUnicodeManager getInstance() {
		return instance;
	}

	@Override
	public void init() {
		whitelistedChars = new ArrayList<>();

		StringBuilder whitelisted = new StringBuilder(" ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");

		if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Allowed.AllowUmlauts")) {
			//UMLAUTS
			whitelisted.append("ÄÖÜß");
		}

		if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Allowed.AllowBrackets")) {
			//BRACKETS
			whitelisted.append("{}[]()");
		}

		if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Allowed.AllowUnits")) {
			//UNITS
			whitelisted.append("€$¢£¥%");
		}

		if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Allowed.AllowPunctuationMarks")) {
			//PunctuationMarks
			whitelisted.append("!?,.;:_\"&\\`´'");
		}

		if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Allowed.AllowMathChars")) {
			//MATH CHARS
			whitelisted.append("+-*/<>~=");
		}

		if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Allowed.AllowMathSpecialChars")) {
			//MATH SPECIAL CHARS
			whitelisted.append("ø±×÷ƒºµ¼½¾¹²³‰");
		}

		if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Allowed.AllowSpanishChars")) {
			//SPANISH
			whitelisted.append("ñÑáÁéÉíÍóÓúÚ¿¡");
		}

		if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Allowed.AllowFrenchChars")) {
			//FRENCH
			whitelisted.append("àÀâÂæÆçÇèÈéÉêÊëËîÎïÏôÔœŒùÙûÛÿŸ»«");
		}

		if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Allowed.AllowCustomChars.Enabled")) {
			List<String> cfgList = EasyAntiInsult.getDefaultConfig().getStringList("AntiUnicode.Allowed.AllowCustomChars.Chars");
			for (String aCfgList : cfgList) {
				whitelisted.append(aCfgList);
			}
		}

		for (int i = 0; i < whitelisted.length(); i++) {
			int current = whitelisted.charAt(i);
			whitelistedChars.add(current);
		}
	}

	@Override
	public boolean isForbidden(Player player, String message) {
		for (int i = 0; i < message.length(); i++) {
			int current = message.charAt(i);
			if (!whitelistedChars.contains(current)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void punishPlayer(Player player) {
		int count = EasyAntiInsult.getDataManager().getData(player, DataManager.DataType.UNICODE);
		List<String> commands = EasyAntiInsult.getDefaultConfig().getStringList("AntiUnicode.Punishment.PunishmentCommands");
		String command = commands.get(count - 1);
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
	}

	@Override
	public void disable() {
		whitelistedChars = null;
	}

	@Override
	public void reload() {
		init();
	}
}
