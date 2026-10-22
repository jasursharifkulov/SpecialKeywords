package task01

fun parseStatus(text: String): OrderStatus? =
    OrderStatus.entries.firstOrNull { it.name.equals(text.trim(), ignoreCase = true) }
