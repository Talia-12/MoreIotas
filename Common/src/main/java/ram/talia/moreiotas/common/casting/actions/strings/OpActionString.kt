package ram.talia.moreiotas.common.casting.actions.strings

import at.petrak.hexcasting.api.PatternRegistry
import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getPattern
import at.petrak.hexcasting.api.spell.iota.Iota
import ram.talia.moreiotas.api.asActionResult

/**
 * Takes a pattern iota, returns the name of that pattern, or "Unknown" if that pattern doesn't exist (or if it's a great pattern).
 */
object OpActionString : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val pattern = args.getPattern(0, argc)

        val action = PatternRegistry.lookupPatternByShape(pattern) ?: return null.asActionResult

        return if (action.isGreat)
            null.asActionResult
        else
            action.displayName.string.asActionResult
    }
}