package com.epam.webdriver.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.epam.webdriver.logger.LoggerUtils;
import com.epam.webdriver.utils.Utils;
import com.epam.webdriver.utils.WaitUtils;

public class MailPage extends AbstractPage {

	private final String BASE_URL = "https://mail.google.com/mail/";

	private final static String SHORTCUT_OPTIONS_GENERALIZED_XPATH = "//div[@class='p8'][ancestor::div[preceding-sibling::div[descendant::a[@title='%s']]][@class='nL aig']][ancestor::div[@gh='cl']]";
	private final static String SHORTCUT_CHILD_OPTIONS_GENERALIZED_XPATH = "//a[@class='J-Ke n0'][preceding::a[@title='%s']][ancestor::div[@gh='cl']]";
	private final static String SHORTCUT_NAME_XPATH = "//div[@class='aio aip'][descendant::a[@title='%s']]";

	private final static String MESSAGE_THAT_SHORTCUT_WAS_REMOVED = "//div[@class='vh'][contains(text(),'removed')]";

	@FindBy(xpath = MESSAGE_THAT_SHORTCUT_WAS_REMOVED)
	private WebElement messageThatShortcutWasRemoved;

	@FindBy(css = "div.T-I.J-J5-Ji.T-I-KE.L3")
	private WebElement buttonCompose;

	@FindBy(css = "textarea.vO")
	private WebElement editRecipient;

	@FindBy(css = "input.aoT")
	private WebElement editSubject;

	@FindBy(xpath = "//div[@class='Am Al editable LW-avf']")
	private WebElement editTextOfLetter;

	@FindBy(css = "div.T-I.J-J5-Ji.aoO.T-I-atl.L3")
	private WebElement buttonSend;

	@FindBy(css = "div.oZ-jc.T-Jo.J-J5-Ji")
	private WebElement chooseLetter;

	@FindBy(css = "div.asl.T-I-J3.J-J5-Ji")
	private WebElement buttonAddToSpam;

	@FindBy(css = "div.vh")
	private WebElement confirmMessageForAddToSpam;

	@FindBy(xpath = CHOOSE_ALL_LETTERS_FROM_INBOX)
	private WebElement chooseAllLeterFromInbox;

	private final static String CHOOSE_ALL_LETTERS_FROM_INBOX = "//div[@class='T-Jo-auh']";

	@FindBy(xpath = "//div[@aria-label='Delete'][ancestor::div[@style='']]")
	private WebElement deleteAllLetters;

	@FindBy(xpath = "//div[@class='T-I J-J5-Ji ash T-I-ax7 L3'][ancestor::div[@class='Cr aqJ'][preceding-sibling::div[@gh='mtb']]]")
	private WebElement buttonSettings;

	@FindBy(xpath = "//div[@class='J-N aMS']")
	private WebElement buttonSettingsInContextMenu;

	@FindBy(xpath = "//div[@class='yW']/span[@class='zF'][@name='Gmail Team']")
	private WebElement linkToOpenConfirmLetterLetter;

	@FindBy(xpath = "//div[@class='a3s']/a[@target='_blank']")
	private WebElement linkToConfirmLetterForForwarding;

	@FindBy(xpath = "//div[@class='a1 aaA aMZ']")
	private WebElement buttonForAttachFile;

	@FindBy(xpath = "//td[text()='Confirmation Success!']")
	private WebElement confirmationSuccess;

	@FindBy(xpath = "//input[@name='q']")
	private WebElement searchField;

	@FindBy(xpath = "//button[@class='gbqfb']")
	private WebElement buttonSearch;

	@FindBy(xpath = NEW_LETTER)
	private WebElement newLetter;

	private final static String NEW_LETTER = "//div[@class='yW']/span[@class='zF'][ancestor::div[@class='ae4 aDM']]";

	@FindBy(xpath = "//img[@class='ajz']")
	private WebElement imgInfoAboutLetter;

	@FindBy(xpath = "//div[@class='ajB gt']")
	private WebElement infoAboutLetter;

	@FindBy(xpath = "//div[@class='QT aaA aMZ']")
	private WebElement emoticonIcon;

	@FindBy(xpath = "//div[@goomoji]")
	private List<WebElement> emoticons;

	private final static String EMOTICONS_IN_NEW_LETTER = "//img[@goomoji]";

	@FindBy(xpath = "//div[@class='aJJ']")
	private WebElement emotionsWindow;

