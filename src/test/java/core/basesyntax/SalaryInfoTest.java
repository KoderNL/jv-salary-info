package core.basesyntax;

import org.junit.Assert;
import org.junit.Test;

public class SalaryInfoTest {
    private static final String[] sampleNames = {"John", "Andrew", "Kate"};
    private static final String[] scriptArray = {
            "25.04.2019 John 60 50",
            "25.04.2019 Andrew 3 200",
            "25.04.2019 Kate 10 100",

            "26.04.2019 Andrew 3 200",
            "26.04.2019 Kate 9 100",

            "27.04.2019 John 7 100",
            "27.04.2019 Kate 3 80",
            "27.04.2019 Andrew 8 100"
    };
    private static final String[] dates = {
            "24.04.2019",
            "25.04.2019",
            "26.04.2019",
            "27.04.2019"
    };
    private static final String[] exceptedReports = {
            "Report for period 24.04.2019 - 24.04.2019" +
                    System.lineSeparator() +
                    "John - 0" +
                    System.lineSeparator() +
                    "Andrew - 0" +
                    System.lineSeparator() +
                    "Kate - 0",
            "Report for period 24.04.2019 - 25.04.2019" +
                    System.lineSeparator() +
                    "John - 3000" +
                    System.lineSeparator() +
                    "Andrew - 600" +
                    System.lineSeparator() +
                    "Kate - 1000",
            "Report for period 24.04.2019 - 26.04.2019" +
                    System.lineSeparator() +
                    "John - 3000" +
                    System.lineSeparator() +
                    "Andrew - 1200" +
                    System.lineSeparator() +
                    "Kate - 1900",
            "Report for period 24.04.2019 - 27.04.2019" +
                    System.lineSeparator() +
                    "John - 3700" +
                    System.lineSeparator() +
                    "Andrew - 2000" +
                    System.lineSeparator() +
                    "Kate - 2140"
    };

    private static final String[] secondScriptArray = {
            "13.07.2019 John 60 50",
            "15.07.2019 Andrew 3 200",
            "15.07.2019 Kate 10 100",

            "16.07.2019 Andrew 3 200",
            "16.07.2019 Kate 9 100",

            "10.08.2019 John 7 100",
            "08.08.2019 Kate 3 80",
            "11.08.2019 Andrew 8 100"
    };
    private static final String[] secondDates = {
            "14.07.2019",
            "10.08.2019",
    };
    private static final String secondExceptedReports =
            "Report for period 14.07.2019 - 10.08.2019" +
                    System.lineSeparator() +
                    "John - 700" +
                    System.lineSeparator() +
                    "Andrew - 1200" +
                    System.lineSeparator() +
                    "Kate - 2140";

    @Test
    public void getSalaryInfoByMonth() {
        SalaryInfo salary = new SalaryInfo();
        for (int i = 0; i < dates.length; i++) {
            String actualResult = salary.getSalaryInfo(sampleNames, scriptArray, dates[0], dates[i]);
            String expectedResult = SalaryInfoTest.exceptedReports[i];

            Assert.assertEquals(
                    "Test failed from date " + dates[0] + " to " + dates[i]
                            + "\nactual: \n" + actualResult
                            + "\nexpected: \n" + expectedResult,
                    expectedResult,
                    actualResult);
        }
    }

    @Test
    public void getSalaryInfoByTwoMonths() {
        SalaryInfo salary = new SalaryInfo();
        String actualResult = salary.getSalaryInfo(sampleNames, secondScriptArray,
                secondDates[0], secondDates[1]);
        String expectedResult = SalaryInfoTest.secondExceptedReports;

        Assert.assertEquals(
                "Test failed from date " + secondDates[0] + " to " + secondDates[1]
                        + "\nactual: \n" + actualResult
                        + "\nexpected: \n" + expectedResult,
                expectedResult,
                actualResult);
    }
}
