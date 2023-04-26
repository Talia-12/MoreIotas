package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.spell.ParticleSpray
import at.petrak.hexcasting.api.spell.RenderedSpell
import at.petrak.hexcasting.api.spell.SpellAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getBlockPos
import at.petrak.hexcasting.api.spell.iota.Iota
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.world.level.block.entity.SignBlockEntity
import net.minecraft.world.phys.Vec3
import ram.talia.moreiotas.api.getString
import ram.talia.moreiotas.api.mod.MoreIotasConfig

object OpSetBlockString : SpellAction {
    override val argc = 2

    override fun execute(args: List<Iota>, ctx: CastingContext): Triple<RenderedSpell, Int, List<ParticleSpray>> {
        val pos = args.getBlockPos(0, argc)
        val string = args.getString(1, argc)

        ctx.assertVecInRange(pos)

        return Triple(
                Spell(pos, string),
                MoreIotasConfig.server.setBlockStringCost,
                listOf(ParticleSpray.burst(Vec3.atCenterOf(pos), 1.0))
        )
    }

    private data class Spell(val pos: BlockPos, val string: String) : RenderedSpell {
        override fun cast(ctx: CastingContext) {
            val lines = string.split('\n', limit = SignBlockEntity.LINES)

            val sign = ctx.world.getBlockEntity(pos) as? SignBlockEntity ?: return

            for ((i, line) in lines.withIndex()) {
                sign.setMessage(i, Component.literal(line))
            }
        }
    }
}