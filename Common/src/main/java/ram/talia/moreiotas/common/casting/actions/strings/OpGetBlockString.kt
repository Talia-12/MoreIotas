package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getBlockPos
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.iota.ListIota
import at.petrak.hexcasting.api.utils.getList
import net.minecraft.nbt.Tag
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.entity.LecternBlockEntity
import net.minecraft.world.level.block.entity.SignBlockEntity
import ram.talia.moreiotas.api.asActionResult
import ram.talia.moreiotas.api.spell.iota.StringIota

object OpGetBlockString : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val pos = args.getBlockPos(0, argc)

        ctx.assertVecInRange(pos)

        val blockEntity = ctx.world.getBlockEntity(pos)

        if (blockEntity is SignBlockEntity) {
            var string = blockEntity.getMessage(0, false).string
            for (i in 1 until SignBlockEntity.LINES) {
                string += "\n" + blockEntity.getMessage(i, false).string
            }

            return string.asActionResult
        }
        if (blockEntity is LecternBlockEntity) {
            val book = blockEntity.book

            return if (book.`is`(Items.WRITABLE_BOOK))
                    book.tag.getList("pages", Tag.TAG_STRING)?.map { StringIota(it.asString) }?.let { listOf(ListIota(it)) } ?: null.asActionResult
                else if (book.`is`(Items.WRITTEN_BOOK))
                    book.tag.getList("pages", Tag.TAG_STRING)?.map {
                        StringIota(Component.Serializer.fromJson(it.asString)?.string ?: "")
                    }?.let { listOf(ListIota(it)) } ?: null.asActionResult
                else null.asActionResult
        }

        return null.asActionResult
    }
}