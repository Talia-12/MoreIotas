package ram.talia.moreiotas.common.casting.arithmetic.operator.string

import at.petrak.hexcasting.api.casting.arithmetic.predicates.IotaMultiPredicate
import at.petrak.hexcasting.api.casting.arithmetic.predicates.IotaPredicate
import at.petrak.hexcasting.api.casting.asActionResult
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import ram.talia.moreiotas.common.casting.arithmetic.operator.nextString
import ram.talia.moreiotas.common.lib.hex.MoreIotasIotaTypes.STRING
import ram.talia.moreiotas.common.lib.hex.OperatorShim

object OperatorStringIndexOf : OperatorShim(2, IotaMultiPredicate.pair(IotaPredicate.ofType(STRING), IotaPredicate.ofType(STRING))) {
    override fun apply(iotas: Iterable<Iota>, env: CastingEnvironment): Iterable<Iota> {
        val it = iotas.iterator().withIndex()
        val toSearch = it.nextString(arity)
        val searchFor = it.nextString(arity)
        return toSearch.indexOf(searchFor).asActionResult
    }
}