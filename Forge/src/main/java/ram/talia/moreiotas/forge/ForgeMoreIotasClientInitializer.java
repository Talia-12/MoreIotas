package ram.talia.moreiotas.forge;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import ram.talia.moreiotas.client.RegisterClientStuff;

public class ForgeMoreIotasClientInitializer {
	@SubscribeEvent
	public static void clientInit(FMLClientSetupEvent event) {
		event.enqueueWork(RegisterClientStuff::init);
	}
	
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers evt) {
		RegisterClientStuff.registerBlockEntityRenderers(evt::registerBlockEntityRenderer);
	}
}
