package me.saxon564.flatulence;

import me.saxon564.flatulence.events.FlatulenceEventHandler;
import me.saxon564.flatulence.proxies.CommonProxyFlatulence;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = FlatulenceReference.MODID, name = FlatulenceReference.MODNAME, version = FlatulenceReference.VERSION)
public class Flatulence {
	@SidedProxy(clientSide = "me.saxon564.flatulence.client.ClientProxyFlatulence", serverSide = "me.saxon564.flatulence.proxies.CommonProxyFlatulence")
	public static CommonProxyFlatulence proxy;

	@Instance("farts")
	public static Flatulence instance;
	
	private String test = "test";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new FlatulenceEventHandler());
		FMLCommonHandler.instance().bus().register(new FlatulenceEventHandler());
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerSounds();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}
}
