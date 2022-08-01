package ram.talia.moreiotas.forge.datagen

import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import ram.talia.moreiotas.api.MoreIotasAPI

class MoreIotasForgeDataGenerators {
	companion object {
		@JvmStatic
		@SubscribeEvent
		fun generateData(ev: GatherDataEvent) {
			if (System.getProperty("moreiotas.xplat_datagen") != null) {
				configureXplatDatagen(ev)
			}
			if (System.getProperty("moreiotas.forge_datagen") != null) {
				configureForgeDatagen(ev)
			}
		}

		private fun configureXplatDatagen(ev: GatherDataEvent) {
			MoreIotasAPI.LOGGER.info("Starting cross-platform datagen")

			val gen = ev.generator
//			val efh = ev.existingFileHelper
//			gen.addProvider(ev.includeClient(), MoreIotasSounds.provider(gen))
//			gen.addProvider(ev.includeClient(), HexItemModels(gen, efh))
//			gen.addProvider(ev.includeClient(), HexBlockStatesAndModels(gen, efh))
//			gen.addProvider(ev.includeServer(), PaucalForgeDatagenWrappers.addEFHToAdvancements(HexAdvancements(gen), efh))
		}

		private fun configureForgeDatagen(ev: GatherDataEvent) {
			MoreIotasAPI.LOGGER.info("Starting Forge-specific datagen")

			val gen = ev.generator
			val efh = ev.existingFileHelper
//			gen.addProvider(ev.includeServer(), MoreIotasplatRecipes(gen))
//				val xtags = IXplatAbstractions.INSTANCE.tags()
//				val blockTagProvider = PaucalForgeDatagenWrappers.addEFHToTagProvider(
//					HexBlockTagProvider(gen, xtags), efh
//				)
//				gen.addProvider(blockTagProvider)
//				val itemTagProvider = PaucalForgeDatagenWrappers.addEFHToTagProvider(
//					HexItemTagProvider(gen, blockTagProvider, IXplatAbstractions.INSTANCE.tags()), efh
//				)
//				gen.addProvider(itemTagProvider)
		}
	}
}