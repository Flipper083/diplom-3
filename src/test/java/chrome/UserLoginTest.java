package chrome;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description; // Импорт аннотации Description
import org.example.api.GenerateUser;
import org.example.api.User;
import org.example.api.UserClient;
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
    private UserClient userClient;
    private String accessToken;

    @Before
    public void setUp() {
        // Получаем имя браузера из системной переменной или используем Chrome по умолчанию
        String browser = System.getProperty("browser", "chrome");

        if (browser.equalsIgnoreCase("yandex")) {
            // Настройки для Яндекс.Браузера
            System.setProperty("webdriver.yandex.driver", "src/main/resources/yandexdriver"); // Укажите путь к драйверу Яндекс.Браузера
            Configuration.browser = "chrome"; // Selenide будет использовать ChromeDriver
            Configuration.startMaximized = true; // Открытие в максимальном размере
        } else {
            // Устанавливаем конфигурацию для Chrome
            Configuration.browser = browser;
            Configuration.startMaximized = true; // Открытие в максимальном размере
            WebDriverManager.chromedriver().setup(); // Установка драйвера для Chrome
        }

        userClient = new UserClient();
        user = GenerateUser.getRandomUser(); // Генерация случайного пользователя

        // Создаем пользователя через API и получаем токен
        accessToken = userClient.createClient(user).extract().path("accessToken");

        // Открываем главную страницу
        homePage = open(HomePage.URL, HomePage.class);
    }

    @After
    public void clearState() {
        // Удаляем пользователя после тестов через API
        userClient.deleteClient(accessToken);
        Selenide.clearBrowserLocalStorage(); // Очищаем локальное хранилище браузера
    }

    @Test
    @DisplayName("Login user by login button")
    @Description("Test to login user using the login button")
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
    @Description("Test to login user using the account button")
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
    @Description("Test to login user using the register page")
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
    @Description("Test to login user using the forgot password page")
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