package me.mrlion.lionplugin.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class SitListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (event.getClickedBlock() != null) {
            Location clickedBlockLocation = event.getClickedBlock().getLocation();

            if (action == Action.RIGHT_CLICK_BLOCK) {
                Material blockType = event.getClickedBlock().getType();

                if (isStairs(blockType)) {
                    sitPlayer(player, clickedBlockLocation);
                }
            }
        }
    }


    private void sitPlayer(Player player, Location blockLocation) {
        Location sittingLocation = blockLocation.clone().add(0.5, 0.5, 0.5);

        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(sittingLocation, EntityType.ARMOR_STAND);
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
