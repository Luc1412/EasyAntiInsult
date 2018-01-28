package de.luc1412.eai.manager;

import de.luc1412.eai.EasyAntiInsult;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.zeromq.ZMQ;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AntiInsultManager extends Manager {

	private static AntiInsultManager instance;

	public AntiInsultManager() {
		instance = this;
	}

	public static AntiInsultManager getInstance() {
		return instance;
	}

	@Override
	public void init() {

	}

	@Override
	public boolean isForbidden(Player player, String message) {

		ZMQ.Context context = ZMQ.context(1);

		ZMQ.Socket requester = context.socket(ZMQ.REQ);
		requester.setReceiveTimeOut(1000);
		requester.setLinger(0);

		requester.connect("tcp://Luc1412.tk:5580");

		try {
			String hostAdress = InetAddress.getLocalHost().getHostAddress();
			requester.send(message);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		String replyString = requester.recvStr(0);
		int replyInt;

		if (replyString.equalsIgnoreCase("toManyRequests")) {
			Bukkit.getConsoleSender().sendMessage("§8[§1EasyAntiInsult§8] §4You send to many requests per minute! If you wan't to send more requests you must buy Donator Version!");
			return false;
		}

		if (replyString != null) {
			replyInt = Integer.parseInt(replyString);
		} else {
			Bukkit.getConsoleSender().sendMessage("§8[§1EasyAntiInsult§8] §4There are currently Problems with the Server. Please notice the developer");
			return false;
		}

		return replyInt == 1;
	}

	@Override
	public void punishPlayer(Player player) {

	}

	@Override
	public void disable() {

	}

	@Override
	public void reload() {

	}

	public String replaceInsult(String message) {

		for (String s : EasyAntiInsult.getInsultReplaceConfig().getBlacklisted()) {
			String[] s2 = s.split(":");
			if (s2.length > 2)
				EasyAntiInsult.getUtils().sendError("IN REPLACE INSULT CONFIG IS ONE OR MORE : TO MUCH!");
			message = message.replaceAll("(?i)" + s2[0], s2[1]);
		}

		return message;
	}
}
