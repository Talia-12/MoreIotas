package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import net.minecraft.server.level.ServerPlayer
import ram.talia.moreiotas.api.getStringOrNull
import ram.talia.moreiotas.xplat.IXplatAbstractions

object OpSetChatPrefix : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        IXplatAbstractions.INSTANCE.setChatPrefix(env.castingEntity as? ServerPlayer ?: return listOf(), args.getStringOrNull(0, argc))
        return listOf()
    }
}