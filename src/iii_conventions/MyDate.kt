package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate>
{
    override fun compareTo(other: MyDate): Int
    {
        return when
        {
            year != other.year -> year - other.year
            month != other.month -> month - other.month
            else -> dayOfMonth - other.dayOfMonth
        }
    }
}

operator fun MyDate.rangeTo(other: MyDate) = DateRange(this, other)

enum class TimeInterval
{
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterator<MyDate>
{
    override fun contains(value: MyDate): Boolean
    {
        return super.contains(value);
    }

    private var inRangeValue :MyDate = start
    override fun hasNext(): Boolean
    {
        return inRangeValue <= endInclusive
    }
    override fun next(): MyDate
    {
        val currentValue = inRangeValue
        inRangeValue = inRangeValue.nextDay()
        return currentValue
    }
}
