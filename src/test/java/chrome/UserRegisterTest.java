package chrome;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.example.api.GenerateUser;
import org.example.api.User;
import org.example.page.HomePage;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import io.qameta.allure.Epic;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.*;

@Epic("Register user")
public class UserRegisterTest {
    private User user; // Переменная для хранения информации о пользователе
    private HomePage homePage; // Переменная для главной страницы

    @Before
    public void setUp() {
        // Устанавливаем конфигурацию браузера: открытие в максимальном разрешении
        Configuration.startMaximized = true;
        WebDriverManager.chromedriver().setup(); // Установка драйвера Chrome

        // Генерация случайного пользователя
        user = GenerateUser.getRandomUser();

        // Открываем главную страницу
        homePage = open(HomePage.URL, HomePage.class);
    }

    @After
    public void clearState() {
        // Обнуляем пользователя после тестов и очищаем локальное хранилище браузера
        user = null; // Удаляем информацию о пользователе
        Selenide.clearBrowserLocalStorage(); // Очищаем локальное хранилище браузера
    }

    @Test
    @DisplayName("Register user by valid credentials")
    public void registerUserByValidCredentialsTest() {
        // Переходим на страницу регистрации и заполняем форму с корректными данными
        boolean isDisplayed = homePage.clickLoginButton() // Кликаем кнопку "Войти"
                .clickRegisterLink() // Переходим на страницу регистрации
                .fillRegisterForm(user.getName(), user.getEmail(), user.getPassword()) // Заполняем форму регистрации
                .clickRegisterButton(Condition.hidden); // Кликаем кнопку "Зарегистрироваться" и ждем, когда она скроется

        // Проверяем, что кнопка регистрации скрыта после успешной регистрации
        assertFalse(isDisplayed);
    }

    @Test
    @DisplayName("Register user by invalid password")
    public void registerUserByInvalidPasswordTest() {
        // Переходим на страницу регистрации и заполняем форму с некорректным паролем
        boolean isDisplayed = homePage.clickLoginButton() // Кликаем кнопку "Войти"
                .clickRegisterLink() // Переходим на страницу регистрации
                .fillRegisterForm(user.getName(), user.getEmail(), "888") // Заполняем форму с некорректным паролем
                .clickRegisterButton(Condition.visible); // Кликаем кнопку "Зарегистрироваться" и ожидаем, что она останется видимой

        // Проверяем, что кнопка регистрации отображается, что указывает на ошибку
        assertTrue(isDisplayed);
    }

    @Test
    @DisplayName("Register user is displayed password error")
    public void registerUserIsDisplayedPasswordErrorTest() {
        // Переходим на страницу регистрации и заполняем форму с некорректным паролем
        boolean isDisplayed = homePage.clickLoginButton() // Кликаем кнопку "Войти"
                .clickRegisterLink() // Переходим на страницу регистрации
                .fillRegisterForm(user.getName(), user.getEmail(), "888") // Заполняем форму с некорректным паролем
                .isDisplayedPasswordError(); // Проверяем, отображается ли ошибка пароля

        // Проверяем, что ошибка пароля отображается
        assertTrue(isDisplayed);
    }
}