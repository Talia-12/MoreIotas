package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import ram.talia.moreiotas.api.getString
import ram.talia.moreiotas.api.spell.StringConstAction

object OpParseString : StringConstAction {
    override val argc = 1

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        return args.getString(0, argc).toDoubleOrNull()?.asActionResult ?: null.asActionResult
    }
}