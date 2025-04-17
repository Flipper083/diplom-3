package chrome;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.page.HomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Description; // Импорт аннотации Description
import io.qameta.allure.junit4.DisplayName; // Импорт аннотации DisplayName

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.*;

@Epic("Navigate burger constructor") // Определяет эпик для отчета Allure
public class HomePageBurgersTests {
    private HomePage homePage; // Переменная для страницы HomePage

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
    }

    @After
    public void tearDown() {
        // Закрываем веб-драйвер после выполнения тестов
        closeWebDriver();
    }

    @Test
    @DisplayName("Check click on filling")
    @Description("Checking the click on the filling")
    public void checkClickFillingTest() {
        // Открываем страницу HomePage по URL и инициализируем объект страницы
        homePage = open(HomePage.URL, HomePage.class);

        // Кликаем по разделу "Начинки" на главной странице
        homePage.clickFilling();

        // Проверяем, что после клика мы находимся в разделе "Начинки"
        assertTrue("Не в разделе 'Начинки'", homePage.isHeaderFillingVisible());
    }

    @Test
    @DisplayName("Check click on buns")
    @Description("Checking the click on the buns")
    public void checkClickBunsTest() {
        // Открываем страницу HomePage по URL и инициализируем объект страницы
        homePage = open(HomePage.URL, HomePage.class);

        // Сначала кликаем по разделу "Соусы"
        homePage.clickSauces();

        // Проверяем, что после клика мы находимся в разделе "Соусы"
        assertTrue("Не в разделе 'Соусы'", homePage.isHeaderSaucesVisible());

        // Теперь кликаем по разделу "Булки"
        homePage.clickBuns();

        // Проверяем, что после клика мы находимся в разделе "Булки"
        assertTrue("Не в разделе 'Булки'", homePage.isHeaderBunsVisible());
    }

    @Test
    @DisplayName("Check click on sauces")
    @Description("Checking the click on the sauces")
    public void checkClickSaucesTest() {
        // Открываем страницу HomePage по URL и инициализируем объект страницы
        homePage = open(HomePage.URL, HomePage.class);

        // Кликаем по разделу "Соусы"
        homePage.clickSauces();

        // Проверяем, что после клика мы находимся в разделе "Соусы"
        assertTrue("Не в разделе 'Соусы'", homePage.isHeaderSaucesVisible());
    }
}

