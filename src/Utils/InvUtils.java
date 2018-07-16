package Utils;

import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class InvUtils {

	public void chooseEditKit(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*2, "�3Choose the kit:");
		ItemStack nodebuff = new ItemStack(Material.POTION, 1, (short)16421);
		ItemStack debuff = new ItemStack(Material.POTION, 1, (short)16388);
		ItemStack combo = new ItemStack(Material.RAW_FISH, 1, (short)3);
		ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
		ItemStack arrow = new ItemStack(Material.ARROW);
		ItemStack rod = new ItemStack(Material.FISHING_ROD);
		
		ItemMeta nodebuffm = nodebuff.getItemMeta();
		ItemMeta debuffm = debuff.getItemMeta();
		ItemMeta combom = combo.getItemMeta();
		ItemMeta soupm = soup.getItemMeta();
		ItemMeta arrowm = arrow.getItemMeta();
		ItemMeta rodm = rod.getItemMeta();
		
		nodebuffm.setDisplayName("�3NoDebuff");
		debuffm.setDisplayName("�3Debuff");
		combom.setDisplayName("�3Combo");
		soupm.setDisplayName("�3Soup");
		arrowm.setDisplayName("�3Archer");
		rodm.setDisplayName("�3Hunger Games");
		
		nodebuff.setItemMeta(nodebuffm);
		debuff.setItemMeta(debuffm);
		combo.setItemMeta(combom);
		soup.setItemMeta(soupm);
		arrow.setItemMeta(arrowm);
		rod.setItemMeta(rodm);
		
		inv.setItem(0, nodebuff);
		inv.setItem(1, debuff);
		inv.setItem(2, combo);
		inv.setItem(3, soup);
		inv.setItem(4, arrow);
		inv.setItem(5, rod);
		
		p.openInventory(inv);
	}
	
	public void giveDefaultCombo(Player p) {
		Inventory inv  = p.getInventory();
		inv.clear();
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack food = new ItemStack(Material.GOLDEN_APPLE, 64, (short)1);
		ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
		ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
		
		ItemMeta swordm = sword.getItemMeta();
		ItemMeta chestm = chestplate.getItemMeta();
		ItemMeta helmetm = helmet.getItemMeta();
		ItemMeta bootsm = boots.getItemMeta();
		ItemMeta leggm = leggings.getItemMeta();
		
		swordm.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
		swordm.addEnchant(Enchantment.DURABILITY, 3, true);
		swordm.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
		
		chestm.addEnchant(Enchantment.DURABILITY, 3, true);
		chestm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		
		helmetm.addEnchant(Enchantment.DURABILITY, 3, true);
		helmetm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		
		leggm.addEnchant(Enchantment.DURABILITY, 3, true);
		leggm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		
		bootsm.addEnchant(Enchantment.DURABILITY, 3, true);
		bootsm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		bootsm.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
		
		sword.setItemMeta(swordm);
		chestplate.setItemMeta(chestm);
		helmet.setItemMeta(helmetm);
		leggings.setItemMeta(leggm);
		boots.setItemMeta(bootsm);
		inv.setItem(0, sword);
		inv.setItem(1, food);
		inv.setItem(2, helmet);
		inv.setItem(3, chestplate);
		inv.setItem(4, leggings);
		inv.setItem(5, boots);
		p.getInventory().setBoots(boots);
		p.getInventory().setChestplate(chestplate);
		p.getInventory().setLeggings(leggings);
		p.getInventory().setHelmet(helmet);
		p.updateInventory();
	}
	public void giveDefaultArcher(Player p) {
		Inventory inv  = p.getInventory();
		inv.clear();
		ItemStack bow = new ItemStack(Material.BOW);
		ItemStack food = new ItemStack(Material.COOKED_BEEF, 64);
		ItemStack apples = new ItemStack(Material.GOLDEN_APPLE, 8);
		ItemStack arrow = new ItemStack(Material.ARROW);
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
		ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		
		ItemMeta chestm = chestplate.getItemMeta();
		ItemMeta helmetm = helmet.getItemMeta();
		ItemMeta bootsm = boots.getItemMeta();
		ItemMeta leggm = leggings.getItemMeta();
		
		chestm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		helmetm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		leggm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		bootsm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		
		ItemMeta bowm = bow.getItemMeta();
		bowm.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		bowm.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		bow.setItemMeta(bowm);
		chestplate.setItemMeta(chestm);
		helmet.setItemMeta(helmetm);
		leggings.setItemMeta(leggm);
		boots.setItemMeta(bootsm);
		
		inv.setItem(0, bow);
		inv.setItem(1, apples);
		inv.setItem(2, food);
		inv.setItem(3, arrow);
		p.getInventory().setBoots(boots);
		p.getInventory().setChestplate(chestplate);
		p.getInventory().setLeggings(leggings);
		p.getInventory().setHelmet(helmet);
		p.updateInventory();
	}
	
	public void giveDefaultHG(Player p) {
		Inventory inv  = p.getInventory();
		inv.clear();
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack bow = new ItemStack(Material.BOW);
		ItemStack food = new ItemStack(Material.COOKED_BEEF, 6);
		ItemStack food2 = new ItemStack(Material.COOKED_FISH, 2);
		ItemStack food3 = new ItemStack(Material.GRILLED_PORK, 5);
		ItemStack food4 = new ItemStack(Material.PUMPKIN_PIE);
		ItemStack rod = new ItemStack(Material.FISHING_ROD);
		ItemStack apples = new ItemStack(Material.GOLDEN_APPLE, 8);
		ItemStack arrow = new ItemStack(Material.ARROW, 28);
		
		ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
		ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);

		inv.setItem(0, sword);
		inv.setItem(1, bow);
		inv.setItem(2, rod);
		inv.setItem(3, apples);
		inv.setItem(4, food);
		inv.setItem(5, food2);
		inv.setItem(6, food3);
		inv.setItem(7, food4);
		inv.setItem(8, arrow);
		p.getInventory().setBoots(boots);
		p.getInventory().setChestplate(chestplate);
		p.getInventory().setLeggings(leggings);
		p.getInventory().setHelmet(helmet);
		p.updateInventory();
	}
	
	public void giveDefaultSoup(Player p) {
		Inventory inv  = p.getInventory();
		inv.clear();
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack food = new ItemStack(Material.MUSHROOM_SOUP);
		ItemStack helmet = new ItemStack(Material.IRON_HELMET);
		ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack boots = new ItemStack(Material.IRON_BOOTS);
		
		ItemMeta swordm = sword.getItemMeta();
		swordm.addEnchant(Enchantment.DAMAGE_ALL, 1, true);

		sword.setItemMeta(swordm);
		inv.setItem(0, sword);
		for(int i = 0; i < inv.getSize(); i++) {
			inv.addItem(food);
		}
		p.getInventory().setBoots(boots);
		p.getInventory().setChestplate(chestplate);
		p.getInventory().setLeggings(leggings);
		p.getInventory().setHelmet(helmet);
		p.updateInventory();
	}
	
	public void giveDefaultNodebuff(Player p) {
		Inventory inv  = p.getInventory();
		inv.clear();
		ItemStack heal = new ItemStack(Material.POTION, 1, (short)16421);
		ItemStack fire = new ItemStack(Material.POTION, 1, (short)8259);
		ItemStack speed = new ItemStack(Material.POTION, 1, (short)8226);
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack pearl = new ItemStack(Material.ENDER_PEARL, 16);
		ItemStack food = new ItemStack(Material.COOKED_BEEF, 64);
		ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
		ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
		
		ItemMeta swordm = sword.getItemMeta();
		ItemMeta chestm = chestplate.getItemMeta();
		ItemMeta helmetm = helmet.getItemMeta();
		ItemMeta bootsm = boots.getItemMeta();
		ItemMeta leggm = leggings.getItemMeta();
		
		swordm.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
		swordm.addEnchant(Enchantment.DURABILITY, 3, true);
		swordm.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
		
		chestm.addEnchant(Enchantment.DURABILITY, 3, true);
		chestm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		
		helmetm.addEnchant(Enchantment.DURABILITY, 3, true);
		helmetm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		
		leggm.addEnchant(Enchantment.DURABILITY, 3, true);
		leggm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		
		bootsm.addEnchant(Enchantment.DURABILITY, 3, true);
		bootsm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		bootsm.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
		
		sword.setItemMeta(swordm);
		chestplate.setItemMeta(chestm);
		helmet.setItemMeta(helmetm);
		leggings.setItemMeta(leggm);
		boots.setItemMeta(bootsm);
		inv.setItem(0, sword);
		inv.setItem(1, pearl);
		inv.setItem(2, fire);
		inv.setItem(3, speed);
		inv.setItem(17, speed);
		inv.setItem(16+10, speed);
		inv.setItem(16+19, speed);
		inv.setItem(8, food);
		for(int i = 0; i < 31; i++) {
			inv.addItem(heal);
		}
		p.getInventory().setBoots(boots);
		p.getInventory().setChestplate(chestplate);
		p.getInventory().setLeggings(leggings);
		p.getInventory().setHelmet(helmet);
		p.updateInventory();
	}
	
	public void giveDefaultDebuff(Player p) {
		Inventory inv  = p.getInventory();
		inv.clear();
		ItemStack heal = new ItemStack(Material.POTION, 1, (short)16421);
		ItemStack fire = new ItemStack(Material.POTION, 1, (short)8259);
		ItemStack speed = new ItemStack(Material.POTION, 1, (short)8226);
		ItemStack poison = new ItemStack(Material.POTION, 1, (short)16388);
		ItemStack damage = new ItemStack(Material.POTION, 1, (short)16426);
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack pearl = new ItemStack(Material.ENDER_PEARL, 16);
		ItemStack food = new ItemStack(Material.COOKED_BEEF, 64);
		ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
		ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
		
		ItemMeta swordm = sword.getItemMeta();
		ItemMeta chestm = chestplate.getItemMeta();
		ItemMeta helmetm = helmet.getItemMeta();
		ItemMeta bootsm = boots.getItemMeta();
		ItemMeta leggm = leggings.getItemMeta();
		
		swordm.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
		swordm.addEnchant(Enchantment.DURABILITY, 3, true);
		swordm.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
		
		chestm.addEnchant(Enchantment.DURABILITY, 3, true);
		chestm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		
		helmetm.addEnchant(Enchantment.DURABILITY, 3, true);
		helmetm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		
		leggm.addEnchant(Enchantment.DURABILITY, 3, true);
		leggm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		
		bootsm.addEnchant(Enchantment.DURABILITY, 3, true);
		bootsm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		bootsm.addEnchant(Enchantment.PROTECTION_FALL, 4, true);
		
		sword.setItemMeta(swordm);
		chestplate.setItemMeta(chestm);
		helmet.setItemMeta(helmetm);
		leggings.setItemMeta(leggm);
		boots.setItemMeta(bootsm);
		inv.setItem(0, sword);
		inv.setItem(1, pearl);
		inv.setItem(2, fire);
		inv.setItem(3, speed);
		inv.setItem(17, speed);
		inv.setItem(16, damage);
		inv.setItem(15, poison);
		inv.setItem(16+10, speed);
		inv.setItem(16+9, damage);
		inv.setItem(16+8, poison);
		inv.setItem(16+19, speed);
		inv.setItem(8, food);
		for(int i = 0; i < 31; i++) {
			inv.addItem(heal);
		}
		p.getInventory().setBoots(boots);
		p.getInventory().setChestplate(chestplate);
		p.getInventory().setLeggings(leggings);
		p.getInventory().setHelmet(helmet);
		p.updateInventory();
	}
	
	public void unrankedInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*2, "�3Unranked 1v1 queue:");
		ItemStack nodebuff = new ItemStack(Material.POTION, 1, (short)16421);
		ItemStack debuff = new ItemStack(Material.POTION, 1, (short)16388);
		ItemStack combo = new ItemStack(Material.RAW_FISH, 1, (short)3);
		ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
		ItemStack arrow = new ItemStack(Material.ARROW);
		ItemStack rod = new ItemStack(Material.FISHING_ROD);
		
		ItemMeta nodebuffm = nodebuff.getItemMeta();
		ItemMeta debuffm = debuff.getItemMeta();
		ItemMeta combom = combo.getItemMeta();
		ItemMeta soupm = soup.getItemMeta();
		ItemMeta arrowm = arrow.getItemMeta();
		ItemMeta rodm = rod.getItemMeta();
		
		nodebuffm.setDisplayName("�3NoDebuff");
		debuffm.setDisplayName("�3Debuff");
		combom.setDisplayName("�3Combo");
		soupm.setDisplayName("�3Soup");
		arrowm.setDisplayName("�3Archer");
		rodm.setDisplayName("�3Hunger Games");
		
		nodebuffm.setLore(Arrays.asList("�bIn Match: �7" + Main.getInstance().nodebuff.size() + ".", "�bIn Queue: �7" + Main.getInstance().qnodebuff.size() + "."));
		debuffm.setLore(Arrays.asList("�bIn Match: �7" + Main.getInstance().debuff.size() + ".", "�bIn Queue: �7" + Main.getInstance().qdebuff.size() + "."));
		combom.setLore(Arrays.asList("�bIn Match: �7" + Main.getInstance().combo.size() + ".", "�bIn Queue: �7" + Main.getInstance().qcombo.size() + "."));
		soupm.setLore(Arrays.asList("�bIn Match: �7" + Main.getInstance().soup.size() + ".", "�bIn Queue: �7" + Main.getInstance().qsoup.size() + "."));
		arrowm.setLore(Arrays.asList("�bIn Match: �7" + Main.getInstance().arrow.size() + ".", "�bIn Queue: �7" + Main.getInstance().qarrow.size() + "."));
		rodm.setLore(Arrays.asList("�bIn Match: �7" + Main.getInstance().hg.size() + ".", "�bIn Queue: �7" + Main.getInstance().qhg.size() + "."));
		
		nodebuff.setItemMeta(nodebuffm);
		debuff.setItemMeta(debuffm);
		combo.setItemMeta(combom);
		soup.setItemMeta(soupm);
		arrow.setItemMeta(arrowm);
		rod.setItemMeta(rodm);
		
		inv.setItem(0, nodebuff);
		inv.setItem(1, debuff);
		inv.setItem(2, combo);
		inv.setItem(3, soup);
		inv.setItem(4, arrow);
		inv.setItem(5, rod);
		
		p.openInventory(inv);
		
	}
	
	@SuppressWarnings("deprecation")
	public static void giveSpawnItems(Player p) {
		Inventory inv = p.getInventory();
		inv.clear();
		p.getInventory().setChestplate(null);
		p.getInventory().setBoots(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setHelmet(null);
		p.setHealth(20);
		p.setFoodLevel(20);
		ItemStack modtools = new ItemStack(Material.PAPER, 1);
		ItemStack ranked = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemStack unranked = new ItemStack(Material.IRON_SWORD, 1);
		ItemStack teleport = new ItemStack(Material.NETHER_STAR, 1);
		ItemStack editkit = new ItemStack(Material.ANVIL, 1);
		final ItemStack event = new ItemStack(Material.INK_SACK, 1, (byte)10);
		ItemMeta eventm = event.getItemMeta();
		eventm.setDisplayName("�6Hide players �7(Right-Click)");
		eventm.addEnchant(Enchantment.DURABILITY, 10, true);
		event.setItemMeta(eventm);
		p.getInventory().setItem(8, event);
		
		ItemMeta modtm = modtools.getItemMeta();
		ItemMeta rankedm = ranked.getItemMeta();
		ItemMeta unrankedm = unranked.getItemMeta();
		ItemMeta teleportm = teleport.getItemMeta();
		ItemMeta editkitm = editkit.getItemMeta();

		modtm.setDisplayName("�eSwitch to moderator mod");
		rankedm.setDisplayName("�3Ranked 1v1");
		unrankedm.setDisplayName("�bUnranked 1v1");
		teleportm.setDisplayName("�aWarps");
		editkitm.setDisplayName("�6Edit Kit");

		modtm.addEnchant(Enchantment.DURABILITY, 10, true);
		rankedm.addEnchant(Enchantment.DURABILITY, 10, true);
		unrankedm.addEnchant(Enchantment.DURABILITY, 10, true);
		teleportm.addEnchant(Enchantment.DURABILITY, 10, true);
		editkitm.addEnchant(Enchantment.DURABILITY, 10, true);

		modtools.setItemMeta(modtm);
		ranked.setItemMeta(rankedm);
		unranked.setItemMeta(unrankedm);
		teleport.setItemMeta(teleportm);
		editkit.setItemMeta(editkitm);

		if (p.isOp() || p.hasPermission("staff.mod")) {
			inv.setItem(2, modtools);
		}
		inv.setItem(0, ranked);
		inv.setItem(1, unranked);
		inv.setItem(4, teleport);
		inv.setItem(7, editkit);

		p.updateInventory();

	}

	public void kbMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, "�3Knockback Sword Editor:");
		ItemStack sword1 = new ItemStack(Material.WOOD_SWORD);
		ItemStack sword2 = new ItemStack(Material.WOOD_SWORD);
		ItemStack paper = new ItemStack(Material.PAPER);
		ItemStack sword3 = new ItemStack(Material.WOOD_SWORD);
		ItemStack sword4 = new ItemStack(Material.WOOD_SWORD);

		ItemMeta sword1m = sword1.getItemMeta();
		ItemMeta sword2m = sword2.getItemMeta();
		ItemMeta paperm = paper.getItemMeta();
		ItemMeta sword3m = sword3.getItemMeta();
		ItemMeta sword4m = sword4.getItemMeta();

		sword1m.setDisplayName("�3Knockback Level: �b1");
		sword2m.setDisplayName("�3Knockback Level: �b2");
		paperm.setDisplayName("�3Custom Knockback Level");
		sword3m.setDisplayName("�3Knockback Level: �b3");
		sword4m.setDisplayName("�3Knockback Level: �b4");

		sword1m.setLore(Arrays.asList("�bChange the knockback value to 1."));
		sword2m.setLore(Arrays.asList("�bChange the knockback value to 2."));
		paperm.setLore(Arrays.asList("�bSet the value of the knockback from the chat."));
		sword3m.setLore(Arrays.asList("�bChange the knockback value to 3."));
		sword4m.setLore(Arrays.asList("�bChange the knockback value to 4."));

		sword1.setItemMeta(sword1m);
		sword2.setItemMeta(sword2m);
		sword3.setItemMeta(sword3m);
		sword4.setItemMeta(sword4m);
		paper.setItemMeta(paperm);

		inv.setItem(0, sword1);
		inv.setItem(1, sword2);
		inv.setItem(2, paper);
		inv.setItem(3, sword3);
		inv.setItem(4, sword4);

		p.openInventory(inv);
		
		p.updateInventory();

	}

	public static void giveModItems(Player p) {
		Inventory inv = p.getInventory();
		inv.clear();
		ItemStack compass = new ItemStack(Material.COMPASS, 1);
		ItemStack paper = new ItemStack(Material.PAPER, 1);
		ItemStack blaze = new ItemStack(Material.BLAZE_ROD, 1);
		ItemStack feather = new ItemStack(Material.FEATHER, 1);
		ItemStack netherstar = new ItemStack(Material.NETHER_STAR, 1);
		ItemStack bone = new ItemStack(Material.NAME_TAG, 1);
		ItemStack sword = new ItemStack(Material.WOOD_SWORD, 1);
		ItemStack redstone = new ItemStack(Material.REDSTONE, 1);

		ItemMeta compassm = compass.getItemMeta();
		ItemMeta paperm = paper.getItemMeta();
		ItemMeta blazem = blaze.getItemMeta();
		ItemMeta featherm = feather.getItemMeta();
		ItemMeta netherm = netherstar.getItemMeta();
		ItemMeta bonem = bone.getItemMeta();
		ItemMeta swordm = sword.getItemMeta();
		ItemMeta redm = redstone.getItemMeta();

		compassm.setDisplayName("�3Server Teleport");
		paperm.setDisplayName("�3Verif Player");
		blazem.setDisplayName("�3Manage Player");
		featherm.setDisplayName("�3Random Teleport");
		netherm.setDisplayName("�3Freeze Player");
		bonem.setDisplayName("�3Player Infos");
		swordm.setDisplayName("�3Knockback Sword �7- �bLevel:�f 1");
		redm.setDisplayName("�cLeave the moderator mod");

		compassm.addEnchant(Enchantment.DURABILITY, 10, true);
		paperm.addEnchant(Enchantment.DURABILITY, 10, true);
		blazem.addEnchant(Enchantment.DURABILITY, 10, true);
		featherm.addEnchant(Enchantment.DURABILITY, 10, true);
		netherm.addEnchant(Enchantment.DURABILITY, 10, true);
		bonem.addEnchant(Enchantment.DURABILITY, 10, true);
		swordm.addEnchant(Enchantment.KNOCKBACK, 5, true);
		swordm.addEnchant(Enchantment.DURABILITY, 10, true);

		compass.setItemMeta(compassm);
		paper.setItemMeta(paperm);
		blaze.setItemMeta(blazem);
		feather.setItemMeta(featherm);
		netherstar.setItemMeta(netherm);
		bone.setItemMeta(bonem);
		sword.setItemMeta(swordm);
		redstone.setItemMeta(redm);

		inv.setItem(6, compass);
		inv.setItem(1, paper);
		inv.setItem(2, blaze);
		inv.setItem(5, feather);
		inv.setItem(3, netherstar);
		inv.setItem(4, bone);
		inv.setItem(0, sword);
		inv.setItem(8, redstone);

		p.updateInventory();

	}

}
