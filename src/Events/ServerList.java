package Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerList implements Listener {
	
	@EventHandler
	public void serverList(ServerListPingEvent e) {
		e.setMotd("�3SmoothPVP �8| �7The server is actually in maintenance.\n�7Stay connected by joining our Discord: �bXXXXXXXX.");
		//e.setMaxPlayers(100);
	}

}
