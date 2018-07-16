package Utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class KDR {

	public double getKDR(Player player){
		File floc = new File("plugins//SmoothPVP//Players//" + player.getUniqueId() + ".yml");
		YamlConfiguration yloc = YamlConfiguration.loadConfiguration(floc);
		int kills = yloc.getInt("Kills");
		if(kills == 0)return 0;
		int deaths = yloc.getInt("Deaths");
		if(deaths == 0)return kills;
		double ratio = kills / deaths;
		return ratio;
	}
	
}
