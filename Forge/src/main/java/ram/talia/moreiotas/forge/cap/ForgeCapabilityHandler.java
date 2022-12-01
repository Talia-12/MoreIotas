package ram.talia.moreiotas.forge.cap;

import at.petrak.hexcasting.forge.cap.HexCapabilities;
import at.petrak.hexcasting.forge.cap.adimpl.CapStaticIotaHolder;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.BooleanSupplier;

import static at.petrak.hexcasting.forge.cap.ForgeCapabilityHandler.IOTA_STATIC_CAP;

public class ForgeCapabilityHandler {

    public static void attachItemCaps(AttachCapabilitiesEvent<ItemStack> event) {
        ItemStack stack = event.getObject();

        if (stack.is(Items.WRITABLE_BOOK)) {
            event.addCapability(IOTA_STATIC_CAP, provide(stack, HexCapabilities.IOTA, () ->
                    new CapStaticIotaHolder(BookHelpers::getWritableIota, stack)));
        } else if (stack.is(Items.WRITTEN_BOOK)) {
            event.addCapability(IOTA_STATIC_CAP, provide(stack, HexCapabilities.IOTA, () ->
                    new CapStaticIotaHolder(BookHelpers::getWrittenIota, stack)));
        }
    }

    private static <CAP> SimpleProvider<CAP> provide(ItemStack stack, Capability<CAP> capability, NonNullSupplier<CAP> supplier) {
        Objects.requireNonNull(stack);
        return provide(stack::isEmpty, capability, supplier);
    }

    private static <CAP> SimpleProvider<CAP> provide(BooleanSupplier invalidated, Capability<CAP> capability, NonNullSupplier<CAP> supplier) {
        return new SimpleProvider<>(invalidated, capability, LazyOptional.of(supplier));
    }

    private record SimpleProvider<CAP>(BooleanSupplier invalidated, Capability<CAP> capability, LazyOptional<CAP> instance) implements ICapabilityProvider {

        public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            if (this.invalidated.getAsBoolean()) {
                return LazyOptional.empty();
            } else {
                return cap == this.capability ? this.instance.cast() : LazyOptional.empty();
            }
        }

        public BooleanSupplier invalidated() {
            return this.invalidated;
        }

        public Capability<CAP> capability() {
            return this.capability;
        }

        public LazyOptional<CAP> instance() {
            return this.instance;
        }
    }
}
