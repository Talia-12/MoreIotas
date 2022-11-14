package ram.talia.moreiotas.forge.eventhandlers;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatEventHandler {
    private static final Map<UUID, @Nullable String> lastMessages = new HashMap<>();
    @Nullable
    private static String lastMessage = null;

    @Nullable
    public static String getLastMessage(@Nullable Player player) {
        if (player == null)
            return lastMessage;
        return lastMessages.get(player.getUUID());
    }

    @SubscribeEvent
    public static void chatMessageSent(ServerChatEvent event) {
        lastMessages.put(event.getPlayer().getUUID(), event.getRawText());
        lastMessage = event.getRawText();
    }
}
