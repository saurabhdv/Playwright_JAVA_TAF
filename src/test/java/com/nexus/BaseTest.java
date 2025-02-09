package com.nexus;

import com.microsoft.playwright.Page;
import com.nexus.demo.DA.pages.Admin.adminHomePage;
import com.nexus.demo.DA.pages.Admin.adminInterfacePage;
import com.nexus.demo.DA.pages.Admin.adminLoginPage;
import com.nexus.demo.factory.initBrowser;
import com.nexus.demo.factory.initProperties;
import org.testng.annotations.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class BaseTest {
    protected Page page;
    protected adminInterfacePage adminInterfacePage;
    protected adminLoginPage adminLoginPage;
    protected adminHomePage adminHomePage;
    protected initBrowser initialBrowser;
    protected static Properties prop;
    protected String admin_Url;
    public Path path = Paths.get("src/main/Test-Dependencies/state1.json");

    @BeforeSuite
    public static void initProp() {
        prop = new initProperties().init_Property();
    }

    /*Test Isolation
    Playwright has the concept of a BrowserContext which is an in-memory isolated browser profile.
    It's recommended to create a new BrowserContext for each test to ensure they don't interfere with each other.

     */

    @BeforeClass
    public void setup() {
        initialBrowser = new initBrowser(prop);
        admin_Url = prop.getProperty("admin_Url");
        Page pg= initialBrowser.createNewPage(admin_Url);
        adminInterfacePage = new adminInterfacePage(pg);
        adminLoginPage = adminInterfacePage.clickAdminLogon();
        adminHomePage = adminLoginPage.adminLogin(prop);
    }

    @AfterClass
    public void closeBrowser() {
        initialBrowser.CloseBrowserContext();
        initialBrowser.ClosePlayWrightInstance();
    }


}
