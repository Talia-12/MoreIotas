package ram.talia.moreiotas.api.spell.iota;

import at.petrak.hexcasting.api.spell.iota.Iota;
import at.petrak.hexcasting.api.spell.iota.IotaType;
import at.petrak.hexcasting.api.utils.HexUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;
import ram.talia.moreiotas.common.lib.MoreIotasIotaTypes;

public class StringIota extends Iota {
    public StringIota(@NotNull String string) {
        super(MoreIotasIotaTypes.STRING_TYPE, string);
    }

    public String getString() {
        return (String) this.payload;
    }

    @Override
    protected boolean toleratesOther(Iota that) {
        return typesMatch(this, that)
                && that instanceof StringIota sthat
                && this.getString().equals(sthat.getString());
    }

    @Override
    public boolean isTruthy() {
        return !this.getString().isEmpty();
    }

    @Override
    public @NotNull Tag serialize() {
        return StringTag.valueOf(this.getString());
    }

    public static IotaType<StringIota> TYPE = new IotaType<>() {
        @Override
        public StringIota deserialize(Tag tag, ServerLevel world) throws IllegalArgumentException {
            var stag = HexUtils.downcast(tag, StringTag.TYPE);

            return new StringIota(stag.getAsString());
        }

        @Override
        public Component display(Tag tag) {
            if (!(tag instanceof StringTag stag)) {
                return Component.translatable("hexcasting.spelldata.unknown");
            }
            return Component.literal(stag.getAsString()).withStyle(ChatFormatting.LIGHT_PURPLE);
        }

        @Override
        public int color() {
            return 0xff_ff55ff;
        }
    };
}
