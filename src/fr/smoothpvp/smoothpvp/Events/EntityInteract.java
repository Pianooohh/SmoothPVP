package fr.smoothpvp.smoothpvp.Events;

import fr.smoothpvp.smoothpvp.Utils.PlayerWrapper;
import fr.smoothpvp.smoothpvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class EntityInteract implements Listener {
	
	public static HashMap<Player, PlayerWrapper> opener = new HashMap<>();
	
	public Player gettarget;
	private Main pl;
	public EntityInteract(Main pl) {
		this.pl = pl;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if(e.getRightClicked().getType() != EntityType.PLAYER) return;
		Player t = (Player) e.getRightClicked();
		if(e.getRightClicked() == null) return;
		if(t instanceof Player) {
			if(pl.ismod.contains(p)) {
				e.setCancelled(true);
				if(p.getItemInHand().getItemMeta().getDisplayName() == "§3Verif Player") {
					p.performCommand("see " + t.getName());
					p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
				}
				if(p.getItemInHand().getItemMeta().getDisplayName() == "§3Player Infos") {
					p.performCommand("pinfos " + t.getName());
					p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
				}
				if(p.getItemInHand().getItemMeta().getDisplayName() == "§3Manage Player") {
					openGestionMenu(p, t);
					p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
				}
				if(p.getItemInHand().getItemMeta().getDisplayName() == "§3Kill Player") {
					t.setHealth(0);
					t.sendMessage("§cYou have been killed by an admin.");
					p.sendMessage("§3You have killed " + t.getDisplayName());
					p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
				}
				if(p.getItemInHand().getItemMeta().getDisplayName() == "§3Freeze Player") {
					p.performCommand("freeze " + t.getName());
					p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
				}
			}
		}
	}
	public void openGestionMenu(Player p, Player t) {
		gettarget = t;
		Inventory inv = Bukkit.createInventory(p, 9*3, "§3Manage: §8" + t.getName());
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)7);
		ItemStack tnt = new ItemStack(Material.TNT, 1);
		ItemStack anvil = new ItemStack(Material.ANVIL, 1);
		ItemStack clock = new ItemStack(Material.WATCH, 1);
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (byte)1);
		ItemStack red = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)14);
		
		ItemMeta glassm = glass.getItemMeta();
		ItemMeta tntm = tnt.getItemMeta();
		ItemMeta anvilm = anvil.getItemMeta();
		ItemMeta clockm = clock.getItemMeta();
		ItemMeta headm = head.getItemMeta();
		ItemMeta redm = red.getItemMeta();
		
		glassm.setDisplayName(" ");
		tntm.setDisplayName("§cBan player");
		anvilm.setDisplayName("§aKick Player");
		clockm.setDisplayName("§bFreeze/Unfreeze Player");
		headm.setDisplayName("§6Mute Player");
		redm.setDisplayName("§cClose the menu");
		
		glass.setItemMeta(glassm);
		tnt.setItemMeta(tntm);
		head.setItemMeta(headm);
		anvil.setItemMeta(anvilm);
		clock.setItemMeta(clockm);
		red.setItemMeta(redm);
		
		for(int i = 0; i < 9*3; i++) {
			inv.setItem(i, glass);
		}
		
		inv.setItem(10, tnt);
		inv.setItem(12, anvil);
		inv.setItem(14, clock);
		inv.setItem(16, head);
		inv.setItem(22, red);
		
		p.openInventory(inv);
	}
	
	public Player getTarget() {
		return gettarget;
	}
}
