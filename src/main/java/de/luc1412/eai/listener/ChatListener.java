package de.luc1412.eai.listener;

import de.luc1412.eai.EasyAntiInsult;
import de.luc1412.eai.manager.AntiAdvertiseManager;
import de.luc1412.eai.manager.AntiCapsManager;
import de.luc1412.eai.manager.AntiUnicodeManager;
import de.luc1412.eai.utils.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by Luc1412 on 27.10.2017 at 16:48
 */
public class ChatListener implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String message = event.getMessage();
		EasyAntiInsult.getUtils().log("Start the check of " + player.getName() + "s Message: " + message);

		//TODO PERMISSION
		if (player.hasPermission("eai.bypass")) {
			EasyAntiInsult.getUtils().log("Player has Permissions. The Message will not be checked!");
			return;
		}

		// TEST FOR CAPS
		if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiCapsLock.Enabled")) {
			EasyAntiInsult.getUtils().log("AntiCapsLock check is Enabled!");
			if (AntiCapsManager.getInstance().isForbidden(player, message)) {
				EasyAntiInsult.getUtils().log("Message has CapsLock!");
				if (EasyAntiInsult.getDefaultConfig().getBoolean("DataSaving.Enabled")) {
					int amount = EasyAntiInsult.getDataManager().getData(player, DataManager.DataType.CAPS);
					amount++;
					EasyAntiInsult.getDataManager().setData(player, DataManager.DataType.CAPS, amount);
					EasyAntiInsult.getUtils().log("Set Player Stats +1!");
				}
				if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiCapsLock.Punishment.Enabled")) {
					AntiCapsManager.getInstance().punishPlayer(player);
					EasyAntiInsult.getUtils().log("Punishing Player for using Caps!");
				}

				if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiCapsLock.Blocking")) {
					event.setCancelled(true);
					EasyAntiInsult.getUtils().log("Blocking Message of the player!");
				}

				if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiCapsLock.Notify.NotifyTeamMember.Enabled")) {
					EasyAntiInsult.getUtils().log("Notify all team member");
					for (Player teamMember : Bukkit.getOnlinePlayers()) {
						if (teamMember.hasPermission("eai.team")) {
							teamMember.sendMessage(EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("AntiCapsLock.Notify.NotifyTeamMember.Message", true).replace("%player%", player.getName()));
						}
					}
				}

				if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiCapsLock.Notify.Logging.Enabled")) {
					//TODO: Fill
				}

				if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiCapsLock.Notify.ChatMessage.Enabled")) {
					player.sendMessage(EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("AntiCapsLock.Notify.ChatMessage.Message", true));
					EasyAntiInsult.getUtils().log("Send a message to the player!");
				}

				if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiCapsLock.Notify.Title.Enabled")) {
					try {
						EasyAntiInsult.getUtils().sendTitle(player, EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("AntiCapsLock.Notify.Title.Title", false), EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("AntiCapsLock.Notify.Title.SubTitle", false), EasyAntiInsult.getDefaultConfig().getInt("AntiCapsLock.Notify.Title.FadeInTime"), EasyAntiInsult.getDefaultConfig().getInt("AntiCapsLock.Notify.Title.StayTime"), EasyAntiInsult.getDefaultConfig().getInt("AntiCapsLock.Notify.Title.FadeOutTime"));
						EasyAntiInsult.getUtils().log("Send a title to the player");
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiCapsLock.Notify.ActionBar.Enabled")) {
						try {
							EasyAntiInsult.getUtils().sendActionBar(player, EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("AntiCapsLock.Notify.ActionBar.Message", false));
							EasyAntiInsult.getUtils().log("Send a actionbar to the player");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiCapsLock.Notify.Sound.Enabled")) {
						EasyAntiInsult.getUtils().playSoundOfConfig(player, player.getLocation(), "AntiCapsLock.Notify.Sound.SoundName", 1000);
						EasyAntiInsult.getUtils().log("Play a sound to the player");
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiCapsLock.Notify.DamagePlayer.Enabled")) {
						player.damage(EasyAntiInsult.getDefaultConfig().getDouble("AntiCapsLock.Notify.DamagePlayer.DamageAmount"));
						EasyAntiInsult.getUtils().log("Damage the player!");
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiCapsLock.Notify.KickPlayer.Enabled")) {
						player.kickPlayer(EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("AntiCapsLock.Notify.KickPlayer.KickMessage", false));
						EasyAntiInsult.getUtils().log("Kick the player!");
					}
					return;
				}
			}

			// TEST FOR UNICODE
			if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Enabled")) {
				EasyAntiInsult.getUtils().log("AntiUnicode check is Enabled!");
				if (AntiUnicodeManager.getInstance().isForbidden(player, message)) {
					EasyAntiInsult.getUtils().log("Message has Unicode!");
					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Punishment.Enabled")) {
						AntiUnicodeManager.getInstance().punishPlayer(player);
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Blocking")) {
						event.setCancelled(true);
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Notify.NotifyTeamMember.Enabled")) {
						for (Player teamMember : Bukkit.getOnlinePlayers()) {
							if (teamMember.hasPermission("eai.team")) {
								teamMember.sendMessage(EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("AntiUnicode.Notify.NotifyTeamMember.Message", true).replace("%player%", player.getName()));
							}
						}
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Notify.Logging.Enabled")) {
						//TODO: Fill
					}


					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Notify.ChatMessage.Enabled")) {
						player.sendMessage(EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("AntiUnicode.Notify.ChatMessage.Message", true));
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Notify.Title.Enabled")) {
						try {
							EasyAntiInsult.getUtils().sendTitle(player, EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("AntiUnicode.Notify.Title.Title", false), EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("AntiUnicode.Notify.Title.SubTitle", false), EasyAntiInsult.getDefaultConfig().getInt("AntiUnicode.Notify.Title.FadeInTime"), EasyAntiInsult.getDefaultConfig().getInt("AntiUnicode.Notify.Title.StayTime"), EasyAntiInsult.getDefaultConfig().getInt("AntiUnicode.Notify.Title.FadeOutTime"));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Notify.ActionBar.Enabled")) {
						try {
							EasyAntiInsult.getUtils().sendActionBar(player, EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("AntiUnicode.Notify.ActionBar.Message", false));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Notify.Sound.Enabled")) {
						EasyAntiInsult.getUtils().playSoundOfConfig(player, player.getLocation(), "AntiUnicode.Notify.Sound.SoundName", 1000);
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Notify.DamagePlayer.Enabled")) {
						player.damage(EasyAntiInsult.getDefaultConfig().getDouble("AntiUnicode.Notify.DamagePlayer.DamageAmount"));
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiUnicode.Notify.KickPlayer.Enabled")) {
						player.kickPlayer(EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("AntiUnicode.Notify.KickPlayer.KickMessage", false));
					}
					return;
				}
			}


			//TEST FOR ADVERTISEMENT
			if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiAdvertise.Enabled")) {
				System.out.println("Test Ad");
				if (AntiAdvertiseManager.getInstance().isForbidden(player, message)) {
					System.out.println("Is Ad");
					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiAdvertise.Punishment.Enabled")) {
						AntiAdvertiseManager.getInstance().punishPlayer(player);
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiAdvertise.Blocking.Enabled")) {
						event.setCancelled(true);
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiAdvertise.Notify.NotifyTeamMember.Enabled")) {
						for (Player teamMember : Bukkit.getOnlinePlayers()) {
							if (teamMember.hasPermission("eai.team")) {
								teamMember.sendMessage(EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("AntiAdvertise.Notify.NotifyTeamMember.Message", true).replace("%player%", player.getName()));
							}
						}
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiAdvertise.Notify.Logging.Enabled")) {
						//TODO: Fill
					}


					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiAdvertise.Notify.ChatMessage.Enabled")) {
						player.sendMessage(EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("AntiAdvertise.Notify.ChatMessage.Message", true));
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiAdvertise.Notify.Title.Enabled")) {
						try {
							EasyAntiInsult.getUtils().sendTitle(player, EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("AntiAdvertise.Notify.Title.Title", false), EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("AntiAdvertise.Notify.Title.SubTitle", false), EasyAntiInsult.getDefaultConfig().getInt("AntiAdvertise.Notify.Title.FadeInTime"), EasyAntiInsult.getDefaultConfig().getInt("AntiAdvertise.Notify.Title.StayTime"), EasyAntiInsult.getDefaultConfig().getInt("AntiAdvertise.Notify.Title.FadeOutTime"));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiAdvertise.Notify.ActionBar.Enabled")) {
						try {
							EasyAntiInsult.getUtils().sendActionBar(player, EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("AntiAdvertise.Notify.ActionBar.Message", false));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiAdvertise.Notify.Sound.Enabled")) {
						EasyAntiInsult.getUtils().playSoundOfConfig(player, player.getLocation(), "AntiAdvertise.Notify.Sound.SoundName", 1000);
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiAdvertise.Notify.DamagePlayer.Enabled")) {
						player.damage(EasyAntiInsult.getDefaultConfig().getDouble("AntiAdvertise.Notify.DamagePlayer.DamageAmount"));
					}

					if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiAdvertise.Notify.KickPlayer.Enabled")) {
						player.kickPlayer(EasyAntiInsult.getUtils().getTranslatedMsgOfConfig("AntiAdvertise.Notify.KickPlayer.KickMessage", false));
					}
					return;
				}
			}

			//TEST FOR INSULTS
			if (EasyAntiInsult.getDefaultConfig().getBoolean("AntiInsult.Enabled")) {
				String mode = EasyAntiInsult.getDefaultConfig().getString("AntiInsult.Mode");
				boolean isInsult;
				if (mode.equalsIgnoreCase("database")) {

				} else if (mode.equalsIgnoreCase("replace")) {

				}
			}
		}
	}

}
