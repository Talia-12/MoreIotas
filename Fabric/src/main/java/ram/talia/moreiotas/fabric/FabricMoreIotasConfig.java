package ram.talia.moreiotas.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import ram.talia.moreiotas.api.MoreIotasAPI;
import ram.talia.moreiotas.api.mod.MoreIotasConfig;
import ram.talia.moreiotas.xplat.IXplatAbstractions;

@Config(name = MoreIotasAPI.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/calcite.png")
public class FabricMoreIotasConfig extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Category("common")
    @ConfigEntry.Gui.TransitiveObject
    public final Common common = new Common();
    @ConfigEntry.Category("client")
    @ConfigEntry.Gui.TransitiveObject
    public final Client client = new Client();
    @ConfigEntry.Category("server")
    @ConfigEntry.Gui.TransitiveObject
    public final Server server = new Server();

    public static FabricMoreIotasConfig setup() {
        AutoConfig.register(FabricMoreIotasConfig.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        var instance = AutoConfig.getConfigHolder(FabricMoreIotasConfig.class).getConfig();

        MoreIotasConfig.setCommon(instance.common);
        // We care about the client only on the *physical* client ...
        if (IXplatAbstractions.INSTANCE.isPhysicalClient()) {
            MoreIotasConfig.setClient(instance.client);
        }
        // but we care about the server on the *logical* server
        // i believe this should Just Work without a guard? assuming we don't access it from the client ever
        MoreIotasConfig.setServer(instance.server);

        return instance;
    }


    @Config(name = "common")
    private static class Common implements ConfigData, MoreIotasConfig.CommonConfigAccess { }

    @Config(name = "client")
    private static class Client implements ConfigData, MoreIotasConfig.ClientConfigAccess { }


    @Config(name = "server")
    private static class Server implements ConfigData, MoreIotasConfig.ServerConfigAccess {

        @ConfigEntry.BoundedDiscrete(min = 2, max = MAX_MAX_MATRIX_SIZE)
        @ConfigEntry.Gui.Tooltip
        private int maxMatrixSize = DEFAULT_MAX_MATRIX_SIZE;

        @ConfigEntry.BoundedDiscrete(min = 1, max = MAX_MAX_STRING_LENGTH)
        @ConfigEntry.Gui.Tooltip
        private int maxStringLength = DEFAULT_MAX_STRING_LENGTH;

        @Override
        public void validatePostLoad() throws ValidationException {
            this.maxMatrixSize = Math.max(this.maxMatrixSize, 2);
            this.maxStringLength = Math.max(this.maxStringLength, 1);
        }

        @Override
        public int getMAX_MATRIX_SIZE() {
            return maxMatrixSize;
        }

        @Override
        public int getMAX_STRING_LENGTH() {
            return maxStringLength;
        }
    }
}
