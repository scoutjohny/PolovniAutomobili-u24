import io.qameta.allure.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class polovniAutomobili {
    WebDriver driver;
    @BeforeMethod (description = "Podizanje brauzera")
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_121.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterMethod(description = "Gašenje brauzera")
    public void teardown() throws IOException {
        reportScreenshot("Pretraga"+System.currentTimeMillis(),"Slika glavne strane");
        driver.quit();
    }

    @Epic("Pretraga")
    @Feature("Pretraga automobila")
    @Test(description = "Testiranje pretrage na sajtu")
    @Description("Testiranje pretrage automobila na sajtu")
    @Step("Korišćenje pretrage")
    @Link(name = "Allure TestNG docs", type = "custom", url = "testng/") //myLink
    @Issue("2.15.1") //issue
    @TmsLink("DEMO-2") //tms
    @Severity(SeverityLevel.CRITICAL)
    @Story("Pretraga automobila marke Alfa Romeo")
    public void searchCar(){
        driver.get("https://www.polovniautomobili.com/");
    }

    public void takeScreenshot(String fileName) throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("results/screenshots/"+fileName+".png"));
    }

    public void reportScreenshot(String fileName, String description) throws IOException {
        takeScreenshot(fileName);
        Path content = Paths.get("results/screenshots/"+fileName+".png");
        try(InputStream is = Files.newInputStream(content)){
            Allure.addAttachment(description,is);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
