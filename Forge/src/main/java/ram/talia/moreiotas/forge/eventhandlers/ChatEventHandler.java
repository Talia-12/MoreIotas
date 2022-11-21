package ram.talia.moreiotas.forge.eventhandlers;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatEventHandler {
    private static final Map<UUID, @Nullable String> prefixes = new HashMap<>();
    private static final Map<UUID, @Nullable String> lastMessages = new HashMap<>();

    private static @Nullable String lastMessage = null;

    public static void setPrefix(Player player, @Nullable String prefix) {
        prefixes.put(player.getUUID(), prefix);
    }

    public static @Nullable String getPrefix(Player player) {
        return prefixes.get(player.getUUID());
    }

    public static @Nullable String getLastMessage(@Nullable Player player) {
        if (player == null)
            return lastMessage;
        return lastMessages.get(player.getUUID());
    }

    @SubscribeEvent
    public static void chatMessageSent(ServerChatEvent event) {
        var uuid = event.getPlayer().getUUID();
        var text = event.getRawText();
        lastMessage = text;


        if (!prefixes.containsKey(uuid)) {
            lastMessages.put(uuid, text);
            return;
        }

        var prefix = prefixes.get(uuid);

        if (prefix == null) {
            lastMessages.put(uuid, text);
            return;
        }


        if (text.startsWith(prefix)) {
            event.setCanceled(true);
            lastMessages.put(uuid, text.substring(prefix.length()));
        }
    }
}
