package ram.talia.moreiotas.client;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;
import ram.talia.moreiotas.xplat.IClientXplatAbstractions;

public class RegisterClientStuff {
	public static void init () {
		var x = IClientXplatAbstractions.INSTANCE;
	}
	
	public static void registerBlockEntityRenderers(@NotNull BlockEntityRendererRegisterer registerer) {
	}
	
	@FunctionalInterface
	public interface BlockEntityRendererRegisterer {
		<T extends BlockEntity> void registerBlockEntityRenderer(BlockEntityType<T> type, BlockEntityRendererProvider<? super T> berp);
	}
}
