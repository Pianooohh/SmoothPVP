package fr.smoothpvp.smoothpvp.main;

import entity.Arena;
import fr.smoothpvp.smoothpvp.Commands.*;
import fr.smoothpvp.smoothpvp.Events.*;
import fr.smoothpvp.smoothpvp.Listeners.*;
import fr.smoothpvp.smoothpvp.Managers.KnockbackManager;
import fr.smoothpvp.smoothpvp.Runnable.CheckRunnable;
import fr.smoothpvp.smoothpvp.Runnable.PlayerInfosRunnable;
import fr.smoothpvp.smoothpvp.Runnable.SeeRun;
import fr.smoothpvp.smoothpvp.Runnable.ServerListRunnable;
import fr.smoothpvp.smoothpvp.Utils.PlayerUtils;
import fr.smoothpvp.smoothpvp.Utils.PlayerWrapper;
import fr.smoothpvp.smoothpvp.Utils.Prefixs;
import fr.smoothpvp.smoothpvp.Utils.TPS;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class Main extends JavaPlugin {
	
	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	public FileConfiguration config;
	public boolean FFA = true;
	public boolean Duels = true;
	public boolean mutechat = false;
	public boolean build = false;

	public ArrayList<Player> ismod = new ArrayList<Player>();
	public ArrayList<Player> isfreeze = new ArrayList<Player>();
	public ArrayList<Player> istextingban = new ArrayList<Player>();
	public List<Player> istextingkb = new ArrayList<Player>();
	public Map<Player, Player> duelreq = new HashMap<>();
	public ArrayList<Player> induel = new ArrayList<>();
	public ArrayList<Player> isranked = new ArrayList<Player>();
	public ArrayList<Player> isunranked = new ArrayList<Player>();
	
	public List<Player> editnodebuff = new ArrayList<>();
	public List<Player> editdebuff = new ArrayList<>();
	public List<Player> editcombo = new ArrayList<>();
	public List<Player> editsoup = new ArrayList<>();
	public List<Player> editarcher = new ArrayList<>();
	public List<Player> edithg = new ArrayList<>();

	// UNRANKED/RANKED QUEUES
	public ArrayList<Player> qnodebuff = new ArrayList<>();
	public ArrayList<Player> qdebuff = new ArrayList<>();
	public ArrayList<Player> qcombo = new ArrayList<>();
	public ArrayList<Player> qsoup = new ArrayList<>();
	public ArrayList<Player> qarrow = new ArrayList<>();
	public ArrayList<Player> qhg = new ArrayList<>();

	public ArrayList<Player> qrnodebuff = new ArrayList<>();
	public ArrayList<Player> qrdebuff = new ArrayList<>();
	public ArrayList<Player> qrcombo = new ArrayList<>();
	public ArrayList<Player> qrsoup = new ArrayList<>();
	public ArrayList<Player> qrarrow = new ArrayList<>();
	public ArrayList<Player> qrhg = new ArrayList<>();

	// UNRANKED/RANKED MATCHS
	public Map<Player, Player> nodebuff = new HashMap<>();
	public Map<Player, Player> debuff = new HashMap<>();
	public Map<Player, Player> combo = new HashMap<>();
	public Map<Player, Player> soup = new HashMap<>();
	public Map<Player, Player> arrow = new HashMap<>();
	public Map<Player, Player> hg = new HashMap<>();

	public Map<Player, Player> rnodebuff = new HashMap<>();
	public Map<Player, Player> rdebuff = new HashMap<>();
	public Map<Player, Player> rcombo = new HashMap<>();
	public Map<Player, Player> rsoup = new HashMap<>();
	public Map<Player, Player> rarrow = new HashMap<>();
	public Map<Player, Player> rhg = new HashMap<>();

	public PlayerUtils getPlayerUtils() {
		return null;
	}

	@SuppressWarnings("deprecation")
	public void onEnable() {
		instance = this;
		
		//SET CONFIGS
		config = getConfig();
		if ((getConfig().get("SECONDS_BETWEEN_ALERTS") == null) || (!getConfig().isInt("SECONDS_BETWEEN_ALERTS"))) {
			getConfig().set("SECONDS_BETWEEN_ALERTS", Integer.valueOf(3));
		}
		if ((getConfig().get("MAX_CPS") == null) || (!getConfig().isInt("MAX_CPS"))) {
			getConfig().set("MAX_CPS", Integer.valueOf(15));
		}
		if ((getConfig().get("ALERT_MESSAGE") == null) || (!getConfig().isString("ALERT_MESSAGE"))) {
			getConfig().set("ALERT_MESSAGE",
					"§cSmoothGuard: §7%PLAYER% is probably autoclicking: (%CPS%cps %MS%ms %TPS%TPS).");
		}
		if ((getConfig().get("ALERT_PERM") == null) || (!getConfig().isString("ALERT_PERM"))) {
			getConfig().set("ALERT_PERM", "perm.mod");
		}
		if ((getConfig().get("COMMAND_PERM") == null) || (!getConfig().isString("COMMAND_PERM"))) {
			getConfig().set("COMMAND_PERM", "perm.mod");
		}

		//Create all arenas from config.
		Arena.createAllArenasFromDataFolder(); // <6<6
		
		System.out.println(Prefixs.RCONTag + "Plugin loaded.");
		// fr.smoothpvp.smoothpvp.Events
		PluginManager pm = Bukkit.getPluginManager();
		ItemInteract iListener = new ItemInteract(this);
		
		pm.registerEvents(iListener, this);
		pm.registerEvents(new Chat(this), this);
		pm.registerEvents(new PlayerJoinLeftEvent(this, iListener), this);
		pm.registerEvents(new PlayerSpawn(this), this);
		pm.registerEvents(new FoodEvent(), this);
		pm.registerEvents(new EntityInteract(this), this);
		pm.registerEvents(new PlayerDeath(this), this);
		pm.registerEvents(new DropItems(this), this);
		pm.registerEvents(new BlockBreak(this), this);
		pm.registerEvents(new PlayerMove(this), this);
		pm.registerEvents(new InventoryInteract(this, iListener), this);
		pm.registerEvents(new AutoclickListener(), this);
		pm.registerEvents(new PlayerLogin(), this);
		pm.registerEvents(new ServerList(), this);
		pm.registerEvents(new WeatherChange(), this);
		pm.registerEvents(new PearlListener(this), this);
		pm.registerEvents(new DoubleJumpListener(), this);
		pm.registerEvents(new KnockbackListener(), this);

		// Commandes
		ArenaCmds arenacmd = new ArenaCmds();
		getCommand("gamemode").setExecutor(new Gamemode(this));
		getCommand("build").setExecutor(new Build(this));
		getCommand("gm").setExecutor(new Gamemode(this));
		getCommand("freeze").setExecutor(new Freeze(this));
		getCommand("mc").setExecutor(new ChatCmds(this));
		getCommand("cc").setExecutor(new ChatCmds(this));
		getCommand("clearchat").setExecutor(new ChatCmds(this));
		getCommand("mod").setExecutor(new Mod(this));
		getCommand("report").setExecutor(new Report(this));
		getCommand("see").setExecutor(new SeeCmd("perm.mod"));
		getCommand("tp").setExecutor(new Teleports());
		getCommand("tphere").setExecutor(new Teleports());
		getCommand("spawn").setExecutor(new SetSpawn());
		getCommand("setspawn").setExecutor(new SetSpawn());
		getCommand("arenas").setExecutor(arenacmd);
		getCommand("t").setExecutor(new Time());
		getCommand("duel").setExecutor(new Duel(this));
		getCommand("accept").setExecutor(new Duel(this));
		getCommand("deny").setExecutor(new Duel(this));
		getCommand("pinfos").setExecutor(new PlayerInfos(this));
		getCommand("server").setExecutor(new ServerCmd(this));
		getCommand("stats").setExecutor(new StatesCommands());
		saveConfig();
		
	    KnockbackManager knockbackManager = new KnockbackManager();
	    knockbackManager = new KnockbackManager();
	    knockbackManager.setSwordKBHorizontal(1.0D);
	    knockbackManager.setSwordKBHVertical(1.0D);
	    knockbackManager.setSpeedKBHorizontal(1.0D);
	    knockbackManager.setSpeedKBVertical(1.0D);

		new PlayerInfosRunnable(this).runTaskTimerAsynchronously(this, 0L, 1L);
		new ServerListRunnable(this, iListener).runTaskTimerAsynchronously(this, 0L, 1L);
		new SeeRun(this).runTaskTimerAsynchronously(this, 0L, 1L);
		new CheckRunnable(getConfig().getInt("SECONDS_BETWEEN_ALERTS"), getConfig().getInt("MAX_CPS"),
				getConfig().getString("ALERT_MESSAGE"), getConfig().getString("ALERT_PERM"))
						.runTaskTimerAsynchronously(this, 0L, 20L);
		for (Player p : Bukkit.getOnlinePlayers()) {
			new PlayerWrapper(p);
		}
		Player[] arrayOfPlayer;
		int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length;
		for (int i = 0; i < j; i++) {
			Player p = arrayOfPlayer[i];
			new PlayerWrapper(p);
		}
		Bukkit.getServer().getWorld("world").getEntities().clear();
		FeastListener.clearChests();
		FeastListener.doFeastListener();
		new BukkitRunnable() {
			Location getloc1 = new Location(Bukkit.getWorld("world"), 518, 8, -906);
			Location getloc2 = new Location(Bukkit.getWorld("world"), 544, 4, -941);

			public void run() {
				Bukkit.getWorld("world").playEffect(getloc1, Effect.ENDER_SIGNAL, 0);
				Bukkit.getWorld("world").playEffect(getloc2, Effect.ENDER_SIGNAL, 0);
			}
		}.runTaskTimer(this, 0L, 20L);

		new BukkitRunnable() {
			// int n = 0;
			Location getloc1 = new Location(Bukkit.getWorld("world"), 519, 3, -906);
			Location getloc2 = new Location(Bukkit.getWorld("world"), 544, -4, -940);

			public void run() {
				Bukkit.getWorld("world").playSound(getloc1, Sound.PORTAL, 1, 1);
				Bukkit.getWorld("world").playSound(getloc2, Sound.PORTAL, 1, 1);
				// this.n += 1;
				/*
				 * if (this.n == 1){ Bukkit.
				 * broadcastMessage("§3Old§bMemories §8| §7Des questions? N'h§sitez pas § demander votre aide dans le canal principal."
				 * ); } else if (this.n == 2){ Bukkit.
				 * broadcastMessage("§3Old§bMemories §8| §7Faites /help pour visualiser la liste des commandes."
				 * ); } else if (this.n == 3){ Bukkit.
				 * broadcastMessage("§3Old§bMemories §8| §7Un cheateur? Utilisez la commande /report et laissez faire les admins !"
				 * ); } else if (this.n == 4){ Bukkit.
				 * broadcastMessage("§3Old§bMemories §8| §7Vous pouvez b§n§ficiez d'avantages gr§ce § notre boutique. www.oldmemories.fr"
				 * ); this.n = 0; }
				 */
			}
		}.runTaskTimer(this, 0L, 80L);
	}
	
	public void onReload() {
		boolean success=true;
		try {
			Arena.saveAllConfig();
		} catch (IOException e) {
			e.printStackTrace();
			Bukkit.getLogger().log(Level.SEVERE, "§c§l ERROR: CANT SAVE ARENAS TO FILE.", e);
			success=false;
		}
		if(success) {
			Arena.flushCache();
		}
	}

	public void onDisable() {
		Player[] arrayOfPlayer;
		@SuppressWarnings("deprecation")
		int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length;
		for (int i = 0; i < j; i++) {
			Player p = arrayOfPlayer[i];
			PlayerWrapper.removePlayer(p);
		}
	}

	public static double getTps() {
		return TPS.tps + 1.0D;
	}

	public void performCommand(String cmd) {
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd);
	}

	public void sendRconMsg(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg);
	}
}
