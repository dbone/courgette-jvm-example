import courgette.api.{CourgetteOptions, CourgetteRunLevel}
import courgette.api.junit.Courgette
import cucumber.api.CucumberOptions
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith

@RunWith(classOf[Courgette])
@CourgetteOptions(
  threads = 5,
  runLevel = CourgetteRunLevel.SCENARIO,
  rerunFailedScenarios = false,
  rerunAttempts = 2,
  showTestOutput = true,
  reportTargetDir = "build",
  cucumberOptions = new CucumberOptions(
    features = Array("src/test/resources/features"),
    glue = Array("steps"),
    tags = Array("@regression"),
    plugin = Array("pretty",
      "json:build/cucumber-report/cucumber.json",
      "html:build/cucumber-report/cucumber.html",
      "junit:build/cucumber-report/cucumber.xml"),
    strict = true))
class ScenarioSuite {
}