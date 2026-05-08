package com.onesports.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StudentAcademicDetails4thStep {

    private WebDriver driver;
    private WebDriverWait wait;

    public StudentAcademicDetails4thStep(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[normalize-space()='Applicant and Program Details']")
    private WebElement applicantAndProgramDetailsStep;

    @FindBy(xpath = "//span[normalize-space()='Payment Tab']")
    private WebElement paymentTabStep;

    @FindBy(xpath = "//span[normalize-space()='Address Details']")
    private WebElement addressDetailsStep;

    @FindBy(xpath = "//span[normalize-space()='Academic Details']")
    private WebElement academicDetailsStep;

    @FindBy(xpath = "//span[normalize-space()='Upload Files']")
    private WebElement uploadFilesStep;

    @FindBy(xpath = "//span[normalize-space()='Declaration']")
    private WebElement declarationStep;

    @FindBy(css = "input[formcontrolname='tenthInstitute']")
    private WebElement tenthInstituteField;

    @FindBy(css = "input[formcontrolname='tenthInstitutePlace']")
    private WebElement tenthInstitutePlaceField;

    @FindBy(css = "select[formcontrolname='tenthBoard']")
    private WebElement tenthBoardSelect;

    @FindBy(css = "input[formcontrolname='tenthMedium']")
    private WebElement tenthMediumField;

    @FindBy(css = "input[formcontrolname='tenthHallTicket']")
    private WebElement tenthHallTicketField;

    @FindBy(css = "input[formcontrolname='tenthYear']")
    private WebElement tenthYearField;

    @FindBy(css = "select[formcontrolname='tenthMarkingScheme']")
    private WebElement tenthMarkingSchemeSelect;

    @FindBy(css = "input[formcontrolname='tenthMaxMarks']")
    private WebElement tenthMaxMarksField;

    @FindBy(css = "input[formcontrolname='tenthObtMarks']")
    private WebElement tenthObtMarksField;

    @FindBy(css = "input[formcontrolname='tenthPercentage']")
    private WebElement tenthPercentageField;

    @FindBy(css = "input[formcontrolname='has12th']")
    private WebElement has12thCheckbox;

    @FindBy(css = "input[formcontrolname='twelfthInstitute']")
    private WebElement twelfthInstituteField;

    @FindBy(css = "input[formcontrolname='twelfthBranch']")
    private WebElement twelfthBranchField;

    @FindBy(css = "select[formcontrolname='twelfthBoard']")
    private WebElement twelfthBoardSelect;

    @FindBy(css = "input[formcontrolname='twelfthMedium']")
    private WebElement twelfthMediumField;

    @FindBy(css = "input[formcontrolname='twelfthHallTicket']")
    private WebElement twelfthHallTicketField;

    @FindBy(css = "input[formcontrolname='twelfthStream']")
    private WebElement twelfthStreamField;

    @FindBy(css = "input[formcontrolname='twelfthYear']")
    private WebElement twelfthYearField;

    @FindBy(css = "select[formcontrolname='twelfthResultStatus']")
    private WebElement twelfthResultStatusSelect;

    @FindBy(css = "select[formcontrolname='twelfthMarkingScheme']")
    private WebElement twelfthMarkingSchemeSelect;

    @FindBy(css = "input[formcontrolname='twelfthMaxMarks']")
    private WebElement twelfthMaxMarksField;

    @FindBy(css = "input[formcontrolname='twelfthObtMarks']")
    private WebElement twelfthObtMarksField;

    @FindBy(css = "input[formcontrolname='twelfthPercentage']")
    private WebElement twelfthPercentageField;

    @FindBy(css = "input[formcontrolname='twelfthMaths']")
    private WebElement twelfthMathsField;

    @FindBy(css = "input[formcontrolname='twelfthPhysics']")
    private WebElement twelfthPhysicsField;

    @FindBy(css = "input[formcontrolname='twelfthChemistry']")
    private WebElement twelfthChemistryField;

    @FindBy(css = "input[formcontrolname='twelfthMPCPercentage']")
    private WebElement twelfthMPCPercentageField;

    @FindBy(css = "input[formcontrolname='hasDiploma']")
    private WebElement hasDiplomaCheckbox;

    @FindBy(css = "input[formcontrolname='hasEntranceExams']")
    private WebElement hasEntranceExamsCheckbox;

    @FindBy(xpath = "//button[normalize-space()='Previous']")
    private WebElement previousButton;

    @FindBy(xpath = "//button[contains(normalize-space(.), 'Save & Continue')]")
    private WebElement saveContinueButton;

    private WebElement waitForVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void typeText(WebElement element, String text) {
        WebElement el = waitForVisible(element);
        el.clear();
        el.sendKeys(text);
    }

    private void selectByVisibleText(WebElement element, String visibleText) {
        Select select = new Select(waitForVisible(element));
        select.selectByVisibleText(visibleText);
    }

    private void setCheckbox(WebElement element, boolean value) {
        WebElement el = waitForVisible(element);
        if (el.isSelected() != value) {
            el.click();
        }
    }

    public boolean isAcademicDetailsStepActive() {
        return waitForVisible(academicDetailsStep).isDisplayed();
    }

    public void fillTenthStandardDetails(
            String institute,
            String institutePlace,
            String board,
            String medium,
            String hallTicket,
            String year,
            String markingScheme,
            String maxMarks,
            String obtainedMarks,
            String percentage) {
         if (!institute.trim().isEmpty())
        typeText(tenthInstituteField, institute);

    if (!institutePlace.trim().isEmpty())
        typeText(tenthInstitutePlaceField, institutePlace);

    if (!board.trim().isEmpty())
        selectByVisibleText(tenthBoardSelect, board);

    if (!medium.trim().isEmpty())
        typeText(tenthMediumField, medium);

    if (!hallTicket.trim().isEmpty())
        typeText(tenthHallTicketField, hallTicket);

    if (!year.trim().isEmpty())
        typeText(tenthYearField, year);

    if (!markingScheme.trim().isEmpty())
        selectByVisibleText(tenthMarkingSchemeSelect, markingScheme);

    if (!maxMarks.trim().isEmpty())
        typeText(tenthMaxMarksField, maxMarks);

    if (!obtainedMarks.trim().isEmpty())
        typeText(tenthObtMarksField, obtainedMarks);

    if (!percentage.trim().isEmpty())
        typeText(tenthPercentageField, percentage);
    }

    public void fillTwelfthStandardDetails(
            boolean has12th,
            String institute,
            String branch,
            String board,
            String medium,
            String hallTicket,
            String stream,
            String year,
            String resultStatus,
            String markingScheme,
            String maxMarks,
            String obtainedMarks,
            String percentage,
            String maths,
            String physics,
            String chemistry,
            String mpcPercentage) {
        setCheckbox(has12thCheckbox, has12th);

         if (!has12th) {
        return;
    }
        if (!institute.trim().isEmpty())
        typeText(twelfthInstituteField, institute);

    if (!branch.trim().isEmpty())
        typeText(twelfthBranchField, branch);

    if (!board.trim().isEmpty())
        selectByVisibleText(twelfthBoardSelect, board);

    if (!medium.trim().isEmpty())
        typeText(twelfthMediumField, medium);

    if (!hallTicket.trim().isEmpty())
        typeText(twelfthHallTicketField, hallTicket);

    if (!stream.trim().isEmpty())
        selectByVisibleText(twelfthStreamField, stream);

    if (!year.trim().isEmpty())
        typeText(twelfthYearField, year);

    if (!resultStatus.trim().isEmpty())
        selectByVisibleText(twelfthResultStatusSelect, resultStatus);

    if (!markingScheme.trim().isEmpty())
        selectByVisibleText(twelfthMarkingSchemeSelect, markingScheme);

    if (!maxMarks.trim().isEmpty())
        typeText(twelfthMaxMarksField, maxMarks);

    if (!obtainedMarks.trim().isEmpty())
        typeText(twelfthObtMarksField, obtainedMarks);

    if (!percentage.trim().isEmpty())
        typeText(twelfthPercentageField, percentage);

    if (!maths.trim().isEmpty())
        typeText(twelfthMathsField, maths);

    if (!physics.trim().isEmpty())
        typeText(twelfthPhysicsField, physics);

    if (!chemistry.trim().isEmpty())
        typeText(twelfthChemistryField, chemistry);

    if (!mpcPercentage.trim().isEmpty())
        typeText(twelfthMPCPercentageField, mpcPercentage);
}

    public void setHasDiploma(boolean value) {
        setCheckbox(hasDiplomaCheckbox, value);
    }

    public void setHasEntranceExams(boolean value) {
        setCheckbox(hasEntranceExamsCheckbox, value);
    }

    public void clickPrevious() {
        waitForVisible(previousButton).click();
    }

    public void clickSaveAndContinue() {
        waitForVisible(saveContinueButton).click();
    }
}
