package ram.talia.moreiotas.common.casting.arithmetic

import at.petrak.hexcasting.api.casting.arithmetic.Arithmetic
import at.petrak.hexcasting.api.casting.arithmetic.Arithmetic.*
import at.petrak.hexcasting.api.casting.arithmetic.engine.InvalidOperatorException
import at.petrak.hexcasting.api.casting.arithmetic.operator.Operator
import at.petrak.hexcasting.api.casting.arithmetic.operator.OperatorUnary
import at.petrak.hexcasting.api.casting.arithmetic.predicates.IotaMultiPredicate
import at.petrak.hexcasting.api.casting.arithmetic.predicates.IotaPredicate
import at.petrak.hexcasting.api.casting.iota.DoubleIota
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.math.HexPattern
import org.jblas.DoubleMatrix
import org.jblas.MatrixFunctions
import ram.talia.moreiotas.api.casting.iota.MatrixIota
import ram.talia.moreiotas.common.casting.arithmetic.operator.matrix.OperatorMatrixAdd
import ram.talia.moreiotas.common.casting.arithmetic.operator.matrix.OperatorMatrixDiv
import ram.talia.moreiotas.common.casting.arithmetic.operator.matrix.OperatorMatrixMul
import ram.talia.moreiotas.common.lib.hex.MoreIotasIotaTypes.MATRIX
import java.util.function.Function
import java.util.function.UnaryOperator

object MatrixArithmetic : Arithmetic {
    private val OPS = listOf(
        ADD,
        SUB,
        MUL,
        DIV,
        ABS,
        POW,
        FLOOR,
        CEIL,
        SIN,
        COS,
        TAN,
        ARCSIN,
        ARCCOS,
        ARCTAN,
        ARCTAN2,
        LOG,
        REV
    )

    override fun arithName(): String = "matrix_maths"

    override fun opTypes(): Iterable<HexPattern> = OPS

    override fun getOperator(pattern: HexPattern): Operator = when (pattern) {
        ADD -> OperatorMatrixAdd(false)
        SUB -> OperatorMatrixAdd(true)
        MUL -> OperatorMatrixMul
        DIV -> OperatorMatrixDiv
        ABS -> make1Double { it.norm2() }
        POW -> OperatorMatrixMul // TODO
        FLOOR -> make1(MatrixFunctions::floor)
        CEIL -> make1(MatrixFunctions::ceil)
        SIN -> make1(MatrixFunctions::floor) // TODO
        COS -> make1(MatrixFunctions::floor) // TODO
        TAN -> make1(MatrixFunctions::floor) // TODO
        ARCSIN -> make1(MatrixFunctions::floor) // TODO
        ARCCOS -> make1(MatrixFunctions::floor) // TODO
        ARCTAN -> make1(MatrixFunctions::floor) // TODO
        ARCTAN2 -> OperatorMatrixMul // TODO
        LOG -> OperatorMatrixMul // TODO
        REV -> make1(DoubleMatrix::transpose)
        else -> throw InvalidOperatorException("$pattern is not a valid operator in Arithmetic $this.")
    }

    private fun make1Double(op: Function<DoubleMatrix, Double>): OperatorUnary = OperatorUnary(IotaMultiPredicate.all(IotaPredicate.ofType(MATRIX)))
    { i: Iota -> DoubleIota(
        op.apply(Operator.downcast(i, MATRIX).matrix)
    ) }

    private fun make1(op: UnaryOperator<DoubleMatrix>): OperatorUnary = OperatorUnary(IotaMultiPredicate.all(IotaPredicate.ofType(MATRIX)))
    { i: Iota ->
        MatrixIota(
                op.apply(Operator.downcast(i, MATRIX).matrix)
        )
    }
}