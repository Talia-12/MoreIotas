package ram.talia.moreiotas.api

import at.petrak.hexcasting.api.spell.iota.DoubleIota
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.iota.Vec3Iota
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota
import at.petrak.hexcasting.api.spell.mishaps.MishapNotEnoughArgs
import com.mojang.datafixers.util.Either
import net.minecraft.core.BlockPos
import net.minecraft.world.phys.Vec3
import org.jblas.DoubleMatrix
import ram.talia.moreiotas.api.spell.iota.MatrixIota
import ram.talia.moreiotas.api.spell.iota.StringIota
import ram.talia.moreiotas.api.util.Anyone

operator fun Double.times(vec: Vec3): Vec3 = vec.scale(this)
operator fun Vec3.times(d: Double): Vec3 = this.scale(d)
operator fun Vec3.div(d: Double): Vec3 = this.scale(1/d)
operator fun Vec3.plus(vec3: Vec3): Vec3 = this.add(vec3)
operator fun Vec3.minus(vec3: Vec3): Vec3 = this.subtract(vec3)
operator fun Vec3.unaryMinus(): Vec3 = this.scale(-1.0)

operator fun Double.times(mat: DoubleMatrix): DoubleMatrix = mat.mul(this)
operator fun DoubleMatrix.times(d: Double): DoubleMatrix = this.mul(d)

operator fun Vec3.times(mat: DoubleMatrix): DoubleMatrix = (this.asMatrix).mmul(mat)
operator fun DoubleMatrix.times(vec: Vec3): DoubleMatrix = this.mmul(vec.asMatrix)
operator fun DoubleMatrix.times(mat: DoubleMatrix): DoubleMatrix = this.mmul(mat)
operator fun DoubleMatrix.plus(mat: DoubleMatrix): DoubleMatrix = this.add(mat)
operator fun DoubleMatrix.minus(mat: DoubleMatrix): DoubleMatrix = this.sub(mat)
operator fun DoubleMatrix.unaryMinus(): DoubleMatrix = this.mul(-1.0)

fun List<Iota>.getString(idx: Int, argc: Int = 0): String {
    val x = this.getOrElse(idx) { throw MishapNotEnoughArgs(idx + 1, this.size) }
    if (x is StringIota) {
        return x.string
    } else {
        throw MishapInvalidIota.ofType(x, if (argc == 0) idx else argc - (idx + 1), "string")
    }
}

fun List<Iota>.getMatrix(idx: Int, argc: Int = 0): DoubleMatrix {
    val x = this.getOrElse(idx) { throw MishapNotEnoughArgs(idx + 1, this.size) }
    if (x is MatrixIota) {
        return x.matrix
    } else {
        throw MishapInvalidIota.ofType(x, if (argc == 0) idx else argc - (idx + 1), "matrix")
    }
}

fun List<Iota>.getNumOrVecOrMatrix(idx: Int, argc: Int = 0): Anyone<Double, Vec3, DoubleMatrix> {
    val datum = this.getOrElse(idx) { throw MishapNotEnoughArgs(idx + 1, this.size) }
    return when (datum) {
        is DoubleIota -> Anyone.first(datum.double)
        is Vec3Iota -> Anyone.second(datum.vec3)
        is MatrixIota -> Anyone.third(datum.matrix)
        else -> throw MishapInvalidIota.of(
                datum,
                if (argc == 0) idx else argc - (idx + 1),
                "numvecmat"
        )
    }
}

inline val String.asActionResult get() = listOf(StringIota(this))
inline val DoubleMatrix.asActionResult get() = listOf(MatrixIota(this))

inline val Vec3.asMatrix get() = DoubleMatrix(1, 3, this.x, this.y, this.z)
inline val BlockPos.asMatrix get() = DoubleMatrix(1, 3, this.x.toDouble(), this.y.toDouble(), this.z.toDouble())
inline val List<Vec3>.asMatrix get(): DoubleMatrix {
    val matrix = DoubleMatrix(this.size, 3)
    this.forEachIndexed { i, vec ->
        matrix.put(i, 0, vec.x)
        matrix.put(i, 1, vec.y)
        matrix.put(i, 2, vec.z)
    }
    return matrix;
}

fun MishapInvalidIota.Companion.matrixWrongSize(perpetrator: Iota,
                                                reverseIdx: Int,
                                                expectedRows: Int?,
                                                expectedColumns: Int?): MishapInvalidIota {
    if (expectedRows == null && expectedColumns == null)
        throw Exception("Need at least one of expectedRows and expectedColumns non-null.")

    return if (expectedRows == null)
        MishapInvalidIota.of(perpetrator, reverseIdx, "matrix.wrong_size", "n", expectedColumns!!)
    else if (expectedColumns == null)
        MishapInvalidIota.of(perpetrator, reverseIdx, "matrix.wrong_size", expectedRows, "n")
    else
        MishapInvalidIota.of(perpetrator, reverseIdx, "matrix.wrong_size", expectedRows, expectedColumns)
}