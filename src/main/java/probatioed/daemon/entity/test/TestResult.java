package probatioed.daemon.entity.test;

import java.util.ArrayList;

public class TestResult {
    private String title;
    private String description;
    private Integer testId;
    private String info;
    private Boolean successful;
    private ArrayList<StepResult> stepResults;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public ArrayList<StepResult> getStepResults() {
        return stepResults;
    }

    public void setStepResults(ArrayList<StepResult> stepResults) {
        this.stepResults = stepResults;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
