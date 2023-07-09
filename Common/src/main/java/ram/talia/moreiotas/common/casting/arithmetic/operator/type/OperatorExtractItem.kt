package ram.talia.moreiotas.common.casting.arithmetic.operator.type

import at.petrak.hexcasting.api.casting.arithmetic.operator.Operator
import at.petrak.hexcasting.api.casting.arithmetic.predicates.IotaMultiPredicate
import at.petrak.hexcasting.api.casting.arithmetic.predicates.IotaPredicate
import at.petrak.hexcasting.api.casting.asActionResult
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.EntityIota
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.Vec3Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapInvalidIota
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes.ENTITY
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes.VEC3
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.decoration.ItemFrame
import net.minecraft.world.entity.item.ItemEntity
import ram.talia.moreiotas.api.asActionResult

object OperatorExtractItem : Operator(1, IotaMultiPredicate.all(IotaPredicate.or(IotaPredicate.ofType(VEC3), IotaPredicate.ofType(ENTITY)))) {
    override fun apply(iotas: Iterable<Iota>, env: CastingEnvironment): Iterable<Iota> {
        val iota = iotas.first()

        return if (iota is Vec3Iota) {
            if (!env.isVecInRange(iota.vec3))
                null.asActionResult
            else {
                val blockState = env.world.getBlockState(BlockPos.containing(iota.vec3))
                blockState.block.asActionResult
            }
        } else {
            when (val entity = (iota as EntityIota).entity) {
                is ItemEntity -> entity.item.item.asActionResult
                is ItemFrame -> entity.item.item.asActionResult
                else -> throw MishapInvalidIota.of(iota, 0, "blockitementityitemframeitem")
            }
        }
    }
}