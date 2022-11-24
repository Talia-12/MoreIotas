package ram.talia.moreiotas.common.lib

import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.iota.IotaType
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import org.jetbrains.annotations.ApiStatus
import ram.talia.moreiotas.api.MoreIotasAPI.modLoc
import ram.talia.moreiotas.api.spell.iota.MatrixIota
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
    val STRING_TYPE: IotaType<StringIota> = type("string", StringIota.TYPE)
    @JvmField
    val MATRIX_TYPE: IotaType<MatrixIota> = type("matrix", MatrixIota.TYPE)

    private fun <U : Iota, T : IotaType<U>> type(name: String, type: T): T {
        val old = TYPES.put(modLoc(name), type)
        require(old == null) { "Typo? Duplicate id $name" }
        return type
    }
}