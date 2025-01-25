package stepDefinitions;

import Common.NewSupportAccount;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.*;

import java.time.Duration;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLOutput;
import java.util.List;

public class MyStepdefs {

    //private NewSupportAccount basketballEngland;
    private String browser, firstName, lastName, email, password;
    private WebDriver driver;



    @Given("I have the the web address and using a {string}")
    public void iAmTryingToJoinTheWebpage(String browser) {
        if (browser.equals("Chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("Firefox")) {
            driver = new FirefoxDriver();
        } else
            System.out.println("Invalid browser parameter");
    }

    @When("I start the web page")
    public void iStartTheWebPageAndFillInTheFields() {
        driver.get("https://membership.basketballengland.co.uk/newsupporteraccount");
    }

    @Then("I should join the site {string}")
    public void iShouldJoinTheSite(String success) {
        String expected;

        WebElement welcomeMessage = driver.findElement(By.cssSelector("h2[class='bold  gray  text-center  margin-bottom-40']"));
        String actual = welcomeMessage.getText();

        if (success.equals("yes")){
             expected= "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";
        }else {
            expected = "";
        }
        assertEquals(expected,actual);
    }

    @And("select a date")
    public void selectADate() throws InterruptedException {
        WebElement dateField = driver.findElement(By.cssSelector("[name='DateOfBirth']"));
        dateField.click();
        WebElement datePicker = driver.findElement(By.cssSelector(".datepicker-days .datepicker-switch"));
        datePicker.click();
        WebElement datePickerPrev = driver.findElement(By.cssSelector(".datepicker-months .prev"));
        WebElement yearDate = driver.findElement(By.cssSelector(".datepicker-switch"));
        for (int i = 0; i < 19; i++) {
            datePickerPrev.click();
        }
        /*String year = yearDate.getText();
        while (!year.equals("1992")){
            datePickerPrev.click();
            year = yearDate.getText();
        }*/
        WebElement monthMay = driver.findElement(By.xpath("//*[contains(text(), 'May')]"));
        monthMay.click();
        WebElement dayTwelve = driver.findElement(By.xpath("//td[contains(.,'12')]"));
        dayTwelve.click();
    }

    @And("fill in my {string} name")
    public void fillInMyFirstName(String firstName) {
        this.firstName = firstName;
        WebElement firstNameField = driver.findElement(By.cssSelector("[id='member_firstname']"));
        firstNameField.click();
        firstNameField.sendKeys(firstName);
    }

    @And("fill in my {string}")
    public void fillInMyLastName(String lastName) {
        this.lastName = lastName;
        WebElement firstNameField = driver.findElement(By.cssSelector("[id='member_lastname']"));
        firstNameField.click();
        firstNameField.sendKeys(lastName);
    }

    @And("fill in my email")
    public void fillInMyEmail() {
        Random rand = new Random();
        email = firstName + rand.nextInt(100000) + "@gmail.com";
        WebElement emailField = driver.findElement(By.cssSelector("[id='member_emailaddress']"));
        emailField.click();
        emailField.sendKeys(email);
    }

    @And("put in a password")
    public void putInAPassword() {
        Random rand = new Random();
        for (int i = 0; i < 9; i++) {
            int number = rand.nextInt(25);
            char randomLetter = (char) ('A' + number);
            password += randomLetter;
        }
        password += rand.nextInt(1000);

        WebElement passwordField = driver.findElement(By.cssSelector("[id='signupunlicenced_password']"));
        passwordField.click();
        passwordField.sendKeys(password);
    }

    @And("retype the password {string}")
    public void retypeThePassword(String retypePassword) {
        WebElement passwordField = driver.findElement(By.cssSelector("[id='signupunlicenced_confirmpassword']"));
        passwordField.click();
        if (retypePassword.equals("incorrectly")) {
            password = "incorrectPassword";
        }
        passwordField.sendKeys(password);
    }

    @And("{string} the terms and conditions")
    public void checkOffTheTermsAndConditions(String check) {
        if(check.equals("yes")) {
            WebElement termsButton = driver.findElement(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) .box"));
            termsButton.click();
        }
    }

    @And("check off being a legal age")
    public void checkOffBeingALegalAge() {
        WebElement legalAge = driver.findElement(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(2) .box"));
        legalAge.click();
    }

    @And("check off having read the code of conduct")
    public void checkOffHavingReadTheCodeOfConduct() {
        WebElement codeOfCondButton = driver.findElement(By.cssSelector(".md-checkbox:nth-child(7) .box"));
        codeOfCondButton.click();
    }

    @And("click confirm")
    public void clickConfirm() {
        WebElement confirmAndJoinButton = driver.findElement(By.cssSelector("input[class='btn btn-big red']"));
        confirmAndJoinButton.click();
    }

    @And("confirm my email")
    public void confirmMyEmail() {
        WebElement emailField = driver.findElement(By.cssSelector("[id='member_confirmemailaddress']"));
        emailField.click();
        emailField.sendKeys(email);
    }
}
