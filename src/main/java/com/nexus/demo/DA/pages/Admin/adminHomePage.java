package com.nexus.demo.DA.pages.Admin;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.nexus.demo.DA.pages.ManageAccountsAndStorage.manageAccountsAndStorage;

import java.nio.file.Paths;
import com.nexus.demo.factory.initBrowser;
public class adminHomePage {

    private Page pg;
    public adminHomePage(Page page) {
        pg = page;
    }

public manageAccountsAndStorage clickManageAccountsAndStorage(){
    pg.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Manage Accounts and Storage")).click();
    return new manageAccountsAndStorage(pg);
}



    public String getHomePageTitle(){return pg.title();}

}
