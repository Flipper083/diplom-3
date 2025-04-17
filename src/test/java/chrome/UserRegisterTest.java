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
    @DisplayName("Register user by valid credentials")
    @Description("Test to register user with valid credentials")
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
    @Description("Test to register user with invalid password")
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
    @Description("Test to check if password error is displayed")
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