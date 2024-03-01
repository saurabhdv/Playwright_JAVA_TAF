package com.nexus.demo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.nexus.BaseTest;
import com.nexus.demo.DA.pages.Admin.adminInterfacePage;
import com.nexus.demo.DA.pages.Admin.adminLoginPage;
import com.nexus.demo.DA.pages.ManageAccountsAndStorage.*;
import com.nexus.demo.factory.initBrowser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class DemoTest extends BaseTest {

    manageAccountsAndStorage manageAccountsAndStorage;

    @Test
    public void demo1(){
        /* Although playwright handles wait but can add waits if needed.
        page.waitForLoadState(); */

        /* Locators
        Locator are the central piece of Playwright's auto-waiting and retry-ability.
        element is Attached to the DOM
        element is Visible
        element is Stable, as in not animating or completed animation
        element Receives Events, as in not obscured by other elements
        element is Enabled
         */

        Assert.assertTrue(adminHomePage.getHomePageTitle().contains("Smart ID"));
    }

    @Test
    public void demo2(){
        manageAccountsAndStorage = adminHomePage.clickManageAccountsAndStorage();
        contextStorage(initialBrowser);
    }

    public void contextStorage(initBrowser baseBrowser){

        // Save storage state into the Json file

        baseBrowser.getBrowserContext().storageState(
                new BrowserContext.StorageStateOptions().setPath(path));

        // Create a new Browser context with the saved storage state to reuse the authentication
        initBrowser newBrowser2 =new initBrowser(prop);
        Browser browser2 = newBrowser2.getBrowser();
        BrowserContext context2 = browser2.newContext(
                new Browser.NewContextOptions().setStorageStatePath(path));

        Page page2;
        page2 = context2.newPage();
        page2.navigate(admin_Url);

        page2.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Log on")).click();
        //SKIP LOGIN STEPS
        page2.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Manage Accounts and Storage")).click();
        // Logged in

        page2.close();
        browser2.close();

    }




}