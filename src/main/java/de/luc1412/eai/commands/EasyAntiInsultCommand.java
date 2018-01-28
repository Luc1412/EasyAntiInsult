package de.luc1412.eai.commands;

import de.luc1412.eai.EasyAntiInsult;
import de.luc1412.eai.utils.TitleAnimation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Luc1412 on 26.10.2017 at 20:57
 */
public class EasyAntiInsultCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (args.length == 0) {
				if (player.hasPermission("eai.use")) {

				} else {
					//TODO: HAS DONATOR
					if (false) {
						player.sendMessage(EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("NoPermissions", true));
						return false;
					}
					long cd = EasyAntiInsult.getCommandCooldownManager().isPlayerInCooldown(player);
					if (cd > 0L) {
						//player.sendMessage(EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("CooldownMessage", true));
						return false;
					}
					EasyAntiInsult.getCommandCooldownManager().addPlayer(player);
				}
				new TitleAnimation(player);

				String[] messages = new String[1];
				messages[0] = "§f§l┌§0§l─§f§l─§0§l─§f§l─§0§l┤§1§lEasy§4§lMaintenance §3§lby Luc1412§f§l├§0§l─§f§l─§0§l─§f§l┤";
				//EasyAntiInsult.getUtils().translateMessagesToCfgValues(messages);
				player.sendMessage(" ");
				player.sendMessage(" ");
				player.sendMessage(" ");
				player.sendMessage(" ");
				player.sendMessage(messages[0]);
			} else {
				if (args[0].equals("reload")) {

				}
			}
		}
		return false;
	}
}

/*
COMMANDS:
	/EASYANTIINSULT HELP
	/EASYANTIINSULT INFO
	/EASYANTIINSULT SEND <LANGUAGE> <WORD>
	/EASYANTIINSULT LOCAL ADD
	/EASYANTIINSULT RELOAD
	/EASYANTIINSULT CHATCLEAR/CC
	/EASYANTIINUSLT SLOWCHAT [ON/OFF]
	/EASYANTIINSULT CHATMUTE [ON/OFF]
 */
