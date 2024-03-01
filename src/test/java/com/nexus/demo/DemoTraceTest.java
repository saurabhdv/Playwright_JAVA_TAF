package com.nexus.demo;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.nexus.BaseTest;
import com.nexus.demo.DA.pages.Admin.adminHomePage;
import com.nexus.demo.DA.pages.Admin.adminInterfacePage;
import com.nexus.demo.DA.pages.Admin.adminLoginPage;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.regex.Pattern;

public class DemoTraceTest extends BaseTest {

    @Test
    public void demoTracing(){


        Page tracerPage = initialBrowser.createNewTracerPage(admin_Url);

        adminLoginPage traceAdminLoginPage  = new adminInterfacePage(tracerPage).clickAdminLogon();


        adminHomePage traceAdminHomePage  = traceAdminLoginPage.adminLogin(prop);
        traceAdminHomePage.clickManageAccountsAndStorage();

        tracerPage.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./OutPut-Files/screenshot1.png")));

        /* Assertions
        Playwright provides assertThat overloads which will wait until the expected condition is met.
        assertions from Playwright
        */
        PlaywrightAssertions.assertThat(tracerPage).hasTitle(Pattern.compile("Smart ID Digital Access"));

        initialBrowser.stopTracerPage();
    }
}
