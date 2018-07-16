package fr.smoothpvp.smoothpvp.Utils;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GuiUtils {

	public static void setItem(Inventory inv, ItemStack item, int slot, String name)
	  {
	    ItemStack itemstack = item;
	    ItemMeta ismeta = itemstack.getItemMeta();
	    ismeta.setDisplayName(name);
	    itemstack.setItemMeta(ismeta);
	    inv.setItem(slot, itemstack);
	  }
	  
	  public static void setItemWithLore(Inventory inv, ItemStack item, int slot, String name, String lore)
	  {
	    ItemStack itemstack = item;
	    ItemMeta ismeta = itemstack.getItemMeta();
	    ismeta.setDisplayName(name);
	    ismeta.setLore(Arrays.asList(lore));
	    itemstack.setItemMeta(ismeta);
	    inv.setItem(slot, itemstack);
	  }
	  
	  public static void addItem(Inventory inv, ItemStack item, String name)
	  {
	    ItemStack itemstack = item;
	    ItemMeta ismeta = itemstack.getItemMeta();
	    ismeta.setDisplayName(name);
	    itemstack.setItemMeta(ismeta);
	    inv.addItem(itemstack);
	  }
	  
	  public static void addFreeItem(Inventory inv, ItemStack item, String name)
	  {
	    ItemStack itemstack = item;
	    ItemMeta ismeta = itemstack.getItemMeta();
	    ismeta.setDisplayName(name);
	    ismeta.setLore(Arrays.asList("§e§lTemporairement gratuit !"));
	    itemstack.setItemMeta(ismeta);
	    inv.addItem(itemstack);
	  }
	  
	  public static void addEnchantedItem(Inventory inv, ItemStack item, String name, Enchantment ench, int enchlevel)
	  {
	    ItemStack itemstack = item;
	    itemstack.addUnsafeEnchantment(ench, enchlevel);
	    ItemMeta ismeta = itemstack.getItemMeta();
	    ismeta.setDisplayName(name);
	    itemstack.setItemMeta(ismeta);
	    inv.addItem(itemstack);
	  }
	  
	  public static void addEnchantment(Inventory inv, int slot, Enchantment enchant, int enchlevel)
	  {
	    ItemStack is = inv.getItem(slot);
	    is.addUnsafeEnchantment(enchant, enchlevel);
	  }
}
