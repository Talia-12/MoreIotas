package ram.talia.moreiotas.common.casting.arithmetic.operator.matrix

import at.petrak.hexcasting.api.casting.arithmetic.operator.Operator
import at.petrak.hexcasting.api.casting.arithmetic.predicates.IotaMultiPredicate
import at.petrak.hexcasting.api.casting.arithmetic.predicates.IotaPredicate
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapInvalidIota
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes.DOUBLE
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes.LIST
import ram.talia.moreiotas.api.*
import ram.talia.moreiotas.common.casting.arithmetic.operator.nextNumOrVecOrMatrix
import ram.talia.moreiotas.common.lib.hex.MoreIotasIotaTypes.MATRIX

class OperatorMatrixAdd(private val subtract: Boolean)
    : Operator(2, IotaMultiPredicate.any(IotaPredicate.ofType(MATRIX), IotaPredicate.or(IotaPredicate.ofType(DOUBLE), IotaPredicate.ofType(LIST)))) {
    override fun apply(iotas: Iterable<Iota>, env: CastingEnvironment): Iterable<Iota> {
        val it = iotas.iterator().withIndex()
        val mat0 = it.nextNumOrVecOrMatrix(arity).asMatrix
        val mat1 = it.nextNumOrVecOrMatrix(arity).asMatrix

        if (mat0.rows != mat1.rows || mat0.columns != mat1.columns)
            throw MishapInvalidIota.matrixWrongSize(iotas.last(), 0, mat0.rows, mat0.columns)

        return if (subtract) { mat0 - mat1 } else { mat0 + mat1 }.asActionResult
    }
}