	@FindBy(xpath = "//div[@class='T-I J-J5-Ji T-I-atl L3'][text()='Insert']")
	private WebElement buttonInsert;

	@FindBy(xpath = "//span[@class='ag npDjle']")
	private WebElement messageThatLetterWasSend;

	@FindBy(xpath = "//img[@goomoji]")
	private WebElement emoticonsInLetter;

	@FindBy(xpath = "//div[@class='y6']")
	private WebElement topicAndBodyOfTheLetter;

	private final static String TOPIC_AND_BODY_OF_THE_LETTER = "//div[@class='y6']";

	@FindBy(xpath = "//div[@class='gmail_signature']")
	private WebElement gmailSignature;

	@FindBy(xpath = "//img[@alt='Close']")
	private WebElement buttonForCloseMessageWindow;

	@FindBy(xpath = "//img[@class='T-KT-JX']")
	private WebElement imgStarToSelect;

	@FindBy(xpath = "//a[contains(text(),'Create new label')][ancestor::div[@class='n3']]")
	private WebElement buttonCreateNewLabel;

	@FindBy(xpath = "//a[contains(text(),'Manage labels')][ancestor::div[@class='n3']]")
	private WebElement buttonManagelabels;

	@FindBy(xpath = "//span[contains(text(),'More')]")
	private WebElement triangleMore;

	@FindBy(xpath = "//a[@title='Drafts']")
	private WebElement buttonDrafts;

	@FindBy(xpath = "//a[@title='Categories']")
	private WebElement buttonCategories;

	@FindBy(xpath = "//input[@class='xx']")
	private WebElement inputNewLabelName;

	@FindBy(xpath = "//button[@class='J-at1-auR']")
	private WebElement buttonCreateLabel;

	@FindBy(xpath = "//div[text()='Add sublabel']")
	private WebElement addSublabel;

	@FindBy(xpath = "//input[following-sibling::label[text()='Nest label under:']]")
	private WebElement checkBoxNestLabelUnder;

	@FindBy(xpath = "//select[@class='xx']")
	private WebElement selectedNameOfParentShortcut;

	@FindBy(xpath = ARROW_NEAR_MY_SHORTCUT)
	private WebElement arrowNearMyShortcut;

	private final static String ARROW_NEAR_MY_SHORTCUT = "//div[contains(@title,'Expand label')][contains(@title,'My shortcut')]";

	@FindBy(xpath = "//span[ancestor::div[@class='J-N-Jz']]")
	private WebElement labelColor;

	@FindBy(xpath = "//div[@class='JA-Kn-Jr-Kw-Jt']")
	private List<WebElement> colorListForShortcut;

	@FindBy(xpath = "//td[descendant::div[@class='JA-Kn-Jr-Kw-Jt']]")
	private List<WebElement> parentOfcolorListForShortcut;

	@FindBy(xpath = "//input[@class='ajH'][following-sibling::label[contains(text(),'Label')]]")
	private WebElement radioButtonLabelWithSublabels;

	@FindBy(xpath = "//button[@name='sc']")
	private WebElement buttonSetColorForShortcut;

	@FindBy(xpath = "//div[@class='J-N-Jz'][contains(text(),'Remove label')]")
	private WebElement removeLabel;

	@FindBy(xpath = "//button[contains(text(),'Delete')]")
	private WebElement buttonDeleteShortcut;

	@FindBy(xpath = "//span[@class='ajP']")
	private List<WebElement> listWithShortcutsInDialog;

	@FindBy(xpath = MESSAGE_AFTER_SET_VACATION)
	private WebElement messageAfterSetVacation;

	private final static String MESSAGE_AFTER_SET_VACATION = "//div[@class='w-MH a6P']";

	private final static String LOADING_BAR_WHEN_ATTACHED_FILE = "//div[@class='dR']";
	private final static String ERROR_MESSAGE_THAT_ATTACHED_FILE_BIGGER_THEN_25MB = "//span[@class='Kj-JD-K7-K0'][contains(text(),'25')]";
	private final static String TOPIC_OF_THE_LETTER = "//div[@class='y6']/span/b[contains(text(),'%s')]";
	// private final static String BOBY_OF_THE_LETTER =
	// "//div[@class='y6']/span[contains(text(),'%s')]";
	private final static String IMAGE_ATTACHMENT = "//img[@alt='Attachments']";

