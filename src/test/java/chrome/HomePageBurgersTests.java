package chrome;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.example.page.HomePage;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import io.qameta.allure.Epic;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.*;

@Epic("Navigate burger constructor") // Определяет эпик для Allure отчета, указывая, что тесты связаны с конструкторами бургеров
public class HomePageBurgersTests {
    private HomePage homePage; // Переменная для страницы HomePage

    @Before
    public void setUp() {
        // Устанавливаем конфигурацию для браузера: открытие в максимальном размере
        Configuration.startMaximized = true;
        WebDriverManager.chromedriver().setup();
    }

    @After
    public void tearDown() {
        // Закрываем веб-драйвер после выполнения тестов
        closeWebDriver();
    }

    @Test
    @Description("Checking the click on the filling")
    public void checkClickFillingTest() {

        // Открываем страницу HomePage по URL и инициализируем объект страницы
        homePage = open(HomePage.URL, HomePage.class);

        // Выполняем клик по разделу "Начинки" на главной странице
        homePage.clickFilling();

        // Проверяем, что после клика мы находимся на разделе "Начинки"
        assertTrue("Нахождение не в разделе 'Начинки'", homePage.isHeaderFillingVisible());
    }

    @Test
    @Description("Checking the click on the buns")
    public void checkClickBunsTest() {

        // Открываем страницу HomePage по URL и инициализируем объект страницы
        homePage = open(HomePage.URL, HomePage.class);

        // Проверяем, что заголовок для раздела "Булки" отображается
        assertTrue(homePage.isHeaderBunsVisible());
    }

    @Test
    @Description("Checking the click on the sauces")
    public void checkClickSaucesTest() {

        // Открываем страницу HomePage по URL и инициализируем объект страницы
        homePage = open(HomePage.URL, HomePage.class);

        // Выполняем клик по разделу "Соусы"
        homePage.clickSauces();

        // Проверяем, что после клика мы находимся в разделе "Соусы"
        assertTrue("Нахождение не в разделе 'Соусы'", homePage.isHeaderSaucesVisible());
    }
}