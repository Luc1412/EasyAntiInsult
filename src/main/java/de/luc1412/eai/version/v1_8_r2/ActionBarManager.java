package de.luc1412.eai.version.v1_8_r2;

import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by Luc1412 on 26.07.2017 at 11:32
 */
public class ActionBarManager {

	public void sendBar(Player p, String msg) {
		IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
		PacketPlayOutChat packet = new PacketPlayOutChat(icbc, (byte) 2);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}
}
