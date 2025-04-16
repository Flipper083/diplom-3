package chrome;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.example.api.GenerateUser;
import org.example.api.User;
import org.example.page.*;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import io.qameta.allure.Epic;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.*;

@Epic("Login user")
public class UserLoginTest {
    private User user; // Переменная для хранения информации о пользователе
    private HomePage homePage; // Переменная для главной страницы
    private LoginPage loginPage; // Переменная для страницы логина
    private ForgotPasswordPage forgotPassword; // Переменная для страницы восстановления пароля
    private RegisterPage registrationPage; // Переменная для страницы регистрации

    @Before
    public void setUp() {
        // Устанавливаем конфигурацию браузера: открытие в максимальном разрешении
        Configuration.startMaximized = true;
        WebDriverManager.chromedriver().setup();

        // Генерация случайного пользователя
        user = GenerateUser.getRandomUser();

        // Открываем главную страницу и выполняем регистрацию нового пользователя
        homePage = open(HomePage.URL, HomePage.class);
        homePage.clickLoginButton() // Кликаем кнопку "Войти"
                .clickRegisterLink() // Переходим на страницу регистрации
                .fillRegisterForm(user.getName(), user.getEmail(), user.getPassword()) // Заполняем форму регистрации
                .clickRegisterButton(Condition.hidden); // Кликаем кнопку "Зарегистрироваться" и ждем, когда она скроется
        homePage = null; // Обнуляем переменную homePage после регистрации
    }

    @After
    public void clearState() {
        // Обнуляем пользователя после тестов и очищаем локальное хранилище браузера
        user = null;
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Login user by login button")
    public void loginUserByLoginButtonTest() {
        // Открываем главную страницу
        homePage = open(HomePage.URL, HomePage.class);

        // Переходим на страницу логина и выполняем вход с использованием кнопки "Войти"
        boolean isDisplayed = homePage.clickLoginButton() // Кликаем на кнопку "Войти"
                .fillLoginForm(user.getEmail(), user.getPassword()) // Заполняем форму логина
                .clickLoginButton(Condition.hidden); // Кликаем кнопку для входа и ожидаем скрытие

        // Проверяем, что кнопка входа скрыта после успешного логина
        assertFalse(isDisplayed);
    }

    @Test
    @DisplayName("Login user by account button")
    public void loginUserByAccountButtonTest() {
        // Открываем главную страницу
        homePage = open(HomePage.URL, HomePage.class);

        // Переходим на страницу логина через кнопку "Личный кабинет"
        boolean isDisplayed = homePage.clickAccountButton() // Кликаем на кнопку "Личный кабинет"
                .fillLoginForm(user.getEmail(), user.getPassword()) // Заполняем форму логина
                .clickLoginButton(Condition.hidden); // Кликаем кнопку для входа и ожидаем скрытие

        // Проверяем, что кнопка входа скрыта после успешного логина
        assertFalse(isDisplayed);
    }

    @Test
    @DisplayName("Login user by register page")
    public void loginUserByRegisterPageTest() {
        // Открываем главную страницу
        homePage = open(HomePage.URL, HomePage.class);

        // Переходим на страницу логина через страницу регистрации
        boolean isDisplayed = homePage.clickLoginButton() // Кликаем на кнопку "Войти"
                .clickRegisterLink() // Переходим на страницу регистрации
                .clickLoginLink() // Возвращаемся на страницу логина
                .fillLoginForm(user.getEmail(), user.getPassword()) // Заполняем форму логина
                .clickLoginButton(Condition.hidden); // Кликаем кнопку для входа и ожидаем скрытие

        // Проверяем, что кнопка входа скрыта после успешного логина
        assertFalse(isDisplayed);
    }

    @Test
    @DisplayName("Login user by forgot password page")
    public void loginUserByForgotPasswordPageTest() {
        // Открываем главную страницу
        homePage = open(HomePage.URL, HomePage.class);

        // Переходим на страницу логина через страницу восстановления пароля
        boolean isDisplayed = homePage.clickLoginButton() // Кликаем на кнопку "Войти"
                .clickForgotPasswordLink() // Переходим на страницу восстановления пароля
                .clickLoginLink() // Возвращаемся на страницу логина
                .fillLoginForm(user.getEmail(), user.getPassword()) // Заполняем форму логина
                .clickLoginButton(Condition.hidden); // Кликаем кнопку для входа и ожидаем скрытие

        // Проверяем, что кнопка входа скрыта после успешного логина
        assertFalse(isDisplayed);
    }
}