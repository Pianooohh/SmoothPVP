package fr.smoothpvp.smoothpvp.Listeners;

import fr.smoothpvp.smoothpvp.Managers.KnockbackManager;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityVelocity;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class KnockbackListener implements Listener {

	private KnockbackManager knockbackManager = new KnockbackManager();

	@EventHandler
	public void onPlayerVelocity(PlayerVelocityEvent event) {
		Player player = event.getPlayer();
		EntityDamageEvent lastDamage = player.getLastDamageCause();
		if ((lastDamage == null) || (!(lastDamage instanceof EntityDamageByEntityEvent))) {
			return;
		}
		if ((((EntityDamageByEntityEvent) lastDamage).getDamager() instanceof Player)) {
			event.setCancelled(true);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if ((!(event.getEntity() instanceof Player)) || (!(event.getDamager() instanceof Player))) {
			return;
		}
		if (event.isCancelled()) {
			return;
		}
		Player damaged = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		if (damaged.getNoDamageTicks() > damaged.getMaximumNoDamageTicks() / 2.0D) {
			return;
		}
		if (!damager.hasPotionEffect(PotionEffectType.SPEED)) {
			double horMultiplier = knockbackManager.getSwordKBHorizontal();
			double verMultiplier = knockbackManager.getSwordKBHVertical();
			double sprintMultiplier = damager.isSprinting() ? 0.8D : 0.5D;
			double kbMultiplier = damager.getItemInHand() == null ? 0.0D
					: damager.getItemInHand().getEnchantmentLevel(Enchantment.KNOCKBACK) * 0.2D;

			double airMultiplier = damaged.isOnGround() ? 1.0D : 0.5D;

			Vector knockback = damaged.getLocation().toVector().subtract(damager.getLocation().toVector()).normalize();
			knockback.setX((knockback.getX() * sprintMultiplier + kbMultiplier) * horMultiplier);
			knockback.setY(0.35D * airMultiplier * verMultiplier);
			knockback.setZ((knockback.getZ() * sprintMultiplier + kbMultiplier) * horMultiplier);

			((CraftPlayer) damaged).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityVelocity(
					damaged.getEntityId(), knockback.getX(), knockback.getY(), knockback.getZ()));
		} else {
			double horMultiplier = knockbackManager.getSpeedKBHorizontal();
			double verMultiplier = knockbackManager.getSpeedKBVertical();
			double sprintMultiplier = damager.isSprinting() ? 0.8D : 0.5D;
			double kbMultiplier = damager.getItemInHand() == null ? 0.0D
					: damager.getItemInHand().getEnchantmentLevel(Enchantment.KNOCKBACK) * 0.2D;

			double airMultiplier = damaged.isOnGround() ? 1.0D : 0.5D;

			Vector knockback = damaged.getLocation().toVector().subtract(damager.getLocation().toVector()).normalize();
			knockback.setX((knockback.getX() * sprintMultiplier + kbMultiplier) * horMultiplier);
			knockback.setY(0.35D * airMultiplier * verMultiplier);
			knockback.setZ((knockback.getZ() * sprintMultiplier + kbMultiplier) * horMultiplier);

			((CraftPlayer) damaged).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityVelocity(
					damaged.getEntityId(), knockback.getX(), knockback.getY(), knockback.getZ()));
		}
	}

}
