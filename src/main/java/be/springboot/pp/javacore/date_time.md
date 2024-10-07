# Introduction
The main classes used for date and time formatting in Java are:
- `DateFormat` and `SimpleDateFormat` (old and part of the `java.text` package)
- `DateTimeFormatter` (part of the `java.time` package, introduced in Java 8, influenced by the popular third-party library Joda-Time)

## `java.util.Date` v1.0
The `Date` class was the primary option for handling dates and times in early Java, representing a specific instant of time with millisecond precision as a long value (milliseconds since the Unix epoch, January 1, 1970). The class had several design flaws, such as a lack of separation between date-only and time-only values, providing limited and basic date manipulation functionality, mutability leading to thread-safety issues, and poor support for time zones. Due to these poor design limitations, most of its methods were deprecated in later versions.
#### Common Methods:
- `Date()`: Creates a `Date` object representing the current date and time.
- `getTime()`: Returns the number of milliseconds since the epoch.
- `setTime(long time)`: Sets the date and time in milliseconds.
- `toString()`: Returns a string representation of the date.
```java
import java.util.Date;

public class DateExample {
    public static void main(String[] args) {
        Date currentDate = new Date();
        System.out.println("Current Date: " + currentDate);

        long currentTimeMillis = currentDate.getTime();
        System.out.println("Milliseconds since epoch: " + currentTimeMillis);
    }
}
```

## `java.text.DateFormat` v1.1
It is an abstract class for formatting and parsing dates and times in a locale-sensitive manner but is not thread-safe. It can convert `Date` to strings and parses strings into `Date` but has limited flexibility. It supports various date styles like SHORT, MEDIUM, LONG, and FULL, and can handle both dates and times.
#### Common Methods:
- `getDateInstance(int style)`: Returns a `DateFormat` object for formatting dates.
- `format(Date date)`: Formats a date into a string.
- `parse(String source)`: Parses a string into a date.
```java
import java.text.DateFormat;
import java.util.Date;

public class DateFormatExample {
    public static void main(String[] args) {
        Date currentDate = new Date();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
        String formattedDate = dateFormat.format(currentDate);
        System.out.println("Formatted Date: " + formattedDate);
    }
}
```

## `java.text.SimpleDateFormat` v1.1
`SimpleDateFormat` was introduced to assist with parsing and formatting the `Date` object. It is a concrete subclass of `DateFormat` that allows for locale-sensitive customizable pattern-based (e.g., "yyyy-MM-dd") date and time formatting and parsing, but it too suffered from thread-safety problems.

#### Pattern Symbols:
- y: Year (yyyy for 2024)
- M: Month (MM for 08, MMM for Aug, MMMM for August)
- d: Day of the month (dd for 10)
- H: Hour in 24-hour format (HH for 16)
- h: Hour in 12-hour format (hh for 4)
- m: Minute (mm for 05)
- s: Second (ss for 09)
- a: AM/PM marker
- z: Time zone (zzz for PDT)
```java
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatExample {
    public static void main(String[] args) {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(currentDate);
        System.out.println("Formatted Date: " + formattedDate);

        try {
            Date parsedDate = sdf.parse("2024-08-10 14:30:00");
            System.out.println("Parsed Date: " + parsedDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

## `java.util.Calendar` v1.5
It's an abstract class with `GregorianCalendar` being the most common implementation that provides better control over date and time manipulation than `Date` by providing methods for converting between specific instant in time, supports different calendar systems, handles logical calendar fields like `YEAR`, `MONTH`, `DAY_OF_MONTH`, `HOUR`, `MINUTE`, etc., allowing more complex operations like adding or subtracting days and months. However, it remained mutable and suffered from usage complexity due to less intuitive API and heavy legacy baggage, making it cumbersome with thread safety issues and localization challenges. While `Calendar` was an improvement over the `Date` class, it still relied on `SimpleDateFormat` for formatting, making it more powerful but error-prone and difficult to use.
#### Common Methods:
- `getInstance()`: Returns a `Calendar` object initialized with the current date and time.
- `get(int field)`: Returns the value of the given calendar field.
- `set(int field, int value)`: Sets the value of a specific calendar field.
- `add(int field, int amount)`: Adds or subtracts time from a specific field.
- `getTime()`: Converts the `Calendar` object to a `Date` object.
```java
import java.util.Calendar;

