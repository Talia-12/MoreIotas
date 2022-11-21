package ram.talia.moreiotas.fabric.cc

import dev.onyxstudios.cca.api.v3.component.ComponentKey
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer
import ram.talia.moreiotas.api.MoreIotasAPI.modLoc

class MoreIotasCardinalComponents : EntityComponentInitializer, ItemComponentInitializer {
	override fun registerEntityComponentFactories(registry: EntityComponentFactoryRegistry) {
		registry.registerForPlayers(CHAT_PREFIX_HOLDER, ::CCChatPrefixHolder, RespawnCopyStrategy.ALWAYS_COPY)
	}

	override fun registerItemComponentFactories(registry: ItemComponentFactoryRegistry) {
		return
	}

	companion object {
		@JvmField
		val CHAT_PREFIX_HOLDER: ComponentKey<CCChatPrefixHolder> = ComponentRegistry.getOrCreate(
			modLoc("chat_prefix_holder"),
			CCChatPrefixHolder::class.java
		)
	}
}