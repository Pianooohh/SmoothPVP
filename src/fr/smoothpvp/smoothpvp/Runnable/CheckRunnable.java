package fr.smoothpvp.smoothpvp.Runnable;

import fr.smoothpvp.smoothpvp.Events.AutoclickAlertEvent;
import fr.smoothpvp.smoothpvp.Utils.PlayerWrapper;
import fr.smoothpvp.smoothpvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class CheckRunnable extends BukkitRunnable {

	private int timeBetweenAlerts;
	private int maxCps;
	private String AlertMessage;
	private String Perm;
  
	public CheckRunnable(int secondes, int maxCps, String AlertMessage, String Perm){
		this.timeBetweenAlerts = secondes;
		this.maxCps = maxCps;
		this.AlertMessage = AlertMessage;
		this.Perm = Perm;
	}
  
	public void run(){
		for (PlayerWrapper wp : PlayerWrapper.players.values()){
			int ping = wp.getPing();
			double tps = Math.round(Main.getTps() * 100.0D) / 100.0D;
			int AntiLag = (int)((20.0D - tps) * 2.0D);
			AntiLag += ping / 50;
			if ((wp.clicks >= this.maxCps + AntiLag) && (wp.lastAlert + this.timeBetweenAlerts * 1000L < System.currentTimeMillis())){
				AutoclickAlertEvent event = new AutoclickAlertEvent(wp.pseudo, wp.clicks, ping, tps);
				Bukkit.getServer().getPluginManager().callEvent(event);
				wp.lastAlert = System.currentTimeMillis();
				if (!event.isCancelled()){
					Bukkit.getConsoleSender().sendMessage(this.AlertMessage.replace("%PLAYER%", wp.pseudo).replace("%CPS%", String.valueOf(wp.clicks)).replace("%MS%", String.valueOf(ping)).replace("%TPS%", String.valueOf(tps)).replace("&", "§"));
					Bukkit.broadcast(this.AlertMessage.replace("%PLAYER%", wp.pseudo)
					.replace("%CPS%", String.valueOf(wp.clicks))
					.replace("%MS%", String.valueOf(ping))
					.replace("%TPS%", String.valueOf(tps))
					.replace("&", "§"),
					this.Perm);
					wp.nombreAlertesAutoClick += 1;
				}
			}
			wp.clicks6 = wp.clicks5;
			wp.clicks5 = wp.clicks4;
			wp.clicks4 = wp.clicks3;
			wp.clicks3 = wp.clicks2;
			wp.clicks2 = wp.clicks;
			wp.clicks = 0;
		}
	}
}

