package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import ram.talia.moreiotas.api.getStringOrNull
import ram.talia.moreiotas.xplat.IXplatAbstractions

object OpChatPrefixSet : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        IXplatAbstractions.INSTANCE.setChatPrefix(ctx.caster, args.getStringOrNull(0, argc))
        return listOf()
    }
}