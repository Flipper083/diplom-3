package yandex;

import com.codeborne.selenide.Configuration;
import jdk.jfr.Description;
import org.example.page.HomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.*;

public class HomePageBurgersYandexTests extends BaseBurgers {
    private HomePage homePage; // Объект для хранения страницы главного меню

    @Before
    public void setup() {
        // Открыть браузер в максимальном разрешении
        Configuration.startMaximized = true;

        // Запустить Яндекс браузер
        startYandexBrowser();
    }

    @After
    public void tearDown() {
        // Закрыть браузер после выполнения тестов
        closeWebDriver();
    }

    @Test
    @Description("Checking the click on the filling")
    public void checkClickFillingTest() {
        // Перейти на страницу тестового стенда
        homePage = open(HomePage.URL, HomePage.class);

        // Кликнуть на раздел "Начинки"
        homePage.clickFilling();

        // Проверить на дисплее после перехода в раздел "Начинки"
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
        // Перейти на страницу тестового стенда
        homePage = open(HomePage.URL, HomePage.class);

        // Кликнуть на раздел "Соусы"
        homePage.clickSauces();

        // Проверить на дисплее после перехода в раздел "Соусы"
        assertTrue("Нахождение не в разделе 'Соусы'", homePage.isHeaderSaucesVisible());
    }
}
