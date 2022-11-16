package ram.talia.moreiotas.common.casting.actions.matrices

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota
import org.jblas.Solve
import ram.talia.moreiotas.api.*

object OpInverseMatrix : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val mat = args.getNumOrVecOrMatrix(0, argc).asMatrix
        if (mat.rows != mat.columns)
            throw MishapInvalidIota.matrixWrongSize(args[0], 0, mat.rows, mat.rows)
        // pseudo-inverse, will see how it works.
        return Solve.pinv(mat).asActionResult
    }
}