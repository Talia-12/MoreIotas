@file:Suppress("unused")

package ram.talia.moreiotas.common.casting

import at.petrak.hexcasting.api.spell.Action
import at.petrak.hexcasting.api.spell.math.HexDir
import at.petrak.hexcasting.api.spell.math.HexPattern
import at.petrak.hexcasting.common.casting.operators.selectors.*
import net.minecraft.resources.ResourceLocation
import ram.talia.moreiotas.common.casting.actions.*
import ram.talia.moreiotas.common.casting.actions.spells.*

object Patterns {

	@JvmField
	var PATTERNS: MutableList<Triple<HexPattern, ResourceLocation, Action>> = ArrayList()
	@JvmField
	var PER_WORLD_PATTERNS: MutableList<Triple<HexPattern, ResourceLocation, Action>> = ArrayList()

//	@JvmField
//	val LINK_COMM_CLOSE_TRANSMIT = make(HexPattern.fromAngles("ewaqawe", HexDir.EAST), modLoc("link/comm/close_transmit"), OpCloseTransmit)

	private fun make (pattern: HexPattern, location: ResourceLocation, operator: Action, isPerWorld: Boolean = false): Triple<HexPattern, ResourceLocation, Action> {
		val triple = Triple(pattern, location, operator)
		if (isPerWorld)
			PER_WORLD_PATTERNS.add(triple)
		else
			PATTERNS.add(triple)
		return triple
	}
}