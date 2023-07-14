package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.PatternRegistry
import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.casting.SpecialPatterns.*
import at.petrak.hexcasting.api.spell.getPattern
import at.petrak.hexcasting.api.spell.iota.Iota
import net.minecraft.network.chat.Component
import ram.talia.moreiotas.api.asActionResult

/**
 * Takes a pattern iota, returns the name of that pattern, or "Unknown" if that pattern doesn't exist (or if it's a great pattern).
 */
object OpActionString : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val pattern = args.getPattern(0, argc)

        if (pattern.sigsEqual(INTROSPECTION))
            return Component.translatable("hexcasting.spell.hexcasting:open_paren").string.asActionResult
        if (pattern.sigsEqual(RETROSPECTION))
            return Component.translatable("hexcasting.spell.hexcasting:close_paren").string.asActionResult
        if (pattern.sigsEqual(CONSIDERATION))
            return Component.translatable("hexcasting.spell.hexcasting:escape").string.asActionResult

        val action = PatternRegistry.lookupPatternByShape(pattern) ?: return null.asActionResult

        return if (action.isGreat)
            null.asActionResult
        else
            action.displayName.string.asActionResult
    }
}