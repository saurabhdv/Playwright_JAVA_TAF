package com.nexus.demo.DA.pages.Admin;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class adminInterfacePage {

    private Page pg;
    public adminInterfacePage(Page page) {
        pg = page;
    }

    public adminLoginPage clickAdminLogon() {
        pg.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Log on")).click();
        return new adminLoginPage(pg);
    }
}
