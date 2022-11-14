package ram.talia.moreiotas.api.spell.mishaps

import at.petrak.hexcasting.api.misc.FrozenColorizer
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.mishaps.Mishap
import at.petrak.hexcasting.api.utils.asTranslatedComponent
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.HoverEvent
import net.minecraft.network.chat.Style
import net.minecraft.world.item.DyeColor
import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.random.Random

class MishapString(private val wrapped: Mishap) : Mishap() {
    override fun accentColor(ctx: CastingContext, errorCtx: Context): FrozenColorizer = dyeColor(DyeColor.MAGENTA)

    override fun errorMessage(ctx: CastingContext, errorCtx: Context): Component {
        val error = wrapped.errorMessage(ctx, errorCtx).copy()

        val random = Random(wrapped.hashCode())
        val memory = random.nextInt(NUM_MEMORIES)

        val fullMemory = "hexal.memories.$memory".asTranslatedComponent

        val errorLength = error.string.length
        val memoryLength = fullMemory.string.length

        val idx = random.nextInt(max(memoryLength - errorLength - 6, 1))

        val partialMemory = fullMemory.string.slice(idx until min(idx + errorLength, memoryLength))

        // I *wanted* to do a text-transformation effect where it starts as the memory and character by character gets
        // turned into the error, but that seems impossible or very very annoying to actually pull off.

        val style = Style.EMPTY.withHoverEvent(HoverEvent(HoverEvent.Action.SHOW_TEXT, error))

        return Component.translatable("hexal.memories", partialMemory).withStyle(style)
    }

    override fun execute(ctx: CastingContext, errorCtx: Context, stack: MutableList<Iota>) {
        wrapped.execute(ctx, errorCtx, stack)
    }

    companion object {
        const val NUM_MEMORIES = 14
    }
}