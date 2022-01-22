package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class Supplier223Page {
    private final String supplierSearchLinkXPATH = "//h2[text() = 'Расширенный поиск']";

    /**
     * Переходит на страницу Расширенного поиска
     */
    public void clickSearchLink() {
        $x(supplierSearchLinkXPATH).shouldBe(Condition.visible).click();
    }
}


