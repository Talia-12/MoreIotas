package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import ram.talia.moreiotas.api.getString
import ram.talia.moreiotas.api.spell.StringConstAction
import ram.talia.moreiotas.api.spell.iota.StringIota

object OpSplitString : StringConstAction {
    override val argc = 2

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val toSplit = args.getString(0, argc)
        val splitOn = args.getString(1, argc)

        return toSplit.split(splitOn).map { s -> StringIota(s) }.asActionResult
    }
}