package ram.talia.moreiotas.api.mod

import at.petrak.hexcasting.api.HexAPI
import net.minecraft.resources.ResourceLocation

object MoreIotasConfig {
    interface CommonConfigAccess { }

    interface ClientConfigAccess { }

    interface ServerConfigAccess {
        val MAX_MATRIX_SIZE: Int
        val MAX_STRING_LENGTH: Int

        companion object {
            const val DEFAULT_MAX_MATRIX_SIZE: Int = 144
            const val MAX_MAX_MATRIX_SIZE: Int = 512

            const val DEFAULT_MAX_STRING_LENGTH: Int = 1728
            const val MAX_MAX_STRING_LENGTH: Int = 32768
        }
    }

    // Simple extensions for resource location configs
    fun anyMatch(keys: List<String>, key: ResourceLocation): Boolean {
        for (s in keys) {
            if (ResourceLocation.isValidResourceLocation(s)) {
                val rl = ResourceLocation(s)
                if (rl == key) {
                    return true
                }
            }
        }
        return false
    }

    fun noneMatch(keys: List<String>, key: ResourceLocation): Boolean {
        return !anyMatch(keys, key)
    }

    private object DummyCommon : CommonConfigAccess {  }
    private object DummyClient : ClientConfigAccess {  }
    private object DummyServer : ServerConfigAccess {
        override val MAX_MATRIX_SIZE: Int
            get() = throw IllegalStateException("Attempted to access property of Dummy Config Object")
        override val MAX_STRING_LENGTH: Int
            get() = throw IllegalStateException("Attempted to access property of Dummy Config Object")

    }



    @JvmStatic
    var common: CommonConfigAccess = DummyCommon
        set(access) {
            if (field != DummyCommon) {
                HexAPI.LOGGER.warn("CommonConfigAccess was replaced! Old {} New {}",
                        field.javaClass.name, access.javaClass.name)
            }
            field = access
        }

    @JvmStatic
    var client: ClientConfigAccess = DummyClient
        set(access) {
            if (field != DummyClient) {
                HexAPI.LOGGER.warn("ClientConfigAccess was replaced! Old {} New {}",
                        field.javaClass.name, access.javaClass.name)
            }
            field = access
        }

    @JvmStatic
    var server: ServerConfigAccess = DummyServer
        set(access) {
            if (field != DummyServer) {
                HexAPI.LOGGER.warn("ServerConfigAccess was replaced! Old {} New {}",
                        field.javaClass.name, access.javaClass.name)
            }
            field = access
        }
}