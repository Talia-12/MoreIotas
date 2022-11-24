package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import ram.talia.moreiotas.api.asActionResult
import ram.talia.moreiotas.xplat.IXplatAbstractions

class OpChatString(private val allChat: Boolean) : ConstMediaAction {
    override val argc = 0

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        return IXplatAbstractions.INSTANCE.lastMessage(if (allChat) null else ctx.caster)?.asActionResult ?:
            "".asActionResult
    }
}