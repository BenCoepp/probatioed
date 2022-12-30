package probatioed.daemon.entity.test;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private Integer version;
    private Integer depth;
    @Lob
    @Column(length=512)
    private String description;
    @Transient
    private List<Step> steps;

    private String root;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }


    public TestResult execute() {
        TestResult testResult = new TestResult();
        testResult.setTestId(this.id);
        testResult.setTitle(this.title);
        ArrayList<StepResult> stepResults = new ArrayList<>();
        for (Step step : this.steps) {
            StepResult stepResult = new StepResult();
            stepResult.setTitle(step.getTitle());
            for (String command : step.getCommands()) {
                stepResult.setCommand(stepResult.getCommand() + "\n" + command);
                String output = "";
                try {
                    Runtime rt = Runtime.getRuntime();
                    Process proc = rt.exec(command.split(" "));

                    BufferedReader stdInput = new BufferedReader(new
                            InputStreamReader(proc.getInputStream()));

                    BufferedReader stdError = new BufferedReader(new
                            InputStreamReader(proc.getErrorStream()));

                    String s = null;
                    while ((s = stdInput.readLine()) != null) {
                        output += "\n" + s;
                    }
                    while ((s = stdError.readLine()) != null) {
                        output += "\n" + s;
                    }
                    if(stepResult.getSuccessful()){
                        stepResult.setSuccessful(true);
                    }
                }catch (Exception e){
                    output += "\n" + e;
                    stepResult.setSuccessful(false);
                }
                stepResult.setOutput(stepResult.getOutput() + "\n" + output);
            }
            stepResults.add(stepResult);
        }
        testResult.setStepResults(stepResults);
        for (ListIterator<StepResult> iter = stepResults.listIterator(); iter.hasNext(); ) {
            StepResult element = iter.next();
            if(!element.getSuccessful()){
                testResult.setSuccessful(false);
            }else{
                testResult.setSuccessful(true);
            }
        }
        return testResult;
    }
}
