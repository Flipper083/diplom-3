package yandex;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import org.example.api.GenerateUser;
import org.example.api.User;
import org.example.page.AccountPage;
import org.example.page.HomePage;
import org.example.page.LoginPage;
import org.example.page.RegisterPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.*;

public class UserAccountYandexTest extends BaseBurgers {
    private User user; // Объект для хранения данных о пользователе
    private HomePage homePage; // Страница главного меню
    private LoginPage loginPage; // Страница входа

    private RegisterPage registrationPage; // Страница регистрации
    private AccountPage accountProfile; // Страница профиля аккаунта

    @Before
    public void setUp() {
        // Открыть браузер в максимальном разрешении
        Configuration.startMaximized = true;

        // Запустить Яндекс браузер
        startYandexBrowser();

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
    public void tearDown() {
        // Закрыть браузер после выполнения тестов
        closeWebDriver();
    }

    @Test
    @DisplayName("Transition user to constructor")
    public void transitionToConstructor() {
        // Открываем главную страницу
        homePage = open(HomePage.URL, HomePage.class);

        // Логинимся и переходим в конструктор
        String url = homePage.clickAccountButton() // Кликаем на кнопку "Личный кабинет"
                .fillLoginForm(user.getEmail(), user.getPassword()) // Заполняем форму логина
                .clickLoginButton() // Кликаем кнопку для входа
                .clickConstructor(); // Переходим в конструктор

        // Проверяем, что URL соответствует главной странице
        assertEquals(HomePage.URL, url);
    }

    @Test
    @DisplayName("Transition user to logo burger")
    public void transitionToLogoBurger() {
        // Открываем главную страницу
        homePage = open(HomePage.URL, HomePage.class);

        // Логинимся и переходим по клику на логотип бургера
        String url = homePage.clickAccountButton() // Кликаем на кнопку "Личный кабинет"
                .fillLoginForm(user.getEmail(), user.getPassword()) // Заполняем форму логина
                .clickLoginButton() // Кликаем кнопку для входа
                .clickLogoBurger(); // Кликаем на логотип бургера

        // Проверяем, что URL соответствует главной странице
        assertEquals(HomePage.URL, url);
    }

    @Test
    @DisplayName("Logout user by logout button")
    public void logoutUserByLogoutButtonTest() {
        // Открываем главную страницу
        homePage = open(HomePage.URL, HomePage.class);

        // Переходим на страницу учетной записи, логинимся и ждем скрытие кнопки "Войти"
        homePage.clickAccountButton() // Кликаем на кнопку "Личный кабинет"
                .fillLoginForm(user.getEmail(), user.getPassword()) // Заполняем форму логина
                .clickLoginButton(Condition.hidden); // Кликаем кнопку для входа и ожидаем скрытие

        // Выполняем клик по кнопке выхода и проверяем, что кнопка скрыта после выхода
        boolean isDisplayed = homePage.clickAccountButtonGoAccountPage() // Кликаем по кнопке перехода в личный кабинет
                .clickLogoutButton(Condition.hidden); // Кликаем по кнопке выхода

        // Проверяем, что кнопка выхода исчезла после выхода
        assertFalse(isDisplayed); // Ожидаем, что кнопка не отображается
    }
}