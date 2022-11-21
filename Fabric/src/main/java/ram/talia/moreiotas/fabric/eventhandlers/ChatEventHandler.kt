package ram.talia.moreiotas.fabric.eventhandlers

import net.minecraft.network.chat.ChatType
import net.minecraft.network.chat.PlayerChatMessage
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import java.util.*

object ChatEventHandler {
    private val prefixes: MutableMap<UUID, String?> = mutableMapOf()
    private val lastMessages: MutableMap<UUID, String?> = mutableMapOf()
    var lastMessage: String? = null

    fun receiveChat(message: PlayerChatMessage, player: ServerPlayer, params: ChatType.Bound): Boolean {
        val text = message.signedBody.content.plain + message.unsignedContent.map { it.string }.orElse("")
        lastMessage = text

        if (!prefixes.containsKey(player.uuid)) {
            lastMessages[player.uuid] = text
            return true
        }

        val prefix = prefixes[player.uuid]

        if (prefix == null) {
            lastMessages[player.uuid] = text
            return true
        }

        if (!text.startsWith(prefix))
            return true

        lastMessages[player.uuid] = text.substring(prefix.length)

        return false
    }

    @JvmStatic
    fun setPrefix(player: Player, prefix: String) = prefixes.put(player.uuid, prefix)

    @JvmStatic
    fun getPrefix(player: Player) = prefixes[player.uuid]

    @JvmStatic
    fun lastMessage(player: Player?): String? = if (player != null) lastMessages[player.uuid] else lastMessage
}