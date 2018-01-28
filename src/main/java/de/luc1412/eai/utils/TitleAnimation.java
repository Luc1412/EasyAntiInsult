package de.luc1412.eai.utils;

import de.luc1412.eai.EasyAntiInsult;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Created by Luc1412 on 26.10.2017 at 21:24
 */
public class TitleAnimation {

	private Player player;

	public TitleAnimation(Player player) {
		this.player = player;
		startAnimation();
	}

	private void startAnimation() {
		player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1000F, 0.1F);
		Thread thread = new Thread(() -> {
			int aniState = 0;
			String title = ">                               ";
			for (; ; ) {
				try {
					EasyAntiInsult.getUtils().sendTitle(player, "§l" + title, " ", 0, 20, 0);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (title.length() == 1) {
					title = " >";
					aniState++;
				}

				if (aniState == 0) {
					title = title.substring(2, title.length());
					title = ">" + title;
				} else if (aniState == 1) {
					title = title.substring(0, title.length() - 1);
					title += " >";
					if (title.length() == 32) break;
				}


				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			title = "                               <";
			aniState = 3;
			player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1000F, 0.1F);
			for (; ; ) {
				try {
					EasyAntiInsult.getUtils().sendTitle(player, "§l" + title, " ", 0, 20, 0);
				} catch (Exception e) {
					e.printStackTrace();
				}


				if (title.length() == 1) {
					title = "< ";
					aniState++;
				}

				if (aniState == 3) {
					title = title.substring(0, title.length() - 2);
					title += "<";
				} else if (aniState == 4) {
					title = title.substring(1, title.length());
					title = "< " + title;
					if (title.length() == 32) break;
				}

				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			title = ">                              <";
			player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_IMPACT, 1000F, 2.0F);
			for (; ; ) {
				try {
					EasyAntiInsult.getUtils().sendTitle(player, "§l" + title, " ", 0, 20, 0);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (title.length() == 2) break;

				title = title.substring(2, title.length() - 2);
				title = ">" + title + "<";

				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			int count = 0;
			for (; ; ) {
				try {
					switch (count) {

						case 0:
							EasyAntiInsult.getUtils().sendTitle(player, "§cti", "", 0, 20, 0);
							break;

						case 1:
							EasyAntiInsult.getUtils().sendTitle(player, "§cnti§eI", "", 0, 20, 0);
							break;

						case 2:
							EasyAntiInsult.getUtils().sendTitle(player, "§cAnti§eIn", "", 0, 20, 0);
							break;

						case 3:
							EasyAntiInsult.getUtils().sendTitle(player, "§9y§cAnti§eIns", "", 0, 20, 0);
							break;

						case 4:
							EasyAntiInsult.getUtils().sendTitle(player, "§9sy§cAnti§eInsu", "", 0, 20, 0);
							break;

						case 5:
							EasyAntiInsult.getUtils().sendTitle(player, "§9asy§cAn§4ti§eInsul", "", 0, 20, 0);
							break;

						case 6:
							EasyAntiInsult.getUtils().sendTitle(player, "§9asy§cAnti§eInsult", "n ", 0, 20, 0);
							break;

						case 7:
							EasyAntiInsult.getUtils().sendTitle(player, "§9Easy§cA§4nti§6I§ensult", "rn A", 0, 20, 0);
							break;

						case 8:
							EasyAntiInsult.getUtils().sendTitle(player, "§9Easy§4Anti§6In§esult", "ern An", 0, 20, 0);
							break;

						case 9:
							EasyAntiInsult.getUtils().sendTitle(player, "§9Eas§1y§4Anti§6Ins§eult", "dern Ant", 0, 20, 0);
							break;

						case 10:
							EasyAntiInsult.getUtils().sendTitle(player, "§9Ea§1sy§4Anti§6Insu§elt", "odern Anti", 0, 20, 0);
							break;

						case 11:
							EasyAntiInsult.getUtils().sendTitle(player, "§9E§1asy§4Anti§6Insul§et", "Modern Anti ", 0, 20, 0);
							break;

						case 12:
							EasyAntiInsult.getUtils().sendTitle(player, "§1Easy§4Anti§6Insult", " Modern Anti I", 0, 20, 0);
							break;

						case 13:
							EasyAntiInsult.getUtils().sendTitle(player, "§1Easy§4Anti§6Insult", "d Modern Anti In", 0, 20, 0);
							break;

						case 14:
							EasyAntiInsult.getUtils().sendTitle(player, "§1Easy§4Anti§6Insult", "and Modern Anti Ins", 0, 20, 0);
							break;

						case 15:
							EasyAntiInsult.getUtils().sendTitle(player, "§1Easy§4Anti§6Insult", " and Modern Anti Insu", 0, 20, 0);
							break;

						case 16:
							EasyAntiInsult.getUtils().sendTitle(player, "§1Easy§4Anti§6Insult", "y and Modern Anti Insul", 0, 20, 0);
							break;

						case 17:
							EasyAntiInsult.getUtils().sendTitle(player, "§1Easy§4Anti§6Insult", "sy and Modern Anti Insult", 0, 20, 0);
							break;

						case 18:
							EasyAntiInsult.getUtils().sendTitle(player, "§1Easy§4Anti§6Insult", "asy and Modern Anti Insult S", 0, 20, 0);
							break;

						case 19:
							EasyAntiInsult.getUtils().sendTitle(player, "§1Easy§4Anti§6Insult", "Easy and Modern Anti Insult So", 0, 20, 0);
							break;

						case 20:
							EasyAntiInsult.getUtils().sendTitle(player, "§1Easy§4Anti§6Insult", " Easy and Modern Anti Insult Sol", 0, 20, 0);
							break;

						case 21:
							EasyAntiInsult.getUtils().sendTitle(player, "§1Easy§4Anti§6Insult", "e Easy and Modern Anti Insult Solu", 0, 20, 0);
							break;

						case 22:
							EasyAntiInsult.getUtils().sendTitle(player, "§1Easy§4Anti§6Insult", "he Easy and Modern Anti Insult Solut", 0, 20, 0);
							break;

						case 23:
							EasyAntiInsult.getUtils().sendTitle(player, "§1Easy§4Anti§6Insult", "The Easy and Modern Anti Insult Soluti", 0, 20, 0);
							break;

						case 24:
							EasyAntiInsult.getUtils().sendTitle(player, "§1Easy§4Anti§6Insult", "The Easy and Modern Anti Insult Solutio", 0, 20, 0);
							break;

						case 25:
							EasyAntiInsult.getUtils().sendTitle(player, "§1Easy§4Anti§6Insult", "The Easy and Modern Anti Insult Solution", 0, 100, 100);
							break;

						default:
							break;
					}

					if (count == 25) break;

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					count++;
				} catch (Exception e) {
					e.printStackTrace();
				}
				player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1000F, 2.0F);
			}
		});
		thread.start();
	}

}


//- - - - - - - - - - - - - -
//  - - - - - - - - - - - -
//    - - - - - - - - - -
//      - - - - - - - -
//        - - - - - -
//          - - - -
//            - -
//E a s y A n t i I n s u l t
//T H E   E A S Y   A N D   M O D E R N   A N T I   I N S U L T   S O L U T I O N
//1 2 3 4 5 6 7 8 9 0 1 2 3 4 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
//                  10                20                  30                  40