package Utils;


import net.serenitybdd.core.pages.PageObject;


public class Launch {

    PageObject page;
    public void navigateTo (String URL){
        page.setDefaultBaseUrl(URL);
        page.open();
    }
}
