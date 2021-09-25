package test;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Steps {
    protected WebDriver webDriver;

    @Step
    public void открытьХром(){
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver.exe");
        webDriver =new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    }

    @Step
    public void закрытьХром(){
        webDriver.quit();
    }
}
