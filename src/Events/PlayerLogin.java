package Events;

import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLogin implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		if(e.getResult() == PlayerLoginEvent.Result.KICK_FULL) {
			if(p.hasPermission("perm.mod")) { e.setResult(PlayerLoginEvent.Result.ALLOWED); }
			e.setKickMessage("�3SmothPVP �8- �bKICK Info:\n�bThe server is full, comeback later!");
			Main.getInstance().sendRconMsg("�3Player Logs �7�l> �b" + p.getName() + " tried to connect but the server is full.");
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(all.hasPermission("perm.mod")) {
					all.sendMessage("�b�o" + p.getName() + " �8�otried to connect but the server is full.");
				}
			}
		}
		if(e.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST) {
			if(p.hasPermission("perm.mod")) { e.setResult(PlayerLoginEvent.Result.ALLOWED); }
			e.setKickMessage("�3SmothPVP �8- �bKICK Info:\n�bYou are not on the whitelist!\n\n�7�oYou can visit our discord if you want to be in the project: �b�oXXXXXXXX.");
			Main.getInstance().sendRconMsg("�3Player Logs �7�l> �b" + p.getName() + " tried to connect but is not on the whitelist.");
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(all.hasPermission("perm.mod")) {
					all.sendMessage("�b�o" + p.getName() + " �8�otried to connect but he is not on the whitelist.");
				}
			}
		}
		if(e.getResult() == PlayerLoginEvent.Result.KICK_OTHER) {
			e.setKickMessage("�3SmothPVP �8- �bKICK Info:\n�bYou have been kicked for unknow reason!\n\n�7�oTry to reconnect.\n�7�oIf the problem persist, contact the staff on discord; �b�oXXXXXXX.");
			Main.getInstance().sendRconMsg("�3Player Logs �7�l> �b" + p.getName() + " tried to connect but have been kicked for unknow reason.");
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(all.hasPermission("perm.mod")) {
					all.sendMessage("�b�o" + p.getName() + " �8�ofailed to connect on the server ffor unknow reason!");
				}
			}
		}
	}

}
