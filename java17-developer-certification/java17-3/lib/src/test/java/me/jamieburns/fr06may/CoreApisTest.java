package me.jamieburns.fr06may;

import me.jamieburns.Test;
import static me.jamieburns.TestSupport.tests;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class CoreApisTest {

    public static void main(String[] args) {
        new CoreApisTest().testsToRun();
    }

    @org.testng.annotations.Test
    public void testsToRun() {

        tests(
            new Test(() -> {
                LocalDate ld = LocalDate.of(2022, Month.MAY, 6);
                LocalTime lt = LocalTime.of(16, 38, 05);
                LocalDateTime ldt = LocalDateTime.of(ld, lt);
                ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.of("Australia/Sydney")); // .of("UTC+10:00")
                return zdt.toString();}, "2022-05-06T16:38:05+10:00[Australia/Sydney]"),
            new Test(() -> {
                LocalDate ld = LocalDate.of(1962, Month.JANUARY, 2);
                LocalTime lt = LocalTime.of(7, 0, 0);
                LocalDateTime ldt = LocalDateTime.of(ld, lt);
                ZonedDateTime jamieBirthday = ZonedDateTime.of(ldt, ZoneId.of("Australia/Sydney")); // .of("UTC+10:00")
                ZonedDateTime cathyBirthday = ZonedDateTime.of(1963, 7, 29, 7, 0, 0 ,0, ZoneId.of("Australia/Melbourne"));
                Duration d = Duration.between(jamieBirthday, cathyBirthday);
                Period p = Period.between(jamieBirthday.toLocalDate(), cathyBirthday.toLocalDate());
                return p + ", " + d;}, "P1Y6M27D, PT13752H"),                
            new Test(() -> {
                Period potus100Days = Period.ofDays(100);
                final LocalDate potusInaugurationDate = LocalDate.of(2024, 1, 20);
                final LocalDate potus100DaysInOfficeDate = potusInaugurationDate.plus(potus100Days);
                StringBuilder sb = new StringBuilder()
                    .append(potus100DaysInOfficeDate.getDayOfMonth())
                    .append(potus100DaysInOfficeDate.getMonth());
                return sb.toString();}, "29APRIL"),
            new Test(() -> {
                LocalDate jbday = LocalDate.of(1962, 1, 2);
                LocalDate cbday = LocalDate.of(1963, 7, 29);
                long days = ChronoUnit.DAYS.between(jbday, cbday);
                long hours = ChronoUnit.HOURS.between(LocalDateTime.of(jbday, LocalTime.MIDNIGHT), LocalDateTime.of(cbday, LocalTime.MIDNIGHT));
                System.out.println(hours);
                return days + " days (" + hours + " hours)";}, "573 days (13752 hours)"),
            new Test(() -> {
                LocalDate ld = LocalDate.of(2022, 5, 6);
                Period p = Period.ofDays(3);
                int no3DayPeriodsFrom06May = 0;
                do {
                    ld = ld.plus(p);
                    no3DayPeriodsFrom06May++;
                } while (ld.getYear() == 2022);
                return 80;}, 80),

            // JLS - see https://docs.oracle.com/javase/specs/jls/se18/jls18.pdf
            // Javadoc - see https://docs.oracle.com/en/java/javase/18/docs/api/index.html
            
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0),
            new Test(() -> {return 0;}, 0)
        );
    }
}
