package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class AdvancedSearchPage extends AbstractBasicPage {
    private final String advancedSearchSpanXpath = "//span[text() = 'Настройка поиска']";
    private final String checkBox615PPRFXpath = "//label[text() = '615-ПП РФ']";
    private final String notJoinPurchaseXpath = "//label[text() = 'Исключить совместные закупки']";
    private final String dateFiltersXpath = "//div[text() ='Фильтры по датам']";
    private final String dateFilterFromXpath = "//div[text()='ПОДАЧА ЗАЯВОК']/following-sibling::div//div//div//div/input[1]";
    private final String dateFilterToXpath = "//div[text()='ПОДАЧА ЗАЯВОК']/following-sibling::div//div[@class='form-group__cell-mdash']/following-sibling::div//input";
    private final String dateChooseToXpath = "//div[contains(@class, 'react-datepicker__day--keyboard-selected react-datepicker__day--today')]";
    private final String regionDeliveryXpath = "//div[text()='Регион поставки']";
    private final String regionAltayXpath = "//label[text()='Алтайский край']";
    private final String pressFindXpath = "//button[@class='search__btn bottomFilterSearch']";
    private final String cardItemXpath = "//div[@class='card-item']";
    private final String loadMoreCss = "#load-more";
    private final String cardsCountXpath = "//span[@class='main-tabs__btn tab-btn active']//span";
    private final String closeHelpWindowXpath = "//button[text()='Закрыть']";
    private final String openHelpWindowXpath = "//button[@class='consultation-btn']";
    private final String purchaseNumberXpath = ".//div[@class='card-item__about']//a";
    private final String deleteFindElementXpath = "//div[text()='Удалить']";
    private final String inputSearchValueXpath = "//div[@class='main-search__controls']//input";
    private final String pressFindingBtnXpath = "//span[@class='search__btn_def']";
    private final String loaderWaitXpath = "//div[@class='loader']";

    /**
     * Нажимает на копку "Настройка поиска"
     */
    public void clickSearchSettingsButton() {
        clickOnElement($x(advancedSearchSpanXpath));
    }

    /**
     * Выбирает чек-бокс "615ППРФ"
     */
    public void clickCheckBox615PPRF() {
        clickOnElement($x(checkBox615PPRFXpath));
    }

    /**
     * Выбирает чек-бокс "Исключить совместные закупки"
     */
    public void clickNotJoinPurchase() {
        clickOnElement($x(notJoinPurchaseXpath));
    }

    /**
     * Открывает вкладку "Фильтры по датам"
     */
    public void clickDateFilters() {
        clickOnElement($x(dateFiltersXpath));
    }

    /**
     * Устанавливает дату от "04-01-2022" в поле "ПОДАЧА ЗАЯВОК"
     */
    public void setDateFrom() {
        $x(dateFilterFromXpath).shouldBe(Condition.visible).setValue("04-01-2022").pressEnter();
    }

    /**
     * Открывает календарь дат До в поле "ПОДАЧА ЗАЯВОК"
     */
    public void clickDateTo() {
        clickOnElement($x(dateFilterToXpath));
    }

    /**
     * Устанавливает дату до "Сегодня" в поле "ПОДАЧА ЗАЯВОК"
     */
    public void clickDateToday() {
        clickOnElement($x(dateChooseToXpath));
    }
    /**
     * Открывает вкладку "Регион поставки"
     */
    public void clickRegionDelivery() {
        clickOnElement($x(regionDeliveryXpath));
    }

    /**
     * Устанавливает Алтай как "Регион поставки"
     */
    public void clickRegionAltay() {
        clickOnElement($x(regionAltayXpath));
    }

    /**
     * Нажимает на копку "Найти"
     */
    public void clickFind() {
        clickOnElement($x(pressFindXpath));

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
        ElementsCollection purchaseCards = $$x(cardItemXpath);
        return purchaseCards;
    }

    /**
     * Проверяет кнопку "Загрузить ещё" на наличие
     */
    public boolean checkButtonLoadMoreIsDisplayed() {
  //      sleep(900);
        return $(loadMoreCss).isDisplayed();
    }

    /**
     * Нажимает кнопку "Загрузить ещё"
     */
    public void clickLoadMore() {
        clickOnElement($(loadMoreCss));
        $x(loaderWaitXpath).shouldNotBe(Condition.visible, Duration.ofMillis(10000));

    }

    /**
     * Получает кол-во карточек закупок
     */
    public int getCardsCount() {
        return Integer.parseInt($x(cardsCountXpath).getText().replaceAll(" ", ""));
    }

    /**
     * Открывает и закрывает окно "Поможем в любой ситуации"
     */
    public void openAndCloseHelpWindow() {
        clickOnElement($x(openHelpWindowXpath));
        clickOnElement($x(closeHelpWindowXpath));
    }

    public String getPurchaseNumberValue(SelenideElement cardPurchase) {
        SelenideElement cardPurchaseNumber = cardPurchase.find(By.xpath(purchaseNumberXpath)).scrollIntoView(false);
        String purchaseNumberValue = cardPurchaseNumber.getText();
        String numberValue = purchaseNumberValue.replaceAll("[^0-9]", "");
        return numberValue;

    }

    public void searchPurchaseByNumber(String purchaseNumber) {
        $x(inputSearchValueXpath).shouldBe(Condition.visible).sendKeys(purchaseNumber);
        clickOnElement($x(pressFindingBtnXpath));
        $x(loaderWaitXpath).shouldNotBe(Condition.visible, Duration.ofMillis(10000));

    }

    public void deletePreviousNumber() {
        clickOnElement($x(deleteFindElementXpath));
    }
}
