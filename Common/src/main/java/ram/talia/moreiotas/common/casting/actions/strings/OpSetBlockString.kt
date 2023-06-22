package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.spell.ParticleSpray
import at.petrak.hexcasting.api.spell.RenderedSpell
import at.petrak.hexcasting.api.spell.SpellAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getBlockPos
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.utils.putList
import com.mojang.datafixers.util.Either
import net.minecraft.core.BlockPos
import net.minecraft.nbt.StringTag
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.entity.LecternBlockEntity
import net.minecraft.world.level.block.entity.SignBlockEntity
import net.minecraft.world.phys.Vec3
import ram.talia.moreiotas.api.getStringOrList
import ram.talia.moreiotas.api.mod.MoreIotasConfig
import ram.talia.moreiotas.api.nbt.toNbtList

object OpSetBlockString : SpellAction {
    override val argc = 2

    override fun execute(args: List<Iota>, ctx: CastingContext): Triple<RenderedSpell, Int, List<ParticleSpray>> {
        val pos = args.getBlockPos(0, argc)
        val string = args.getStringOrList(1, argc)

        ctx.assertVecInRange(pos)

        return Triple(
                Spell(pos, string),
                MoreIotasConfig.server.setBlockStringCost,
                listOf(ParticleSpray.burst(Vec3.atCenterOf(pos), 1.0))
        )
    }

    private data class Spell(val pos: BlockPos, val string: Either<String, List<String>>) : RenderedSpell {
        override fun cast(ctx: CastingContext) {
            string.map({
                val blockEntity = ctx.world.getBlockEntity(pos) ?: return@map

                if (blockEntity is SignBlockEntity) {
                    val lines = it.split("\n")

                    for (i in 0 until SignBlockEntity.LINES)
                        blockEntity.setMessage(i, Component.literal(""))
                    for ((i, line) in lines.withIndex()) {
                        if (i >= SignBlockEntity.LINES)
                            break
                        blockEntity.setMessage(i, Component.literal(line))
                    }

                } else if (blockEntity is LecternBlockEntity) {
                    val book = blockEntity.book.copy()

                    if (book.`is`(Items.WRITABLE_BOOK)) {
                        book.tag.putList("pages", listOf(StringTag.valueOf(it)).toNbtList())
                    }

                    blockEntity.book = book
                }

                blockEntity.setChanged()
                ctx.world.sendBlockUpdated(pos, ctx.world.getBlockState(pos), ctx.world.getBlockState(pos), 3)
            }, {list ->
                val blockEntity = ctx.world.getBlockEntity(pos) ?: return@map

                if (blockEntity is SignBlockEntity) {
                    val lines = list.flatMap { it.split("\n") }

                    for (i in 0 until SignBlockEntity.LINES)
                        blockEntity.setMessage(i, Component.literal(""))
                    for ((i, line) in lines.withIndex()) {
                        if (i >= SignBlockEntity.LINES)
                            break
                        blockEntity.setMessage(i, Component.literal(line))
                    }
                } else if (blockEntity is LecternBlockEntity) {
                    val book = blockEntity.book.copy()

                    if (book.`is`(Items.WRITABLE_BOOK)) {
                        book.tag.putList("pages", list.map { StringTag.valueOf(it) }.toNbtList())
                    }

                    blockEntity.book = book
                }
                blockEntity.setChanged()
                ctx.world.sendBlockUpdated(pos, ctx.world.getBlockState(pos), ctx.world.getBlockState(pos), 3)
            })
        }
    }
}