package com.nexus.demo;

import com.microsoft.playwright.*;
import com.nexus.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
Test Api calls with playwright library
benefits
Single library for UI and API
Test your server API.
Prepare server side state before visiting the web application in a test.
Validate server side post-conditions after running some actions in the browser.
   */
public class DemoApiTest extends BaseTest {
    Playwright playwright;
    @Test
    public void demoApit(){
        playwright = Playwright.create();
        APIRequest pRequest =playwright.request();
        APIRequestContext requestContext= pRequest.newContext(new APIRequest.NewContextOptions()
                .setStorageStatePath(path)
                .setIgnoreHTTPSErrors(true)
        );
        APIResponse response =requestContext.get("https://da-dev1.test.nexusgroup.com:8443/swagger/#/AccessRule/getAccessRules");
        System.out.println("STATUS:-"+ response.status() );
        Assert.assertTrue(response.ok(), "error fetching API status");

    }
}
