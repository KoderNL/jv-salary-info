package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_OF_WORK = 0;
    private static final int EMPLOYEE_NAME = 1;
    private static final int WORKING_HOURS_PER_DAY = 2;
    private static final int SALARY_RATE = 3;
    private static final String SEPARATOR = " - ";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        LocalDate dateFromLocalDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToLocalDate = LocalDate.parse(dateTo, FORMATTER);
        int[] salaries = new int[names.length];
        int lastIndexOfNames = names.length - 1;

        stringBuilder.append("Report for period ").append(dateFrom).append(SEPARATOR).append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            for (String dataLine : data) {
                String[] currentString = dataLine.split(" ");
                LocalDate dateForValidation = LocalDate
                        .parse(currentString[DATE_OF_WORK], FORMATTER);
                if (!dateForValidation.isBefore(dateFromLocalDate)
                        && !dateForValidation.isAfter(dateToLocalDate)) {
                    if (names[i].equals(currentString[EMPLOYEE_NAME])) {
                        salaries[i] += Integer.parseInt(currentString[WORKING_HOURS_PER_DAY])
                                * Integer.parseInt(currentString[SALARY_RATE]);
                    }
                }
            }
            stringBuilder.append(names[i]).append(SEPARATOR).append(salaries[i]);
            if (i != lastIndexOfNames) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }
}
