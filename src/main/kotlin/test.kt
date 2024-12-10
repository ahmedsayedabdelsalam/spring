import java.time.Instant
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.TimeZone

fun main() {
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tokyo"));
    println(LocalDateTime.now())
    println(ZonedDateTime.now())
    println(Instant.now())
}