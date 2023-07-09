package ram.talia.moreiotas.common.casting.arithmetic.operator.matrix

import at.petrak.hexcasting.api.casting.arithmetic.operator.Operator
import at.petrak.hexcasting.api.casting.arithmetic.predicates.IotaMultiPredicate
import at.petrak.hexcasting.api.casting.arithmetic.predicates.IotaPredicate
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapInvalidIota
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes.DOUBLE
import ram.talia.moreiotas.api.asActionResult
import ram.talia.moreiotas.api.asMatrix
import ram.talia.moreiotas.api.matrixWrongSize
import ram.talia.moreiotas.common.casting.arithmetic.operator.nextNumOrVecOrMatrix
import ram.talia.moreiotas.common.lib.hex.MoreIotasIotaTypes.MATRIX

object OperatorMatrixPow : Operator(2, IotaMultiPredicate.either(
        IotaMultiPredicate.pair(IotaPredicate.ofType(MATRIX), IotaPredicate.ofType(DOUBLE)),
        IotaMultiPredicate.pair(IotaPredicate.ofType(DOUBLE), IotaPredicate.ofType(MATRIX)))) {
    override fun apply(iotas: Iterable<Iota>, env: CastingEnvironment): Iterable<Iota> {
        val it = iotas.iterator().withIndex()
        val arg0 = it.nextNumOrVecOrMatrix(arity)
        val arg1 = it.nextNumOrVecOrMatrix(arity)

        arg0.a?.let {
            val mat = arg1.asMatrix
            if (mat.rows != mat.columns)
                throw MishapInvalidIota.matrixWrongSize(iotas.last(), 0, mat.rows, mat.rows)

//            return it.pow(mat)
            TODO()
        }

        val mat = arg0.asMatrix
        if (mat.rows != mat.columns)
            throw MishapInvalidIota.matrixWrongSize(iotas.last(), 0, mat.rows, mat.rows)

        return mat.asActionResult
    }
}