	public MailPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);

	}

	public void send(String recipient) {
		buttonCompose.click();
		editRecipient.sendKeys(recipient);
		editSubject.sendKeys(Utils.getRandomString(5));
		editTextOfLetter.sendKeys(Utils.getRandomString(15));
		buttonSend.click();
		WaitUtils.waitForElementVisible(driver, messageThatLetterWasSend, 5);

	}

	public void clickCompose() {
		buttonCompose.click();
	}

	public void editRecepient(String recipient) {
		editRecipient.sendKeys(recipient);
	}

	public void clickSendInNewLetter() {
		buttonSend.click();
		WaitUtils.waitForElementVisible(driver, messageThatLetterWasSend, 5);

	}

	public List<String> attachEmoticonsInNewLetter(int firstSmile,
			int secondSmile) throws AWTException {
		List<String> emoticonsNames = new ArrayList<String>();
		emoticonIcon.click();
		Actions action = new Actions(driver);
		emoticonsNames.add(emoticons.get(firstSmile).getAttribute("goomoji"));
		action.moveToElement(emoticons.get(firstSmile)).build().perform();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		emoticons.get(secondSmile).click();
		emoticonsNames.add(emoticons.get(secondSmile).getAttribute("goomoji"));
		buttonInsert.click();
		LoggerUtils.info("Emoticons were attached");
		return emoticonsNames;

	}

	public void attachFile(long size) throws IOException, AWTException {
		buttonForAttachFile.click();
		File file = Utils.getRandomFile(size);
		StringSelection ss = new StringSelection(file.getAbsolutePath());
		Utils.javaRobotForAttachFile(ss);
		if (WaitUtils.isElementPresent(driver, LOADING_BAR_WHEN_ATTACHED_FILE)) {
			WaitUtils.waitForElementInvisibility(driver,
					LOADING_BAR_WHEN_ATTACHED_FILE, 120);
		}

	}

	public void enterSubjectAntBobyOfLetter(String subject, String message) {
		editSubject.sendKeys(subject);
		editTextOfLetter.sendKeys(message);

	}

	public void addLetterToSpam(String subject) {

		int i = 0;
		while (WaitUtils.isElementNotPresent(driver,
				String.format(TOPIC_OF_THE_LETTER, subject), 10)) {
			driver.navigate().refresh();
			i++;
			if (i == 12) {
				LoggerUtils
						.error("There are not new message more then 2 minutes!!!");
				Assert.fail();
			}
		}

		chooseLetter.click();
		WaitUtils.waitForElementVisible(driver, buttonAddToSpam, 10);
		buttonAddToSpam.click();
		WaitUtils.waitForElementVisible(driver, confirmMessageForAddToSpam, 10);
		WaitUtils.waitForElementInvisibility(driver,
				TOPIC_AND_BODY_OF_THE_LETTER, 10);

	}

	public void deleteAllLetters() {

		if (WaitUtils.isElementPresent(driver, TOPIC_AND_BODY_OF_THE_LETTER)) {
			chooseAllLeterFromInbox.click();
			deleteAllLetters.click();
			WaitUtils.waitForElementInvisibility(driver,
					"topicAndBodyOfTheLetter", 10);

		}

	}

	public void clickSettings() {
		buttonSettings.click();
		buttonSettingsInContextMenu.click();

	}

	// public void openTrashPage() {
	// searchField.sendKeys("in:trash");
	// buttonSearch.click();
	// driverWait2.until(ExpectedConditions.invisibilityOfElementLocated(By
	// .xpath("//span[@class=v1][text()='Loading...']")));
	// }

	public void openInbox() {
		MailPage mailPage = new MailPage(driver);
		mailPage.openPage();

	}

	public void openConfirmLetter() {
		WaitUtils.waitForElementVisible(driver, linkToOpenConfirmLetterLetter,
				10);
		linkToOpenConfirmLetterLetter.click();
	}

	public void confirmLetterForForwarding() {
		linkToConfirmLetterForForwarding.click();
		Set<String> set = driver.getWindowHandles();
		String newWindowHandle = null;
		int i = 0;
		for (String str : set) {
			if (i == 1) {
				newWindowHandle = str;
				break;
			}
			i++;
		}
		driver.switchTo().window(newWindowHandle);
		WaitUtils.waitForElementVisible(driver, confirmationSuccess, 5);

	}

	public boolean isLetterFromUser1WithoutAttachInInboxAndNotMArkAsImportant(
			String subject)

	{
		int i = 0;
		while (WaitUtils.isElementNotPresent(driver,
				String.format(TOPIC_OF_THE_LETTER, subject), 10)) {
			driver.navigate().refresh();
			i++;
			if (i == 12) {
				LoggerUtils
						.error("There are not new message more then 2 minutes!!!");
				Assert.fail();
			}
		}
		WebElement letter = driver.findElement(By.xpath(String.format(
				TOPIC_OF_THE_LETTER, subject)));

		letter.click();

		if (WaitUtils.isElementNotPresent(driver, IMAGE_ATTACHMENT))

		{
			imgInfoAboutLetter.click();
			if (!infoAboutLetter.getText().contains("Important")) {
				return true;
			}
		}

		return false;
	}

	public boolean isLetterFromUser1WithoutAttachInInbox(String subject) {

		int i = 0;
		while (WaitUtils.isElementNotPresent(driver,
				String.format(TOPIC_OF_THE_LETTER, subject), 10)) {
			driver.navigate().refresh();
			i++;
			if (i == 12) {
				LoggerUtils
						.error("There are not new message more then 2 minutes!!!");
				Assert.fail();
			}
		}
		WebElement letter = driver.findElement(By.xpath(String.format(
				TOPIC_OF_THE_LETTER, subject)));

		letter.click();
		if (WaitUtils.isElementNotPresent(driver, IMAGE_ATTACHMENT)) {
			return true;
		}
		return false;

	}

	public boolean isWarningMessageAppeared() {
		if (WaitUtils.isElementPresent(driver,
				ERROR_MESSAGE_THAT_ATTACHED_FILE_BIGGER_THEN_25MB)) {
			return true;
		}

		return false;

	}

	public void openNewLetter() {
		WaitUtils.waitForElementVisible(driver, newLetter, 60);
		if (WaitUtils.isElementPresent(driver, NEW_LETTER)) {
			newLetter.click();
		} else {
			LoggerUtils.error("There aren't new message");
		}
	}

	public boolean isTheSentEmoticonsAreAtTheTextArea(
			List<String> sendEmoticonsNames) {
		List<String> emoticonsNames = new ArrayList<String>();
		if (WaitUtils.isElementPresent(driver, EMOTICONS_IN_NEW_LETTER)) {
			List<WebElement> emoticonsNamesElements = driver.findElements(By
					.xpath(EMOTICONS_IN_NEW_LETTER));
			for (WebElement e : emoticonsNamesElements) {
				emoticonsNames.add(e.getAttribute("goomoji"));
			}

			if (emoticonsNames.containsAll(sendEmoticonsNames)
					&& emoticonsNames.size() == sendEmoticonsNames.size())
				return true;
		}
		return false;
	}

	public String getSignatureOfNewMessage() {
		return gmailSignature.getText();
	}

	public void closeMessageWindow() {
		buttonForCloseMessageWindow.click();
	}

	public String clickStarNearMessage() {

		WaitUtils.waitForElementVisible(driver, imgStarToSelect, 60);
		imgStarToSelect.click();
		return topicAndBodyOfTheLetter.getText();

	}

	public String getTopicAndBodyOfTheLetter() {
		return topicAndBodyOfTheLetter.getText();
	}

	public void createNewLabel(String labelName) {

		triangleMore.click();
		buttonCreateNewLabel.click();
		WaitUtils.waitForElementVisible(driver, inputNewLabelName, 2);
		inputNewLabelName.sendKeys(labelName);
		buttonCreateLabel.click();

	}

	public void addInsertedLabel(String parentLabelName, String childLabelName) {
		WaitUtils.waitForElementVisible(driver, addSublabel, 2);
		addSublabel.click();
		WaitUtils.waitForElementVisible(driver, inputNewLabelName, 2);
		inputNewLabelName.sendKeys(childLabelName);
		if (checkBoxNestLabelUnder.isSelected()
				&& selectedNameOfParentShortcut.getText().contains(
						parentLabelName)) {
			buttonCreateLabel.click();
		} else {
			Assert.fail();
			LoggerUtils
					.error("Error. Wrong parent of insert shortcut of thr time of creation");
		}

	}

	public boolean isCreatedShortcutIsPresent(String parentShortcutName,
			String childShortcutName) {
		if (WaitUtils.isElementNotPresent(driver, String.format(
				SHORTCUT_CHILD_OPTIONS_GENERALIZED_XPATH, parentShortcutName))) {
			LoggerUtils.error("Error.Created shortcut is present");
			Assert.fail();

		}
		WebElement createdInsertShortcut = driver.findElement(By.xpath(String
				.format(SHORTCUT_CHILD_OPTIONS_GENERALIZED_XPATH,
						parentShortcutName)));
		if (createdInsertShortcut.getText().contains(childShortcutName)) {
			return true;
		}

		return false;
	}

	public void clickTriangle(String shortcutName) {

		Actions action = new Actions(driver);
		action.moveToElement(
				driver.findElement(By.xpath(String.format(SHORTCUT_NAME_XPATH,
						shortcutName)))).build().perform();
		WebElement triangleNearShortcut = driver.findElement(By.xpath(String
				.format(SHORTCUT_OPTIONS_GENERALIZED_XPATH, shortcutName)));
		WaitUtils.waitForElementVisible(driver, triangleNearShortcut, 2);
		triangleNearShortcut.click();

	}

	public void clickLabelColorForShortcut() {
		WaitUtils.waitForElementVisible(driver, labelColor, 2);
		Actions action = new Actions(driver);
		action.moveToElement(labelColor).build().perform();

	}

	public void changeShortcutColor(int numberOfColor) {
		Actions action = new Actions(driver);
		action.moveToElement(colorListForShortcut.get(numberOfColor)).build()
				.perform();
		colorListForShortcut.get(numberOfColor).click();
		WaitUtils.waitForElementVisible(driver, radioButtonLabelWithSublabels,
				2);
		radioButtonLabelWithSublabels.click();
		buttonSetColorForShortcut.click();

	}

	public void clickArrowNearParentShortcut() {
		if (WaitUtils.isElementPresent(driver, ARROW_NEAR_MY_SHORTCUT)) {
			arrowNearMyShortcut.click();
			LoggerUtils.info("Arrow near parent shortcut was clicked");
		}
	}

	public boolean isColorOfShortcutWasChanged(int numberOfColor) {

		Actions action = new Actions(driver);
		WebElement e = driver
				.findElement(By
						.xpath("//div[@class='J-awr J-awr-JE'][contains(text(),'color')]"));
		action.moveToElement(e);
		colorListForShortcut.get(numberOfColor).click();
		if (parentOfcolorListForShortcut.get(numberOfColor)
				.getAttribute("aria-selected").equals("true")) {

			return true;
		}
		return false;
	}

	public void deleteShortcut(String parentShortcutName,
			String childShortcutName) {
		removeLabel.click();
		LoggerUtils.info("Button remove was clicked");
		String name = "";
		for (WebElement e : listWithShortcutsInDialog) {
			String currentName = e.getText().concat(" ");
			name = name.concat(currentName);
		}
		if (name.contains(parentShortcutName + " " + childShortcutName)) {
			LoggerUtils
					.info("The names of borth shortcuts are present at the dialog ");
			buttonDeleteShortcut.click();
			LoggerUtils.info("Button Delete was clicked");

			WaitUtils.waitForElementVisible(driver,
					messageThatShortcutWasRemoved, 10);

		} else {
			Assert.fail();
			LoggerUtils
					.error("The names of borth shortcuts are not present at the dialog");
		}

	}

	public boolean isBorthShortcutsWereDeleted(String parentShortcutName,
			String childShortcutName) {
		boolean status = false;
		if (WaitUtils.isElementNotPresent(driver,
				String.format(SHORTCUT_NAME_XPATH, parentShortcutName))
				&& WaitUtils.isElementNotPresent(driver,
						String.format(SHORTCUT_NAME_XPATH, childShortcutName))) {
			status = true;
		}
		return status;
	}

	public boolean isVacationWasSet(String subject)

	{
		if (WaitUtils.isElementPresent(driver, MESSAGE_AFTER_SET_VACATION, 5)) {
			if (messageAfterSetVacation.getText().contains(subject)
					&& messageAfterSetVacation.getText().contains("End")
					&& messageAfterSetVacation.getText().contains(
							"Vacation Settings")) {
				return true;
			}
		}
		return false;

	}

	public boolean isResponderLetterInInbox(String subject, String message) {

		int i = 0;
		while (WaitUtils.isElementNotPresent(driver,
				String.format(TOPIC_OF_THE_LETTER, subject), 10)) {
			driver.navigate().refresh();
			i++;
			if (i == 12) {
				LoggerUtils
						.error("There are not new message more then 2 minutes!!!");
				Assert.fail();
			}
		}

		if (topicAndBodyOfTheLetter.getText().contains(subject)
				&& topicAndBodyOfTheLetter.getText().contains(message)) {
			return true;

		}

		return false;
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);

	}

}
