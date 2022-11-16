package ram.talia.moreiotas.common.casting.actions.matrices

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getDouble
import at.petrak.hexcasting.api.spell.getVec3
import at.petrak.hexcasting.api.spell.iota.Iota
import org.jblas.DoubleMatrix
import ram.talia.moreiotas.api.asActionResult
import ram.talia.moreiotas.api.component1
import ram.talia.moreiotas.api.component2
import ram.talia.moreiotas.api.component3
import kotlin.math.cos
import kotlin.math.sin

object OpRotationMatrix: ConstMediaAction {
    override val argc = 2

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val axis = args.getVec3(0, argc)
        val theta = args.getDouble(1, argc)

        val (x, y, z) = axis
        val c = cos(theta)
        val s = sin(theta)
        val nc = 1 - c

        return DoubleMatrix(arrayOf(
                doubleArrayOf(c + x*x*nc,   x*y*nc - z*s, x*z*nc + y*s),
                doubleArrayOf(y*x*nc + z*s, c + y*y*nc,   y*z*nc - x*s),
                doubleArrayOf(z*x*nc - y*s, z*y*nc + x*s, c + z*z*nc)
        )).asActionResult
    }
}