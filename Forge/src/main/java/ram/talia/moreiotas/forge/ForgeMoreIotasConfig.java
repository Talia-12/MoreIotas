package ram.talia.moreiotas.forge;


import net.minecraftforge.common.ForgeConfigSpec;
import ram.talia.moreiotas.api.mod.MoreIotasConfig;

public class ForgeMoreIotasConfig implements MoreIotasConfig.CommonConfigAccess {
    public ForgeMoreIotasConfig(ForgeConfigSpec.Builder builder) {

    }

    public static class Client implements MoreIotasConfig.ClientConfigAccess {
        public Client(ForgeConfigSpec.Builder builder) {

        }
    }

    public static class Server implements MoreIotasConfig.ServerConfigAccess {
        private static ForgeConfigSpec.IntValue maxMatrixSize;
        private static ForgeConfigSpec.IntValue maxStringLength;

        public Server(ForgeConfigSpec.Builder builder) {
            builder.push("Spells");
            maxMatrixSize = builder.comment("How large can matrices be")
                    .defineInRange("maxMatrixSize", DEFAULT_MAX_MATRIX_SIZE, 2, MAX_MAX_MATRIX_SIZE);
            maxStringLength = builder.comment("How long can strings be")
                    .defineInRange("maxStringLength", DEFAULT_MAX_STRING_LENGTH, 1, MAX_MAX_STRING_LENGTH);
        }

            @Override
        public int getMAX_MATRIX_SIZE() {
            return maxMatrixSize.get();
        }

        @Override
        public int getMAX_STRING_LENGTH() {
            return maxStringLength.get();
        }
    }
}
