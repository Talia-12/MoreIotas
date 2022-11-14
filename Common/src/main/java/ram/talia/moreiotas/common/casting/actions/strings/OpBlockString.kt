package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getBlockPos
import at.petrak.hexcasting.api.spell.iota.Iota
import net.minecraft.world.level.block.entity.SignBlockEntity
import ram.talia.moreiotas.api.asActionResult
import ram.talia.moreiotas.api.spell.StringConstAction

object OpBlockString : StringConstAction {
    override val argc = 1

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val pos = args.getBlockPos(0, argc)

        val sign = ctx.world.getBlockEntity(pos) as? SignBlockEntity ?: return null.asActionResult

        var string = ""
        for (i in 0 until SignBlockEntity.LINES) {
            string += sign.getMessage(i, false).string
        }

        return string.asActionResult
    }
}