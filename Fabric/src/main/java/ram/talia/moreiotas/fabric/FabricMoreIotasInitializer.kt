package ram.talia.moreiotas.fabric

import net.fabricmc.api.ModInitializer
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import ram.talia.moreiotas.fabric.network.FabricPacketHandler
import ram.talia.moreiotas.api.MoreIotasAPI
import ram.talia.moreiotas.common.casting.RegisterPatterns
import ram.talia.moreiotas.common.lib.MoreIotasIotaTypes
import java.util.function.BiConsumer

object FabricMoreIotasInitializer : ModInitializer {
    override fun onInitialize() {
        MoreIotasAPI.LOGGER.info("Hello Fabric World!")

        FabricPacketHandler.initServerBound()

        initListeners()

        initRegistries()

        RegisterPatterns.registerPatterns()
    }

    private fun initListeners() {

    }

    private fun initRegistries() {
        MoreIotasIotaTypes.registerTypes();
    }


    private fun <T> bind(registry: Registry<in T>): BiConsumer<T, ResourceLocation> =
        BiConsumer<T, ResourceLocation> { t, id -> Registry.register(registry, id, t) }
}