public class CalendarExample {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        System.out.println("Current Date: " + calendar.getTime());

        calendar.add(Calendar.DAY_OF_MONTH, 5);
        System.out.println("Date after 5 days: " + calendar.getTime());

        int year = calendar.get(Calendar.YEAR);
        System.out.println("Year: " + year);
    }
}
```

## `java.time.LocalDate` v1.8
`LocalDate` represents a date without a time or time zone. It's ideal for representing dates like birthdays, anniversaries, or any date-based event.
#### Key Features:
- Immutable and thread-safe.
- Represents only the date, e.g., `2024-08-10`.
- Useful for scenarios where only the date is needed, without concern for time or time zone.
#### Common Methods:
- `now()`: Returns the current date.
- `of(int year, int month, int dayOfMonth)`: Creates an instance for a specific date.
- `plusDays(long daysToAdd)`, `minusDays(long daysToSubtract)`: Adds or subtracts days.
- `getYear()`, `getMonth()`, `getDayOfMonth()`: Extracts parts of the date.
- `isBefore(LocalDate otherDate)`, `isAfter(LocalDate otherDate)`: Compares dates.
```java
import java.time.LocalDate;

public class LocalDateExample {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("Today's Date: " + today);

        LocalDate specificDate = LocalDate.of(2022, 10, 1);
        System.out.println("Specific Date: " + specificDate);

        LocalDate nextWeek = today.plusDays(7);
        System.out.println("Next Week: " + nextWeek);
    }
}
```

## `java.time.LocalTime` v1.8
`LocalTime` represents time without a date and without a time zone. It's useful for scenarios where you need to work with time alone, such as setting an alarm, train scheduler or tracking store hours.
#### Key Features:
- Immutable and thread-safe.
- Represents only the time, e.g., `14:30:00`.
- Ideal for representing times like office hours, meal times, etc.
#### Common Methods:
- `now()`: Returns the current time.
- `of(int hour, int minute)`: Creates an instance for a specific time.
- `plusHours(long hoursToAdd)`, `minusMinutes(long minutesToSubtract)`: Adjusts the time.
- `getHour()`, `getMinute()`, `getSecond()`: Extracts parts of the time.
- `isBefore(LocalTime otherTime)`, `isAfter(LocalTime otherTime)`: Compares times.
```java
import java.time.LocalTime;

public class LocalTimeExample {
    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        System.out.println("Current Time: " + now);

        LocalTime startOfDay = LocalTime.of(9, 0);
        System.out.println("Start of Day: " + startOfDay);

        LocalTime meetingTime = now.plusHours(1);
        System.out.println("Meeting Time: " + meetingTime);
    }
}
```

## `java.time.LocalDateTime` v1.8
`LocalDateTime` combines both `LocalDate` and `LocalTime` into a single class, representing both date and time without a time zone. Suitable for event scheduling, timestamps, etc.
#### Key Features:
- Immutable and thread-safe.
- Represents both date and time, e.g., `2024-08-10T14:30:00`.
- Useful when you need to work with both date and time, but without time zone information.
#### Common Methods:
- `now()`: Returns the current date and time.
- `of(int year, int month, int dayOfMonth, int hour, int minute)`: Creates an instance for a specific date and time.
- `plusDays(long daysToAdd)`, `minusHours(long hoursToSubtract)`: Adjusts the date and time.
- `toLocalDate()`, `toLocalTime()`: Extracts the date or time part.
- `isBefore(LocalDateTime otherDateTime)`, `isAfter(LocalDateTime otherDateTime)`: Compares date and time.
```java
import java.time.LocalDateTime;

public class LocalDateTimeExample {
    public static void main(String[] args) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Current Date and Time: " + currentDateTime);

        LocalDateTime specificDateTime = LocalDateTime.of(2024, 8, 10, 14, 30);
        System.out.println("Specific Date and Time: " + specificDateTime);

