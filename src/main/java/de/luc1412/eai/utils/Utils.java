package de.luc1412.eai.utils;

import de.luc1412.eai.EasyAntiInsult;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by Luc1412 on 26.10.2017 at 21:01
 */
public class Utils {

	public Utils() {

	}

	public void sendError(String error) {
		Bukkit.broadcastMessage("§8[EasyAntiInsult] §4§lERROR: " + error + " PLUGIN WILL BE DISABLED IN 10 SECONDS!");
		Bukkit.getConsoleSender().sendMessage("§8[EasyAntiInsult] §4§lERROR: " + error + " PLUGIN WILL BE DISABLED IN 10 SECONDS!");
		Bukkit.getScheduler().scheduleAsyncDelayedTask(EasyAntiInsult.getInstance(), () -> Bukkit.getServer().getPluginManager().disablePlugin(EasyAntiInsult.getInstance()), 200);
	}

	public String getTranslatedMsgOfConfig(String path, boolean prefix) {
		String cache;
		if (prefix) {
			cache = getPrefix() + EasyAntiInsult.getDefaultConfig().getString(path).replaceAll("&", "§");
		} else cache = EasyAntiInsult.getDefaultConfig().getString(path).replaceAll("&", "§");
		return cache;
	}

	private String getPrefix() {
		switch (EasyAntiInsult.getDefaultConfig().getInt("PrefixStyle")) {
			case 1:
				return "§8|§1Easy§5Anti§dInsult§8| §3§l->§r ";
			case 2:
				return "§7[§1Easy§5Anti§dInsult§7]§r ";
			case 3:
				return "§7(§1Easy§5Anti§dInsult§7)§r ";
			case 4:
				return "§2§l【§1Easy§5Anti§dInsult§l§2§l】§r ";
			//case 5:
			//	if (EasyMaintenance.getDonatorChecker().hasDonator()) {
			//		return EasyMaintenance.getDonatorConfig().getString("CustomPrefix");
			//	} else return "§8|§1Easy§4Maintenance§8| §3§l->§r ";
			default:
				sendError("INVALID PREFIX-STYLE!");
				break;
		}
		return "";
	}

	public void log(String msg) {
		if (EasyAntiInsult.getDefaultConfig() != null) {
			if (EasyAntiInsult.getDefaultConfig().getBoolean("DebugLog")) {
				Bukkit.getLogger().log(Level.INFO, msg);
			}
		}
	}

	public void playSoundOfConfig(Player p, Location loc, String path, float volume) {
		try {
			String[] convertedStringArray = EasyAntiInsult.getDefaultConfig().getString(path).split(":");
			String configSound = convertedStringArray[0].toUpperCase();
			float configPitch = Float.parseFloat(convertedStringArray[1]);
			p.playSound(loc, Sound.valueOf(configSound), volume, configPitch);
		} catch (IllegalArgumentException | NullPointerException e) {
			sendError("INVALID SOUND! PATH: " + path);
		}
	}

	public String[] translateMessagesToCfgValues(String[] messages) {
		if (EasyAntiInsult.getDefaultConfig().getInt("MessagesStyle") == 2) {
			for (int i = 0; i < messages.length; i++) messages[i] = messages[i].replaceAll("§f", "§1");
			for (int i = 0; i < messages.length; i++) messages[i] = messages[i].replaceAll("§0", "§4");
		}
		if (EasyAntiInsult.getDefaultConfig().getInt("MessagesStyle") == 3)
			for (int i = 0; i < messages.length; i++) messages[i] = messages[i].replaceAll("§f", "§4");
		if (EasyAntiInsult.getDefaultConfig().getInt("MessagesStyle") == 4) {
			for (int i = 0; i < messages.length; i++) messages[i] = messages[i].replaceAll("§f", "§1");
			for (int i = 0; i < messages.length; i++) messages[i] = messages[i].replaceAll("§0", "§2");
		}
		if (EasyAntiInsult.getDefaultConfig().getInt("MessagesStyle") == 5) {
			for (int i = 0; i < messages.length; i++) messages[i] = messages[i].replaceAll("§f", "§4");
			for (int i = 0; i < messages.length; i++) messages[i] = messages[i].replaceAll("§0", "§2");
		}
		if (EasyAntiInsult.getDefaultConfig().getInt("MessagesStyle") == 6) {
			for (int i = 0; i < messages.length; i++) messages[i] = messages[i].replaceAll("§f", "§4");
			for (int i = 0; i < messages.length; i++) messages[i] = messages[i].replaceAll("§0", "§e");
		}
		return messages;
	}

	private String getCurrentVersionAsString() {
		return Bukkit.getServer().getClass().getPackage().getName().substring(Bukkit.getServer().getClass().getPackage().getName().lastIndexOf(46) + 1);
	}

	public int getCurrentVersionAsInt() {
		String chache;
		chache = getCurrentVersionAsString().substring(3, getCurrentVersionAsString().length());
		chache = chache.substring(0, chache.length() - 3);
		return Integer.valueOf(chache);
	}

	public boolean isDefaultConfigExists() {
		return new File(EasyAntiInsult.getInstance().getDataFolder(), "config.yml").exists();
	}


	private boolean isRedirected(Map<String, List<String>> header) {
		for (String hv : header.get(null)) {
			if (hv.contains(" 301 ") || hv.contains(" 302 ")) return true;
		}
		return false;
	}

	void downloadFileFromGitHub(String link, String filePath) {
		try {
			URL url = new URL(link);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			Map<String, List<String>> header = http.getHeaderFields();
			while (isRedirected(header)) {
				link = header.get("Location").get(0);
				url = new URL(link);
				http = (HttpURLConnection) url.openConnection();
				header = http.getHeaderFields();
			}
			InputStream input = http.getInputStream();
			byte[] buffer = new byte[4096];
			int n;
			OutputStream output = new FileOutputStream(new File(filePath));
			while ((n = input.read(buffer)) != -1) {
				output.write(buffer, 0, n);
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendTitle(Player player, String title, String subtitle, int fadeInTime, int stayTime, int fadeOutTime) throws Exception {
		Class<?> clazz = Class.forName("de.luc1412.eai.version." + getCurrentVersionAsString().toLowerCase() + ".TitleManager");
		Method titelMethod = clazz.getDeclaredMethod("sendTitle", Player.class, String.class, String.class, int.class, int.class, int.class);
		titelMethod.invoke(clazz.newInstance(), player, title, subtitle, fadeInTime, stayTime, fadeOutTime);
	}

	public void sendActionBar(Player player, String msg) throws Exception {
		Class<?> clazz = Class.forName("de.luc1412.eai.version." + getCurrentVersionAsString().toLowerCase() + ".ActionBarManager");
		Method barMethod = clazz.getDeclaredMethod("sendBar", Player.class, String.class);
		barMethod.invoke(clazz.newInstance(), player, msg);
	}

	public String getLatestVersion(String url) {
		String latest = "";
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			latest = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
			connection.disconnect();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return latest;
	}

	public InetAddress getCurrentIp(boolean criticalErr) {
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface ni = networkInterfaces.nextElement();
				Enumeration<InetAddress> nias = ni.getInetAddresses();
				while (nias.hasMoreElements()) {
					InetAddress ia = nias.nextElement();
					if (!ia.isLinkLocalAddress() && !ia.isLoopbackAddress() && ia instanceof Inet4Address) {
						return ia;
					}
				}
			}
		} catch (SocketException e) {
			if (criticalErr) {
				sendError("ERROR WHILE GETTING IP");
			} else {
				e.printStackTrace();
			}
		}
		return null;
	}
}
