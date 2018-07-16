package fr.smoothpvp.smoothpvp.Runnable;

import fr.smoothpvp.smoothpvp.Commands.SeeCmd;
import fr.smoothpvp.smoothpvp.Utils.PlayerWrapper;
import fr.smoothpvp.smoothpvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class SeeRun extends BukkitRunnable {
	
	private Main pl;
	public SeeRun(Main pl) {
		this.pl = pl;
	}
	
	public void run(){
		for(Player verifier : SeeCmd.verifiers.keySet()){
			if ((verifier.getOpenInventory().getTopInventory() != null) && (verifier.getOpenInventory().getTopInventory().getTitle().startsWith("§3See:"))){
				String o = verifier.getOpenInventory().getTopInventory().getName().split(": §8")[1];
		        if (Bukkit.getPlayer(o) != null){
		        	Player player = Bukkit.getPlayer(o);
		            verifier.getOpenInventory().getTopInventory().setItem(0, player.getInventory().getHelmet());
		            verifier.getOpenInventory().getTopInventory().setItem(1, player.getInventory().getChestplate());
		            verifier.getOpenInventory().getTopInventory().setItem(2, player.getInventory().getLeggings());
		            verifier.getOpenInventory().getTopInventory().setItem(3, player.getInventory().getBoots());
		            PlayerWrapper wp = SeeCmd.verifiers.get(verifier);
		            
		            ItemStack it = new ItemStack(Material.DIAMOND_BLOCK, wp.maxClicks > 64 ? 64 : wp.maxClicks);
		            ItemMeta im = it.getItemMeta();
		            im.setDisplayName("§3Maximum CPS§f: §7" + wp.maxClicks + ".");
		            im.setLore(Arrays.asList("§bMaximum number of CPS reach by "+player.getName()+"."));
		            it.setItemMeta(im);
		            
		            ItemStack it1 = new ItemStack(Material.EMERALD_BLOCK, wp.clicks > 64 ? 64 : wp.clicks);
		            ItemMeta im1 = it1.getItemMeta();
		            im1.setDisplayName("§3Current clicks§f: §7" + wp.clicks + " CPS.");
		            im1.setLore(Arrays.asList("§bCPS of "+player.getName()+"."));
		            it1.setItemMeta(im1);
		            
		            int ping = wp.getPing();
		            ItemStack it2 = new ItemStack(Material.IRON_BLOCK, ping > 64 ? 64 : ping);
		            ItemMeta im2 = it2.getItemMeta();
		            im2.setDisplayName("§3" + player.getName() + "'s Ping§f: §7" + ping + "ms.");
		            
		            Long l = Long.valueOf(wp.Connexion);
		            Long l2 = Long.valueOf(System.currentTimeMillis());
		            long diffMs = l2.longValue() - l.longValue();
		            long diffHours = diffMs / 3600000L;
		            if (diffHours != 0L){
		            	long diffMins = diffMs / 3600000L % 60L;
		            	im2.setLore(Arrays.asList("§b"+player.getName()+" is online since " + diffHours + "h" + diffMins + ".", "§b" + player.getName() + "'s IP is " + player.getAddress().getHostString() + "."));
		            } else {
		            	long diffSec = diffMs / 1000L;
		            	long min = diffSec / 60L;
		            	im2.setLore(Arrays.asList("§b"+player.getName()+" is online since " + min + "min(s).", "§b" + player.getName() + "'s IP is " + player.getAddress().getHostString() + "."));
		            }
		            it2.setItemMeta(im2);
		            int nombrealert = 1;
		            if (wp.nombreAlertesAutoClick > 0) {
		            	nombrealert = wp.nombreAlertesAutoClick;
		            }
		            ItemStack it3 = new ItemStack(Material.REDSTONE_BLOCK, nombrealert > 64 ? 64 : nombrealert);
		            ItemMeta im3 = it3.getItemMeta();
		            im3.setDisplayName("§3CPS Alert(s)§f: §7" + wp.nombreAlertesAutoClick + ".");
		            im3.setLore(Arrays.asList("§bAutoclick alerts of "+player.getName()+"."));
		            it3.setItemMeta(im3);
		            
		            ItemStack it4 = new ItemStack(Material.GOLD_BLOCK, wp.clicks2 > 64 ? 64 : wp.clicks2);
		            ItemMeta im4 = it4.getItemMeta();
		            im4.setDisplayName("§3Clicks last 5 seconds§f: §7");
		            im4.setLore(Arrays.asList("§b- §7" + wp.clicks2, "§b- §7" + wp.clicks3,
							"§b- §7" + wp.clicks4, "§b- §7" + wp.clicks5,
							"§b- §7" + wp.clicks6));
		            it4.setItemMeta(im4);
		            
		            ItemStack fermer = new ItemStack(Material.REDSTONE);
					ItemMeta fermerm = fermer.getItemMeta();
					fermerm.setDisplayName("§cClose");
					fermerm.setLore(Arrays.asList("§7Close the inventory."));
					fermer.setItemMeta(fermerm);
					
					ItemStack clear = new ItemStack(Material.FEATHER);
					ItemMeta clearm = clear.getItemMeta();
					clearm.setDisplayName("§3Clear inventory of§f: §7"+player.getName());
					clearm.setLore(Arrays.asList("§bLeft click to clear inventory of "+player.getName()));
					clear.setItemMeta(clearm);
					
					ItemStack freeze = new ItemStack(Material.NETHER_STAR);
					ItemMeta freezem = freeze.getItemMeta();
					if(pl.isfreeze.contains(player)){
						freezem.setDisplayName("§3"+player.getName()+" §7§l> §bUnfreeze player");
						freezem.setLore(Arrays.asList("§bLeft click to unfreeze§f: §7"+player.getName()));
					} else {
						freezem.setDisplayName("§3"+player.getName()+" §7§l> §bFreeze player");
						freezem.setLore(Arrays.asList("§bLeft click to freeze§f: §7"+player.getName()));
					}
					freeze.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
					freeze.setItemMeta(freezem);
					
					ItemStack kill = new ItemStack(Material.ARROW);
					ItemMeta killm = kill.getItemMeta();
					killm.setDisplayName("§3"+player.getName()+" §7§l> §bKill player");
					killm.setLore(Arrays.asList("§bLeft click to kill§f: §7"+player.getName()));
					kill.setItemMeta(killm);
					
					ItemStack s = new ItemStack(Material.EYE_OF_ENDER);
					ItemMeta sm = kill.getItemMeta();
					sm.setDisplayName("§3"+player.getName()+" §7§l> §bSpawn player");
					sm.setLore(Arrays.asList("§bLeft click to respawn§f: §7"+player.getName()));
					s.setItemMeta(sm);
					
					ItemStack ban = new ItemStack(Material.BLAZE_ROD);
					ItemMeta banm = ban.getItemMeta();
					banm.setDisplayName("§3"+player.getName()+" §7§l> §bManage player");
					banm.setLore(Arrays.asList("§bBan the player§f: §7"+player.getName()));
					ban.setItemMeta(banm);
					
					ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
					ItemMeta skullm = skull.getItemMeta();
					skullm.setDisplayName("§3Player infos§f:");
					skullm.setLore(Arrays.asList("§bLeft click to get the infos of "+player.getName()));
					skull.setItemMeta(skullm);
		            
					verifier.getOpenInventory().getTopInventory().setItem(45, clear);
					verifier.getOpenInventory().getTopInventory().setItem(53, fermer);
					verifier.getOpenInventory().getTopInventory().setItem(47, ban);
					verifier.getOpenInventory().getTopInventory().setItem(48, freeze);
					verifier.getOpenInventory().getTopInventory().setItem(49, skull);
					verifier.getOpenInventory().getTopInventory().setItem(50, kill);
					verifier.getOpenInventory().getTopInventory().setItem(51, s);
		            verifier.getOpenInventory().getTopInventory().setItem(5, it3);
		            verifier.getOpenInventory().getTopInventory().setItem(6, it2);
		            verifier.getOpenInventory().getTopInventory().setItem(7, it1);
		            verifier.getOpenInventory().getTopInventory().setItem(8, it);
		            verifier.getOpenInventory().getTopInventory().setItem(4, it4);
		            
		            int slot = 8;
		            ItemStack[] arrayOfItemStack;
		            int j = (arrayOfItemStack = player.getInventory().getContents()).length;
		            for (int i = 0; i < j; i++)
		            {
		              ItemStack itemStack = arrayOfItemStack[i];
		              
		              slot++;
		              verifier.getOpenInventory().getTopInventory().setItem(slot, itemStack);
		            }
		        } else {
		        	verifier.closeInventory();
		        	SeeCmd.verifiers.remove(verifier);
		        }
			}
		}
	}
}
