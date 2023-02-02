package ram.talia.moreiotas.api.mod

import at.petrak.hexcasting.api.HexAPI
import net.minecraft.resources.ResourceLocation

object MoreIotasConfig {
    interface CommonConfigAccess { }

    interface ClientConfigAccess { }

    interface ServerConfigAccess {
        val maxMatrixSize: Int
        val maxStringLength: Int

        companion object {
            const val DEFAULT_MAX_MATRIX_SIZE: Int = 144
            const val MIN_MAX_MATRIX_SIZE: Int = 3
            const val MAX_MAX_MATRIX_SIZE: Int = 512
            const val DEFAULT_MAX_STRING_LENGTH: Int = 1728
            const val MIN_MAX_STRING_LENGTH: Int = 1
            const val MAX_MAX_STRING_LENGTH: Int = 32768

            @JvmStatic
            val MAX_MATRIX_SIZE: Int = server?.maxMatrixSize ?: DEFAULT_MAX_MATRIX_SIZE
            @JvmStatic
            val MAX_STRING_LENGTH: Int = server?.maxStringLength ?: DEFAULT_MAX_STRING_LENGTH
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

    @JvmStatic
    var common: CommonConfigAccess? = null
        set(access) {
            if (access == null)
                throw Exception("Attempted to set MoreIotasConfig.common to null.")
            if (field != null) {
                HexAPI.LOGGER.warn("CommonConfigAccess was replaced! Old {} New {}",
                        field!!.javaClass.name, access.javaClass.name)
            }
            field = access
        }

    @JvmStatic
    var client: ClientConfigAccess? = null
        set(access) {
            if (access == null)
                throw Exception("Attempted to set MoreIotasConfig.client to null.")
            if (field != null) {
                HexAPI.LOGGER.warn("ClientConfigAccess was replaced! Old {} New {}",
                        field!!.javaClass.name, access.javaClass.name)
            }
            field = access
        }

    @JvmStatic
    var server: ServerConfigAccess? = null
        set(access) {
            if (access == null)
                throw Exception("Attempted to set MoreIotasConfig.client to null.")
            if (field != null) {
                HexAPI.LOGGER.warn("ServerConfigAccess was replaced! Old {} New {}",
                        field!!.javaClass.name, access.javaClass.name)
            }
            field = access
        }
}