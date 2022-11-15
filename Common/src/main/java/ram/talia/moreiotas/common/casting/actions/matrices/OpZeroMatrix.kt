package ram.talia.moreiotas.common.casting.actions.matrices

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getPositiveInt
import at.petrak.hexcasting.api.spell.iota.Iota
import org.jblas.DoubleMatrix
import ram.talia.moreiotas.api.asActionResult

object OpZeroMatrix : ConstMediaAction {
    override val argc = 2

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        return DoubleMatrix.zeros(args.getPositiveInt(0, argc), args.getPositiveInt(1, argc)).asActionResult
    }
}