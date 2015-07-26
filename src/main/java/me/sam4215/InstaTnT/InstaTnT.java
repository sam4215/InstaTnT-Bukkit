package me.sam4215.InstaTnT;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * THIS CODE MAY BE LICENSED AND/OR COPYRIGHTED BY EMULSION TECHNOLOGIES.
 *
 * @author Sam ("sam4215")
 *         <p/>
 *         CREATED FOR PROJECT: InstaTnT-Bukkit IN me.sam4215.InstaTnT
 *         <p/>
 *         FILE CREATED ON 7/26/2015 at 8:08 AM
 */
public class InstaTnT extends JavaPlugin implements Listener {
    public final ArrayList<Player> InstaTnTUsers = new ArrayList<Player>();
    public void onEnable() {
        getLogger().info("InstaTnT Enabled || By sam4215");
        getServer().getPluginManager().registerEvents(this, this);
    }
    public void onDisable() {
        getLogger().info("InstaTnT Disabled || By sam4215");
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("instatnt")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage("Only players can use this command!");
                return false;
            }
            toggleInstaTnT(sender);
            return true;
        }
        return false;
    }
    public void toggleInstaTnT(CommandSender sender) {
        if(InstaTnTUsers.contains((Player) sender)) {
            InstaTnTUsers.remove((Player) sender);
            ((Player) sender).sendMessage(ChatColor.YELLOW + "Disabled InstaTnT");
        } else {
            InstaTnTUsers.add((Player) sender);
            ((Player) sender).sendMessage(ChatColor.GREEN + "Enabled InstaTnT");
        }
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onBlockPlace(BlockPlaceEvent e) {
        if(e.getBlock().equals(Material.TNT)) {
            e.getBlock().setType(Material.AIR);
            e.getPlayer().getWorld().spawn(e.getBlock().getLocation(), TNTPrimed.class);
        }
    }
}
