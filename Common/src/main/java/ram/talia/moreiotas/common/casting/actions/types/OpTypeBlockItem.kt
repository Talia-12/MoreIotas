package ram.talia.moreiotas.common.casting.actions.types

import at.petrak.hexcasting.api.casting.*
import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.EntityIota
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.Vec3Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapInvalidIota
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.decoration.ItemFrame
import net.minecraft.world.entity.item.ItemEntity
import ram.talia.moreiotas.api.asActionResult

object OpTypeBlockItem : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        // get anything that's convertable to an item, and return its type.
        return when (val arg = args[0]) {
            is Vec3Iota -> {
                if (!env.isVecInRange(arg.vec3))
                    null.asActionResult
                else {
                    val blockState = env.world.getBlockState(BlockPos.containing(arg.vec3))
                    blockState.block.asActionResult
                }
            }
            is EntityIota -> {
                return when (val entity = arg.entity) {
                    is ItemEntity -> entity.item.item.asActionResult
                    is ItemFrame -> entity.item.item.asActionResult
                    else -> throw MishapInvalidIota.of(arg, 0, "blockitementityitemframeitem")
                }
            }
            else -> throw MishapInvalidIota.of(arg, 0, "blockitementityitemframeitem")
        }
    }
}