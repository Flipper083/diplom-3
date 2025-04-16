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

@Epic("Logout user and transitions between pages") // Эпик для Allure отчета, описывает логику выхода пользователя и переходов между страницами
public class UserAccountTest {
    private User user; // Переменная для пользователя
    private HomePage homePage; // Переменная для главной страницы

    @Before
    public void setUp() {
        // Устанавливаем конфигурацию браузера: открытие в максимальном разрешении
        Configuration.startMaximized = true;
        WebDriverManager.chromedriver().setup();

        // Генерация случайного пользователя
        user = GenerateUser.getRandomUser();

        // Открываем главную страницу и выполняем регистрацию пользователя
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
    @DisplayName("Transition user to constructor")
    public void transitionToConstructorTest() {
        // Открываем главную страницу
        homePage = open(HomePage.URL, HomePage.class);

        // Переходим на страницу учетной записи, логинимся и переходим к конструктору
        String url = homePage.clickAccountButton() // Кликаем на кнопку "Личный кабинет"
                .fillLoginForm(user.getEmail(), user.getPassword()) // Заполняем форму логина
                .clickLoginButton() // Кликаем кнопку для входа
                .clickConstructor(); // Переходим к конструктору

        // Проверяем, что после перехода URL совпадает с URL главной страницы
        assertEquals(HomePage.URL, url);
    }

    @Test
    @DisplayName("Transition user to logo burger")
    public void transitionToLogoBurgerTest() {
        // Открываем главную страницу
        homePage = open(HomePage.URL, HomePage.class);

        // Переходим на страницу учетной записи, логинимся и кликаем по логотипу бургеров
        String url = homePage.clickAccountButton() // Кликаем на кнопку "Личный кабинет"
                .fillLoginForm(user.getEmail(), user.getPassword()) // Заполняем форму логина
                .clickLoginButton() // Кликаем кнопку для входа
                .clickLogoBurger(); // Кликаем по логотипу бургеров

        // Проверяем, что после перехода URL совпадает с URL главной страницы
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
        assertFalse(isDisplayed);
    }
}