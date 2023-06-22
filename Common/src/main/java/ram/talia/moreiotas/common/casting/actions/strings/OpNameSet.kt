package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.spell.ParticleSpray
import at.petrak.hexcasting.api.spell.RenderedSpell
import at.petrak.hexcasting.api.spell.SpellAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getEntity
import at.petrak.hexcasting.api.spell.iota.Iota
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.item.ItemEntity
import ram.talia.moreiotas.api.getString
import ram.talia.moreiotas.api.mod.MoreIotasConfig

object OpNameSet : SpellAction {
    override val argc = 2

    override fun execute(args: List<Iota>, ctx: CastingContext): Triple<RenderedSpell, Int, List<ParticleSpray>> {
        val newName = args.getString(0, argc)
        val entityToRename = args.getEntity(1, argc)

        ctx.assertEntityInRange(entityToRename)

        return Triple(
            Spell(newName, entityToRename),
            MoreIotasConfig.server.setBlockStringCost,
            listOf(ParticleSpray.burst(entityToRename.position(), 0.5))
        )
    }

    private data class Spell(val name: String, val entity: Entity) : RenderedSpell {
        override fun cast(ctx: CastingContext) {
            val nameComp = Component.literal(name)
            entity.customName = nameComp
            if (entity is Mob)
                entity.setPersistenceRequired()
            else if (entity is ItemEntity)
                entity.item.setHoverName(nameComp)
        }
    }
}