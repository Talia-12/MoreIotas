package ram.talia.moreiotas.forge;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegisterEvent;
import ram.talia.moreiotas.forge.cap.CapSyncers;
import ram.talia.moreiotas.forge.datagen.MoreIotasForgeDataGenerators;
import ram.talia.moreiotas.api.MoreIotasAPI;
import ram.talia.moreiotas.common.casting.RegisterPatterns;
import ram.talia.moreiotas.common.lib.MoreIotasIotaTypes;
import ram.talia.moreiotas.forge.eventhandlers.ChatEventHandler;
import ram.talia.moreiotas.forge.network.ForgePacketHandler;
import thedarkcolour.kotlinforforge.KotlinModLoadingContext;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod(MoreIotasAPI.MOD_ID)
public class ForgeMoreIotasInitializer {

	public ForgeMoreIotasInitializer() {
		MoreIotasAPI.LOGGER.info("Hello Forge World!");
		initConfig();
		initRegistry();
		initListeners();
	}
	
	private static void initConfig () {

	}
	
	private static void initRegistry () {
		MoreIotasIotaTypes.registerTypes();
	}
	
	private static void initListeners () {
		IEventBus modBus = getModEventBus();
		IEventBus evBus = MinecraftForge.EVENT_BUS;
		
		modBus.register(ForgeMoreIotasClientInitializer.class);
		
		modBus.addListener((FMLCommonSetupEvent evt) ->
			 evt.enqueueWork(() -> {
				 //noinspection Convert2MethodRef
				 ForgePacketHandler.init();
			 }));
		
		modBus.addListener((FMLCommonSetupEvent evt) -> evt.enqueueWork(RegisterPatterns::registerPatterns));
		
		// We have to do these at some point when the registries are still open
//		modBus.addGenericListener(Item.class, (GenericEvent<Item> evt) -> HexalRecipeSerializers.registerTypes());
//		modBus.addListener((RegisterEvent evt) -> {
//			if (evt.getRegistryKey().equals(Registry.ITEM_REGISTRY)) {
//				HexalRecipeSerializers.registerTypes();
//			}
//		});
		
		modBus.register(MoreIotasForgeDataGenerators.class);

		evBus.register(ChatEventHandler.class);
		evBus.register(CapSyncers.class);
	}
	
	// https://github.com/VazkiiMods/Botania/blob/1.18.x/Forge/src/main/java/vazkii/botania/forge/ForgeCommonInitializer.java
	private static <T> void bind (ResourceKey<Registry<T>> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
		getModEventBus().addListener((RegisterEvent event) -> {
			if (registry.equals(event.getRegistryKey())) {
				source.accept((t, rl) -> event.register(registry, rl, () -> t));
			}
		});
	}
	
	// This version of bind is used for BuiltinRegistries.
	private static <T> void bind(Registry<T> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
		source.accept((t, id) -> Registry.register(registry, id, t));
	}
	
	private static IEventBus getModEventBus () {
		return KotlinModLoadingContext.Companion.get().getKEventBus();
	}
}