package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.asActionResult
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import net.minecraft.server.level.ServerPlayer
import ram.talia.moreiotas.api.asActionResult
import ram.talia.moreiotas.xplat.IXplatAbstractions

object OpGetChatPrefix : ConstMediaAction {
    override val argc = 0

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val caster = env.castingEntity as? ServerPlayer ?: return null.asActionResult
        return IXplatAbstractions.INSTANCE.getChatPrefix(caster)?.asActionResult ?: null.asActionResult
    }
}