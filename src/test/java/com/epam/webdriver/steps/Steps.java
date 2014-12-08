package com.epam.webdriver.steps;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.epam.webdriver.logger.LoggerUtils;
import com.epam.webdriver.pages.FilterPage;
import com.epam.webdriver.pages.ForwardPage;
import com.epam.webdriver.pages.LoginPage;
import com.epam.webdriver.pages.MailPage;
import com.epam.webdriver.pages.SentPage;
import com.epam.webdriver.pages.SettingsPage;
import com.epam.webdriver.pages.SpamPage;
import com.epam.webdriver.pages.StarredPage;
import com.epam.webdriver.pages.ThemesPage;
import com.epam.webdriver.pages.TrashPage;

public class Steps {

	private WebDriver driver;

	public void initBrowser() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		LoggerUtils.info("Browser started");
	}

	public void closeDriver() {
		driver.quit();
		LoggerUtils.info("Browser closed");
	}

	public void loginGmail(String username, String password) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openPage();
		loginPage.login(username, password);
		LoggerUtils.info("Login performed");
	}

	public void sendLetter(String recipient) {
		MailPage mailPage = new MailPage(driver);
		mailPage.send(recipient);
		LoggerUtils.info("Letter was sent");

	}

	public void enterSubjectAntBobyOfLetter(String subject, String message) {
		MailPage mailPage = new MailPage(driver);
		mailPage.enterSubjectAntBobyOfLetter(subject, message);
		LoggerUtils.info("The subject and body were entered");

	}

	public boolean isLetterGoToSpam(String subject) {
		SpamPage spamPage = new SpamPage(driver);
		spamPage.openPage();
		return spamPage.isTheLetterInSpam(subject);

	}

	public void openSpamPage() {
		SpamPage spamPage = new SpamPage(driver);
		spamPage.openPage();
		LoggerUtils.info("The spam page was opened");
	}

	public void markLetterAsNotSpam(String subject) {
		SpamPage spamPage = new SpamPage(driver);
		spamPage.markLetterAsNotSpam(subject);
		LoggerUtils.info("The letter was marked as not spam");

	}

	public void addLetterToSpam(String subject) {
		MailPage mailPage = new MailPage(driver);
		mailPage.addLetterToSpam(subject);
		LoggerUtils.info("Letter was added to spam");
	}

	public void openSettingsPage() {
		MailPage mailPage = new MailPage(driver);
		mailPage.clickSettings();
		LoggerUtils.info("The setting page was opened");
	}

	public void openForwardPage() {

		SettingsPage settingPage = new SettingsPage(driver);
		settingPage.goToForwardPage();
		LoggerUtils.info("The forward page was opened");

	}

	public void openFilterPage() {
		SettingsPage settingPage = new SettingsPage(driver);
		settingPage.goToFiltersPage();
		LoggerUtils.info("The filter page was opened");
	}

	public void openThemesPage() {
		SettingsPage settingPage = new SettingsPage(driver);
		settingPage.goToThemesPage();
		LoggerUtils.info("The themes page was opened");

	}

	public void createANewFilter(String fieldFrom) {
		FilterPage filterPage = new FilterPage(driver);
		filterPage.createANewFilter(fieldFrom);
		LoggerUtils.info("A new filter was created");
	}

	public void setForwardToUser3(String forwardingEmail) {

		ForwardPage forwardPage = new ForwardPage(driver);
		forwardPage.setForwardToUser3(forwardingEmail);
		LoggerUtils.info("Forward to user3 was set");

	}

	public void confirmForwarding() {
		MailPage mailPage = new MailPage(driver);
		mailPage.openConfirmLetter();
		mailPage.confirmLetterForForwarding();
		LoggerUtils.info("User3 confirmed forward");
	}

	public void setForwardACopyOfIncomingMailTo() {
		ForwardPage forwardPage = new ForwardPage(driver);
		forwardPage.setForwardACopyOfIncomingMailTo();
		LoggerUtils
				.info("Radiobutton Forward a copy of incoming mail to was choosen");

	}

	public void attachFile(long size) throws IOException, AWTException {
		MailPage mailPage = new MailPage(driver);
		mailPage.attachFile(size);
		LoggerUtils.info("The file was attached");

	}

	public void openTrashPage() {
		// MailPage mailPage = new MailPage(driver);
		// mailPage.openTrashPage();
		TrashPage trashPage = new TrashPage(driver);
		trashPage.openPage();
		LoggerUtils.info("The trash page was opened");

	}

	public boolean isLetterFromUser1WithAttachInTrash(String subject) {
		TrashPage trashPage = new TrashPage(driver);
		return trashPage.isLetterFromUser1WithAttachInTrash(subject);

	}

	public boolean isLetterFromUser1WithoutAttachInInboxAndNotMArkAsImportant(
			String subject) {
		MailPage mailPage = new MailPage(driver);
		return mailPage
				.isLetterFromUser1WithoutAttachInInboxAndNotMArkAsImportant(subject);

	}

	public boolean isLetterFromUser1WithoutAttachInInbox(String subject) {
		MailPage mailPage = new MailPage(driver);
		return mailPage.isLetterFromUser1WithoutAttachInInbox(subject);

	}

	public void openInboxPage() {
		MailPage mailPage = new MailPage(driver);
		mailPage.openInbox();
		LoggerUtils.info("The inbox page was opened");

	}

	public boolean isWarningMessageAppeared() {
		MailPage mailPage = new MailPage(driver);
		return mailPage.isWarningMessageAppeared();

	}

	public void changeBackgroundPtoto(long size) throws IOException,
			AWTException {
		ThemesPage themesPage = new ThemesPage(driver);
		themesPage.changeBackgroundPhoto(size);
		LoggerUtils.info("Attempt to download an improper file has occurred");

	}

	public boolean isErrorMessageApeared() {
		ThemesPage themesPage = new ThemesPage(driver);
		return themesPage.isErrorMessageApeared();

	}

	public void editRecepient(String recipient) {
		MailPage mailPage = new MailPage(driver);
		mailPage.editRecepient(recipient);
		LoggerUtils.info("The recipient was entered");

	}

	public void clickSendInNewLetter() {
		MailPage mailPage = new MailPage(driver);
		mailPage.clickSendInNewLetter();
		LoggerUtils.info("The button send was clicked");

	}

	public void openNewLetter() {
		MailPage mailPage = new MailPage(driver);
		mailPage.openNewLetter();
		LoggerUtils.info("New letter was opened");
	}

	public List<String> attachEmoticonsInNewLetter(int firstSmile,
			int secondSmile) throws AWTException {
		MailPage mailPage = new MailPage(driver);
		return mailPage.attachEmoticonsInNewLetter(firstSmile, secondSmile);

	}

	public boolean isTheSentEmoticonsAreAtTheTextArea(
			List<String> sendEmoticonsNames) {
		MailPage mailPage = new MailPage(driver);
		return mailPage.isTheSentEmoticonsAreAtTheTextArea(sendEmoticonsNames);

	}

	public void chooseBeachTheme() {
		ThemesPage themePage = new ThemesPage(driver);
		themePage.chooseBeachTheme();
		LoggerUtils.info("Beach Theme was clicked");
	}

	public void chooseLightTheme() {
		ThemesPage themePage = new ThemesPage(driver);
		themePage.chooseLightTheme();
		LoggerUtils.info("The default theme was choosen");
	}

	public boolean isBeachThemeChoosen() {
		ThemesPage themePage = new ThemesPage(driver);
		return themePage.isBeachThemeWasChoosen();
	}

	public void createSignature(String signature) {
		SettingsPage settingPage = new SettingsPage(driver);
		settingPage.createSignature(signature);
		LoggerUtils.info("Signature was created");

	}

	public void clickCompose() {
		MailPage mailPage = new MailPage(driver);
		mailPage.clickCompose();
		LoggerUtils.info("The button Compose was clicked");

	}

	public String getSignatureOfNewMessage() {
		MailPage mailPage = new MailPage(driver);
		return mailPage.getSignatureOfNewMessage();
	}

	public void deleteSignatureAfterTest() {
		SettingsPage settingPage = new SettingsPage(driver);
		settingPage.deleteSignatureAfterTest();
		LoggerUtils.info("The signature was deleted");

	}

	public String clickStarNearMessage() {
		MailPage mailPage = new MailPage(driver);
		LoggerUtils.info("The star near the message was clicked");
		return mailPage.clickStarNearMessage();

	}

	public void closeMessageWindow() {
		MailPage mailPage = new MailPage(driver);
		mailPage.closeMessageWindow();
	}

	public void openStarredPage() {
		StarredPage starredPage = new StarredPage(driver);
		starredPage.openPage();
		LoggerUtils.info("The starred page was opened");
	}

	public String getTopicAndBodyOfTheLetterInStarredFolder() {
		StarredPage starredPage = new StarredPage(driver);
		return starredPage.getTopicAndBodyOfTheLetterInStarredFolder();
	}

	public void deleteAllLettersInStarredFolder() {
		StarredPage starredPage = new StarredPage(driver);
		starredPage.deleteAllLetters();
		LoggerUtils.info("Letters in starred folder were deleted");
	}

	public String getTopicAndBodyOfTheLetterInInboxFolder() {
		MailPage mailPage = new MailPage(driver);
		return mailPage.getTopicAndBodyOfTheLetter();
	}

	public void deleteAllLetersInInbox() {
		MailPage mailPage = new MailPage(driver);
		mailPage.deleteAllLetters();
		LoggerUtils.info("Letters in inbox folder were deleted");
	}

	public void createNewParentLabel(String nameShortcut) {
		MailPage mailPage = new MailPage(driver);
		mailPage.createNewLabel(nameShortcut);
		LoggerUtils.info("New parent shortcut was created");
	}

	public void addInsertedLabel(String parentShortcutName,
			String childShortcutName) {
		MailPage mailPage = new MailPage(driver);
		mailPage.addInsertedLabel(parentShortcutName, childShortcutName);
		LoggerUtils.info("Inserted shortcut was created");
	}

	public boolean isCreatedShortcutIsPresent(String parentShortcutName,
			String childShortcutName) {
		MailPage mailPage = new MailPage(driver);
		return mailPage.isCreatedShortcutIsPresent(parentShortcutName,
				childShortcutName);
	}

	public void clickTriangle(String shortcutName) {
		MailPage mailPage = new MailPage(driver);
		mailPage.clickTriangle(shortcutName);
		LoggerUtils.info("Triangle near shortcut was clicked");
	}

	public void clickLabelColorForShortcut() {
		MailPage mailPage = new MailPage(driver);
		mailPage.clickLabelColorForShortcut();
		LoggerUtils.info("Label color for shortcut was clicked");
	}

	public void changeShortcutColor(int numberOfColor) {
		MailPage mailPage = new MailPage(driver);
		mailPage.changeShortcutColor(numberOfColor);
		LoggerUtils.info("Shortcut color was changed");

	}

	public void clickArrowNearParentShortcut() {
		MailPage mailPage = new MailPage(driver);
		mailPage.clickArrowNearParentShortcut();
		LoggerUtils.info("Arrow near shortcut was clicked");

	}

	public boolean isColorOfShortcutWasChanged(int numberOfColor) {
		MailPage mailPage = new MailPage(driver);
		return mailPage.isColorOfShortcutWasChanged(numberOfColor);
	}

	public void deleteShortcut(String parentShortcutName,
			String childShortcutName) {
		MailPage mailPage = new MailPage(driver);
		mailPage.deleteShortcut(parentShortcutName, childShortcutName);

	}

	public boolean isBorthShortcutsWereDeleted(String parentShortcutName,
			String childShortcutName) {
		MailPage mailPage = new MailPage(driver);
		return mailPage.isBorthShortcutsWereDeleted(parentShortcutName,
				childShortcutName);
	}

	public void selectVacationResponderOn() {
		SettingsPage settingPage = new SettingsPage(driver);
		settingPage.selectVacationResponderOn();
		LoggerUtils.info("Vacation responder on was selected");
	}

	public void enterDataInVacation(String subject, String message) {
		SettingsPage settingPage = new SettingsPage(driver);
		settingPage.enterDataInVacation(subject, message);

		LoggerUtils.info("Data in vacation was entered");

	}

	public boolean isVacationWasSet(String subject) {
		MailPage mailPage = new MailPage(driver);
		return mailPage.isVacationWasSet(subject);

	}

	public boolean isResponderLetterInInbox(String subject, String message) {

		MailPage mailPage = new MailPage(driver);
		return mailPage.isResponderLetterInInbox(subject, message);
	}

	public void openSentPage() {
		SentPage sentPage = new SentPage(driver);
		sentPage.openPage();
		LoggerUtils.info("The sent page was opened");

	}

	public void deleteAllLetersInSent() {
		SentPage sentPage = new SentPage(driver);
		sentPage.deleteAllLetters();
		LoggerUtils.info("Letters in sent folder were deleted");
	}

	public void deleteVacation() {
		SettingsPage settingPage = new SettingsPage(driver);
		settingPage.deleteVacation();
		LoggerUtils.info("Vacation was deleted");
	}

	public void removeAllMessagesAfterMethodFromSpamToInbox() {
		SpamPage spamPage = new SpamPage(driver);
		spamPage.removeAllMessagesAfterMethodFromSpamToInbox();
		LoggerUtils.info("All leters were removed from spam to inbox");
	}

	public void deleteFilter() {
		FilterPage filterPage = new FilterPage(driver);
		filterPage.deleteFilter();
		LoggerUtils.info("Filter was deleted");
	}
	
	public void deleteForward() throws AWTException {
		ForwardPage forwardPage = new ForwardPage(driver);
		forwardPage.deleteForward();
		LoggerUtils.info("Forward was deleted");
	}

}
