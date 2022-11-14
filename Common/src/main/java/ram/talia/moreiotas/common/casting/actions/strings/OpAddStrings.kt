package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import ram.talia.moreiotas.api.asActionResult
import ram.talia.moreiotas.api.getString
import ram.talia.moreiotas.api.spell.StringConstAction

object OpAddStrings : StringConstAction {
    override val argc = 2

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        return (args.getString(0, argc) + args.getString(1, argc)).asActionResult
    }
}