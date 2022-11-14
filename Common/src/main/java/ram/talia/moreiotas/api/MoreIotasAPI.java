package ram.talia.moreiotas.api;

import com.google.common.base.Suppliers;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public interface MoreIotasAPI
{
	String MOD_ID = "moreiotas";
	Logger LOGGER = LogManager.getLogger(MOD_ID);
	
	Supplier<MoreIotasAPI> INSTANCE = Suppliers.memoize(() -> {
		try {
			return (MoreIotasAPI) Class.forName("com.talia.moreiotas.common.impl.MoreIotasAPIImpl")
								 .getDeclaredConstructor().newInstance();
		} catch (ReflectiveOperationException e) {
			LogManager.getLogger().warn("Unable to find MoreIotasAPIImpl, using a dummy");
			return new MoreIotasAPI() {
			};
		}
	});
	
	static MoreIotasAPI instance() {
		return INSTANCE.get();
	}
	
	static ResourceLocation modLoc(String s) {
		return new ResourceLocation(MOD_ID, s);
	}
}
