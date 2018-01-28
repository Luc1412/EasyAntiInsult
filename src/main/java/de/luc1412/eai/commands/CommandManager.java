package de.luc1412.eai.commands;

import de.luc1412.eai.EasyAntiInsult;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandManager {

	public void clearChat(Player player) {
		Thread t = new Thread(() -> {
			int rows = EasyAntiInsult.getDefaultConfig().getInt("ChatClear.ClearedRows");
			for (int i = 0; i < rows; i++) {
				player.sendMessage(" ");
			}
			player.sendMessage(EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("ChatClear.EndRow", true));
		});
		t.start();
	}

	public void clearChat() {
		Thread t = new Thread(() -> {
			int rows = EasyAntiInsult.getDefaultConfig().getInt("ChatClear.ClearedRows");
			for (Player all : Bukkit.getOnlinePlayers()) {
				for (int i = 0; i < rows; i++) {
					all.sendMessage(" ");
				}
				all.sendMessage(EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("ChatClear.EndRow", true));
			}
		});
		t.start();
	}

	public boolean getMute() {
		return false;
	}

	public void setMute(boolean b) {

	}

	public boolean getSlow() {
		return false;
	}

	public void setSlow(boolean b) {

	}
}
