package ram.talia.moreiotas.api.spell.iota;

import at.petrak.hexcasting.api.spell.iota.DoubleIota;
import at.petrak.hexcasting.api.spell.iota.Iota;
import at.petrak.hexcasting.api.spell.iota.IotaType;
import at.petrak.hexcasting.api.utils.HexUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.*;
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
        var tag = new CompoundTag();

        var mat = this.getMatrix();

        tag.putInt(TAG_ROWS, mat.rows);
        tag.putInt(TAG_COLS, mat.columns);

        var list = new ListTag();

        for (int i = 0; i < mat.rows; i++) {
            var curList = new ListTag();
            for (int j = 0; j < mat.columns; j++) {
                curList.add(DoubleTag.valueOf(mat.get(i, j)));
            }
            list.add(curList);
        }

        tag.put(TAG_MAT, list);
        return tag;
    }

    public static IotaType<MatrixIota> TYPE = new IotaType<>() {
        @Override
        public MatrixIota deserialize(Tag tag, ServerLevel world) throws IllegalArgumentException {
            var ctag = HexUtils.downcast(tag, CompoundTag.TYPE);
            return new MatrixIota(deserialise(ctag));
        }

        @Override
        public Component display(Tag tag) {
            if (!(tag instanceof CompoundTag ctag)) {
                return Component.translatable("hexcasting.spelldata.unknown");
            }

            DoubleMatrix mat;
            try {
                mat = deserialise(ctag);
            } catch (IllegalArgumentException e) {
                return Component.translatable("hexcasting.spelldata.unknown");
            }

            return Component.literal(mat.toString()).withStyle(ChatFormatting.BLUE);
        }

        private DoubleMatrix deserialise(CompoundTag ctag) throws IllegalArgumentException {

            if (!ctag.contains(TAG_ROWS) || !ctag.contains(TAG_COLS) || !ctag.contains(TAG_MAT))
                throw new IllegalArgumentException("expected tags \"rows\": int, \"cols\": int, and \"mat\": list(list(double))");

            int rows = ctag.getInt(TAG_ROWS);
            int cols = ctag.getInt(TAG_COLS);

            var mat = new DoubleMatrix(rows, cols);

            var list = ctag.getList(TAG_MAT, Tag.TAG_LIST);

            for (int i = 0; i < rows; i++) {
                var curList = list.getList(i);
                for (int j = 0; j < cols; j++) {
                    mat.put(i, j, curList.getDouble(j));
                }
            }

            return mat;
        }

        @Override
        public int color() {
            return 0xff_5555ff;
        }
    };

    private static final String TAG_ROWS = "rows";
    private static final String TAG_COLS = "cols";
    private static final String TAG_MAT = "mat";
}
