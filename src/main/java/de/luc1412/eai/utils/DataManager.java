package de.luc1412.eai.utils;

import de.luc1412.eai.EasyAntiInsult;
import org.bukkit.entity.Player;

/**
 * Created by Luc1412 on 27.10.2017 at 19:54
 */
public class DataManager {

	public int getData(Player player, DataType dataType) {
		if (EasyAntiInsult.DATA_SAVING_MODE == 1) {
			return EasyAntiInsult.getDatabaseManager().getData(player, dataType);
		} else if (EasyAntiInsult.DATA_SAVING_MODE == 0) {
			return EasyAntiInsult.getStatsConfig().getData(player, dataType);
		} else return 0;
	}

	public void setData(Player player, DataType dataType, int data) {
		if (EasyAntiInsult.DATA_SAVING_MODE == 1) {
			EasyAntiInsult.getDatabaseManager().setData(player, dataType, data);
		} else if (EasyAntiInsult.DATA_SAVING_MODE == 0) {
			EasyAntiInsult.getStatsConfig().setData(player, dataType, data);
		}
	}

	public void addPlayer(Player player) {
		if (EasyAntiInsult.DATA_SAVING_MODE == 1) {
			EasyAntiInsult.getDatabaseManager().addPlayer(player);
		} else if (EasyAntiInsult.DATA_SAVING_MODE == 0) {
			EasyAntiInsult.getStatsConfig().addPlayer(player);
		}
	}


	public enum DataType {
		GLOBAL("Global", ""),
		ADS("Ads", "'%player_name%'(UUID:'%player_uuid%') advertise!"),
		CAPS("Caps", "'%player_name%'(UUID:'%player_uuid%') use caps!"),
		INSULTS("Insults", "'%player_name%'(UUID:'%player_uuid%') use insults!"),
		SPAM("Spam", "'%player_name%'(UUID:'%player_uuid%') was spamming!"),
		UNICODE("Unicode", "'%player_name%'(UUID:'%player_uuid%') use Unicode chars!");

		public String value;

		public String logValue;

		DataType(String value, String logValue) {
			this.value = value;
			this.logValue = logValue;
		}

		public String getValue() {
			return value;
		}

		public String getLogValue() {
			return logValue;
		}
	}

}
