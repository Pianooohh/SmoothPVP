package Runnable;

import Events.ItemInteract;
import Main.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class ServerListRunnable extends BukkitRunnable {

	private ItemInteract it;
	private Main pl;
	public ServerListRunnable(Main pl, ItemInteract it) {
		this.pl = pl;
		this.it = it;
	}

	public void run() {
		for (Player p : it.opener) {
			if (p.getOpenInventory().getTopInventory() != null && p.getOpenInventory().getTopInventory().getTitle().startsWith("�3Server")) {
				ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
				ItemMeta glassm = glass.getItemMeta();
				glassm.setDisplayName(" ");
				glass.setItemMeta(glassm);
				for (int i = 0; i < p.getOpenInventory().getTopInventory().getSize(); i++) {
					p.getOpenInventory().getTopInventory().setItem(i, glass);
				}
				if(pl.FFA == true) {
					ItemStack ist = new ItemStack(Material.EMERALD_BLOCK, 1);
					ItemMeta istm = ist.getItemMeta();
					istm.setDisplayName("�3The server FFA is currently: �aOnline.");
					istm.setLore(Arrays.asList("�bClick to close the server."));
					ist.setItemMeta(istm);
					p.getOpenInventory().getTopInventory().setItem(10, ist);
				} else {
					ItemStack ist = new ItemStack(Material.REDSTONE_BLOCK, 1);
					ItemMeta istm = ist.getItemMeta();
					istm.setDisplayName("�3The server FFA is currently: �cOffline.");
					istm.setLore(Arrays.asList("�bClick to open the server."));
					ist.setItemMeta(istm);
					p.getOpenInventory().getTopInventory().setItem(10, ist);
				}
				if(pl.Duels == true) {
					ItemStack ist = new ItemStack(Material.EMERALD_BLOCK, 1);
					ItemMeta istm = ist.getItemMeta();
					istm.setDisplayName("�3The server Duels is currently: �aOnline.");
					istm.setLore(Arrays.asList("�bClick to close the server."));
					ist.setItemMeta(istm);
					p.getOpenInventory().getTopInventory().setItem(12, ist);
				} else {
					ItemStack ist = new ItemStack(Material.REDSTONE_BLOCK, 1);
					ItemMeta istm = ist.getItemMeta();
					istm.setDisplayName("�3The server Duels is currently: �cOffline.");
					istm.setLore(Arrays.asList("�bClick to open the server."));
					ist.setItemMeta(istm);
					p.getOpenInventory().getTopInventory().setItem(12, ist);
				}
				ItemStack closed = new ItemStack(Material.BEDROCK);
				ItemMeta closm = closed.getItemMeta();
				closm.setDisplayName("�6Coming soon..");
				closed.setItemMeta(closm);
				p.getOpenInventory().getTopInventory().setItem(14, closed);
				p.getOpenInventory().getTopInventory().setItem(16, closed);
				
				ItemStack tp = new ItemStack(Material.ENDER_PEARL);
				ItemMeta tpm = tp.getItemMeta();
				tpm.setDisplayName("�bTeleport to the FFA server");
				tp.setItemMeta(tpm);
				p.getOpenInventory().getTopInventory().setItem(19, tp);
				
				ItemStack tp2 = new ItemStack(Material.ENDER_PEARL);
				ItemMeta tpm2 = tp2.getItemMeta();
				tpm2.setDisplayName("�bTeleport to the Duels server");
				tp2.setItemMeta(tpm2);
				p.getOpenInventory().getTopInventory().setItem(21, tp2);
				
				ItemStack red = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
				ItemMeta redm = red.getItemMeta();
				redm.setDisplayName("�cClose the menu.");
				red.setItemMeta(redm);
				p.getOpenInventory().getTopInventory().setItem(p.getOpenInventory().getTopInventory().getSize() - 1, red);
			} else {
				p.closeInventory();
				it.opener.remove(p);
			}
		}
	}
}
