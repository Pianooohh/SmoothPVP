package Listeners;

import Main.Main;
import org.bukkit.*;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class FeastListener {

	public static World world = Bukkit.getWorld("world");
	public static int countdown = 8800;

	public static int getRandom(int from, int to) {
		if (from < to) {
			return from + new Random().nextInt(Math.abs(to - from));
		}
		return from - new Random().nextInt(Math.abs(to - from));
	}
	
	public static void clearChests() {
		FeastListener.world.getBlockAt(new Location(FeastListener.world, 260, 22, 1433)).setType(Material.AIR);
		FeastListener.world.getBlockAt(new Location(FeastListener.world, 258, 22, 1433)).setType(Material.AIR);
		FeastListener.world.getBlockAt(new Location(FeastListener.world, 258, 22, 1435)).setType(Material.AIR);
		FeastListener.world.getBlockAt(new Location(FeastListener.world, 256, 22, 1433)).setType(Material.AIR);
		FeastListener.world.getBlockAt(new Location(FeastListener.world, 259, 22, 1434)).setType(Material.AIR);
		FeastListener.world.getBlockAt(new Location(FeastListener.world, 257, 22, 1434)).setType(Material.AIR);
		FeastListener.world.getBlockAt(new Location(FeastListener.world, 260, 22, 1435)).setType(Material.AIR);
		FeastListener.world.getBlockAt(new Location(FeastListener.world, 256, 22, 1435)).setType(Material.AIR);
		FeastListener.world.getBlockAt(new Location(FeastListener.world, 257, 22, 1436)).setType(Material.AIR);
		FeastListener.world.getBlockAt(new Location(FeastListener.world, 259, 22, 1436)).setType(Material.AIR);
		FeastListener.world.getBlockAt(new Location(FeastListener.world, 260, 22, 1437)).setType(Material.AIR);
		FeastListener.world.getBlockAt(new Location(FeastListener.world, 258, 22, 1437)).setType(Material.AIR);
		FeastListener.world.getBlockAt(new Location(FeastListener.world, 256, 22, 1437)).setType(Material.AIR);
		FeastListener.world.playSound(new Location(FeastListener.world, 256, 22, 1433), Sound.BAT_TAKEOFF, 1, 1);
	}

	public static void buildChestEVent() {
		world.getBlockAt(new Location(world, 260, 22, 1433)).setType(Material.CHEST);
		Chest chest1 = (Chest) world.getBlockAt(new Location(world, 260, 22, 1433)).getState();
		world.getBlockAt(new Location(world, 258, 22, 1433)).setType(Material.CHEST);
		Chest chest2 = (Chest) world.getBlockAt(new Location(world, 258, 22, 1433)).getState();
		world.getBlockAt(new Location(world, 258, 22, 1435)).setType(Material.ENCHANTMENT_TABLE);
		world.getBlockAt(new Location(world, 256, 22, 1433)).setType(Material.CHEST);
		Chest chest3 = (Chest) world.getBlockAt(new Location(world, 256, 22, 1433)).getState();
		world.getBlockAt(new Location(world, 259, 22, 1434)).setType(Material.CHEST);
		Chest chest4 = (Chest) world.getBlockAt(new Location(world, 259, 22, 1434)).getState();
		world.getBlockAt(new Location(world, 257, 22, 1434)).setType(Material.CHEST);
		Chest chest5 = (Chest) world.getBlockAt(new Location(world, 257, 22, 1434)).getState();
		world.getBlockAt(new Location(world, 260, 22, 1435)).setType(Material.CHEST);
		Chest chest6 = (Chest) world.getBlockAt(new Location(world, 260, 22, 1435)).getState();
		world.getBlockAt(new Location(world, 256, 22, 1435)).setType(Material.CHEST);
		Chest chest7 = (Chest) world.getBlockAt(new Location(world, 256, 22, 1435)).getState();
		world.getBlockAt(new Location(world, 257, 22, 1436)).setType(Material.CHEST);
		Chest chest8 = (Chest) world.getBlockAt(new Location(world, 257, 22, 1436)).getState();
		world.getBlockAt(new Location(world, 259, 22, 1436)).setType(Material.CHEST);
		Chest chest9 = (Chest) world.getBlockAt(new Location(world, 259, 22, 1436)).getState();
		world.getBlockAt(new Location(world, 260, 22, 1437)).setType(Material.CHEST);
		Chest chest10 = (Chest) world.getBlockAt(new Location(world, 260, 22, 1437)).getState();
		world.getBlockAt(new Location(world, 258, 22, 1437)).setType(Material.CHEST);
		Chest chest11 = (Chest) world.getBlockAt(new Location(world, 258, 22, 1437)).getState();
		world.getBlockAt(new Location(world, 256, 22, 1437)).setType(Material.CHEST);
		Chest chest12 = (Chest) world.getBlockAt(new Location(world, 256, 22, 1437)).getState();

		fillChest(chest1);
		fillChest(chest2);
		fillChest(chest3);
		fillChest(chest4);
		fillChest(chest5);
		fillChest(chest6);
		fillChest(chest7);
		fillChest(chest8);
		fillChest(chest9);
		fillChest(chest10);
		fillChest(chest11);
		fillChest(chest12);

		new BukkitRunnable() {
			public void run() {
				chest1.getInventory().clear();
				chest2.getInventory().clear();
				chest3.getInventory().clear();
				chest4.getInventory().clear();
				chest5.getInventory().clear();
				chest6.getInventory().clear();
				chest7.getInventory().clear();
				chest8.getInventory().clear();
				chest9.getInventory().clear();
				chest10.getInventory().clear();
				chest11.getInventory().clear();
				chest12.getInventory().clear();
				clearChests();
				Bukkit.broadcastMessage("�3�lFEAST: �bThe feast has just disappeared.");
				FeastListener.world.playSound(new Location(FeastListener.world, 256, 22, 1433), Sound.BAT_TAKEOFF, 1, 1);
			}
		}.runTaskLater(Main.getInstance(), 1650L);
	}

	public static void fillChest(Chest chest) {
		Random random = new Random();
		ItemStack[] items = { new ItemStack(Material.POTION, 1, (short)16388), new ItemStack(Material.ENDER_PEARL, getRandom(0, 2)) };
		Random r = new Random();
		int ran = r.nextInt(2);
		if (ran == 0) {
			for (int i = 0; i < 3; i++) {
				chest.getInventory().addItem(new ItemStack[] { new ItemStack(items[random.nextInt(items.length)]) });
			}
		}
		if (ran == 1) {
			for (int i = 0; i < 2; i++) {
				chest.getInventory().addItem(new ItemStack[] { new ItemStack(items[random.nextInt(items.length)]) });
			}
		}
	}

	public static void doFeastListener() {
		new BukkitRunnable() {
			public void run() {
				int minute = FeastListener.countdown / 60 / 20;
				String countdownminute = "�3�lFEAST: �bThe feast will spawn in " + minute + " minute(s).";
				String countdownsec = "�3�lFEAST: �bThe feast will spawn in " + FeastListener.countdown / 20
						+ " secondes.";
				if (FeastListener.countdown == 6200) {
					Bukkit.broadcastMessage(countdownminute);
				}
				if (FeastListener.countdown == 1200) {
					Bukkit.broadcastMessage(countdownminute);
				}
				if (FeastListener.countdown == 600) {
					Bukkit.broadcastMessage(countdownsec);
				}
				if (FeastListener.countdown == 300) {
					Bukkit.broadcastMessage(countdownsec);
				}
				if (FeastListener.countdown == 200) {
					Bukkit.broadcastMessage(countdownsec);
				}
				if (FeastListener.countdown == 100) {
					Bukkit.broadcastMessage(countdownsec);
				}
				if (FeastListener.countdown == 80) {
					Bukkit.broadcastMessage(countdownsec);
				}
				if (FeastListener.countdown == 60) {
					Bukkit.broadcastMessage(countdownsec);
				}
				if (FeastListener.countdown == 40) {
					Bukkit.broadcastMessage(countdownsec);
				}
				if (FeastListener.countdown == 20) {
					Bukkit.broadcastMessage(countdownsec);
				}
				if (FeastListener.countdown == 0) {
					Bukkit.broadcastMessage("�3�lFEAST: �bThe feast has spawned.");
					FeastListener.buildChestEVent();
					FeastListener.countdown = 8800;
				}
			}
		}.runTaskTimer(Main.getInstance(), 0L, 20L);
	}

}
