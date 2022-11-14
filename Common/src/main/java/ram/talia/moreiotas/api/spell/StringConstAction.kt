package ram.talia.moreiotas.api.spell

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.OperationResult
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.casting.SpellContinuation
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.mishaps.Mishap
import ram.talia.moreiotas.api.spell.mishaps.MishapString


interface StringConstAction : ConstMediaAction {
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