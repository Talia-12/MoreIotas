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
import ram.talia.moreiotas.common.casting.actions.spells.*
import ram.talia.moreiotas.common.casting.actions.strings.*

object Patterns {

	@JvmField
	val PATTERNS: MutableList<Triple<HexPattern, ResourceLocation, Action>> = ArrayList()
	@JvmField
	val PER_WORLD_PATTERNS: MutableList<Triple<HexPattern, ResourceLocation, Action>> = ArrayList()

	// ================================ Strings =======================================
	@JvmField
	val STRING_SPACE = make(HexPattern.fromAngles("awd", HexDir.SOUTH_WEST),
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


	private fun make (pattern: HexPattern, location: ResourceLocation, operator: Action, isPerWorld: Boolean = false): Triple<HexPattern, ResourceLocation, Action> {
		val triple = Triple(pattern, location, operator)
		if (isPerWorld)
			PER_WORLD_PATTERNS.add(triple)
		else
			PATTERNS.add(triple)
		return triple
	}
}