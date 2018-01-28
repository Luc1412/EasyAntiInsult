package de.luc1412.eai.listener;

import de.luc1412.eai.EasyAntiInsult;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Luc1412 on 26.10.2017 at 21:34
 */
public class JoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		EasyAntiInsult.getDataManager().addPlayer(player);

		/*
		if (EasyAntiInsult.getUtils().isDefaultConfigExists()) {
			if (EasyAntiInsult.getDefaultConfig().getBoolean("Updater")) {
				if (player.hasPermission("eai.use")) {
					String latestVersion = EasyAntiInsult.getUtils().getLatestVersion("");
					String currentVersion = EasyAntiInsult.getInstance().getDescription().getVersion();
					if (!latestVersion.equals(currentVersion)) {
						String[] messages = new String[7];
						messages[0] = "§f§l┌§0§l─§f§l─§0§l─§f§l─§0§l┤§1§lEasy§4§lMaintenance §3§lby Luc1412§f§l├§0§l─§f§l─§0§l─§f§l┤";
						messages[1] = "§0§l│ §3§lAn update is available!";
						messages[2] = "§f§l│ §3§lPlugin: §b" + EasyAntiInsult.getInstance().getDescription().getName() + " v" + currentVersion;
						messages[3] = "§0§l│ §3§lLatest Version: §bv" + latestVersion;
						messages[4] = "§f§l│ §3§lDownload-Link:";
						messages[5] = "§0§l│ §bhttps://goo.gl/7hdKxA";
						messages[6] = "§f§l└§0§l─§f§l─§0§l─§f§l─§0§l┤§1§lEasy§4§lMaintenance §3§lby Luc1412§f§l├§0§l─§f§l─§0§l─§f§l┤";
						messages = EasyAntiInsult.getUtils().translateMessagesToCfgValues(messages);
						player.sendMessage(" ");
						player.sendMessage(" ");
						player.sendMessage(" ");
						player.sendMessage(" ");
						for (String message : messages) player.sendMessage(message);
					}
				}
			}
		}
		*/
	}

}
