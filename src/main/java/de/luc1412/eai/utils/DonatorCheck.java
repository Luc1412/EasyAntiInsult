package de.luc1412.eai.utils;

import org.bukkit.Bukkit;
import org.zeromq.ZMQ;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Luc1412 on 30.10.2017 at 10:56
 */
public class DonatorCheck {

	private static boolean hasDonator = false;

	public DonatorCheck() {
		startClient();
	}

	private void startClient() {

		ZMQ.Context context = ZMQ.context(1);

		ZMQ.Socket requester = context.socket(ZMQ.REQ);
		requester.setReceiveTimeOut(5000);
		requester.setLinger(0);

		requester.connect("tcp://Luc1412.tk:5570");

		try {
			String request = InetAddress.getLocalHost().getHostAddress();
			Bukkit.getConsoleSender().sendMessage("§8[§1EasyAntiInsult§8] §b§lSend Request to the Auth Server with your Server IP \n" +
					"to check if you have §3Donator Version§b. Your IP: §3" + request);
			requester.send(request);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		String replyString = requester.recvStr(0);
		int replyInt = 0;

		if (replyString != null) {
			replyInt = Integer.parseInt(replyString);
		}

		if (replyInt == 1) {
			hasDonator = true;
			Bukkit.getConsoleSender().sendMessage("§8[§1EasyAntiInsult§8] §b§lThe §3§lDONATOR Version §bwas activated! Thank you for your Donation <3");
		} else {
			hasDonator = false;
			if (replyString != null) {
				Bukkit.getConsoleSender().sendMessage("§8[§1EasyAntiInsult§8] §b§lThe §3§lNORMAL Version §bwas activated!");
			} else {
				Bukkit.getConsoleSender().sendMessage("§8[§1EasyAntiInsult§8] §4§lFailed to Connect to the Auth Server. Use Normal Version!");
			}
		}

	}

	public void startServer() {

		ZMQ.Context context = ZMQ.context(1);

		ZMQ.Socket responder = context.socket(ZMQ.REP);
		responder.bind("tcp://*:5555");
		responder.setLinger(0);

	}

	public boolean hasDonator() {
		return hasDonator;
	}
}