        LocalDateTime tomorrowSameTime = currentDateTime.plusDays(1);
        System.out.println("Tomorrow at the same time: " + tomorrowSameTime);
    }
}
```

## `java.time.DateTimeFormatter` v1.8
`DateTimeFormatter` is used to format and parse date-time objects. It provides a way to customize how dates and times are displayed or interpreted from strings.
#### Key Features:
- Supports predefined and custom patterns for formatting and parsing.
- Works with `LocalDate`, `LocalTime`, `LocalDateTime`, and other date-time classes.
- Thread-safe and immutable.
#### Common Methods:
- `ofPattern(String pattern)`: Creates a formatter with a custom pattern.
- `format(TemporalAccessor temporal)`: Formats a date-time object into a string.
- `parse(CharSequence text)`: Parses a string into a date-time object.
```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterExample {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDateTime = dateTime.format(formatter);
        System.out.println("Formatted Date and Time: " + formattedDateTime);

        LocalDateTime parsedDateTime = LocalDateTime.parse("10-08-2024 14:30:00", formatter);
        System.out.println("Parsed Date and Time: " + parsedDateTime);
    }
}
```
#### Pattern symbols
- yyyy: Year
- MM: Month (two digits)
- dd: Day of the month
- HH: Hour (24-hour clock)
- mm: Minute
- ss: Second
- SSS: Millisecond
- a: AM/PM marker
- VV: Time zone ID
#### Predefined Formatters
Java provides several predefined formatters in the DateTimeFormatter class:
- DateTimeFormatter.ISO_LOCAL_DATE
- DateTimeFormatter.ISO_LOCAL_TIME
- DateTimeFormatter.ISO_LOCAL_DATE_TIME
- DateTimeFormatter.ISO_ZONED_DATE_TIME

## `java.time.ZonedDateTime` v1.8
It combines a LocalDateTime with a time zone (ZoneId). It represents a date and time with full time zone rules, making it aware of Daylight Saving Time (DST) and other time zone transitions.
#### Key Characteristics:
- It is the most comprehensive date-time class in the java.time package.
- Useful for applications that need to consider time zone differences and DST.
#### Common Methods:
- `now()`: Gets the current date and time with the default time zone.
- `withZoneSameInstant(ZoneId zone)`: Converts this ZonedDateTime to a different time zone, keeping the same instant.
- `toLocalDateTime()`: Converts this ZonedDateTime to a LocalDateTime.
- `toOffsetDateTime()`: Converts this ZonedDateTime to an OffsetDateTime.
- `getZone()`: Retrieves the time zone (ZoneId) of this ZonedDateTime
```java
import java.time.ZonedDateTime;
import java.time.ZoneId;

public class ZonedDateTimeExample {
    public static void main(String[] args) {
        // Current date-time with system default time zone
        ZonedDateTime current = ZonedDateTime.now();
        System.out.println("Current ZonedDateTime: " + current);

        // ZonedDateTime with a specific time zone
        ZonedDateTime nyTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("New York Time: " + nyTime);

        // Convert to a different time zone, preserving the same instant
        ZonedDateTime parisTime = nyTime.withZoneSameInstant(ZoneId.of("Europe/Paris"));
        System.out.println("Paris Time: " + parisTime);
    }
}
```

## `java.time.OffsetDateTime` v1.8
It combines a LocalDateTime with an offset from UTC (e.g., +02:00). It represents a date and time with a fixed offset from UTC, but without any time zone information.
#### Key Characteristics:
- It includes a ZoneOffset, which represents the difference between the local time and UTC.
- Useful for scenarios where you need to store a date and time with a specific offset, but not necessarily in a specific time zone
#### Common Methods:
- `now()`: Gets the current date and time from the system clock with the default time-zone offset.
- `withOffsetSameInstant(ZoneOffset offset)`: Adjusts this OffsetDateTime to a different offset, keeping the same instant.
- `toLocalDateTime()`: Converts this OffsetDateTime to a LocalDateTime.
- `toInstant()`: Converts this OffsetDateTime to an Instant
```java
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class OffsetDateTimeExample {
    public static void main(String[] args) {
        // Current date-time with offset
        OffsetDateTime current = OffsetDateTime.now();
        System.out.println("Current OffsetDateTime: " + current);

        // Create OffsetDateTime with a specific offset
        OffsetDateTime customOffset = OffsetDateTime.of(2024, 8, 10, 10, 0, 0, 0, ZoneOffset.ofHours(-5));
        System.out.println("Custom OffsetDateTime: " + customOffset);

        // Convert to Instant
        System.out.println("As Instant: " + customOffset.toInstant());
    }
}
```

## `java.time.Instant` v1.8
It represents a specific point on the timeline in GMT time zone. Defined as the number of nanoseconds since the Unix epoch (January 1, 1970, 00:00:00 UTC). It is the closest class in Java to the concept of "machine time" or "absolute time". Only `ZonedDateTime` can be converted to instant as it gives complete timestamp which is universally recognizable.
#### Key Characteristics:
- Instant is immutable and thread-safe.
- It has no concept of time zones or human calendar systems (e.g., years, months, days).
- Often used for timestamping events.
#### Common Methods:
- `now()`: Returns the current instant from the system clock.
- `ofEpochSecond(long epochSecond)`: Creates an Instant from the given number of seconds since the epoch.
- `plusSeconds(long secondsToAdd)`: Returns a copy of this instant with the specified duration added.
- `toEpochMilli()`: Converts this instant to the number of milliseconds from the epoch.
```java
import java.time.Instant;

