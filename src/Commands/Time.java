package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Date now = new Date();
		SimpleDateFormat hours = new SimpleDateFormat("HH");
		SimpleDateFormat minutes = new SimpleDateFormat("mm");
		sender.sendMessage("�3Actually, it is: �8" + hours.format(now) + "h" + minutes.format(now) + ".");
		return false;
	}

}
