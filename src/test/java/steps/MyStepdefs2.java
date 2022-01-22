package steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import pages.AdvancedSearchPage;
import io.cucumber.java.en.And;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class MyStepdefs2 {
    private final AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage();

    @And("собрать доступные номера закупок со страницы")
    public void getAvailablePurchaseNumbersFromPage() {
        advancedSearchPage.openAndCloseHelpWindow();
        try {
            int cardNumber = advancedSearchPage.getCardsCount();
            int numbersOfCycles = 1000;
            while (advancedSearchPage.checkButtonLoadMoreIsDisplayed()) {
                if (numbersOfCycles == 0) {
                    throw new Exception("Превышено число итераций");

                }

                advancedSearchPage.clickLoadMore();
                numbersOfCycles--;
            }
            sleep(1000);
            ElementsCollection purchaseCards = advancedSearchPage.getCards();
            ArrayList<String> numbersPurchaseFromCards = new ArrayList<>();
            for (int i = 0; i < cardNumber; i++) {
                SelenideElement card = purchaseCards.get(i);
                numbersPurchaseFromCards.add(advancedSearchPage.getPurchaseNumberValue(card));
            }
            for (String number: numbersPurchaseFromCards) {
                advancedSearchPage.searchPurchaseByNumber(number);
                isResultsCompare(number);
                isSingleElementDisplayed();
                advancedSearchPage.deletePreviousNumber();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }

    public void isResultsCompare(String purchaseNumber) {
        ElementsCollection numberPurchaseFromFindingCards = advancedSearchPage.getCards();
        Assert.assertEquals(purchaseNumber, advancedSearchPage.getPurchaseNumberValue(numberPurchaseFromFindingCards.get(0)));

    }
    public void isSingleElementDisplayed(){
        Assert.assertEquals(1, advancedSearchPage.getCardsCount());

    }
    

}
