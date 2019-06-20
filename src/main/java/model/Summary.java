package model;

import java.util.HashMap;

public class Summary {

    private String executionDate;
    private int totalTestCases;
    private int count;
    private String status;
    static HashMap<String, Integer> map;


    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public int getTotalTestCases() {
        return totalTestCases;
    }

    public void setTotalTestCases(int totalTestCases) {
        this.totalTestCases = totalTestCases;
    }

    public static HashMap<String, Integer> getMap() {
        return map;
    }

    public static void setMap(HashMap<String, Integer> map) {
        Summary.map = map;
    }

    public Summary(String executionDate, int totalTestCases, HashMap<String, Integer> map) {
        this.executionDate = executionDate;
        this.totalTestCases = totalTestCases;
        this.map = map;

    }

}
