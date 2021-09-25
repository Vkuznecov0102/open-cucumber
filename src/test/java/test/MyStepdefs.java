package test;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.Steps;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class MyStepdefs extends Steps {

    @Given("перейти на сайт {string}")
    public void перейтиНаСайтUrl(String url) {
        открытьХром();
        webDriver.get(url);
    }

    @Then("вбить в поиске {string}")
    public void вбитьВПоискеBankName(String bankName) {
        String search = "q";
        webDriver.findElement(By.name(search)).sendKeys(bankName);
    }

    @Then("нажать на {string}")
    public void нажатьНаButton(String button) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        button = "btnK";
        wait.until(ExpectedConditions.elementToBeClickable(By.name(button))).click();
    }

    @Then("проверить что в списке есть {string}")
    public void проверитьЧтоВСпискеЕстьOpenUrl(String openUrl) {
        openUrl = "https://www.open.ru";
        WebElement element = webDriver.findElement(By.partialLinkText(openUrl));
        assertTrue(element.getText().contains(openUrl));
    }

    @Then("проверить что в списке есть <openUrl>")
    public void проверить_что_в_списке_есть_openUrl() throws PendingException {
        if(false) {
            throw new PendingException();
        }
    }

    @Then("перейти на сайт")
    public void перейтиНаСайт() {
        String containsOpenRu = "//*[@class='g']//*[contains(text(),'https://www.open.ru')]";
        WebElement element = webDriver.findElement(By.xpath(containsOpenRu));
        element.click();
    }

    @Then("получить курсы валют")
    public void получитьКурсыВалют() {
        String exchangeRates = "//*[@class='main-page-exchange__table main-page-exchange__table--online']";
        webDriver.findElement(By.xpath(exchangeRates));

        String firstField = "//table[@class='main-page-exchange__table main-page-exchange__table--online']";
        String secondField = "//*/tr[@class='main-page-exchange__row main-page-exchange__row--with-border']";
        List<WebElement> moneyFields = webDriver.findElements(By.xpath(firstField + secondField));

        WebElement[] usdField = new WebElement[1];
        WebElement[] eurField = new WebElement[1];

        String xPathContainsUsd = "//*/tr[@class='main-page-exchange__row main-page-exchange__row--with-border']//*[contains(text(),'USD')]";
        String xPathContainsEur = "//*/tr[@class='main-page-exchange__row main-page-exchange__row--with-border']//*[contains(text(),'EUR')]";

        moneyFields.forEach(x -> {
            if (x.getText().contains("USD")) {
                usdField[0] = webDriver.findElement(By.xpath(xPathContainsUsd));
            } else if (x.getText().contains("EUR")) {
                eurField[0] = webDriver.findElement(By.xpath(xPathContainsEur));
            }
        });

        String xPathForUsd = "//*/span[@class='main-page-exchange__rate']";
        List<WebElement> usd = usdField[0].findElements(By.xpath(xPathForUsd));
        double bankBuyUSD = Double.parseDouble(usd.get(0).getText().replace(",", "."));
        double bankSellUSD = Double.parseDouble(usd.get(1).getText().replace(",", "."));

        double bankBuyEUR = Double.parseDouble(usd.get(2).getText().replace(",", "."));
        double bankSellEUR = Double.parseDouble(usd.get(3).getText().replace(",", "."));

        System.out.println(bankBuyUSD + " " + bankSellUSD + " " + bankBuyEUR + " " + bankSellEUR);
    }

    @And("закрыть браузер")
    public void закрытьБраузер() {
        закрытьХром();
    }
}
