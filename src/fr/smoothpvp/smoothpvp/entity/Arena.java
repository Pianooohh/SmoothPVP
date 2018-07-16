package fr.smoothpvp.smoothpvp.entity;

import fr.smoothpvp.smoothpvp.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;

/**
 * A class to represent an arena and load/save it to a file via YAML.
 * @author U_Bren & Pianooohh
 *
 */ 
public class Arena { 
	
	private String arenaName;
	private final File YAMLFile;
	private ArrayList<Location> spawns;
	public static Set<Arena> arenas = Collections.newSetFromMap(new HashMap<Arena, Boolean>()); //Not weak, else GC will collect it when it's still of use
	
	private Arena(String arenaName, ArrayList<Location> spawns, File YAMLFile){
		this.arenaName = arenaName;
		this.spawns = spawns;
		this.YAMLFile = YAMLFile;
	}
	
	@Override()
	public void finalize() throws IOException {
		saveConfigForArena();
	}
	
	public static void createAllArenasFromDataFolder(){
		File file = new File("plugins//SmoothPVP//Arenas//");
		File[] YAMLFiles = file.listFiles(new FilenameFilter() {
			
			@Override()
			public boolean accept(File dir, String name) {
				return name.endsWith(".yml");
			}
		});
		
		for (File YAMLFile : YAMLFiles) {
			createArena(YAMLFile.getName());
		}
		return;
	}
	
	public static Arena createArena(String arenaName) {
		File file = new File("plugins//SmoothPVP//Arenas//" + arenaName);
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
		ArrayList<Location> spawns = new ArrayList<Location>();
		int spawnNumber = 1;
		while((new Double(yaml.getDouble(arenaName.replace(".yml", "" + ".spawn" + spawnNumber + ".locX")))) != null) {
			String worldName = yaml.getString(arenaName.replace(".yml", "" + ".spawn" + spawnNumber + ".world"));
			
			double x = yaml.getDouble(arenaName.replace(".yml", "") + ".spawn" + spawnNumber + ".locX");
			double y = yaml.getDouble(arenaName.replace(".yml", "") + ".spawn" + spawnNumber + ".locY");
			double z = yaml.getDouble(arenaName.replace(".yml", "") + ".spawn" + spawnNumber + ".locZ");
			double yaw = yaml.getDouble(arenaName.replace(".yml", "") + ".spawn" + spawnNumber + ".yaw");
			
			Location loc = new Location(Bukkit.getWorld(worldName), x, y, z);
			
			loc.setYaw((float) yaw);
			spawns.add(loc);
			spawnNumber++;
		}
		return createArena(arenaName, spawns, file);
	}
	
	/**
	 * 
	 * @param arenaName The name of the arena to remove
	 * @return {@value true} if the arena has been remove, {@value false} otherwise
	 */
	public static boolean removeArena(String arenaName) {
		return arenas.remove(findArena(arenaName));
	}
	
	private static Arena createArena(String arenaName, ArrayList<Location> spawns, File YAMLFile) {
		Arena arena = new Arena(arenaName, spawns, YAMLFile);
		arenas.add(arena);
		return arena;
	}
	
	public static Arena[] getArenas(){
		return (Arena[]) arenas.toArray();
	}
	
	public static Arena findArena(String arenaName){
		for(Arena arena : arenas) {
			if(arena.getName().equalsIgnoreCase(arenaName)){
				return arena;
			}
		}
		return null;
	}
	
	
	//GETTERS & SETTERS 
	
	public ArrayList<Location> getSpawnPoints(){
		return spawns;
	}
	/**
	 * Set (and remplace) spawn point number {@code spawnNumber}
	 * @param loc Location of the spawn point
	 * @param spawnNumber The number of the spawnpoint.
	 * @return An ArrayList of all SpawnPoints.
	 */
	public ArrayList<Location> setSpawnPoint(Location loc, int spawnNumber){
		spawns.set(spawnNumber, loc);
		return spawns;
	}
	
	/**
	 * Add a NEW spawn point at spawn point number {@code spawnNumber}
	 * @param loc Location of the spawn point
	 * @param spawnNumber The number of the SpawnPoint.
	 * @return An ArrayList of all SpawnPoints.
	 */
	public ArrayList<Location> addSpawnPoint(Location loc, int spawnNumber){
		spawns.add(spawnNumber, loc);
		return spawns;
	}
	
	/**
	 * Add a new spawn point at the end of the list.
	 * @param loc Location of the spawn point
	 * @return An ArrayList of all SpawnPoints.
	 */
	public ArrayList<Location> addSpawnPoint(Location loc){
		spawns.add(loc);
		return spawns;
	}
	
	public ArrayList<Location> removeSpawnPoint(Location loc, int spawnNumber){
		spawns.remove(spawnNumber);
		return spawns;
	}
	
	public ArrayList<Location> removeSpawnPoint(Location loc){
		spawns.remove(loc);
		return spawns;
	}
	
	public String getName() {
		return arenaName;
	}

	/**
	 * Save internal cache to file.
	 * @throws IOException If we cant save to the file.
	 */
	public void saveConfigForArena() throws IOException {
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(YAMLFile);
		int index=0;
		
		while(index > getSpawnPoints().size()) {
			
			Location loc = getSpawnPoints().get(index);
			
			yaml.set(getName().replace(".yml", "") + ".spawn" + index + ".world", loc.getWorld().getName());
			yaml.set(getName().replace(".yml", "") + ".spawn" + index + ".locX", loc.getX());
			yaml.set(getName().replace(".yml", "") + ".spawn" + index + ".locY", loc.getY());
			yaml.set(getName().replace(".yml", "") + ".spawn" + index + ".locZ", loc.getZ());
			yaml.set(getName().replace(".yml", "") + ".spawn" + index + ".yaw", loc.getYaw());

			index++;
		}
		
		yaml.save(YAMLFile);
	}
	
	public static void saveAllConfig() throws IOException {
		while(arenas.iterator().hasNext()) {
			arenas.iterator().next().saveConfigForArena();
		}
	}
	
	/**
	 * Flush internal cache of all arenas.
	 * WARNING: ALL UNSAVED MODIFICATIONS WILL BE ERASED.
	 * IF YOU CAN TO KEEP THEM, PLEASE USE {@code Arena.saveAllConfig()} BEFORE.
	 */
	public static void flushCache() {
		arenas.clear();
		if(Main.getInstance().getConfig().getBoolean("Internal.GC_ON_ARENA_FLUSH")) {
			Bukkit.getLogger().log(Level.FINE, "Running GC after Arena cache flush (as specified by config.)");
			Runtime.getRuntime().gc();
			Bukkit.getLogger().log(Level.FINE, "GC finished.");
			createAllArenasFromDataFolder();
		}
	}
}
