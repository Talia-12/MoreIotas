package ram.talia.moreiotas.common.casting.actions.matrices

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.DoubleIota
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.iota.ListIota
import at.petrak.hexcasting.api.spell.iota.Vec3Iota
import net.minecraft.world.phys.Vec3
import ram.talia.moreiotas.api.asVec3
import ram.talia.moreiotas.api.getMatrix

object OpUnmakeMatrix : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val mat = args.getMatrix(0, argc)

        if (mat.rows == 1 && mat.columns == 1)
            return mat[0,0].asActionResult
        if ((mat.rows == 1 && mat.columns == 3) || mat.rows == 3 && mat.columns == 1)
            return mat.asVec3.asActionResult
        val list = mutableListOf<Iota>()
        if (mat.rows == 3) {
            for (i in 0 until mat.columns) {
                list.add(Vec3Iota(Vec3(mat[0, i], mat[1, i], mat[2, i])))
            }
        } else if (mat.columns == 3) {
            for (i in 0 until mat.rows) {
                list.add(Vec3Iota(Vec3(mat[i, 0], mat[i, 1], mat[i, 2])))
            }
        } else {
            for (r in 0 until mat.rows) {
                val toAdd = mutableListOf<Iota>()
                for (c in 0 until mat.columns) {
                    toAdd.add(DoubleIota(mat[r,c]))
                }
                list.add(ListIota(toAdd))
            }
        }

        return list
    }
}