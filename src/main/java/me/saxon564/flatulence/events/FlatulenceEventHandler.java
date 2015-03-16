package me.saxon564.flatulence.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class FlatulenceEventHandler {
	private int t=1;
	int flatulence = 0;
	boolean water = false;
	
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		EntityPlayer player = (EntityPlayer) event.player.getCommandSenderEntity();
		World world = player.worldObj;
		
		if (water) {
			flatulence++;
		}
		
		if (flatulence >= 100) {
			world.playSound(player.posX, player.posY, player.posZ, "flatulence:waterFlatulence",
		               1.0F, 1.0F, true);
			flatulence = 0;
			water = false;
		}
		
		if (world.isRemote && player.isSneaking() && !player.isSpectator() && !player.capabilities.isCreativeMode) {
			if (t == 1) {
				t = 100;
				
				if (player.isInWater()) {
					world.playSound(player.posX, player.posY, player.posZ, "flatulence:flatulence",
	               1.0F, 0.25F, true);
					water = true;
				} else {
					world.playSound(player.posX, player.posY, player.posZ, "flatulence:flatulence",
				               1.0F, 1.0F, true);
				}
			}
		}
		if (world.isRemote && !player.isSneaking()) {
			if (t > 1) {
				t--;
			}
		}
	}

}
