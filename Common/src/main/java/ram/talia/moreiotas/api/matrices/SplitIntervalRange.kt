package ram.talia.moreiotas.api.matrices

import org.jblas.ranges.Range

class SplitIntervalRange(private val start: Int, private val end: Int, private val split: Int) : Range {
    private var value: Int = start

    init {
        require(split in start .. end) { "$split must be between $start and $end." }
    }

    override fun init(lower: Int, upper: Int) {
        value = start
        require(!(start < lower || end > upper + 1))
            { "Bounds $lower to $upper are beyond range interval $start to $end." }
    }

    override fun length() = end - start - 1

    override fun next() {
        value++
        if (value == split)
            value++
    }

    override fun index() = if (value > split) value - start - 1 else value - start

    override fun value(): Int = value

    override fun hasMore(): Boolean = value < end
}