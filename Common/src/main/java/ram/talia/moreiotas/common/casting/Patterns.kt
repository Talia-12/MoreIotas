@file:Suppress("unused")

package ram.talia.moreiotas.common.casting

import at.petrak.hexcasting.api.spell.Action
import at.petrak.hexcasting.api.spell.math.HexDir
import at.petrak.hexcasting.api.spell.math.HexPattern
import at.petrak.hexcasting.common.casting.operators.selectors.*
import net.minecraft.resources.ResourceLocation
import ram.talia.moreiotas.api.MoreIotasAPI.modLoc
import ram.talia.moreiotas.api.spell.iota.StringIota
import ram.talia.moreiotas.common.casting.actions.*
import ram.talia.moreiotas.common.casting.actions.matrices.*
import ram.talia.moreiotas.common.casting.actions.spells.*
import ram.talia.moreiotas.common.casting.actions.strings.*

object Patterns {

	@JvmField
	val PATTERNS: MutableList<Triple<HexPattern, ResourceLocation, Action>> = ArrayList()
	@JvmField
	val PER_WORLD_PATTERNS: MutableList<Triple<HexPattern, ResourceLocation, Action>> = ArrayList()

	// ================================ Strings =======================================
	@JvmField
	val STRING_SPACE = make(HexPattern.fromAngles("awdwa", HexDir.SOUTH_EAST),
	                        modLoc("string/space"),
	                        Action.makeConstantOp(StringIota(" ")))
	@JvmField
	val STRING_COMMA = make(HexPattern.fromAngles("qa", HexDir.EAST),
			modLoc("string/comma"),
			Action.makeConstantOp(StringIota(",")))
	@JvmField
	val STRING_BLOCK = make(HexPattern.fromAngles("awqwawqe", HexDir.EAST), modLoc("string/block"), OpBlockString)
	@JvmField
	val STRING_CHAT_CASTER = make(HexPattern.fromAngles("waqa", HexDir.EAST), modLoc("string/chat/caster"), OpChatString(false))
	@JvmField
	val STRING_CHAT_ALL = make(HexPattern.fromAngles("wded", HexDir.EAST), modLoc("string/chat/all"), OpChatString(true))
	@JvmField
	val STRING_IOTA = make(HexPattern.fromAngles("wawqwawaw", HexDir.EAST), modLoc("string/iota"), OpIotaString)
	@JvmField
	val STRING_ADD = make(HexPattern.fromAngles("waawaqwawqq", HexDir.NORTH_EAST), modLoc("string/add"), OpAddStrings)
	@JvmField
	val STRING_SPLIT = make(HexPattern.fromAngles("aqwaqa", HexDir.EAST), modLoc("string/split"), OpSplitString)
	@JvmField
	val STRING_PARSE = make(HexPattern.fromAngles("aqwaq", HexDir.EAST), modLoc("string/parse"), OpParseString)

	// ================================ Matrices =======================================
	@JvmField
	val MATRIX_MAKE = make(HexPattern.fromAngles("awwaeawwaadwa", HexDir.SOUTH_WEST), modLoc("matrix/make"), OpMakeMatrix)
	@JvmField
	val MATRIX_IDENTITY = make(HexPattern.fromAngles("awwaeawwaqw", HexDir.SOUTH_WEST), modLoc("matrix/identity"), OpIdentityMatrix)
	@JvmField
	val MATRIX_ZERO = make(HexPattern.fromAngles("awwaeawwa", HexDir.SOUTH_WEST), modLoc("matrix/zero"), OpZeroMatrix)
	@JvmField
	val MATRIX_ROTATION = make(HexPattern.fromAngles("awwaeawwawawddw", HexDir.SOUTH_WEST), modLoc("matrix/rotation"), OpRotationMatrix)
	@JvmField
	val MATRIX_ADD = make(HexPattern.fromAngles("waawawaeawwaea", HexDir.EAST), modLoc("matrix/add"), OpAddMatrix)
	@JvmField
	val MATRIX_MUL = make(HexPattern.fromAngles("waqawawwaeaww", HexDir.SOUTH_EAST), modLoc("matrix/mul"), OpMulMatrix)
	@JvmField
	val MATRIX_TRANSPOSE = make(HexPattern.fromAngles("wwaeawwaede", HexDir.EAST), modLoc("matrix/transpose"), OpTransposeMatrix)
	@JvmField
	val MATRIX_INVERSE = make(HexPattern.fromAngles("wawawwwdwdw", HexDir.NORTH_EAST), modLoc("matrix/inverse"), OpInverseMatrix)

	private fun make (pattern: HexPattern, location: ResourceLocation, operator: Action, isPerWorld: Boolean = false): Triple<HexPattern, ResourceLocation, Action> {
		val triple = Triple(pattern, location, operator)
		if (isPerWorld)
			PER_WORLD_PATTERNS.add(triple)
		else
			PATTERNS.add(triple)
		return triple
	}
}