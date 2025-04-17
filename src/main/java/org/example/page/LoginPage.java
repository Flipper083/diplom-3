package org.example.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    // Локатор для ссылки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//a[text()='Зарегистрироваться']")
    private SelenideElement registerLink;

    // Локатор для ссылки "Восстановить пароль"
    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    private SelenideElement forgotPasswordLink;

    // Локатор для поля ввода Email
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    public SelenideElement emailField;

    // Локатор для поля ввода пароля
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    public SelenideElement passwordField;

    // Локатор для кнопки "Войти"
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement loginButton;

    // Метод клика по ссылке "Зарегистрироваться"
    @Step("Click register link")
    public RegisterPage clickRegisterLink() {
        registerLink.click(); // Клик по ссылке "Зарегистрироваться"
        return page(RegisterPage.class); // Переход на страницу регистрации
    }

    // Метод клика по ссылке "Восстановить пароль"
    @Step("Click forgot password link")
    public ForgotPasswordPage clickForgotPasswordLink() {
        forgotPasswordLink.click(); // Клик по ссылке "Восстановить пароль"
        return page(ForgotPasswordPage.class); // Переход на страницу восстановления пароля
    }

    // Метод заполнения поля Email
    @Step("Fill email field")
    public void setEmailField(String email) {
        emailField.setValue(email); // Установка значения в поле Email
    }

    // Метод заполнения поля пароля
    @Step("Fill password field")
    public void setPasswordField(String password) {
        passwordField.setValue(password); // Установка значения в поле пароля
    }

    // Метод заполнения формы входа
    @Step("Fill login form")
    public LoginPage fillLoginForm(String email, String password) {
        setEmailField(email); // Заполнение поля Email
        setPasswordField(password); // Заполнение поля пароля
        return page(LoginPage.class); // Возвращение на страницу входа
    }

    // Метод клика по кнопке "Войти" и перехода на страницу аккаунта
    @Step("Click login button, go account page")
    public AccountPage clickLoginButton() {
        loginButton.click(); // Клик по кнопке "Войти"
        return page(AccountPage.class); // Переход на страницу аккаунта
    }

    // Метод клика по кнопке "Войти" с проверкой условия
    @Step("Click login button")
    public boolean clickLoginButton(Condition condition) {
        loginButton.click(); // Клик по кнопке "Войти"
        return loginButton.shouldBe(condition).isDisplayed(); // Проверка, что кнопка отображается
    }

    // Метод проверки отображения кнопки "Войти"
    public boolean isLoginButtonDisplayed() {
        loginButton.shouldBe(exist); // Ожидание, что кнопка существует
        return loginButton.isDisplayed(); // Проверка, что кнопка отображается
    }

    // Метод клика по тексту "Зарегистрироваться"
    @Step("Клик по клика \"Зарегистрироваться\"")
    public void clickRegister() {
        loginButton.shouldBe(exist); // Ожидание, что кнопка "Войти" существует
        registerLink.shouldBe(exist); // Ожидание, что ссылка "Зарегистрироваться" существует
        registerLink.click(); // Клик по ссылке "Зарегистрироваться"
    }

    // Метод клика по тексту "Восстановить пароль"
    @Step("Клик по тексту \"Восстановить пароль\"")
    public void clickRecoverPassword() {
        forgotPasswordLink.shouldBe(exist); // Ожидание, что ссылка "Восстановить пароль" существует
        forgotPasswordLink.click(); // Клик по ссылке "Восстановить пароль"
    }
}