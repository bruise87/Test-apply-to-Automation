package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public abstract class AbstractBasicPage {

    public void clickOnElement(SelenideElement element) {
        element.shouldBe(Condition.visible).click();
    }
}
