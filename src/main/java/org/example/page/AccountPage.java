package org.example.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exist;

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
        constructor.click(); // Кликаем по элементу "Конструктор"
        return Selenide.switchTo().window(0).getCurrentUrl(); // Возвращаем текущий URL
    }

    // Метод для клика по логотипу бургеров и возврата текущего URL
    @Step("Click logo burger")
    public String clickLogoBurger() {
        logoBurger.click(); // Кликаем по логотипу бургеров
        return Selenide.switchTo().window(0).getCurrentUrl(); // Возвращаем текущий URL
    }

    // Метод для клика по кнопке "Выход"
    @Step("Click logout button")
    public void clickLogout() {
        logoutButton.click(); // Кликаем по кнопке "Выход"
    }

    // Метод для проверки отображения кнопки "Выход"
    public boolean isLogoutDisplayed() {
        logoutButton.shouldBe(exist); // Ожидаем, что кнопка "Выход" существует
        return logoutButton.isDisplayed(); // Возвращаем результат проверки отображения кнопки
    }

    // Метод для клика по кнопке "Выход" с проверкой состояния
    public boolean clickLogoutButton(Condition condition) {
        logoutButton.click(); // Кликаем по кнопке "Выход"
        return logoutButton.shouldBe(condition).isDisplayed(); // Проверяем, что кнопка отображается в соответствии с условием
    }
}