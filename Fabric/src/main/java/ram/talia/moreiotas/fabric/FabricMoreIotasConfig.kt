package ram.talia.moreiotas.fabric

import at.petrak.hexcasting.api.mod.HexConfig
import at.petrak.hexcasting.fabric.FabricHexConfig
import at.petrak.hexcasting.xplat.IXplatAbstractions
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.annotation.ConfigEntry
import me.shedaniel.autoconfig.annotation.ConfigEntry.BoundedDiscrete
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Tooltip
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.TransitiveObject
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer
import me.shedaniel.autoconfig.serializer.PartitioningSerializer
import ram.talia.moreiotas.api.MoreIotasAPI
import ram.talia.moreiotas.api.mod.MoreIotasConfig
import ram.talia.moreiotas.api.mod.MoreIotasConfig.ServerConfigAccess.Companion.DEFAULT_MAX_MATRIX_SIZE
import ram.talia.moreiotas.api.mod.MoreIotasConfig.ServerConfigAccess.Companion.DEFAULT_MAX_STRING_LENGTH
import ram.talia.moreiotas.api.mod.MoreIotasConfig.ServerConfigAccess.Companion.MAX_MAX_MATRIX_SIZE
import ram.talia.moreiotas.api.mod.MoreIotasConfig.ServerConfigAccess.Companion.MAX_MAX_STRING_LENGTH

@Config(name = MoreIotasAPI.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/calcite.png")
class FabricMoreIotasConfig : PartitioningSerializer.GlobalData() {
    @ConfigEntry.Category("common")
    @TransitiveObject
    private val common = Common()

    @ConfigEntry.Category("client")
    @TransitiveObject
    private val client = Client()

    @ConfigEntry.Category("server")
    @TransitiveObject
    private val server = Server()


    companion object {
        @JvmStatic
        fun setup() {
            AutoConfig.register(FabricMoreIotasConfig::class.java, PartitioningSerializer.wrap
            { definition: Config, configClass: Class<ConfigData> -> JanksonConfigSerializer(definition, configClass) })
            val instance = AutoConfig.getConfigHolder(FabricMoreIotasConfig::class.java).config
            MoreIotasConfig.common = instance.common
            // We care about the client only on the *physical* client ...
            if (IXplatAbstractions.INSTANCE.isPhysicalClient) {
                MoreIotasConfig.client = instance.client
            }
            // but we care about the server on the *logical* server
            // i believe this should Just Work without a guard? assuming we don't access it from the client ever
            MoreIotasConfig.server = instance.server
        }
    }


    @Config(name = "common")
    private class Common : MoreIotasConfig.CommonConfigAccess, ConfigData { }

    @Config(name = "client")
    private class Client : MoreIotasConfig.ClientConfigAccess, ConfigData { }

    @Config(name = "server")
    private class Server : MoreIotasConfig.ServerConfigAccess, ConfigData {

        @BoundedDiscrete(min = 0, max = MAX_MAX_MATRIX_SIZE.toLong())
        @Tooltip
        override val MAX_MATRIX_SIZE = DEFAULT_MAX_MATRIX_SIZE

        @BoundedDiscrete(min = 0, max = MAX_MAX_STRING_LENGTH.toLong())
        @Tooltip
        override val MAX_STRING_LENGTH = DEFAULT_MAX_STRING_LENGTH
    }
}