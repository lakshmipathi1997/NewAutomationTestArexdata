package tests;

import java.util.Random;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.Admin_AlertsPage;
import pages.Admin_ApplicationLogPage;
import pages.Admin_Manage_SettingsPage;
import pages.Admin_UsersPage;
import pages.LoginPage;
import utility.DriverTestCase;
import utility.ExecutionLog;
import utility.PropertyReader;
import utility.QAAnnotations.TestCaseInfo;

public class Admin_Manage_SettingsTest extends DriverTestCase {
	private static LoginPage loginPage;
	private static Admin_Manage_SettingsPage admin_Manage_SettingsPage;

	PropertyReader propertyReader = new PropertyReader();
	String userName = propertyReader.readProperty("username");
	String password = propertyReader.readProperty("password");

	Random random = new Random();
	int randNo = random.nextInt(100000);

	@BeforeTest
	public void initForManageSetting() throws Exception {
		setup();
		loginPage = new LoginPage(getWebDriver());
		new Admin_ApplicationLogPage(getWebDriver());
		new Admin_AlertsPage(getWebDriver());
		new Admin_UsersPage(getWebDriver());
		admin_Manage_SettingsPage = new Admin_Manage_SettingsPage(getWebDriver());

		loginPage.loginIntoApplication(userName, password);

	}

	@TestCaseInfo(testCaseID = "6056", title = "Verify user is able to change the language from Default language dropdown")
	@Test(priority = 1, groups = { "SmokeTest" })
	public void testUserIsAbleToSelectLanguageFromDefaultLanguageFropdown() throws Exception {

		try {
			admin_Manage_SettingsPage.selectAdminManageSettingMenu();
			admin_Manage_SettingsPage.languageSettingOfApplication("Spanish");
			admin_Manage_SettingsPage.verifySelectedLanguage("Spanish");
			admin_Manage_SettingsPage.languageSettingOfApplication("English");
			admin_Manage_SettingsPage.clickOnSaveButton();
			admin_Manage_SettingsPage.verifySelectedLanguage("English");

		} catch (Error e) {
			getScreenshot("testUserIsAbleToSelectLanguageFromDefaultLanguageFropdown");
			ExecutionLog.logErrorMessage(e);
			throw e;
		} catch (Exception e) {
			getScreenshot("testUserIsAbleToSelectLanguageFromDefaultLanguageFropdown");
			ExecutionLog.logExceptionMessage(e);
			throw e;
		} 
	}

}
