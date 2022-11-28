package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getIntBetween
import at.petrak.hexcasting.api.spell.getPositiveIntUnderInclusive
import at.petrak.hexcasting.api.spell.iota.Iota
import ram.talia.moreiotas.api.asActionResult
import ram.talia.moreiotas.api.getString

object OpSubString : ConstMediaAction {
    override val argc = 3

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val string = args.getString(0, argc)
        val start = args.getPositiveIntUnderInclusive(1, string.length, argc)
        val end = args.getIntBetween(2, start, string.length, argc)

        return string.substring(start, end).asActionResult
    }
}