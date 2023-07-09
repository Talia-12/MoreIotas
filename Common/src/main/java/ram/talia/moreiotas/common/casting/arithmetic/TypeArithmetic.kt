package ram.talia.moreiotas.common.casting.arithmetic

import at.petrak.hexcasting.api.casting.arithmetic.Arithmetic
import at.petrak.hexcasting.api.casting.arithmetic.engine.InvalidOperatorException
import at.petrak.hexcasting.api.casting.arithmetic.operator.Operator
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import ram.talia.moreiotas.common.casting.arithmetic.operator.type.OperatorExtractItem

object TypeArithmetic : Arithmetic {
    @JvmField
    val EXTRACT_ITEM: HexPattern = HexPattern.fromAngles("qaqqaea", HexDir.EAST)

    private val OPS = listOf(
        EXTRACT_ITEM
    )

    override fun arithName() = "type_ops"

    override fun opTypes(): Iterable<HexPattern> = OPS

    override fun getOperator(pattern: HexPattern): Operator = when (pattern) {
        EXTRACT_ITEM -> OperatorExtractItem
        else -> throw InvalidOperatorException("$pattern is not a valid operator in Arithmetic $this.")
    }
}