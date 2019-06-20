package utility;

import model.Result;
import model.Summary;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ReportGenerator {
    public static List<Result> details;
    public static List<Summary> summaryInfo;
    private static final String resultPlaceholder = "<!-- INSERT_RESULTS -->";
    private static final String executionDatePlaceholder = "<!-- INSERT_EXECUTION DATE -->";
    private static final String totalTestcasePlaceholder = "<!-- INSERT_TOTAL TESTCASE -->";
    private static final String passCountPlaceholder = "<!-- INSERT_PASSCOUNT -->";
    private static final String failCountPlaceholder = "<!-- INSERT_FAILCOUNT -->";
    private static final String skipCountPlaceholder = "<!-- INSERT_SKIPCOUNT -->";
    private static final String ignoreCountPlaceholder = "<!-- INSERT_IGNORECOUNT -->";
    private static final String templateFilePath = "E:\\IdeaProject\\TestProject\\output\\Test Results\\Template\\Report.html";
    private static int passCount = 0, failCount = 0, skipCount = 0, ignoreCount = 0;
    private static String reportPath;
    private static String status;
    static HashMap<String, Integer> map = new HashMap<String, Integer>();
    public ReportGenerator() {
    }

    public static void initialize() {
        details = new ArrayList<Result>();
        summaryInfo =new ArrayList<Summary>();
    }

    public static void report(String testName, String actualValue, String expectedValue, String executionDate,long executionTime, String path) {
        if (actualValue.equals(expectedValue)) {
            Result r = new Result(testName, "Pass", "Actual value '" + actualValue + "' matches expected value.",executionDate, executionTime, path);
            details.add(r);
        } else {
            Result r = new Result(testName, "Fail", "Actual value '" + actualValue + "' does not match expected value '" + expectedValue + "'", executionDate,executionTime, path);
            details.add(r);
        }
    }

    public static void displaySummary() {

        for (int i = 0; i < details.size(); i++) {

            if (details.get(i).getResult() == "Pass") {
                status ="Passed";
                passCount=passCount+1;
                map.put(status, passCount);
            } else if (details.get(i).getResult() == "Fail") {
                status ="Failed";
                failCount=failCount+1;
                map.put(status, failCount);
            } else if (details.get(i).getResult() == "Skip") {
                status ="Skipped";
                skipCount=skipCount+1;
                map.put(status, skipCount);
            } else if (details.get(i).getResult() == "Ignore") {
                status ="Ignored";
                ignoreCount=ignoreCount+1;
                map.put(status, ignoreCount);
            }
        }
        int totalTestCases = passCount + failCount + skipCount + ignoreCount;
        String executionDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        Summary summaryChart = new Summary(executionDate, totalTestCases, map);
        System.out.println(map);
        summaryInfo.add(summaryChart);

        try {
            String reportIn = new String(Files.readAllBytes(Paths.get(templateFilePath)));
            for (int i = 0; i < summaryInfo.size(); i++) {
                reportIn = reportIn.replaceFirst(executionDatePlaceholder, "<td id=\"exeDate\">" + summaryInfo.get(i).getExecutionDate() + "</td>");
                reportIn = reportIn.replaceFirst(totalTestcasePlaceholder, "<td id=\"tCount\">" + summaryInfo.get(i).getTotalTestCases() + "</td>");
                if (summaryInfo.get(i).getMap().get("Passed")==null){
                    passCount = 0;
                }
                if(summaryInfo.get(i).getMap().get("Failed")==null){
                    failCount = 0;
                }
                if (summaryInfo.get(i).getMap().get("Skipped")==null) {
                    skipCount = 0;
                }
                if (summaryInfo.get(i).getMap().get("Ignored")==null) {
                    ignoreCount =0;
                }
                else {
                    if(summaryInfo.get(i).getMap().get("Passed")!=null) {
                        passCount = summaryInfo.get(i).getMap().get("Passed");
                    }
                    if(summaryInfo.get(i).getMap().get("Failed")!=null) {
                        failCount = summaryInfo.get(i).getMap().get("Failed");
                    }
                    if(summaryInfo.get(i).getMap().get("Skipped")!=null) {
                        failCount = summaryInfo.get(i).getMap().get("Skipped");
                    }
                    if(summaryInfo.get(i).getMap().get("Ignored")!=null) {
                        failCount = summaryInfo.get(i).getMap().get("Ignored");
                    }
                }

                reportIn = reportIn.replaceFirst(passCountPlaceholder, "<td class =\"count\">" + passCount + "</td>");
                reportIn = reportIn.replaceFirst(failCountPlaceholder, "<td class =\"count\">" + failCount + "</td>");
                reportIn = reportIn.replaceFirst(skipCountPlaceholder, "<td class =\"count\">" + skipCount + "</td>");
                reportIn = reportIn.replaceFirst(ignoreCountPlaceholder, "<td class =\"count\">" + ignoreCount + "</td>");
            }

            String cDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String currentDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String path = "E:\\IdeaProject\\TestProject\\output\\Test Results\\" + cDate;
            Files.createDirectories(Paths.get(path));
            reportPath = path + "\\report_on_" + currentDate + ".html";
            Files.write(Paths.get(reportPath), reportIn.getBytes(), StandardOpenOption.CREATE);
        }catch (Exception e) {
            System.out.println("Error when writing report file:\n" + e.toString());
        }
    }

    public static void writeTestResults() {
        try {
            String reportIn = new String(Files.readAllBytes(Paths.get(reportPath)));
            for (int i = 0; i < details.size(); i++) {
                reportIn = reportIn.replaceFirst(resultPlaceholder,
                        "<tr>" +
                                "<td>" + Integer.toString(i + 1) + "</td>" +
                                "<td class =\"testname\">" + details.get(i).getTestName() + "</td>" +
                                "<td>" + details.get(i).getResultText() + "</td>" +
                                "<td class =\"result\">" + details.get(i).getResult() + "</td>"
                                + "<td>" + details.get(i).getExecutionDate() + "</td>" +
                                "<td>" + details.get(i).getExecutionTime() + "</td>" +
                                "<td><a target=\"_self\" href=\"#\">" +
                                "<img id= \"myBtn-" + Integer.toString(i) + "\" onclick=\"setImage(this.id,this.src,this.alt)\" " +
                                "src=\"../../screenshots/" + details.get(i).getTestName() + "/" + details.get(i).getScreenshot() + ".png" + "\" alt=\"" + details.get(i).getScreenshot() + "\" " +
                                "style=\"width:100%;max-width:50px\">"
                                + "</a>" +
                                "</td>" +
                                "</tr>" + resultPlaceholder);
            }

            Files.write(Paths.get(reportPath),reportIn.getBytes(),StandardOpenOption.TRUNCATE_EXISTING);

        } catch (Exception e) {
            System.out.println("Error when writing report file:\n" + e.toString());
        }
    }


    public static void writeResults() {
        try {
            String reportIn = new String(Files.readAllBytes(Paths.get(templateFilePath)));
            for (int i = 0; i < details.size();i++) {
                reportIn = reportIn.replaceFirst(resultPlaceholder,"<tr><td>" + Integer.toString(i+1) + "</td><td>" + details.get(i).getResult() + "</td><td>" + details.get(i).getResultText() + "</td></tr>" + resultPlaceholder);
            }

            String cDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String currentDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String path = "E:\\IdeaProject\\TestProject\\output\\Test Results\\"+cDate;
            Files.createDirectories(Paths.get(path));
            String reportPath =path+"\\report_on_" + currentDate + ".html";
            Files.write(Paths.get(reportPath),reportIn.getBytes(),StandardOpenOption.CREATE);

        } catch (Exception e) {
            System.out.println("Error when writing report file:\n" + e.toString());
        }
    }
}