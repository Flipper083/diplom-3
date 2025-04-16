package org.example.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.page;

public class HomePage {

    // URL главной страницы
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    // Локатор кнопки "Личный Кабинет"
    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    private SelenideElement accountButton;

    // Локатор кнопки "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;

    // Локатор для списка ингредиентов
    @FindBy(how = How.CLASS_NAME, using = "BurgerIngredients_ingredients__list__2A-mT")
    private ElementsCollection menuIngredients;

    // Локатор кнопки "Оформить заказ"
    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement checkoutButton;

    // Локатор раздела "Начинки"
    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement filling;

    // Локатор заголовка "Начинки"
    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
    private SelenideElement headerFilling;

    // Локатор заголовка "Начинки" после клика на него
    @FindBy(how = How.XPATH, using = ".//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span")
    private SelenideElement headerFillingVisibleAfterClick;

    // Локатор раздела "Соусы"
    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement sauces;

    // Локатор заголовка "Соусы"
    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
    private SelenideElement headerSauces;

    // Локатор заголовка "Соусы" после клика на него
    @FindBy(how = How.XPATH, using = ".//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span")
    private SelenideElement headerSaucesVisibleAfterClick;

    // Локатор раздела "Булки"
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Булки')]")
    private SelenideElement buns;

    // Локатор заголовка "Булки"
    @FindBy(how = How.XPATH, using = ".//h2[text()='Булки']")
    private SelenideElement headerBuns;

    // Локатор заголовка "Булки" после клика на него
    @FindBy(how = How.XPATH, using = ".//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span")
    private SelenideElement headerBunsVisibleAfterClick;

    // Метод для клика по кнопке "Личный кабинет"
    @Step("Click account button")
    public LoginPage clickAccountButton() {
        accountButton.click(); // Кликаем по кнопке "Личный кабинет"
        return page(LoginPage.class); // Переходим на страницу входа
    }

    // Метод клика по кнопке "Личный кабинет" с проверкой на доступность
    public void clickPersonalAccountButton() {
        accountButton.shouldBe(enabled).click(); // Убедимся, что кнопка доступна для клика
    }

    // Метод для клика по кнопке "Личный кабинет" и перехода на страницу аккаунта
    @Step("Click account button, go account page")
    public AccountPage clickAccountButtonGoAccountPage() {
        accountButton.click(); // Кликаем по кнопке "Личный кабинет"
        return page(AccountPage.class); // Переходим на страницу аккаунта
    }

    // Метод для клика по кнопке "Войти в аккаунт"
    @Step("Click login button")
    public LoginPage clickLoginButton() {
        loginButton.click(); // Кликаем по кнопке "Войти в аккаунт"
        return page(LoginPage.class); // Переходим на страницу входа
    }

    // Метод для нахождения последнего ингредиента булки
    @Step("Find last bun ingredient")
    public boolean findLastBunIngredient() {
        SelenideElement bun = menuIngredients.get(0).lastChild(); // Получаем последний элемент булки
        bun.scrollIntoView(true); // Прокручиваем к элементу
        bun.click(); // Кликаем по элементу
        return bun.isDisplayed(); // Возвращаем статус отображения элемента
    }

    // Метод для нахождения последнего ингредиента соуса
    @Step("Find last sauce ingredient")
    public boolean findLastSauceIngredient() {
        SelenideElement sauce = menuIngredients.get(1).lastChild(); // Получаем последний элемент соуса
        sauce.scrollIntoView(true); // Прокручиваем к элементу
        sauce.click(); // Кликаем по элементу
        return sauce.isDisplayed(); // Возвращаем статус отображения элемента
    }

    // Метод для нахождения последнего ингредиента начинки
    @Step("Find last filling ingredient")
    public boolean findLastFillingIngredient() {
        SelenideElement filling = menuIngredients.get(2).lastChild(); // Получаем последний элемент начинки
        filling.scrollIntoView(true); // Прокручиваем к элементу
        filling.click(); // Кликаем по элементу
        return filling.isDisplayed(); // Возвращаем статус отображения элемента
    }

    // Метод для проверки отображения кнопки "Оформить заказ"
    public boolean isCheckoutButtonDisplayed() {
        checkoutButton.shouldBe(exist); // Проверяем, что кнопка существует
        return checkoutButton.isDisplayed(); // Возвращаем статус отображения кнопки
    }

    // Метод для клика по разделу "Начинки"
    @Step("Клик по разделу \"Начинки\"")
    public void clickFilling() {
        filling.shouldBe(exist); // Проверяем, что раздел "Начинки" существует
        filling.click(); // Кликаем по разделу "Начинки"
    }

    // Метод для проверки, что заголовок "Начинки" виден
    public boolean isHeaderFillingVisible() {
        return headerFillingVisibleAfterClick.getText().contentEquals("Начинки"); // Проверяем текст заголовка
    }

    // Метод для клика по разделу "Соусы"
    @Step("Клик по разделу \"Соусы\"")
    public void clickSauces() {
        sauces.shouldBe(exist); // Проверяем, что раздел "Соусы" существует
        sauces.click(); // Кликаем по разделу "Соусы"
    }

    // Метод для проверки, что заголовок "Соусы" виден
    public boolean isHeaderSaucesVisible() {
        return headerSaucesVisibleAfterClick.getText().contentEquals("Соусы"); // Проверяем текст заголовка
    }

    // Метод для клика по разделу "Булки"
    @Step("Клик по разделу \"Булки\"")
    public void clickBuns() {
        buns.shouldBe(exist); // Проверяем, что раздел "Булки" существует
        buns.click(); // Кликаем по разделу "Булки"
    }

    // Метод для проверки, что заголовок "Булки" виден
    public boolean isHeaderBunsVisible() {
        return headerBunsVisibleAfterClick.getText().contentEquals("Булки"); // Проверяем текст заголовка
    }
}