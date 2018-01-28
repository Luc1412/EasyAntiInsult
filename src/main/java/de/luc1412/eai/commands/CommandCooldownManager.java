package de.luc1412.eai.commands;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Luc1412 on 26.10.2017 at 21:49
 */
public class CommandCooldownManager {

	private HashMap<Player, Long> cooldownPlayer;

	public CommandCooldownManager() {
		cooldownPlayer = new HashMap<>();
	}

	public void addPlayer(Player player) {
		if (!cooldownPlayer.containsKey(player)) {
			long currentMillis = System.currentTimeMillis();
			long cooldownMillis = currentMillis + TimeUnit.SECONDS.toMillis(10);
			cooldownPlayer.put(player, cooldownMillis);
		}
	}

	public long isPlayerInCooldown(Player player) {
		if (cooldownPlayer.containsKey(player)) {
			long currentMillis = System.currentTimeMillis();
			long playerMillis = cooldownPlayer.get(player);
			if (currentMillis >= playerMillis) {
				cooldownPlayer.remove(player);
			} else {
				long out = (currentMillis - playerMillis) * -1;
				out = TimeUnit.MILLISECONDS.toSeconds(out);
				return out;
			}
		}
		return 0L;
	}
}
