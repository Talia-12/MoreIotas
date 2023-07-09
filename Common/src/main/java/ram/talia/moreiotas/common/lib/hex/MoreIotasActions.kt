@file:Suppress("unused")

package ram.talia.moreiotas.common.lib.hex

import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.castables.Action
import at.petrak.hexcasting.api.casting.castables.OperationAction
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import net.minecraft.resources.ResourceLocation
import ram.talia.moreiotas.api.MoreIotasAPI.modLoc
import ram.talia.moreiotas.api.casting.iota.StringIota
import ram.talia.moreiotas.common.casting.actions.matrices.*
import ram.talia.moreiotas.common.casting.actions.strings.*
import ram.talia.moreiotas.common.casting.actions.types.*
import java.util.function.BiConsumer

object MoreIotasActions {

	private val ACTIONS: MutableMap<ResourceLocation, ActionRegistryEntry> = mutableMapOf()


	@JvmStatic
	fun register(r: BiConsumer<ActionRegistryEntry, ResourceLocation>) {
		val var1: Iterator<*> = ACTIONS.entries.iterator()
		while (var1.hasNext()) {
			val (key, value) = var1.next() as Map.Entry<*, *>
			r.accept(value as ActionRegistryEntry, key as ResourceLocation)
		}
	}

	// ================================ Strings =======================================
	@JvmField
	val STRING_EMPTY = make("string/empty",
			HexPattern.fromAngles("awdwa", HexDir.SOUTH_EAST),
			Action.makeConstantOp(StringIota("")))
	@JvmField
	val STRING_SPACE = make("string/space",
			HexPattern.fromAngles("awdwaaww", HexDir.SOUTH_EAST),
			Action.makeConstantOp(StringIota(" ")))
	@JvmField
	val STRING_COMMA = make("string/comma",
			HexPattern.fromAngles("qa", HexDir.EAST),
			Action.makeConstantOp(StringIota(",")))
	@JvmField
	val STRING_NEWLINE = make("string/newline",
			HexPattern.fromAngles("waawaw", HexDir.EAST),
			Action.makeConstantOp(StringIota("\n")))
	@JvmField
	val STRING_BLOCK_GET = make("string/block/get", HexPattern.fromAngles("awqwawqe", HexDir.EAST), OpGetBlockString)
	@JvmField
	val STRING_BLOCK_SET = make("string/block/set", HexPattern.fromAngles("dwewdweq", HexDir.WEST), OpSetBlockString)
	@JvmField
	val STRING_CHAT_CASTER = make("string/chat/caster", HexPattern.fromAngles("waqa", HexDir.EAST), OpChatString(false))
	@JvmField
	val STRING_CHAT_ALL = make("string/chat/all", HexPattern.fromAngles("wded", HexDir.EAST), OpChatString(true))
	@JvmField
	val STRING_CHAT_PREFIX_GET = make("string/chat/prefix/get", HexPattern.fromAngles("ewded", HexDir.NORTH_EAST), OpGetChatPrefix)
	@JvmField
	val STRING_CHAT_PREFIX_SET = make("string/chat/prefix/set", HexPattern.fromAngles("qwaqa", HexDir.SOUTH_EAST), OpSetChatPrefix)
	@JvmField
	val STRING_IOTA = make("string/iota", HexPattern.fromAngles("wawqwawaw", HexDir.EAST), OpIotaString)
	@JvmField
	val STRING_ACTION = make("string/action", HexPattern.fromAngles("wdwewdwdw", HexDir.NORTH_WEST), OpActionString)
	@JvmField
	val STRING_NAME_GET = make("string/name/get", HexPattern.fromAngles("deqqeddqwqqqwq", HexDir.SOUTH_EAST), OpNameGet)
	@JvmField
	val STRING_NAME_SET = make("string/name/set", HexPattern.fromAngles("aqeeqaaeweeewe", HexDir.SOUTH_WEST), OpNameSet)

//	@JvmField
//	val STRING_ADD = make("string/add", HexPattern.fromAngles("waawaqwawqq", HexDir.NORTH_EAST), OpAddStrings)
	@JvmField
	val STRING_SPLIT = make("string/split", HexPattern.fromAngles("aqwaqa", HexDir.EAST), OpSplitString)
	@JvmField
	val STRING_PARSE = make("string/parse", HexPattern.fromAngles("aqwaq", HexDir.EAST), OpParseString)
//	@JvmField
//	val STRING_FIND = make("string/find", HexPattern.fromAngles("waqwwaqa", HexDir.EAST), OpFindString)
//	@JvmField
//	val STRING_SUB = make("string/sub", HexPattern.fromAngles("aqwwaqwaad", HexDir.EAST), OpSubString)
	@JvmField
	val STRING_CASE = make("string/case", HexPattern.fromAngles("dwwdwwdwdd", HexDir.WEST), OpCaseString)

