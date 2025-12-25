package dev.ladibells.utilities

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object SuperUtilities {

    fun toDate(dateString: String): String {
        val date = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE)
        val formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
        return date.format(formatter)
    }
}