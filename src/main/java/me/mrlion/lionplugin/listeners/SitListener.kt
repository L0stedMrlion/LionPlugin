package me.mrlion.lionplugin.listeners

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class SitListener : Listener {
    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val action = event.action
        val clickedBlock = event.clickedBlock

        if (action == Action.RIGHT_CLICK_BLOCK && clickedBlock != null) {
            val blockType = clickedBlock.type

            if (isStairs(blockType)) {
                sitPlayer(player, clickedBlock.location)
            }
        }
    }

    private fun sitPlayer(player: Player, blockLocation: Location) {
        val sittingLocation = blockLocation.clone().add(0.5, 0.5, 0.5)

        val armorStand = player.world.spawnEntity(sittingLocation, EntityType.ARMOR_STAND) as ArmorStand
        armorStand.isVisible = false
        armorStand.isSmall = true
        armorStand.isMarker = true
        armorStand.setGravity(false)
        armorStand.addPassenger(player)
    }

    private fun isStairs(material: Material): Boolean {
        return material.name.endsWith("_STAIRS")
    }
}
