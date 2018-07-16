package Runnable;

import Commands.PlayerInfos;
import Events.EntityInteract;
import Main.Main;
import Utils.PlayerUtils;
import Utils.PlayerWrapper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.Arrays;

public class PlayerInfosRunnable extends BukkitRunnable {
	
	private Main pl;
	public PlayerInfosRunnable(Main pl) {
		this.pl = pl;
	}
		
	public void run(){
		for(Player p: PlayerInfos.players.keySet()){
			if((p.getOpenInventory().getTopInventory().getTitle().contains("�3Infos"))){
				PlayerUtils utils = new PlayerUtils(pl);
				String ta = p.getOpenInventory().getTopInventory().getName().split(": �8")[1];
				if (Bukkit.getPlayer(ta) != null){
		        	Player t = Bukkit.getPlayer(ta);
		    		int slot = -1;
		            ItemStack[] arrayOfItemStack;
		            int j = (arrayOfItemStack = t.getInventory().getContents()).length;
		            for (int i = 0; i < j; i++)
		            {
		              ItemStack itemStack = arrayOfItemStack[i];
		              slot++;
		              p.getOpenInventory().getTopInventory().setItem(slot, itemStack);
		            }
					ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
					ItemMeta skullm = skull.getItemMeta();
					File floct = new File("plugins//SmoothPVP//Players//" + t.getUniqueId() + ".yml");
					YamlConfiguration yloct = YamlConfiguration.loadConfiguration(floct);
					PlayerWrapper wp = (PlayerWrapper) PlayerInfos.players.get(p);
					skullm.setDisplayName("�3"+t.getName()+"'s stats:");
					skullm.setLore(Arrays.asList("�bKills: �7"+yloct.getInt("Kills"), "�bDeaths: �7"+yloct.getInt("Deaths"), "�bMoney: �7"+yloct.getInt("Argent")+"�7$", "�bKDR�f: �7"+yloct.getInt("KDR"), "�bAlerts (verif)�f: �7"+wp.nombreAlertesAutoClick));
					skull.setItemMeta(skullm);
					
					int ping = (int) (((CraftPlayer)p).getHandle().ping / 2.0D);
					ItemStack gold = new ItemStack(Material.GOLD_BLOCK);
					ItemMeta goldm = gold.getItemMeta();
					goldm.setDisplayName("�3"+t.getName()+"'s Network Infos:");
					Long l = Long.valueOf(wp.Connexion);
		            Long l2 = Long.valueOf(System.currentTimeMillis());
		            long diffMs = l2.longValue() - l.longValue();
		            long diffHours = diffMs / 3600000L;
		            if (diffHours != 0L){
		            	long diffMins = diffMs / 3600000L % 60L;
		            	goldm.setLore(Arrays.asList("�bIP�f: �7"+t.getAddress().getHostString(), "�bPort�f: �7"+t.getAddress().getPort(), "�bPing�f: �7"+ping, "�bUUID�f: �7"+t.getUniqueId(), "�bOnline since�f: �7"+diffHours +"�7h"+diffMins+"�7."));
		            
		            } else {
		            	long diffSec = diffMs / 1000L;
		            	long min = diffSec / 60L;
		            	goldm.setLore(Arrays.asList("�bIP�f: �7"+t.getAddress().getHostString(), "�bPort�f: �7"+t.getAddress().getPort(), "�bPing�f: �7"+ping, "�bUUID�f: �7"+t.getUniqueId(), "�bOnline since�f: �7"+min+"�7min(s)."));
		            }
		            gold.setItemMeta(goldm);
		            String statue;
		            if(utils.isInMatch(p) == true) {
		            	statue = "�71v1.";
		            } else {
		            	statue = "�7FFA.";
		            }
					ItemStack g = new ItemStack(Material.DIAMOND_BLOCK);
					ItemMeta gm = g.getItemMeta();
					gm.setDisplayName("�3"+t.getName()+"'s Global Infos:");
					gm.setLore(Arrays.asList("�bWorld�f: �7"+t.getWorld().getName(), "�bServer�f: " + statue, "�bLocation�f: �7X: "+t.getLocation().getBlockX()+" �7Y: "+t.getLocation().getBlockY()+" �7Z: "+t.getLocation().getBlockZ(),"�bFreeze�f: "+utils.getFreezeStatue(t),"�bModerator mod�f: "+utils.getModStatue(t)));
					g.setItemMeta(gm);
					ItemStack ban = new ItemStack(Material.BLAZE_ROD);
					ItemMeta banm = ban.getItemMeta();
					banm.setDisplayName("�3"+t.getName()+" �7�l> �bManage player");
					banm.setLore(Arrays.asList("�bBan the player�f: �7"+t.getName()));
					ban.setItemMeta(banm);
					
					ItemStack in = new ItemStack(Material.PAPER);
					ItemMeta inm = in.getItemMeta();
					inm.setDisplayName("�3"+t.getName()+" �7�l> �bVerif player");
					inm.setLore(Arrays.asList("�bVerif the player�f: �7"+t.getName()));
					in.setItemMeta(inm);
					
					ItemStack tp = new ItemStack(Material.ENDER_PEARL);
					ItemMeta tpm = tp.getItemMeta();
					tpm.setDisplayName("�3"+t.getName()+" �7�l> �bTp to player");
					tpm.setLore(Arrays.asList("�bTeleport to player�f: �7"+t.getName()));
					tp.setItemMeta(tpm);
					
					ItemStack st = new ItemStack(Material.BREAD);
					ItemMeta stm = st.getItemMeta();
					stm.setDisplayName("�3"+t.getName()+"'s Status:");
					stm.setLore(Arrays.asList("�bHealth�f: �7"+t.getHealthScale(), "�bFood�f: �7"+t.getFoodLevel(), "�bIn Match/Queue: " + utils.getStatue(t)));
					st.setItemMeta(stm);
					
					ItemStack clear = new ItemStack(Material.FEATHER);
					ItemMeta clearm = clear.getItemMeta();
					clearm.setDisplayName("�3Clear inventory of�f: �7"+t.getName());
					clearm.setLore(Arrays.asList("�bLeft click to clear the inventory of�f: �7"+t.getName()));
					clear.setItemMeta(clearm);
					
					ItemStack freeze = new ItemStack(Material.NETHER_STAR);
					ItemMeta freezem = freeze.getItemMeta();
					if(pl.isfreeze.contains(t)){
						freezem.setDisplayName("�3"+t.getName()+" �7�l> �bUnfreeze player");
						freezem.setLore(Arrays.asList("�bLeft click to unfreeze�f: �7"+t.getName()));
					} else {
						freezem.setDisplayName("�3"+t.getName()+" �7�l> �bFreeze player");
						freezem.setLore(Arrays.asList("�bLeft click to freeze�f: �7"+t.getName()));
					}
					freeze.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
					freeze.setItemMeta(freezem);
					
					ItemStack kill = new ItemStack(Material.ARROW);
					ItemMeta killm = kill.getItemMeta();
					killm.setDisplayName("�3"+t.getName()+" �7�l> �bKill player");
					killm.setLore(Arrays.asList("�bLeft click to kill�f: �7"+t.getName()));
					kill.setItemMeta(killm);
					
					ItemStack s = new ItemStack(Material.EYE_OF_ENDER);
					ItemMeta sm = kill.getItemMeta();
					sm.setDisplayName("�3"+t.getName()+" �7�l> �bSpawn player");
					sm.setLore(Arrays.asList("�bLeft click to respawn�f: �7"+t.getName()));		
					s.setItemMeta(sm);
					
					ItemStack red = new ItemStack(Material.REDSTONE);
					ItemMeta redm = red.getItemMeta();
					redm.setDisplayName("�cClose the menu.");
					red.setItemMeta(redm);
					
					p.getOpenInventory().getTopInventory().setItem(51, red);
					p.getOpenInventory().getTopInventory().setItem(37, skull);
					p.getOpenInventory().getTopInventory().setItem(38, gold);
					p.getOpenInventory().getTopInventory().setItem(39, g);
					p.getOpenInventory().getTopInventory().setItem(41, in);
					p.getOpenInventory().getTopInventory().setItem(43, tp);
					p.getOpenInventory().getTopInventory().setItem(42, ban);
					p.getOpenInventory().getTopInventory().setItem(40, st);
					p.getOpenInventory().getTopInventory().setItem(48, freeze);
					p.getOpenInventory().getTopInventory().setItem(49, kill);
					p.getOpenInventory().getTopInventory().setItem(50, s);
					p.getOpenInventory().getTopInventory().setItem(47, clear);
					
				} else {
					p.closeInventory();
					EntityInteract.opener.remove(p);
				}
			}
		}
	}

}
