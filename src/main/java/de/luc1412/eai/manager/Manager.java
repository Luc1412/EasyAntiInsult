package de.luc1412.eai.manager;

import org.bukkit.entity.Player;

/**
 * Created by Luc1412 on 05.12.2017 at 14:07
 */
public abstract class Manager {

	Manager() {

	}

	public void initialize() {
		init();
	}

	public abstract void init();

	public abstract boolean isForbidden(Player player, String message);

	public abstract void punishPlayer(Player player);

	public abstract void disable();

	public abstract void reload();

}
