package ram.talia.moreiotas.api.spell

import at.petrak.hexcasting.api.spell.OperationResult
import at.petrak.hexcasting.api.spell.SpellAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.casting.eval.SpellContinuation
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.mishaps.Mishap
import ram.talia.moreiotas.api.spell.mishaps.MishapString

interface StringSpellAction : SpellAction {
    override fun operate(
            continuation: SpellContinuation,
            stack: MutableList<Iota>,
            ravenmind: Iota?,
            ctx: CastingContext
    ): OperationResult {
        try {
            return super.operate(continuation, stack, ravenmind, ctx)
        } catch (m: Mishap) {
            throw MishapString(m)
        }
    }
}