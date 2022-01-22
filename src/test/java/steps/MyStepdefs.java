package steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AdvancedSearchPage;
import pages.RtsTenderPage;
import pages.Supplier223Page;

import java.io.FileWriter;

import org.apache.log4j.Logger;

import static com.codeborne.selenide.Selenide.open;

public class MyStepdefs {
    Logger logger223 = Logger.getLogger(String.valueOf(MyStepdefs.class));
    private final RtsTenderPage rtsTenderPage = new RtsTenderPage();
    private final Supplier223Page supplier223Page = new Supplier223Page();
    private final AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage();


    @And("открыть сайт Rts-tender.ru")
    public void openPageRts() {
        PropertyConfigurator.configure("src\\test\\resources\\log4j.properties");
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        open("https://www.rts-tender.ru/");

    }

    @And("перейти в футере на 223 поставщик")
    public void openSuppier233Page() {
        rtsTenderPage.clickLink223();

    }

    @And("открыть страницу расширенного поиска")
    public void openSearch() {
        supplier223Page.clickSearchLink();
    }

    @And("открыть настройки поиска")
    public void openSearchSettings() {
        advancedSearchPage.clickSearchSettingsButton();

    }

    @And("выбирать 615-ПП РФ")
    public void pprfChose() {
        advancedSearchPage.clickCheckBox615PPRF();
    }

    @And("исключить совместные закупки")
    public void notJoinPurchase() {
        advancedSearchPage.clickNotJoinPurchase();
    }

    @And("установить фильтры по датам. Подача заявок с, по \\(сегодня)")
    public void filterInstalling() {
        advancedSearchPage.clickDateFilters();
        advancedSearchPage.setDateFrom();
//        advancedSearchPage.clickDateTo();
//        advancedSearchPage.clickDateToday();
    }

    @And("установить регион поставки - Алтайский край")
    public void regionInstalling() {
        advancedSearchPage.clickRegionDelivery();
        advancedSearchPage.clickRegionAltay();
    }

    @And("нажать найти")
    public void pressFind() {
        advancedSearchPage.clickFind();
    }


    @And("выбрать на странице начальную цену у всех закупок, их кол-во и создать лог файл")
    public void startPriceChose() {
        try {
            FileWriter fileWriter = new FileWriter("log223.txt", true);
            advancedSearchPage.openAndCloseHelpWindow();

            int cardNumber = advancedSearchPage.getCardsCount();
            int numbersOfCycles = 50;
            while (advancedSearchPage.checkButtonLoadMoreIsDisplayed()) {
                advancedSearchPage.clickLoadMore();
                numbersOfCycles--;
                if (numbersOfCycles == 0) {
                    throw new Exception("Превышено число итераций");
                }
            }

            ElementsCollection purchaseCards = advancedSearchPage.getCards();
            for (int i = 0; i < cardNumber; i++) {
                SelenideElement card = purchaseCards.get(i);
                String logFor223Test = i + 1 + ") " + advancedSearchPage.getItemPrice(card) + "; " + advancedSearchPage.getSupplieValue(card) + "\n";
                fileWriter.write(logFor223Test);
                fileWriter.flush();
                logger223.info(logFor223Test);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
