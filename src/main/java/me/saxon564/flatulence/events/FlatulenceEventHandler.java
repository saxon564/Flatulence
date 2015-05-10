package me.saxon564.flatulence.events;

import java.util.Random;

import me.saxon564.flatulence.Flatulence;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class FlatulenceEventHandler {
	
	private int t=1;
	int flatulence = 0;
	boolean water = false;
	Random rand = new Random();
	
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		EntityPlayer player = (EntityPlayer) event.player.getCommandSenderEntity();
		World world = player.worldObj;
		
		if (world.isRemote && player.isSneaking() && !player.isSpectator() && (!player.capabilities.isCreativeMode && Flatulence.allowCreative)) {
			if (t <= 1) {
				t = Flatulence.tickDelay * 20;
				
				if (player.isInWater()) {
					world.playSound(player.posX, player.posY, player.posZ, "flatulence:flatulence",
	               1.0F, 0.25F, true);
					runParticles(world, player);
					water = true;
				} else {
					world.playSound(player.posX, player.posY, player.posZ, "flatulence:flatulence",
				               1.0F, 1.0F, true);
					runParticles(world, player);
				}
			}
		}
		
		if (water) {
			flatulence++;
		}
		
		//Play Bubbles
		if (flatulence >= 100) {
			world.playSound(player.posX, player.posY, player.posZ, "flatulence:waterFlatulence",
		               1.0F, 1.0F, true);
			flatulence = 0;
			water = false;
		}
		
		if (world.isRemote && !player.isSneaking()) {
			if (t > 1) {
				t--;
			}
		}
	}
	
	public void runParticles(World world, EntityPlayer player) {
		for (int i = 0; i < 20; i++) {
			Vec3 vec = player.getLookVec();
			double posX = ((double)player.posX - (vec.xCoord * 0.25)) + (double)(this.rand.nextFloat() * player.width * 0.75F) - (player.width * 0.5);
			double posY = player.posY + 0.8D + (double)(this.rand.nextFloat() * (player.height / 2));
			double posZ = ((double)player.posZ  - (vec.zCoord * 0.25)) + (double)(this.rand.nextFloat() * player.width * 0.75F) - (player.width * 0.5);
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, posX, posY, posZ, d0, d1, d2, new int[0]);
		}
	}

}
