package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import ram.talia.moreiotas.api.asActionResult
import ram.talia.moreiotas.api.spell.StringConstAction

object OpIotaString : StringConstAction {
    override val argc = 1

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        return args[0].display().string.asActionResult
    }
}