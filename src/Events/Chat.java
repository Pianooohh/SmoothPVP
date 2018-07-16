package Events;

import Main.Main;
import Utils.GuiUtils;
import Utils.NotInt;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Chat implements Listener {
	
	private Map<String, Long> cooldowns = new HashMap<String, Long>();
	private Map<Player, Integer> count = new HashMap<Player, Integer>();
	private int i = 0;
	private Main plugin;
	public Chat(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		final Player p = e.getPlayer();
		if(p.isOp()) {
			e.setFormat("�4" + e.getPlayer().getDisplayName() + ": �c�o%2$s");
			e.setMessage(e.getMessage().replaceAll("&", "�"));
		} else if(p.hasPermission("perm.admin")) {
			e.setFormat("�c" + e.getPlayer().getDisplayName() + ": �8�o%2$s");
			e.setMessage(e.getMessage().replaceAll("&", "�"));
		} else if(p.hasPermission("perm.dev")) {
			e.setFormat("�9" + e.getPlayer().getDisplayName() + ": �7�o%2$s");
			e.setMessage(e.getMessage().replaceAll("&", "�"));
		} else if(p.hasPermission("perm.mod")) {
			e.setFormat("�b" + e.getPlayer().getDisplayName() + ": �f�o%2$s");
		} else if(p.hasPermission("perm.builder")) {
			e.setFormat("�2" + e.getPlayer().getDisplayName() + ": �a�o%2$s");
		} else if(p.hasPermission("perm.ytb")) {
			e.setFormat("�5" +e.getPlayer().getDisplayName() + ": �f�o%2$s");
		} else if(p.hasPermission("perm.gold")) {
			e.setFormat("�6" +e.getPlayer().getDisplayName() + ": �7�o%2$s");
		} else {
			e.setFormat("�8" +e.getPlayer().getDisplayName() + ": �7�o%2$s");
		}
		if(!p.hasPermission("perm.mod")) {
			if(cooldowns.containsKey(p.getName())){
	            int seconds = 3;
	            long timeleft = ((cooldowns.get(p.getName())) / 1000 + seconds) - (System.currentTimeMillis() / 1000) ;
	            if(timeleft > 0){
	                e.getPlayer().sendMessage("�cDon't spam or you will get kicked!");
	                Main.getInstance().sendRconMsg("�3Player Logs �7�l> �b" + p.getName() + " tried to say: �7" + e.getMessage());
	                count.put(p, i++);
	                e.setCancelled(true);
	                if(count.containsValue(3)) {
	                	Bukkit.getScheduler().runTask(plugin, new Runnable() {
							public void run() {
								plugin.performCommand("kick " + p.getName() + " Spamming.");
								count.remove(p);
								i = 0;
							}
	                	});
	                }
	            } else {
	            	count.remove(p);
	            	i = 0;
	            }
	        }
		}
        cooldowns.put(p.getName(), System.currentTimeMillis());
		EntityInteract et = new EntityInteract(plugin);
		NotInt notint = new NotInt();
		if(plugin.istextingkb.contains(p)) {
			String value = e.getMessage();
			e.setCancelled(true);
			if(notint.isInt(value)) {
				if(Integer.parseInt(value) >= 1 && Integer.parseInt(value) <= 20) {
					ItemStack sword = new ItemStack(Material.WOOD_SWORD);
	        		sword.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.KNOCKBACK, Integer.valueOf(value));
	        		GuiUtils.setItem(p.getInventory(), sword, 0, "�3Knockback Sword �7- �bLevel�f: " + Integer.valueOf(value));
	        		p.sendMessage("�bThe level of your Knockback Sword has been adjusted to " + Integer.valueOf(value));
			    	e.setCancelled(true);
			    	plugin.istextingkb.remove(p);
				} else {
					p.sendMessage("�cEnter a value between 1 & 20!");
					e.setCancelled(true);
				}
			} else {
				p.sendMessage("�cEnter a value between 1 & 20!");
				e.setCancelled(true);
			}
		}
		if(plugin.istextingban.contains(p)) {
			e.setCancelled(true);
			plugin.istextingban.remove(p);
			p.performCommand("ban " + et.getTarget().getName());
		}
		
		if(plugin.mutechat == true) {
			if(!p.hasPermission("perm.mod")) {
				e.setCancelled(true);
				p.sendMessage("�cThe chat is locked, you can't talk!");
				Main.getInstance().sendRconMsg("�3Player Logs �7�l> �b" + p.getName() + " tried to say: �7" + e.getMessage());
			}
		}
	}
}
