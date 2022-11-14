package ram.talia.moreiotas.common.casting.actions.matrices

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import ram.talia.moreiotas.api.asActionResult
import ram.talia.moreiotas.api.getMatrix

object OpTransposeMatrix : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        return args.getMatrix(0, argc).transpose().asActionResult
    }
}