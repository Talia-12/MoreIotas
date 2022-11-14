package ram.talia.moreiotas.api

import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota
import at.petrak.hexcasting.api.spell.mishaps.MishapNotEnoughArgs
import net.minecraft.world.phys.Vec3
import ram.talia.moreiotas.api.spell.iota.StringIota

operator fun Double.times(vec: Vec3): Vec3 = vec.scale(this)
operator fun Vec3.times(d: Double): Vec3 = this.scale(d)
operator fun Vec3.div(d: Double): Vec3 = this.scale(1/d)
operator fun Vec3.plus(vec3: Vec3): Vec3 = this.add(vec3)
operator fun Vec3.minus(vec3: Vec3): Vec3 = this.subtract(vec3)
operator fun Vec3.unaryMinus(): Vec3 = this.scale(-1.0)

fun List<Iota>.getString(idx: Int, argc: Int = 0): String {
    val x = this.getOrElse(idx) { throw MishapNotEnoughArgs(idx + 1, this.size) }
    if (x is StringIota) {
        return x.string
    } else {
        throw MishapInvalidIota.ofType(x, if (argc == 0) idx else argc - (idx + 1), "string")
    }
}
inline val String.asActionResult get() = listOf(StringIota(this))