public class InstantExample {
    public static void main(String[] args) {
        // Current instant
        Instant now = Instant.now();
        System.out.println("Current Instant: " + now);

        // Instant from a specific epoch second
        Instant epoch = Instant.ofEpochSecond(0);
        System.out.println("Epoch Instant: " + epoch);

        // Instant after adding 10 seconds
        Instant later = now.plusSeconds(10);
        System.out.println("Later Instant: " + later);

        // Convert to milliseconds since epoch
        long millis = now.toEpochMilli();
        System.out.println("Milliseconds since epoch: " + millis);
    }
}
```

## `java.time.temporal.TemporalAdjusters` v1.8
It provides a set of utilities to adjust date and time objects. These adjusters allow you to perform common date-time calculations in a readable and efficient way, like finding the next Monday, the last day of the month, or the first day of the year.
### `Temporal` interface
It is the base interface for date-time types that can be adjusted using `TemporalAdjuster`. Classes like `LocalDate`, `LocalDateTime`, `ZonedDateTime`, `OffsetDateTime`, etc., implement this interface.
### `TemporalAdjuster` interface
It is a functional interface that can be implemented to adjust a temporal object (e.g., a LocalDate) according to some logic. It provides static methods returning common TemporalAdjuster implementations.
#### Common Temporal Adjusters
- `firstDayOfMonth()`: Adjusts the date to the first day of the current month.
- `lastDayOfMonth()`: Adjusts the date to the last day of the current month.
- `firstDayOfYear()`: Adjusts the date to the first day of the current year.
- `lastDayOfYear()`: Adjusts the date to the last day of the current year.
- `next(DayOfWeek dayOfWeek)`: Adjusts the date to the next occurrence of the specified day of the week.
- `previous(DayOfWeek dayOfWeek)`: Adjusts the date to the previous occurrence of the specified day of the week.
- `nextOrSame(DayOfWeek dayOfWeek)`: Adjusts the date to the next occurrence of the specified day, or returns the same date if it already falls on that day.
- `previousOrSame(DayOfWeek dayOfWeek)`: Adjusts the date to the previous occurrence of the specified day, or returns the same date if it already falls on that day.
- `firstInMonth(DayOfWeek dayOfWeek)`: Adjusts the date to the first occurrence of the specified day of the week in the current month.
- `lastInMonth(DayOfWeek dayOfWeek)`: Adjusts the date to the last occurrence of the specified day of the week in the current month.
- `dayOfWeekInMonth(int ordinal, DayOfWeek dayOfWeek)`: Adjusts the date to the nth occurrence of the specified day of the week in the current month (e.g., the second Friday).
```java
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;

public class NextMondayExample {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();

        LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println("Next Monday: " + nextMonday);

        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("Last Day of the Month: " + lastDayOfMonth);

        LocalDate firstFriday = today.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY));
        System.out.println("First Friday of the Month: " + firstFriday);

        LocalDate secondSaturday = today.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.SATURDAY));
        System.out.println("Second Saturday of the Month: " + secondSaturday);
    }
}
```
### Custom Temporal Adjusters
You can also create custom TemporalAdjuster implementations using a lambda expression or by implementing the TemporalAdjuster interface.
```java
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;

