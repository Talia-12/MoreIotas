package ram.talia.moreiotas.common.casting.actions.matrices

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota
import ram.talia.moreiotas.api.asActionResult
import ram.talia.moreiotas.api.getMatrix
import ram.talia.moreiotas.api.matrixWrongSize
import ram.talia.moreiotas.api.plus

object OpAddMatrix : ConstMediaAction {
    override val argc = 2

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val mat0 = args.getMatrix(0, argc)
        val mat1 = args.getMatrix(1, argc)
        if (mat0.rows != mat1.rows || mat0.columns != mat1.columns)
            throw MishapInvalidIota.matrixWrongSize(args[1], 0, mat0.rows, mat0.columns)

        return (mat0 + mat1).asActionResult
    }
}