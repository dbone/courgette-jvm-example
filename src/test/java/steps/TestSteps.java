package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import cucumber.runtime.FeatureBuilder;
import gherkin.I18n;
import gherkin.formatter.Formatter;
import gherkin.parser.Parser;
import io.github.pramcharan.wd.binary.downloader.WebDriverBinaryDownloader;
import io.github.pramcharan.wd.binary.downloader.enums.BrowserType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class TestSteps {
    private WebDriver driver;

    private final String FEATURE_DIRECTORY = (System.getProperty("user.dir") + "/src/test/resources/features/").replaceAll("/", File.separator);

    @Before
    public void beforeAll(){
        WebDriverBinaryDownloader.create().downloadLatestBinaryAndConfigure(BrowserType.CHROME);
        driver = new ChromeDriver();
    }

    @Before("@english")
    public void beforeEnglishFeature(Scenario scenario) throws IOException {
        assertTrue(getI18nLanguage(scenario).getIsoCode().equals("en"));
    }

    @Before("@russian")
    public void beforeRussianFeature(Scenario scenario) throws IOException {
        assertTrue(getI18nLanguage(scenario).getIsoCode().equals("ru"));
    }

    @After
    public void after() {
        if (driver != null) {
            driver.quit();
        }
    }

    private I18n getI18nLanguage(Scenario scenario) throws IOException {
        Formatter formatter = new FeatureBuilder(new ArrayList<>());

        File featureFile = new File(FEATURE_DIRECTORY + scenario.getId().split(";")[0].replace("-", "").trim() + ".feature");

        String gherkin = FileUtils.readFileToString(featureFile, Charset.defaultCharset());

        Parser parser = new Parser(formatter);
        parser.parse(gherkin, featureFile.getAbsolutePath(), 0);

        return parser.getI18nLanguage();
    }

    @When("I navigate to Stack Overflow question page (\\d+)")
    public void navigateToStackOverflowQuestionPage(Integer page) {
        driver.navigate().to("https://stackoverflow.com/questions?page=" + page);
    }

    @Then("I verify Stack Overflow question page (\\d+) is opened")
    public void verifyCorrectQuestionPageIsOpened(Integer page) {
        assertTrue(driver.getTitle().contains("Page " + page));
    }

    @Когда("^Я перейти на переполнение стека вопрос страница (\\d+)$")
    public void яПерейтиНаПереполнениеСтекаВопросСтраница(Integer page) throws Throwable {
        driver.navigate().to("https://stackoverflow.com/questions?page=" + page);
    }

    @Тогда("^Я убедитесь, что переполнение стека вопрос страница (\\d+) открыт$")
    public void яУбедитесьЧтоПереполнениеСтекаВопросСтраницаОткрыт(Integer page) throws Throwable {
        assertTrue(driver.getTitle().contains("Page " + page));
    }
}
