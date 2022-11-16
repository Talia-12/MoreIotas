package ram.talia.moreiotas.common.casting.actions.matrices

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getPositiveIntUnderInclusive
import at.petrak.hexcasting.api.spell.iota.Iota
import org.jblas.DoubleMatrix
import ram.talia.moreiotas.api.asActionResult
import ram.talia.moreiotas.api.spell.iota.MatrixIota

object OpZeroMatrix : ConstMediaAction {
    override val argc = 2

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        return DoubleMatrix.zeros(
                args.getPositiveIntUnderInclusive(0, MatrixIota.MAX_SIZE, argc),
                args.getPositiveIntUnderInclusive(1, MatrixIota.MAX_SIZE, argc)).asActionResult
    }
}