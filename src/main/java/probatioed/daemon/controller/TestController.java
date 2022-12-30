package probatioed.daemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import probatioed.daemon.entity.test.Test;
import probatioed.daemon.entity.test.TestInfo;
import probatioed.daemon.entity.test.TestResult;
import probatioed.daemon.repository.TestRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    TestRepository testRepository;

    @GetMapping("/{depth}/all/info")
    public ResponseEntity<List<TestInfo>> getAllTestsByDepth(@PathVariable String depth){
        List<Test> tests = testRepository.findByDepthLessThanEqual(Integer.parseInt(depth));
        List<TestInfo> infos = new ArrayList<>();
        for (Test test : tests) {
            TestInfo testInfo = new TestInfo();
            testInfo.setTitle(test.getTitle());
            testInfo.setDescription(test.getDescription());
            testInfo.setVersion(test.getVersion());
            infos.add(testInfo);
        }
        return ResponseEntity.ok(infos);
    }
    @GetMapping("/{depth}/all/execute")
    public ResponseEntity<List<TestResult>> executeTests(@PathVariable String depth) throws FileNotFoundException {
        ArrayList<TestResult> testResults = new ArrayList<>();
        List<Test> elements = testRepository.findByDepthLessThanEqual(Integer.parseInt(depth));
        List<Test> tests = new ArrayList<>();
        for (Test test : elements) {
            InputStream inputStream = new FileInputStream(new File(test.getRoot()));
            Yaml yaml = new Yaml(new Constructor(Test.class));
            Test test1 = yaml.load(inputStream);
            tests.add(test1);
        }
        for (Test test : tests) {
            TestResult testResult = test.execute();
            testResults.add(testResult);
        }
        return ResponseEntity.ok(testResults);
    }
}
