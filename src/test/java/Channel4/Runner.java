package Channel4;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.testng.Reporter;
import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions( plugin={"html:target/cucumber-html-report",
        // "json:target/cucumber.json",
        "json:target/cucumber-html-report/cucumber.json",
      //  "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html",
        "pretty:target" +"/cucumber-pretty.txt",
        "junit:target/cucumber-results"+"xml"},
        tags = {"@Sanity"},
        monochrome = true,
        // dryRun = true,
        features="D:\\StellarServiceAPI\\src\\resource\\AreaService.feature")

public class Runner {

    @AfterClass
    public static void writeExtentReport() {

    }


}

