package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class RtsTenderPage {
    private final String supplier223LinkInFooter = "//nav[@class='nav nav-223-fz']//a[text()='Поставщикам']";

    /**
     * Переходит на страницу Поставщика 223ФЗ
     */
    public void clickLink223() {
        $x(supplier223LinkInFooter).shouldBe(Condition.visible).click();
    }
}
