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

@Epic("Logout user and transitions between pages") // Эпик для Allure отчета, описывает логику выхода пользователя и переходов между страницами
public class UserAccountTest {
    private User user; // Переменная для пользователя
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
    @DisplayName("Transition user to constructor")
    @Description("Test to transition user to the constructor after logging in")
    public void transitionToConstructorTest() {
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
    @Description("Test to transition user to the main page by clicking the burger logo")
    public void transitionToLogoBurgerTest() {
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
    @Description("Test to logout user by clicking the logout button")
    public void logoutUserByLogoutButtonTest() {
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

