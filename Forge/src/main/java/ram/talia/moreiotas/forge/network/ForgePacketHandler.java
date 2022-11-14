package ram.talia.moreiotas.forge.network;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static ram.talia.moreiotas.api.MoreIotasAPI.modLoc;

public class ForgePacketHandler {
	private static final String PROTOCOL_VERSION = "1";
	private static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(
					modLoc("main"),
					() -> PROTOCOL_VERSION,
					PROTOCOL_VERSION::equals,
					PROTOCOL_VERSION::equals
	);
	
	public static SimpleChannel getNetwork() {
		return NETWORK;
	}
	
	@SuppressWarnings("UnusedAssignment")
	public static void init() {
		int messageIdx = 0;
		
		// Client -> server

		// Server -> client
		//general

		// forge specific
	}
	
	private static <T> BiConsumer<T, Supplier<NetworkEvent.Context>> makeServerBoundHandler(
					TriConsumer<T, MinecraftServer, ServerPlayer> handler) {
		return (m, ctx) -> {
			handler.accept(m, Objects.requireNonNull(ctx.get().getSender()).getServer(), ctx.get().getSender());
			ctx.get().setPacketHandled(true);
		};
	}
	
	private static <T> BiConsumer<T, Supplier<NetworkEvent.Context>> makeClientBoundHandler(Consumer<T> consumer) {
		return (m, ctx) -> {
			consumer.accept(m);
			ctx.get().setPacketHandled(true);
		};
	}
}
