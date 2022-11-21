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
        val string = message.signedBody.content.plain + message.unsignedContent.map { it.string }.orElse("")

        lastMessages[player.uuid] = string
        lastMessage = string
    }

    @JvmStatic
    fun lastMessage(player: Player?): String? = if (player != null) lastMessages[player.uuid] else lastMessage
}