public class CustomAdjusterExample {
    public static void main(String[] args) {
        TemporalAdjuster nextWorkingDay = temporal -> {
            LocalDate date = LocalDate.from(temporal);
            DayOfWeek dow = date.getDayOfWeek();
            int daysToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {
                daysToAdd = 3;
            } else if (dow == DayOfWeek.SATURDAY) {
                daysToAdd = 2;
            }
            return date.plus(daysToAdd, ChronoUnit.DAYS);
        };

        LocalDate today = LocalDate.now();
        LocalDate nextWorkingDayDate = today.with(nextWorkingDay);
        System.out.println("Next Working Day: " + nextWorkingDayDate);
    }
}
```

## `java.time.Period` v1.8
It represents a date-based amount of time in terms of years, months, and days. Use it when date manipulation is involved.
### Example
```java
LocalDate birthDate = LocalDate.of(1990, 10, 4);
LocalDate currentDate = LocalDate.now();
Period age = Period.between(birthDate, currentDate);
System.out.println("Age: " + age.getYears() + " years, " +
                   age.getMonths() + " months, " +
                   age.getDays() + " days");
```
### Creation
```java
Period period1 = Period.ofYears(2);  // 2 years
Period period2 = Period.ofMonths(3); // 3 months
Period periodW = Period.ofWeek(2);   // 2 weeks
Period periodD = Period.ofDays(2);   // 2 days
Period period3 = Period.of(1, 6, 15); // 1 year, 6 months, and 15 days
Period periodC = Period.of(0, 3, 0); // 3 months
System.out.println(periodC); // Output: P3M

LocalDate start = LocalDate.of(2020, 1, 1);
LocalDate end = LocalDate.of(2023, 3, 15);
Period periodBetween = Period.between(start, end);
System.out.println(periodBetween);  // Output: P3Y2M14D: This gives a period of 3 years, 2 months, and 14 days.

// Zero period
Period zeroPeriod = Period.ZERO;
System.out.println(zeroPeriod);  // Output: P0D (0 days)

// negative period
Period negativePeriod = Period.of(-1, -2, -10);
System.out.println(negativePeriod);  // Output: P-1Y-2M-10D
```
### Accessing
```java
Period period = Period.of(1, 2, 10);  // 1 year, 2 months, and 10 days
System.out.println("Years: " + period.getYears());  // Output: Years: 1
System.out.println("Months: " + period.getMonths());  // Output: Months: 2
System.out.println("Days: " + period.getDays());  // Output: Days: 10
```
### Period and Date arithmetics
```java
// addition of period in date
LocalDate date = LocalDate.of(2022, 1, 1);
Period period = Period.of(2, 3, 5);  // 2 years, 3 months, and 5 days
LocalDate newDate = date.plus(period);
System.out.println(newDate);  // Output: 2024-04-06

// addition of period in period
Period period1 = Period.of(1, 2, 15);  // 1 year, 2 months, 15 days
Period period2 = Period.of(2, 4, 10);  // 2 years, 4 months, 10 days
Period totalPeriod = period1.plus(period2);
System.out.println(totalPeriod);  // Output: P3Y6M25D

// subtraction of period from date
LocalDate date = LocalDate.of(2022, 1, 1);
Period period = Period.of(1, 2, 10);  // 1 year, 2 months, and 10 days
LocalDate newDate = date.minus(period);
System.out.println(newDate);  // Output: 2020-10-22

// subtraction of period from period
Period period = Period.of(1, 2, 3);
Period negated = period.negated();  // Inverts the period to -1 year, -2 months, -3 days
System.out.println(negated);  // Output: P-1Y-2M-3D
```
### Normalization
```java
Period period = Period.of(0, 14, 0);
System.out.println(period);  // Output: P1Y2M (1 year and 2 months)
```
### Parsing
```java
Period Period.parse(CharSequence text);
Period parsedPeriod = Period.parse("P2Y3M5D");  // Represents a period of 2 years, 3 months, and 5 days
System.out.println(parsedPeriod);  // Output: P2Y3M5D
```
### comparison
```java
Period period1 = Period.of(1, 2, 15);
Period period2 = Period.of(1, 2, 15);
System.out.println(period1.equals(period2));  // Output: true
```

## `java.time.Duration` v1.8
It represents a time-based amount of time in terms of days, hours, minutes, seconds and nanoseconds.
### Creation
```java
Duration duration1 = Duration.ofDays(1);     // 1 day
Duration duration2 = Duration.ofHours(5);    // 5 hours
Duration durationM = Duration.ofMinutes(3);  // 3 minutes  
Duration duration3 = Duration.ofSeconds(60); // 60 seconds
Duration durationL = Duration.ofMinutes(20); // 20 milliseconds
Duration durationN = Duration.ofNanos(400);  // 400 nanoseconds

