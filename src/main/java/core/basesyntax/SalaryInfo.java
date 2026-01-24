package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_OF_WORK = 0;
    private static final int EMPLOYEE_NAME = 1;
    private static final int WORKING_HOURS_PER_DAY = 2;
    private static final int SALARY_RATE = 3;
    private static final int ONE_DAY = 1;
    private static final String SEPARATOR = " - ";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        LocalDate dateFromLocalDate = LocalDate.parse(dateFrom, FORMATTER).minusDays(ONE_DAY);
        LocalDate dateToLocalDate = LocalDate.parse(dateTo, FORMATTER).plusDays(ONE_DAY);
        int lastIndexOfDataLines = data.length - 1;
        int lastIndexOfNames = names.length - 1;
        int indexOfName = 0;
        int salary = 0;
        int linesToWrite = 0;
        int wroteLines = 0;

        stringBuilder.append("Report for period ").append(dateFrom).append(SEPARATOR).append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < data.length; i++) {
            String[] currentString = data[i].split(" ");
            LocalDate dateOfCurrentString = LocalDate.parse(currentString[DATE_OF_WORK], FORMATTER);
            if (dateOfCurrentString.isAfter(dateFromLocalDate)
                    && dateOfCurrentString.isBefore(dateToLocalDate)) {
                if (indexOfName == 0) {
                    linesToWrite++;
                }
                if (currentString[EMPLOYEE_NAME].equals(names[indexOfName])) {
                    salary += Integer.parseInt(currentString[WORKING_HOURS_PER_DAY])
                            * Integer.parseInt(currentString[SALARY_RATE]);
                    wroteLines++;
                }
            }
            if (i == lastIndexOfDataLines && linesToWrite > wroteLines) {
                stringBuilder.append(names[indexOfName]).append(SEPARATOR).append(salary)
                        .append(System.lineSeparator());
                salary = 0;
                i = 0;
                indexOfName++;
            }
            if (i == lastIndexOfDataLines && linesToWrite == wroteLines
                    && linesToWrite != 0) {
                stringBuilder.append(names[indexOfName]).append(SEPARATOR).append(salary);
            }
            if (i == lastIndexOfDataLines && linesToWrite == 0 && indexOfName < names.length) {
                stringBuilder.append(names[indexOfName]).append(SEPARATOR).append(salary);
                if (indexOfName != lastIndexOfNames) {
                    stringBuilder.append(System.lineSeparator());
                }
                indexOfName++;
                i = 0;
            }
        }
        return stringBuilder.toString();
    }
}
