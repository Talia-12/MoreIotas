package ram.talia.moreiotas.common.casting.actions.matrices

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getPositiveIntUnderInclusive
import at.petrak.hexcasting.api.spell.iota.Iota
import org.jblas.DoubleMatrix
import ram.talia.moreiotas.api.asActionResult
import ram.talia.moreiotas.api.spell.iota.MatrixIota

object OpIdentityMatrix : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        return DoubleMatrix.eye(args.getPositiveIntUnderInclusive(0, MatrixIota.MAX_SIZE, argc)).asActionResult
    }
}