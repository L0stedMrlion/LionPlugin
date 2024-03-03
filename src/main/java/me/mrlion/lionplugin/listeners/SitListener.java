package me.mrlion.lionplugin.listeners;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SitListener implements Listener {

    private final LionPlugin plugin;

    public SitListener(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        Block clickedBlock = event.getClickedBlock();

        if (action.equals(Action.RIGHT_CLICK_BLOCK) && clickedBlock != null) {
            Material blockType = clickedBlock.getType();

            if (isStairs(blockType)) {
                sitPlayer(player, clickedBlock.getLocation());
            }
        }
    }

    private void sitPlayer(Player player, Location blockLocation) {
        Location sittingLocation = blockLocation.clone().add(0.5, 0.5, 0.5);

        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(sittingLocation, org.bukkit.entity.EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setSmall(true);
        armorStand.setMarker(true);
        armorStand.setGravity(false);
        armorStand.addPassenger(player);
    }

    private boolean isStairs(Material material) {
        return material.name().endsWith("_STAIRS");
    }
}