Duration duration1 = Duration.of(1, ChronoUnit.DAYS);     // 1 day
Duration duration2 = Duration.of(5, ChronoUnit.HOURS);    // 5 hours
Duration durationM = Duration.of(3, ChronoUnit.MINUTES);  // 3 minutes
Duration duration3 = Duration.of(30, ChronoUnit.SECONDS); // 30 seconds
Duration durationL = Duration.of(20, ChronoUnit.MILLIS);  // 20 milliseconds
Duration durationN = Duration.of(400, ChronoUnit.NANOS);  // 400 nanoseconds

Duration negativeDuration = Duration.ofHours(-2);  // -2 hours
System.out.println(negativeDuration);  // Output: PT-2H

Duration duration = Duration.ofHours(2);
Duration negated = duration.negated();  // Output: PT-2H (negative 2 hours)
System.out.println(negated);

Duration Duration.between(Temporal startInclusive, Temporal endExclusive); // calculates the duration between two temporal objects, such as Instant, LocalTime, or LocalDateTime.
Instant start = Instant.now();
Instant end = start.plusSeconds(3600);  // Add 1 hour
Duration durationBetween = Duration.between(start, end);
System.out.println(durationBetween);  // Output: PT1H (1 hour)
```

### Accessing
Note that these methods return whole values only (e.g., toHours() will truncate the minute and second components).
```java
Duration duration = Duration.ofHours(10);  // 10 hours
System.out.println("Days: " + duration.toDays());      // Output: 0 days
System.out.println("Hours: " + duration.toHours());    // Output: 10 hours
System.out.println("Minutes: " + duration.toMinutes()); // Output: 600 minutes
System.out.println("Seconds: " + duration.toSeconds()); // Output: 3600 seconds
System.out.println("Milli Seconds: " + duration.toMillis()); // Output: 360000 milli seconds
System.out.println("Nano Seconds: " + duration.toNanos()); // Output: 36000000 nano seconds
```
### Duration and Temporal arithmetic
```java
// addition
Duration duration = Duration.ofHours(5);   // 5 hours
Duration newDuration = duration.plusMinutes(30);  // Add 30 minutes
System.out.println(newDuration);  // Output: PT5H30M (5 hours 30 minutes)

// addition with time
LocalDateTime now = LocalDateTime.now();
Duration duration = Duration.ofHours(3);
LocalDateTime newTime = now.plus(duration);  // Add 3 hours to the current time
System.out.println(newTime);

// subtraction
Duration duration = Duration.ofHours(5);   // 5 hours
Duration newDuration = duration.minusMinutes(60);  // Subtract 60 minutes
System.out.println(newDuration);  // Output: PT4H (4 hours)
```
### Comparison
```java
Duration duration1 = Duration.ofHours(5);
Duration duration2 = Duration.ofMinutes(300);  // Equivalent to 5 hours
System.out.println(duration1.equals(duration2));  // Output: true

Duration duration1 = Duration.ofHours(3);
Duration duration2 = Duration.ofHours(5);
System.out.println(duration1.compareTo(duration2));  // Output: -1 (duration1 is shorter)
```
### Parsing
Duration strings use ISO-8601 format. P stands for "period." T separates the time components. H for hours, M for minutes, and S for seconds.
```java
Duration Duration.parse(CharSequence text);

