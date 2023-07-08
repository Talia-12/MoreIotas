package ram.talia.moreiotas.common.lib.hex

import at.petrak.hexcasting.api.casting.arithmetic.Arithmetic
import net.minecraft.resources.ResourceLocation
import ram.talia.moreiotas.api.MoreIotasAPI.modLoc
import ram.talia.moreiotas.common.casting.arithmetic.StringArithmetic
import java.util.function.BiConsumer

object MoreIotasArithmetics {
    @JvmStatic
    fun register(r: BiConsumer<Arithmetic, ResourceLocation>) {
        for ((key, value) in ARITHMETICS) {
            r.accept(value, key)
        }
    }

    private val ARITHMETICS: MutableMap<ResourceLocation, Arithmetic> = LinkedHashMap()

    val STRING: StringArithmetic = make("string", StringArithmetic)

    private fun <T : Arithmetic> make(name: String, arithmetic: T): T {
        val old = ARITHMETICS.put(modLoc(name), arithmetic)
        require(old == null) { "Typo? Duplicate id $name" }
        return arithmetic
    }
}