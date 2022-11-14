package ram.talia.moreiotas.fabric.cc

import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer

class MoreIotasCardinalComponents : EntityComponentInitializer, ItemComponentInitializer {
	override fun registerEntityComponentFactories(registry: EntityComponentFactoryRegistry) {
//		registry.registerForPlayers(WISP_CASTING_MANAGER, ::CCWispCastingManager, RespawnCopyStrategy.ALWAYS_COPY)
//		registry.registerForPlayers(PLAYER_LINKSTORE, ::CCPlayerLinkstore, RespawnCopyStrategy.NEVER_COPY)
//		registry.registerForPlayers(EVERBOOK, ::CCEverbook, RespawnCopyStrategy.ALWAYS_COPY)
	}

	override fun registerItemComponentFactories(registry: ItemComponentFactoryRegistry) {
		return
	}

	companion object {
//		@JvmField
//		val WISP_CASTING_MANAGER: ComponentKey<CCWispCastingManager> = ComponentRegistry.getOrCreate(
//			MoreIotasAPI.modLoc("wisp_casting_manager"),
//			CCWispCastingManager::class.java
//		)
//		@JvmField
//		val PLAYER_LINKSTORE: ComponentKey<CCPlayerLinkstore> = ComponentRegistry.getOrCreate(
//			MoreIotasAPI.modLoc("player_linkstore"),
//			CCPlayerLinkstore::class.java
//		)
//		@JvmField
//		val EVERBOOK: ComponentKey<CCEverbook> = ComponentRegistry.getOrCreate(
//			MoreIotasAPI.modLoc("everbook"),
//			CCEverbook::class.java
//		)
	}
}