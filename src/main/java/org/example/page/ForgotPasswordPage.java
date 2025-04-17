package org.example.page;

import io.qameta.allure.Step;
import org.openqa.selenium.support.How;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class ForgotPasswordPage {
    // Локатор для ссылки "Войти"
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginLink; // Элемент, представляющий ссылку для перехода на страницу входа

    // Метод для клика по ссылке "Войти"
    @Step("Click login link")
    public LoginPage clickLoginLink() {
        loginLink.click(); // Выполняем клик по элементу ссылки "Войти"
        return page(LoginPage.class); // Переходим на страницу входа и возвращаем объект LoginPage
    }
}