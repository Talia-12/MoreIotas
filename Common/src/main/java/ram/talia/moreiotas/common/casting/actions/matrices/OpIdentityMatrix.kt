package ram.talia.moreiotas.common.casting.actions.matrices

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getPositiveInt
import at.petrak.hexcasting.api.spell.iota.Iota
import org.jblas.DoubleMatrix
import ram.talia.moreiotas.api.asActionResult

object OpIdentityMatrix : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        return DoubleMatrix.eye(args.getPositiveInt(0, argc)).asActionResult
    }
}