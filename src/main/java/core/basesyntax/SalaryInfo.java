package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int POSITION_OF_DATE = 0;
    private static final int POSITION_OF_NAME = 1;
    private static final int POSITION_OF_WORK_HOURS = 2;
    private static final int POSITION_OF_HOUR_SALARY = 3;
    private static final int ONE_DAY = 1;
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        LocalDate dateFromLocalDate = LocalDate.parse(dateFrom,fmt).minusDays(ONE_DAY);
        LocalDate dateToLocalDate = LocalDate.parse(dateTo,fmt).plusDays(ONE_DAY);
        int nameIndex = 0;
        int salaryAccumulator = 0;
        int correctLinesOnDate = 0;
        int counterOfWroteLines = 0;

            stringBuilder.append("Report for period ").append(dateFrom)//25.04.2019
                    .append(" - ").append(dateTo)
                    .append(System.lineSeparator());

            for (int i = 0; i < data.length; i++) {
                String[] currentString = data[i].split(" ");
                LocalDate currentStringData = LocalDate.parse(currentString[POSITION_OF_DATE], fmt);

                if (currentStringData.isAfter(dateFromLocalDate) && currentStringData.isBefore(dateToLocalDate)) {//date on our range
                    if (nameIndex == 0) {
                        correctLinesOnDate++;
                    }
                    if (currentString[POSITION_OF_NAME].equals(names[nameIndex])) {//if employee from data equal our index employee for counter
                        salaryAccumulator += Integer.parseInt(currentString[POSITION_OF_WORK_HOURS])
                                * Integer.parseInt(currentString[POSITION_OF_HOUR_SALARY]);
                        counterOfWroteLines++;
                    }
                }
                if (i == data.length - 1 && correctLinesOnDate > counterOfWroteLines) {
                    stringBuilder.append(names[nameIndex]).append(" - ").append(salaryAccumulator)
                    .append(System.lineSeparator());//write our collect data for current name
                    salaryAccumulator = 0;
                    i = 0;
                    nameIndex++;//change index to next name for checking if it present in current date
                }
                if (i == data.length - 1 && correctLinesOnDate == counterOfWroteLines
                        && correctLinesOnDate != 0) {
                    stringBuilder.append(names[nameIndex]).append(" - ").append(salaryAccumulator);
                }
                if (i == data.length - 1 && correctLinesOnDate == 0 && nameIndex < names.length) {//case if we dont have correct lines
                    stringBuilder.append(names[nameIndex]).append(" - ").append(salaryAccumulator);
                    if (nameIndex != names.length - 1) {
                        stringBuilder.append(System.lineSeparator());
                    }
                    nameIndex++;
                    i = 0;
                }
            }
        return stringBuilder.toString();
    }
}