	// ================================ Matrices =======================================
	@JvmField
	val MATRIX_MAKE = make("matrix/make", HexPattern.fromAngles("awwaeawwaadwa", HexDir.SOUTH_WEST), OpMakeMatrix)
	@JvmField
	val MATRIX_UNMAKE = make("matrix/unmake", HexPattern.fromAngles("dwwdqdwwddawd", HexDir.SOUTH_EAST), OpUnmakeMatrix)
	@JvmField
	val MATRIX_IDENTITY = make("matrix/identity", HexPattern.fromAngles("awwaeawwaqw", HexDir.SOUTH_WEST), OpIdentityMatrix)
	@JvmField
	val MATRIX_ZERO = make("matrix/zero", HexPattern.fromAngles("awwaeawwa", HexDir.SOUTH_WEST), OpZeroMatrix)
	@JvmField
	val MATRIX_ROTATION = make("matrix/rotation", HexPattern.fromAngles("awwaeawwawawddw", HexDir.SOUTH_WEST), OpRotationMatrix)
//	@JvmField
//	val MATRIX_ADD = make("matrix/add", HexPattern.fromAngles("waawawaeawwaea", HexDir.EAST), OpAddMatrix)
//	@JvmField
//	val MATRIX_MUL = make("matrix/mul", HexPattern.fromAngles("waqawawwaeaww", HexDir.SOUTH_EAST), OpMulMatrix)
//	@JvmField
//	val MATRIX_TRANSPOSE = make("matrix/transpose", HexPattern.fromAngles("wwaeawwaede", HexDir.EAST), OpTransposeMatrix)
	@JvmField
	val MATRIX_INVERSE = make("matrix/inverse", HexPattern.fromAngles("wwdqdwwdqaq", HexDir.WEST), OpInverseMatrix)
	@JvmField
	val MATRIX_DETERMINANT = make("matrix/determinant", HexPattern.fromAngles("aeawwaeawaw", HexDir.WEST), OpDeterminantMatrix)
	@JvmField
	val MATRIX_CONCAT_VERT = make(
			"matrix/concat/vert",
			HexPattern.fromAngles("awwaeawwawawdedwa", HexDir.SOUTH_WEST),
			OpConcatMatrix(true))
	@JvmField
	val MATRIX_CONCAT_HORI = make(
			"matrix/concat/hori",
			HexPattern.fromAngles("dwwdqdwwdwdwaqawd", HexDir.SOUTH_EAST),
			OpConcatMatrix(false))
	@JvmField
	val MATRIX_SPLIT_VERT = make(
			"matrix/split/vert",
			HexPattern.fromAngles("awdedwawawwaeawwa", HexDir.SOUTH_EAST),
			OpSplitMatrix(true))
	@JvmField
	val MATRIX_SPLIT_HORI = make(
			"matrix/split/hori",
			HexPattern.fromAngles("dwaqawdwdwwdqdwwd", HexDir.SOUTH_WEST),
			OpSplitMatrix(false))

	// ================================ Types =======================================

	@JvmField
	val TYPE_TO_ITEM = make("type/to_item", OperationAction(HexPattern.fromAngles("qaqqaea", HexDir.EAST)))
	@JvmField
	val TYPE_ENTITY = make("type/entity", HexPattern.fromAngles("qawde", HexDir.SOUTH_WEST), OpTypeEntity)
	@JvmField
	val TYPE_IOTA = make("type/iota", HexPattern.fromAngles("awd", HexDir.SOUTH_WEST), OpTypeIota)
	@JvmField
	val TYPE_ITEM_HELD = make("type/item_held", HexPattern.fromAngles("edeedqd", HexDir.SOUTH_WEST), OpTypeItemHeld)

	@JvmField
	val GET_ENTITY_TYPE = make("get_entity/type", HexPattern.fromAngles("dadqqqqqdad", HexDir.NORTH_EAST), OpGetEntityAtDyn)
	@JvmField
	val ZONE_ENTITY_TYPE = make("zone_entity/type", HexPattern.fromAngles("waweeeeewaw", HexDir.SOUTH_EAST), OpGetEntitiesByDyn(false))
	@JvmField
	val ZONE_ENTITY_NOT_TYPE = make("zone_entity/not_type", HexPattern.fromAngles("wdwqqqqqwdw", HexDir.NORTH_EAST), OpGetEntitiesByDyn(true))


	private fun make(name: String, pattern: HexPattern, action: Action): ActionRegistryEntry = make(name, ActionRegistryEntry(pattern, action))

	private fun make(name: String, are: ActionRegistryEntry): ActionRegistryEntry {
		return if (ACTIONS.put(modLoc(name), are) != null) {
			throw IllegalArgumentException("Typo? Duplicate id $name")
		} else {
			are
		}
	}

	private fun make(name: String, oa: OperationAction): ActionRegistryEntry {
		val are = ActionRegistryEntry(oa.pattern, oa)
		return if (ACTIONS.put(modLoc(name), are) != null) {
			throw IllegalArgumentException("Typo? Duplicate id $name")
		} else {
			are
		}
	}
}