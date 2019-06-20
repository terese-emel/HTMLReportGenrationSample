package model;


public class Result {


    private long executionTime;
    private String testName;
    private String result;
    private String resultText;
    private String screenshotName;

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    private String executionDate;

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public Result(String testName, String result, String resultText, String executionDate,long executionTime, String screenshotName) {
        this.testName = testName;
        this.result = result;
        this.resultText = resultText;
        this.screenshotName= screenshotName;
        this.executionTime=executionTime;
        this.executionDate=executionDate;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestName() {
        return this.testName;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return this.result;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }

    public String getResultText() {
        return this.resultText;
    }

    public String getScreenshot() {
        return this.screenshotName;
    }

    public void setScreenshot(String screenshotName) {
        this.screenshotName = screenshotName;
    }
}
