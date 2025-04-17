package org.example.page;
import com.codeborne.selenide.Condition;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class RegisterPage {
    // Локатор для ссылки "Войти" (ссылка для перехода на страницу входа)
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginLink;

    // Локатор для кнопки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    // Локатор поля ввода "Имя"
    @FindBy(how = How.XPATH, using = "(//*[contains(@class, 'input pr-6 pl-6')]/input)[1]")
    private SelenideElement nameField;

    // Локатор поля ввода "Email"
    @FindBy(how = How.XPATH, using = "(//*[contains(@class, 'input pr-6 pl-6')]/input)[2]")
    private SelenideElement emailField;

    // Локатор поля ввода "Пароль"
    @FindBy(how = How.XPATH, using = ".//input[@type='password']")
    private SelenideElement passwordField;

    // Локатор ошибки "Некорректный пароль"
    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    public SelenideElement passwordError;

    // Локатор для текста ошибки "Некорректный пароль"
    @FindBy(how = How.XPATH, using = ".//p[@class='input__error text_type_main-default']")
    private SelenideElement unCorrectPassword;

    // Метод для заполнения поля "Имя"
    @Step("Fill name field")
    public void setNameField(String name) {
        nameField.setValue(name); // Ввод имени в поле
    }

    // Метод для заполнения поля "Email"
    @Step("Fill email field")
    public void setEmailField(String email) {
        emailField.setValue(email); // Ввод email в поле
    }

    // Метод для заполнения поля "Пароль"
    @Step("Fill password field")
    public void setPasswordField(String password) {
        passwordField.setValue(password); // Ввод пароля в поле
    }

    // Метод для заполнения всей регистрационной формы
    @Step("Fill register form")
    public RegisterPage fillRegisterForm(String name, String email, String password) {
        setNameField(name); // Заполнение поля "Имя"
        setEmailField(email); // Заполнение поля "Email"
        setPasswordField(password); // Заполнение поля "Пароль"
        return page(RegisterPage.class); // Возвращение на страницу регистрации
    }

    // Метод для клика по кнопке "Зарегистрироваться" с проверкой состояния кнопки
    @Step("Click register button")
    public boolean clickRegisterButton(Condition condition) {
        registerButton.click(); // Клик по кнопке "Зарегистрироваться"
        return registerButton.shouldBe(condition).isDisplayed(); // Проверка, что кнопка отображается после клика
    }

    // Метод для проверки, отображается ли ошибка пароля
    @Step("Is displayed password error")
    public boolean isDisplayedPasswordError() {
        clickRegisterButton(Condition.visible); // Клик по кнопке регистрации
        return passwordError.isDisplayed(); // Проверка, что ошибка пароля отображается
    }

    // Метод для клика по ссылке "Войти" и перехода на страницу входа
    @Step("Click login link")
    public LoginPage clickLoginLink() {
        loginLink.click(); // Клик по ссылке "Войти"
        return page(LoginPage.class); // Переход на страницу входа
    }

    // Метод для проверки, отображается ли текст "Некорректный пароль"
    public boolean isUnCorrectPasswordDisplayed() {
        unCorrectPassword.shouldBe(visible); // Ожидание, что ошибка отображается
        return unCorrectPassword.isDisplayed(); // Проверка, что ошибка "Некорректный пароль" отображается
    }
}