package org.example.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exist;
import static io.restassured.RestAssured.given;

public class AccountPage {
    // Локатор для кнопки "Выход"
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement logoutButton;

    // Локатор для элемента "Конструктор"
    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    private SelenideElement constructor;

    // Локатор для логотипа бургеров
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement logoBurger;

    // Метод для клика по элементу "Конструктор" и возврата текущего URL
    @Step("Click constructor")
    public String clickConstructor() {
        constructor.click();
        return Selenide.switchTo().window(0).getCurrentUrl();
    }

    // Метод для клика по логотипу бургеров и возврата текущего URL
    @Step("Click logo burger")
    public String clickLogoBurger() {
        logoBurger.click();
        return Selenide.switchTo().window(0).getCurrentUrl();
    }

    // Метод для клика по кнопке "Выход"
    @Step("Click logout button")
    public void clickLogout() {
        logoutButton.click();
    }

    // Метод для проверки отображения кнопки "Выход"
    @Step("Check if logout button is displayed")
    public boolean isLogoutDisplayed() {
        logoutButton.shouldBe(exist);
        return logoutButton.isDisplayed();
    }

    // Метод для клика по кнопке "Выход" с проверкой состояния
    @Step("Click logout button with condition")
    public boolean clickLogoutButton(Condition condition) {
        logoutButton.click();
        return logoutButton.shouldBe(condition).isDisplayed();
    }

}