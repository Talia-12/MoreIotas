package ram.talia.moreiotas.common.lib

import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.iota.IotaType
import at.petrak.hexcasting.common.lib.HexIotaTypes
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import org.jetbrains.annotations.ApiStatus
import ram.talia.moreiotas.api.MoreIotasAPI.modLoc
import ram.talia.moreiotas.api.spell.iota.StringIota
import java.util.function.BiConsumer

object MoreIotasIotaTypes {
    @JvmStatic
    @ApiStatus.Internal
    fun registerTypes() {
        val r = BiConsumer { type: IotaType<*>, id: ResourceLocation -> Registry.register(HexIotaTypes.REGISTRY, id, type) }
        for ((key, value) in TYPES) {
            r.accept(value, key)
        }
    }

    private val TYPES: MutableMap<ResourceLocation, IotaType<*>> = LinkedHashMap()

    @JvmField
    val STRING_TYPE: IotaType<StringIota> = type("iota_type", StringIota.TYPE)

    private fun <U : Iota, T : IotaType<U>> type(name: String, type: T): T {
        val old = TYPES.put(modLoc(name), type)
        require(old == null) { "Typo? Duplicate id $name" }
        return type
    }
}