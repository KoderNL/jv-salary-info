package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int RATE = 3;
    private static final int DAY = 1;
    private static final int NULL = 0;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        LocalDate dateFromLocalDate = LocalDate.parse(dateFrom, FORMATTER).minusDays(DAY);
        LocalDate dateToLocalDate = LocalDate.parse(dateTo, FORMATTER).plusDays(DAY);
        int lastIndexOfDataLines = data.length - 1;
        int lastIndexOfNames = names.length - 1;
        int indexOfName = NULL;
        int salary = NULL;
        int linesToWrite = NULL;
        int wroteLines = NULL;

        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());

        for (int i = NULL; i < data.length; i++) {
            String[] currentString = data[i].split(" ");
            LocalDate dateOfCurrentString = LocalDate.parse(currentString[DATE], FORMATTER);
            if (dateOfCurrentString.isAfter(dateFromLocalDate)
                    && dateOfCurrentString.isBefore(dateToLocalDate)) {
                if (indexOfName == NULL) {
                    linesToWrite++;
                }
                if (currentString[NAME].equals(names[indexOfName])) {
                    salary += Integer.parseInt(currentString[HOURS])
                            * Integer.parseInt(currentString[RATE]);
                    wroteLines++;
                }
            }
            if (i == lastIndexOfDataLines && linesToWrite > wroteLines) {
                stringBuilder.append(names[indexOfName]).append(" - ").append(salary)
                        .append(System.lineSeparator());
                salary = NULL;
                i = NULL;
                indexOfName++;
            }
            if (i == lastIndexOfDataLines && linesToWrite == wroteLines
                    && linesToWrite != NULL) {
                stringBuilder.append(names[indexOfName]).append(" - ").append(salary);
            }
            if (i == lastIndexOfDataLines && linesToWrite == NULL && indexOfName < names.length) {
                stringBuilder.append(names[indexOfName]).append(" - ").append(salary);
                if (indexOfName != lastIndexOfNames) {
                    stringBuilder.append(System.lineSeparator());
                }
                indexOfName++;
                i = NULL;
            }
        }
        return stringBuilder.toString();
    }
}
