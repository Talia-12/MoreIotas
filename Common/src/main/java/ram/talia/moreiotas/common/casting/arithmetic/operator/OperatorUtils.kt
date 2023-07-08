package ram.talia.moreiotas.common.casting.arithmetic.operator

import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapInvalidIota
import ram.talia.moreiotas.api.spell.iota.StringIota

fun Iterator<IndexedValue<Iota>>.nextString(argc: Int = 0): String {
    val (idx, x) = this.next()
    if (x is StringIota)
        return x.string
    throw MishapInvalidIota.of(x, if (argc == 0) idx else argc - (idx + 1), "string")
}