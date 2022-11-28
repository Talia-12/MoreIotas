package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import ram.talia.moreiotas.api.getString

object OpFindString : ConstMediaAction {
    override val argc = 2

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val findee = args.getString(0, argc)
        val finder = args.getString(1, argc)

        return findee.indexOf(finder).asActionResult
    }
}