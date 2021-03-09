package me.swipez.blocksefficiency.listeners;

import me.swipez.blocksefficiency.BlocksEfficiency;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Mine implements Listener {
	
	Map<UUID, Integer> blockCount = new HashMap<UUID, Integer>();
	
	public Mine(BlocksEfficiency plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	private boolean isTool(ItemStack i) {
		String item = i.getType().toString();
		return item.contains("SWORD") || item.contains("AXE") || item.contains("SHEARS") || item.contains("SHOVEL") || item.contains("HOE");
		
	}
	
	@EventHandler
	public void hundredBlock(BlockBreakEvent event) {

		Player p = event.getPlayer();
		ItemStack helditem = p.getInventory().getItemInMainHand();

		UUID uuid = event.getPlayer().getUniqueId();

		if (isTool(helditem)) {
			if (!blockCount.containsKey(uuid)) {
				blockCount.put(uuid, 1);
			} else {
				blockCount.replace(uuid, blockCount.get(uuid) + 1);
			}

			if (blockCount.get(uuid) % 100 == 0) {

				p.sendMessage(ChatColor.GREEN + "You have mined " + ChatColor.AQUA + blockCount.get(uuid) + ChatColor.GREEN + " blocks!");

				Map<Enchantment, Integer> enchantments = helditem.getEnchantments();
				ItemMeta meta = helditem.getItemMeta();

				int value;
				if (helditem.containsEnchantment(Enchantment.DIG_SPEED)) {
					value = enchantments.get(Enchantment.DIG_SPEED) + 1;
				} else {
					value = 1;
				}
				meta.addEnchant(Enchantment.DIG_SPEED, value, true);
				helditem.setItemMeta(meta);
			}
		}
	}
}
