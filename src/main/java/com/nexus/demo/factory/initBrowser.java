package com.nexus.demo.factory;
import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

public class initBrowser {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext, tracebrowserContext;
    Page page, tracepage;

    static String urlPath = "https://da-admin.swedencentral.cloudapp.azure.com:8443/";

    public initBrowser(Properties p) {
String headless = p.getProperty("headless");
        String[] browserArgs = {
                "--disable-web-security",
                "--no-sandbox",
                "--start-maximized",
                "--ignore-certificate-errors",
                "--PageLoadStrategy.NORMAL"
        };
        playwright= Playwright.create();

        switch (p.getProperty("browser").trim().toLowerCase()) {

            case "firefox" ->
                    browser= playwright.firefox().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(false).setArgs(Arrays.asList(browserArgs))
                );
            case "safari" ->
                    browser= playwright.webkit().launch(
                            new BrowserType.LaunchOptions()
                                    .setHeadless(false).setArgs(Arrays.asList(browserArgs))
                    );
            default->
                    browser= playwright.chromium().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(false).setArgs(Arrays.asList(browserArgs))
                );

        }

    }

    public Page getPage() {
        return page;
    }
    public Browser getBrowser() {
        return browser;
    }

    public BrowserContext getBrowserContext() {
        return browserContext;
    }

    public Response navigateToPage(Page page,String urlPath) {
        return page.navigate(urlPath);
    }

    public Page createNewPage(String url) {
        browserContext= getBrowser().newContext();
        page =browserContext.newPage();
        page.navigate(url);
        return page;
    }

    public void ClosePlayWrightInstance( ) {
        playwright.close();
    }

    public void CloseBrowserContext( ) {
        page.close();
        browserContext.close();
    }

    public Page createNewTracerPage(String url) {
        tracebrowserContext= getBrowser().newContext();
        tracebrowserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        tracepage =tracebrowserContext.newPage();
        tracepage.navigate(url);
        return tracepage;
    }

    public void stopTracerPage() {
        tracebrowserContext.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("./OutPut-Files/trace.zip")));
    }

}
