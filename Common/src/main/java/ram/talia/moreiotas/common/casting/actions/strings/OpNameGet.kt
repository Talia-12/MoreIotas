package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getEntity
import at.petrak.hexcasting.api.spell.iota.Iota
import net.minecraft.world.entity.item.ItemEntity
import ram.talia.moreiotas.api.asActionResult

object OpNameGet : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val entity = args.getEntity(0, argc)
        ctx.assertEntityInRange(entity)
        val name = when (entity) {
            is ItemEntity -> entity.item.displayName.string
            else -> entity.displayName.string
        }
        return name.asActionResult
    }
}