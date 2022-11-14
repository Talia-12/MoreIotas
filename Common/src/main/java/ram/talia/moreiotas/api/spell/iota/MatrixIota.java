package ram.talia.moreiotas.api.spell.iota;

import at.petrak.hexcasting.api.spell.iota.DoubleIota;
import at.petrak.hexcasting.api.spell.iota.Iota;
import at.petrak.hexcasting.api.spell.iota.IotaType;
import at.petrak.hexcasting.api.utils.HexUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import org.jblas.DoubleMatrix;
import org.jetbrains.annotations.NotNull;
import ram.talia.moreiotas.common.lib.MoreIotasIotaTypes;

public class MatrixIota extends Iota {
    public MatrixIota(@NotNull DoubleMatrix matrix) {
        super(MoreIotasIotaTypes.MATRIX_TYPE, matrix);
    }

    public DoubleMatrix getMatrix() {
        return (DoubleMatrix) this.payload;
    }

    @Override
    protected boolean toleratesOther(Iota that) {
        return false;
    }

    @Override
    public boolean isTruthy() {
        // is true if it has entries, and at least one has abs(entry)>0
        return !this.getMatrix().isEmpty() && this.getMatrix().norm1() > DoubleIota.TOLERANCE;
    }

    @Override
    public @NotNull Tag serialize() {
        return StringTag.valueOf(this.getMatrix().toString("%f", "", "", ", ", "; "));
    }

    public static IotaType<MatrixIota> TYPE = new IotaType<>() {
        @Override
        public MatrixIota deserialize(Tag tag, ServerLevel world) throws IllegalArgumentException {
            var stag = HexUtils.downcast(tag, StringTag.TYPE);

            return new MatrixIota(DoubleMatrix.valueOf(stag.getAsString()));
        }

        @Override
        public Component display(Tag tag) {
            if (!(tag instanceof StringTag stag)) {
                return Component.translatable("hexcasting.spelldata.unknown");
            }
            return Component.literal(stag.getAsString()).withStyle(ChatFormatting.BLUE);
        }

        @Override
        public int color() {
            return 0xff_5555ff;
        }
    };
}
