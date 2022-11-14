package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import ram.talia.moreiotas.api.asActionResult
import ram.talia.moreiotas.api.spell.StringConstAction
import ram.talia.moreiotas.xplat.IXplatAbstractions

class OpChatString(private val allChat: Boolean) : StringConstAction {
    override val argc = 0

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        return IXplatAbstractions.INSTANCE.lastMessage(if (allChat) null else ctx.caster)?.asActionResult ?:
            null.asActionResult
    }
}