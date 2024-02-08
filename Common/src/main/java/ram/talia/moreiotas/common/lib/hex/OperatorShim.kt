package ram.talia.moreiotas.common.lib.hex

import at.petrak.hexcasting.api.casting.arithmetic.operator.Operator
import at.petrak.hexcasting.api.casting.arithmetic.predicates.IotaMultiPredicate
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.eval.OperationResult
import at.petrak.hexcasting.api.casting.eval.vm.CastingImage
import at.petrak.hexcasting.api.casting.eval.vm.SpellContinuation
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.common.lib.hex.HexEvalSounds

abstract class OperatorShim(arity: Int, accepts: IotaMultiPredicate) : Operator(arity, accepts) {
    abstract fun apply(iotas: Iterable<Iota>, env: CastingEnvironment): Iterable<Iota>

    override fun operate(
        env: CastingEnvironment,
        image: CastingImage,
        continuation: SpellContinuation
    ): OperationResult {
        val stack = image.stack.toMutableList()
        val input = mutableListOf(stack.removeFirst(),stack.removeFirst())
        stack.addAll(apply(input,env))
        return OperationResult(
            image.copy(stack),
            listOf(),
            continuation,
            HexEvalSounds.NORMAL_EXECUTE
        )
    }
}