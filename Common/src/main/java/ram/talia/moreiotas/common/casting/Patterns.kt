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
	val STRING_EMPTY = make(HexPattern.fromAngles("awdwa", HexDir.SOUTH_EAST),
			modLoc("string/empty"),
			Action.makeConstantOp(StringIota("")))
	@JvmField
	val STRING_SPACE = make(HexPattern.fromAngles("awdwaaww", HexDir.SOUTH_EAST),
	                        modLoc("string/space"),
	                        Action.makeConstantOp(StringIota(" ")))
	@JvmField
	val STRING_COMMA = make(HexPattern.fromAngles("qa", HexDir.EAST),
			modLoc("string/comma"),
			Action.makeConstantOp(StringIota(",")))
	@JvmField
	val STRING_NEWLINE = make(HexPattern.fromAngles("waawaw", HexDir.EAST),
			modLoc("string/newline"),
			Action.makeConstantOp(StringIota("\n")))
	@JvmField
	val STRING_BLOCK = make(HexPattern.fromAngles("awqwawqe", HexDir.EAST), modLoc("string/block"), OpBlockString)
	@JvmField
	val STRING_CHAT_CASTER = make(HexPattern.fromAngles("waqa", HexDir.EAST), modLoc("string/chat/caster"), OpChatString(false))
	@JvmField
	val STRING_CHAT_ALL = make(HexPattern.fromAngles("wded", HexDir.EAST), modLoc("string/chat/all"), OpChatString(true))
	@JvmField
	val STRING_CHAT_PREFIX_SET = make(HexPattern.fromAngles("qwaqa", HexDir.SOUTH_EAST), modLoc("string/chat/prefix/set"), OpChatPrefixSet)
	@JvmField
	val STRING_CHAT_PREFIX_GET = make(HexPattern.fromAngles("ewded", HexDir.NORTH_EAST), modLoc("string/chat/prefix/get"), OpChatPrefixGet)
	@JvmField
	val STRING_IOTA = make(HexPattern.fromAngles("wawqwawaw", HexDir.EAST), modLoc("string/iota"), OpIotaString)
	@JvmField
	val STRING_ACTION = make(HexPattern.fromAngles("wdwewdwdw", HexDir.NORTH_WEST), modLoc("string/action"), OpActionString)

	@JvmField
	val STRING_ADD = make(HexPattern.fromAngles("waawaqwawqq", HexDir.NORTH_EAST), modLoc("string/add"), OpAddStrings)
	@JvmField
	val STRING_SPLIT = make(HexPattern.fromAngles("aqwaqa", HexDir.EAST), modLoc("string/split"), OpSplitString)
	@JvmField
	val STRING_PARSE = make(HexPattern.fromAngles("aqwaq", HexDir.EAST), modLoc("string/parse"), OpParseString)
	@JvmField
	val STRING_FIND = make(HexPattern.fromAngles("waqwwaqa", HexDir.EAST), modLoc("string/find"), OpFindString)
	@JvmField
	val STRING_SUB = make(HexPattern.fromAngles("aqwwaqwaad", HexDir.EAST), modLoc("string/sub"), OpSubString)
	@JvmField
	val STRING_LEN = make(HexPattern.fromAngles("waqaeaq", HexDir.EAST), modLoc("string/len"), OpLenString)

	// ================================ Matrices =======================================
	@JvmField
	val MATRIX_MAKE = make(HexPattern.fromAngles("awwaeawwaadwa", HexDir.SOUTH_WEST), modLoc("matrix/make"), OpMakeMatrix)
	@JvmField
	val MATRIX_UNMAKE = make(HexPattern.fromAngles("dwwdqdwwddawd", HexDir.SOUTH_EAST), modLoc("matrix/unmake"), OpUnmakeMatrix)
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
	val MATRIX_INVERSE = make(HexPattern.fromAngles("wwdqdwwdqaq", HexDir.WEST), modLoc("matrix/inverse"), OpInverseMatrix)
	@JvmField
	val MATRIX_DETERMINANT = make(HexPattern.fromAngles("aeawwaeawaw", HexDir.WEST), modLoc("matrix/determinant"), OpDeterminantMatrix)
	@JvmField
	val MATRIX_CONCAT_VERT = make(
			HexPattern.fromAngles("awwaeawwawawdedwa", HexDir.SOUTH_WEST),
			modLoc("matrix/concat/vert"),
			OpConcatMatrix(true))
	@JvmField
	val MATRIX_CONCAT_HORI = make(
			HexPattern.fromAngles("dwwdqdwwdwdwaqawd", HexDir.SOUTH_EAST),
			modLoc("matrix/concat/hori"),
			OpConcatMatrix(false))
	@JvmField
	val MATRIX_SPLIT_VERT = make(
			HexPattern.fromAngles("awdedwawawwaeawwa", HexDir.SOUTH_EAST),
			modLoc("matrix/split/vert"),
			OpSplitMatrix(true))
	@JvmField
	val MATRIX_SPLIT_HORI = make(
			HexPattern.fromAngles("dwaqawdwdwwdqdwwd", HexDir.SOUTH_WEST),
			modLoc("matrix/split/hori"),
			OpSplitMatrix(false))

	private fun make (pattern: HexPattern, location: ResourceLocation, operator: Action, isPerWorld: Boolean = false): Triple<HexPattern, ResourceLocation, Action> {
		val triple = Triple(pattern, location, operator)
		if (isPerWorld)
			PER_WORLD_PATTERNS.add(triple)
		else
			PATTERNS.add(triple)
		return triple
	}
}