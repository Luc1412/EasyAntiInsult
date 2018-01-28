package de.luc1412.eai.utils;

import me.creepplays.asyncable.mysql.AsyncableMySQL;
import org.bukkit.entity.Player;

/**
 * Created by Luc1412 on 03.11.2017 at 22:25
 */
public class DatabaseManager {

	private static final String TABLENAME = "player_data";
	private AsyncableMySQL asyncableMySQL;

	public DatabaseManager(String host, int port, String database, String username, String password) {

		asyncableMySQL = new AsyncableMySQL(host, port, username, password, database);

		asyncableMySQL.update("CREATE TABLE IF NOT EXISTS " + TABLENAME + " (UUID VARCHAR(50),PlayerName VARCHAR(20),LastAddress VARCHAR,Global INT,Ads INT,Caps INT,Insults INT,Spam INT,Unicode INT)").run();
	}

	private boolean isUserExists(Player player) {

		boolean[] result = new boolean[1];

		asyncableMySQL.query("SELECT Playername FROM " + TABLENAME + " WHERE UUID = " + player.getUniqueId().toString())
				.then(rs -> {
					result[0] = rs.next();
					return null;
				}).run();
		return result[0];
	}

	public void addPlayer(Player player) {
		if (!isUserExists(player)) {
			asyncableMySQL.update("INSERT INTO " + TABLENAME + " (UUID,PlayerName,LastAddress,Global,Ads,Caps,Insults,Spam,Unicode) VALUES (" + player.getUniqueId().toString() + "," + player.getName() + "," + player.getAddress().getHostName() + ",0,0,0,0,0,0)")
					.run();
		}
	}

	public void setData(Player player, DataManager.DataType type, int data) {
		if (isUserExists(player)) {
			asyncableMySQL.update("UPDATE " + TABLENAME + " SET Playername = " + player.getName() + " WHERE UUID = " + player.getUniqueId()).run();
			asyncableMySQL.update("UPDATE " + TABLENAME + " SET LastAddress = " + player.getAddress().getHostName() + " WHERE UUID = " + player.getUniqueId()).run();
			asyncableMySQL.update("UPDATE " + TABLENAME + " SET " + type.getValue() + " = " + data + " WHERE UUID = " + player.getUniqueId()).run();

		}
	}

	public int getData(Player player, DataManager.DataType type) {
		int[] result = new int[1];
		if (isUserExists(player)) {
			asyncableMySQL.update("UPDATE " + TABLENAME + " SET Playername = " + player.getName() + " WHERE UUID = " + player.getUniqueId()).run();
			asyncableMySQL.update("UPDATE " + TABLENAME + " SET LastAddress = " + player.getAddress().getHostName() + " WHERE UUID = " + player.getUniqueId()).run();

			asyncableMySQL.query("SELECT " + type.getValue() + " FROM " + TABLENAME + " WHERE UUID = " + player.getUniqueId().toString())
					.then(rs -> {
						rs.next();
						result[0] = rs.getInt(1);
						return null;
					}).run();
		}
		return result[0];
	}


}