Duration parsedDuration = Duration.parse("PT1H30M");  // Represents 1 hour and 30 minutes
System.out.println(parsedDuration);  // Output: PT1H30M
```
### Conversion
You can convert a duration to different units using `toDays()`, `toHours()`, `toMinutes()`, `toSeconds()`, `toMillis()`, and `toNanos()`.
```java
Duration duration = Duration.ofDays(1);  // 1 day
System.out.println("Hours: " + duration.toHours());    // Output: 24 hours
System.out.println("Minutes: " + duration.toMinutes());  // Output: 1440 minutes
```

### Instant Vs Duration
`Instant` represents a point in time in UTC, while `Duration` represents the amount of time between two instants. Use `Duration` for calculating the elapsed time between two `Instant` objects.
```java
Instant start = Instant.now();
// Simulate some processing by adding delay
Instant end = start.plusSeconds(1000);
Duration elapsed = Duration.between(start, end);
System.out.println(elapsed);  // Output: PT16M40S (16 minutes, 40 seconds)
```
### Period Vs Duration
While both `Period` and `Duration` represent amounts of time. `Period` is date-based (years, months, days). `Duration` is time-based (hours, minutes, seconds, nanoseconds).
### Duration with Java v9 `to...Part()`
Java 9 introduced the `toDaysPart()`, `toHoursPart()`, `toMinutesPart()`, `toSecondsPart()`, `toMillisPart()`, and `toNanosPart()` methods to access specific parts of the duration.
```java
Duration duration = Duration.ofHours(25).plusMinutes(30);  // 25 hours, 30 minutes
System.out.println("Days part: " + duration.toDaysPart());  // Output: 1
System.out.println("Hours part: " + duration.toHoursPart()); // Output: 1
System.out.println("Minutes part: " + duration.toMinutesPart()); // Output: 30
```

## `java.time.temporal.ChronoUnit`
Enum representing unit of time supported in `java.time` package.
### Enum list
```java
public enum ChronoUnit implements TemporalUnit {
    NANOS("Nanos", Duration.ofNanos(1)),
    MICROS("Micros", Duration.ofNanos(1000)),
    MILLIS("Millis", Duration.ofNanos(1000_000)),
    SECONDS("Seconds", Duration.ofSeconds(1)),
    MINUTES("Minutes", Duration.ofSeconds(60)),
    HOURS("Hours", Duration.ofSeconds(3600)),
    HALF_DAYS("HalfDays", Duration.ofSeconds(43200)),
    DAYS("Days", Duration.ofSeconds(86400)),
    WEEKS("Weeks", Duration.ofSeconds(7 * 86400L)),
    MONTHS("Months", Duration.ofSeconds(31556952L / 12)),
    YEARS("Years", Duration.ofSeconds(31556952L)),
    DECADES("Decades", Duration.ofSeconds(31556952L * 10L)),
    CENTURIES("Centuries", Duration.ofSeconds(31556952L * 100L)),
    MILLENNIA("Millennia", Duration.ofSeconds(31556952L * 1000L)),
    ERAS("Eras", Duration.ofSeconds(31556952L * 1000_000_000L)),
    FOREVER("Forever", Duration.ofSeconds(Long.MAX_VALUE, 999_999_999));

    private final String name;
    private final Duration duration;

    private ChronoUnit(String name, Duration estimatedDuration) {
        this.name = name;
        this.duration = estimatedDuration;
    }
}
```
### Temporal arithematics
```java
// addition
LocalDate date = LocalDate.of(2023, 10, 4);
LocalDate newDate = date.plus(2, ChronoUnit.WEEKS);  // Add 2 weeks
System.out.println(newDate);  // Output: 2023-10-18

// subtraction
LocalDate date = LocalDate.of(2023, 10, 4);
LocalDate newDate = date.minus(3, ChronoUnit.DAYS);  // Subtract 3 days
System.out.println(newDate);  // Output: 2023-10-01

// finding elapsed duration
LocalDate startDate = LocalDate.of(2020, 1, 1);
LocalDate endDate = LocalDate.of(2023, 10, 4);
long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
System.out.println(daysBetween);  // Output: 1372 days

long monthsBetween = ChronoUnit.MONTHS.between(startDate, endDate); // we asked for month, hence date got truncated
System.out.println(monthsBetween);  // Output: 45 months

LocalTime time = LocalTime.of(3, 12, 45);
System.out.println(time);   // 03:12:45
LocalTime truncated = time.truncateTo(ChronoUnit.MINUTES);
System.out.println(truncated);   // 03:12
```
### Supported Temporal Types
- **Date-based objects**: `LocalDate`, `YearMonth`, `Year`, etc.
- **Time-based objects**: `LocalTime`, `OffsetTime`, etc.
- **Combined date-time objects**: `LocalDateTime`, `ZonedDateTime`, `OffsetDateTime`, etc.
- **Instant**: A point in time represented in UTC.

## 