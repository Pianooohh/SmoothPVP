package Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerWrapper {
	private static final Reflection.FieldAccessor<Integer> getConnection;
    private static final Reflection.MethodInvoker getPlayerHandle;
    public static HashMap<UUID, PlayerWrapper> players;
    public int clicks;
    public int clicks2;
    public int clicks3;
    public int clicks4;
    public int clicks5;
    public int clicks6;
    public int nombreAlertesAutoClick;
    public int maxClicks;
    public long lastBlockInteraction;
    public long lastAlert;
    public long Connexion;
    public String pseudo;
    
    static {
        getConnection = Reflection.getField("{nms}.EntityPlayer", "ping", Integer.TYPE);
        getPlayerHandle = Reflection.getMethod("{obc}.entity.CraftPlayer", "getHandle", (Class<?>[])new Class[0]);
        PlayerWrapper.players = new HashMap<UUID, PlayerWrapper>();
    }
    
    public PlayerWrapper(final Player p) {
        this.clicks = 0;
        this.clicks2 = 0;
        this.clicks3 = 0;
        this.clicks4 = 0;
        this.clicks5 = 0;
        this.clicks6 = 0;
        this.nombreAlertesAutoClick = 0;
        this.maxClicks = 0;
        this.lastBlockInteraction = 0L;
        this.lastAlert = 0L;
        this.Connexion = 0L;
        this.pseudo = "";
        PlayerWrapper.players.put(p.getUniqueId(), this);
        this.pseudo = p.getName();
        this.Connexion = System.currentTimeMillis();
    }
    
    public String getName() {
        return this.pseudo;
    }
    
    public Player getPlayer() {
        return Bukkit.getPlayer(this.pseudo);
    }
    
    public static PlayerWrapper getByPlayer(final Player p) {
        for (final PlayerWrapper pw : PlayerWrapper.players.values()) {
            if (pw.getName().equals(p.getName())) {
                return pw;
            }
        }
        return null;
    }
    
    public static PlayerWrapper getByString(final String name) {
        for (final PlayerWrapper pw : PlayerWrapper.players.values()) {
            if (pw.getName().equals(name)) {
                return pw;
            }
        }
        return null;
    }
    
    public static void removePlayer(final Player p) {
        PlayerWrapper.players.remove(p.getUniqueId());
    }
    
    public int getPing() {
        final int ms = PlayerWrapper.getConnection.get(PlayerWrapper.getPlayerHandle.invoke(Bukkit.getPlayer(this.pseudo), new Object[0]));
        return ms;
    }
}