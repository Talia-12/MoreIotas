package ram.talia.moreiotas.fabric.eventhandlers

import net.minecraft.network.chat.ChatType
import net.minecraft.network.chat.PlayerChatMessage
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import java.util.UUID

object ChatEventHandler {
    private val lastMessages: MutableMap<UUID, String?> = mutableMapOf()
    var lastMessage: String? = null

    fun receiveChat(message: PlayerChatMessage, player: ServerPlayer, params: ChatType.Bound) {
        lastMessages[player.uuid] = message.toString()
        lastMessage = message.toString()
    }

    @JvmStatic
    fun lastMessage(player: Player?): String? = if (player != null) lastMessages[player.uuid] else lastMessage
}