package com.nexus.demo.DA.pages.Admin;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.Properties;

public class adminLoginPage {
    private Page pg;

    private final String elementPassword ="input[name='password']";
    private final String elementUsername ="input[name='userName']";
    public adminLoginPage(Page page) {
        pg = page;
    }

    public String getTextBoxPassword() {
        return elementPassword;
    }

    public String getTextBoxUsername() {
        return elementUsername;
    }

    public void setTextBoxPassword(String password) {
        pg.locator(elementPassword).fill(password);
    }

    public void setTextBoxUsername(String uname) {
        // best practice to use elements byRole
        pg.locator(elementUsername).fill(uname);
    }

    public adminHomePage adminLogin(Properties p) {
        setTextBoxUsername( p.getProperty("admin_Username"));
        setTextBoxPassword(p.getProperty("admin_password"));
        pg.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Submit Log On")).click();
        return new adminHomePage(pg);
    }

}
