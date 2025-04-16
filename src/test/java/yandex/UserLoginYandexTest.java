package yandex;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.example.api.GenerateUser;
import org.example.api.User;
import org.example.page.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertFalse;

public class UserLoginYandexTest extends BaseBurgers {

    private User user; // Объект для хранения данных о пользователе
    private HomePage homePage; // Страница главного меню
    private LoginPage loginPage; // Страница входа

    private RegisterPage registrationPage; // Страница регистрации
    private AccountPage accountProfile; // Страница профиля аккаунта

    @Before
    public void setUp() {
        // Устанавливаем конфигурацию браузера: открытие в максимальном разрешении
        Configuration.startMaximized = true;
        startYandexBrowser(); // Запускаем Яндекс браузер

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
        Selenide.clearBrowserLocalStorage(); // Очищаем локальное хранилище браузера
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
        assertFalse(isDisplayed); // Ожидаем, что кнопка не отображается
    }

    @Test
    @Description("Check the login via the \"Personal Account\" button on the main page")
    public void loginUserByAccountButtonTest() {
        // Открываем главную страницу
        homePage = open(HomePage.URL, HomePage.class);

        // Переходим на страницу логина через кнопку "Личный кабинет"
        boolean isDisplayed = homePage.clickAccountButton() // Кликаем на кнопку "Личный кабинет"
                .fillLoginForm(user.getEmail(), user.getPassword()) // Заполняем форму логина
                .clickLoginButton(Condition.hidden); // Кликаем кнопку для входа и ожидаем скрытие

        // Проверяем, что кнопка входа скрыта после успешного логина
        assertFalse(isDisplayed); // Ожидаем, что кнопка не отображается
    }

    @Test
    @Description("Check the login via the button in the registration form on the registration page")
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
        assertFalse(isDisplayed); // Ожидаем, что кнопка не отображается
    }

    @Test
    @Description("Check the login via the button in the password recovery form on the password recovery page")
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
        assertFalse(isDisplayed); // Ожидаем, что кнопка не отображается
    }
}