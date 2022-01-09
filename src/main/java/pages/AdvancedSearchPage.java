package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.eo.Se;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class AdvancedSearchPage {
    private final String advancedSearchSpan = "//span[text() = 'Настройка поиска']";
    private final String checkBox615PPRF = "//label[text() = '615-ПП РФ']";
    private final String notJoinPurchase = "//label[text() = 'Исключить совместные закупки']";
    private final String dateFilters = "//div[text() ='Фильтры по датам']";
    private final String dateFilterFrom = "//div[text()='ПОДАЧА ЗАЯВОК']/following-sibling::div//div//div//div/input[1]";
    private final String dateFilterTo = "//div[text()='ПОДАЧА ЗАЯВОК']/following-sibling::div//div[@class='form-group__cell-mdash']/following-sibling::div//input";
    private final String dateChooseTo = "//div[contains(@class, 'react-datepicker__day--keyboard-selected react-datepicker__day--today')]";
    private final String regionDelivery = "//div[text()='Регион поставки']";
    private final String regionAltay = "//label[text()='Алтайский край']";
    private final String pressFind = "//button[@class='search__btn bottomFilterSearch']";
    private final String cardItem = "//div[@class='card-item']";
    private final String loadMore = "#load-more";
    private final String cardsCount = "//span[@class='main-tabs__btn tab-btn active']//span";
    private final String closeHelpWindow = "//button[text()='Закрыть']";
    private final String openHelpWindow = "//button[@class='consultation-btn']";


    /**
     * Нажимает на копку "Настройка поиска"
     */
    public void clickSearchSettingsButton() {
        $x(advancedSearchSpan).shouldBe(Condition.visible).click();
    }

    /**
     * Выбирает чек-бокс "615ППРФ"
     */
    public void clickCheckBox615PPRF() {
        $x(checkBox615PPRF).shouldBe(Condition.visible).click();
    }

    /**
     * Выбирает чек-бокс "Исключить совместные закупки"
     */
    public void clickNotJoinPurchase() {
        $x(notJoinPurchase).shouldBe(Condition.visible).click();
    }

    /**
     * Открывает вкладку "Фильтры по датам"
     */
    public void clickDateFilters() {
        $x(dateFilters).shouldBe(Condition.visible).click();
    }

    /**
     * Устанавливает дату от "04-01-2022" в поле "ПОДАЧА ЗАЯВОК"
     */
    public void setDateFrom() {
        $x(dateFilterFrom).shouldBe(Condition.visible).setValue("04-01-2022").pressEnter();
    }

    /**
     * Открывает календарь дат До в поле "ПОДАЧА ЗАЯВОК"
     */
    public void clickDateTo() {
        $x(dateFilterTo).shouldBe(Condition.visible).click();
    }

    /**
     * Устанавливает дату до "Сегодня" в поле "ПОДАЧА ЗАЯВОК"
     */
    public void clickDateToday() {
        $x(dateChooseTo).shouldBe(Condition.visible).click();
    }

    /**
     * Открывает вкладку "Регион поставки"
     */
    public void clickRegionDelivery() {
        $x(regionDelivery).shouldBe(Condition.visible).click();
    }

    /**
     * Устанавливает Алтай как "Регион поставки"
     */
    public void clickRegionAltay() {
        $x(regionAltay).shouldBe(Condition.visible).click();
    }

    /**
     * Нажимает на копку "Найти"
     */
    public void clickFind() {
        $x(pressFind).shouldBe(Condition.visible).click();
    }

    /**
     * Получает значение НМЦ
     */
    public String getItemPrice(SelenideElement cardPurchase) {
        String itemPrice = cardPurchase.find(By.xpath(".//div[@class='card-item__properties-desc' and @itemprop='price']")).getText();
        return itemPrice;
    }

    /**
     * Получает значение кол-ва закупок
     */
    public double getSupplieValue(SelenideElement cardPurchase) {
        ElementsCollection elementsCollection = cardPurchase.findAll(By.xpath(".//td[3]"));
        double purchaseValue = 0;

        SelenideElement openButtonShowMore = cardPurchase.find(By.xpath(".//span[@class='more-position show-more']"));
        if (openButtonShowMore.exists()) {
            openButtonShowMore.shouldBe(Condition.visible).scrollIntoView(false).click();
        }
        for (int i = 0; i < elementsCollection.size(); i++) {
            purchaseValue += Double.parseDouble(elementsCollection.get(i).getText().replaceAll(" ", "").replaceAll(",", "."));

        }

        return purchaseValue;
    }

    /**
     * Получает карточку закупки
     */
    public ElementsCollection getCards() {
        ElementsCollection purchaseCards = $$x(cardItem);
        return purchaseCards;
    }

    /**
     * Проверяет кнопку "Загрузить ещё" на наличие
     */
    public boolean checkButtonLoadMoreIsDisplayed() {
        sleep(900);
        return $(loadMore).isDisplayed();
    }

    /**
     * Нажимает кнопку "Загрузить ещё"
     */
    public void clickLoadMore() {
        $(loadMore).shouldBe(Condition.visible).click();
    }

    /**
     * Получает кол-во карточек закупок
     */
    public int getCardsCount() {
        return Integer.parseInt($x(cardsCount).getText().replaceAll(" ", ""));
    }

    /**
     * Открывает окно "Поможем в любой ситуации" (требуется для прохождения теста)
     */
    public void clickOpenHelpWindow() {
        $x(openHelpWindow).shouldBe(Condition.visible).click();
    }

    /**
     * Закрывает окно "Поможем в любой ситуации"
     */
    public void clickCloseHelpWindow() {
        $x(closeHelpWindow).shouldBe(Condition.enabled).click();
    }

}
