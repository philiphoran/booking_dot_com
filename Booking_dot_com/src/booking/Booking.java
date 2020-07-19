package booking;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Booking extends BookingWebDriver {

	public Booking(String location, LocalDate startDate, LocalDate endDate, int numberOfAdults, int numberOfChildren, int numberOfRooms, String bookingWebDriverbrowser) {
		super(bookingWebDriverbrowser);
		enterLocation(location);
		selectStartandEndDates(startDate, endDate);
		selectAdultsChildrenRooms(numberOfAdults, numberOfChildren, numberOfRooms);
	}

	public Booking(String bookingWebDriverbrowser) {
		super(bookingWebDriverbrowser);
	}

	public void enterLocation(String location) {

		WebElement whereAreYouGoing = getWebDriver().findElement(By.name("ss"));
		whereAreYouGoing.clear();
		whereAreYouGoing.sendKeys(location);
		WebDriverWait waitForLocationsList = new WebDriverWait(getWebDriver(), 5);
		waitForLocationsList.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#frm > div.xp__fieldset.accommodation > div.xp__input-group.xp__search > div:nth-child(6) > div.c-autocomplete.sb-destination.region_second_line > ul.c-autocomplete__list.sb-autocomplete__list.sb-autocomplete__list-with_photos > li:nth-child(1)")));

		WebElement locationFirstOption = getWebDriver().findElement(By.cssSelector(
				"#frm > div.xp__fieldset.accommodation > div.xp__input-group.xp__search > div:nth-child(6) > div.c-autocomplete.sb-destination.region_second_line > ul.c-autocomplete__list.sb-autocomplete__list.sb-autocomplete__list-with_photos > li:nth-child(1)"));
		locationFirstOption.click();
	}

	public void selectStartandEndDates(LocalDate startDate, LocalDate endDate) {

		LocalDate currentMonth = LocalDate.now().withDayOfMonth(1);

		int monthsStart = Period.between(currentMonth, startDate).getMonths();
		int yearsStart = Period.between(currentMonth, startDate).getYears();
		int totalMonthsStartDate = (yearsStart * 12) + monthsStart;

		int monthsEnd = Period.between(startDate, endDate).getMonths();
		int yearsEnd = Period.between(startDate, endDate).getYears();
		int totalMonthsEndDate = (yearsEnd * 12) + monthsEnd;

		String startDateString = startDate.toString();
		String endDateString = endDate.toString();

		for (int i = 1; i < totalMonthsStartDate; i++) {
			WebElement datePicker = getWebDriver().findElement(By.cssSelector("#frm > div.xp__fieldset.accommodation > div.xp__dates.xp__group > div.xp-calendar > div > div > div.bui-calendar__control.bui-calendar__control--next"));
			datePicker.click();
		}
		WebElement dateSelection = getWebDriver().findElement(By.cssSelector("td[data-date='" + startDateString + "']"));
		dateSelection.click();

		for (int i = 0; i < totalMonthsEndDate; i++) {
			WebElement datePicker = getWebDriver().findElement(By.cssSelector("#frm > div.xp__fieldset.accommodation > div.xp__dates.xp__group > div.xp-calendar > div > div > div.bui-calendar__control.bui-calendar__control--next"));
			datePicker.click();
		}
		WebElement endDatePage = getWebDriver().findElement(By.cssSelector("td[data-date='" + endDateString + "']"));
		endDatePage.click();
	}

	public void selectAdultsChildrenRooms(int numberOfAdults, int numberOfChildren, int numberOfRooms) {

		getWebDriver().findElement(By.cssSelector("div[class='xp__input-group xp__guests']")).click();

		WebElement increaseAdults = getWebDriver().findElement(By.cssSelector("button[aria-label='Increase number of Adults']"));
		WebElement decreaseAdults = getWebDriver().findElement(By.cssSelector("button[aria-label='Decrease number of Adults']"));
		WebElement increaseChildren = getWebDriver().findElement(By.cssSelector("button[aria-label='Increase number of Children']"));
		// WebElement decreaseChildren = getWebDriver().findElement(By.cssSelector("button[aria-label='Decrease number of Children']"));
		WebElement increaseRooms = getWebDriver().findElement(By.cssSelector("button[aria-label='Increase number of Rooms']"));
		// WebElement decreaseRooms = getWebDriver().findElement(By.cssSelector("button[aria-label='Decrease number of Rooms']"));

		// assuming default of 2 adults
		if (numberOfAdults == 1) {
			decreaseAdults.click();
		} else {
			for (int i = 2; i < numberOfAdults; i++) {
				increaseAdults.click();
			}
		}

		// assuming default of 0 children
		for (int i = 0; i < numberOfChildren; i++) {
			increaseChildren.click();
		}

		// assuming default of 1 room
		for (int i = 1; i < numberOfRooms; i++) {
			increaseRooms.click();
		}
		/*
		 * accommodation.click(); WebDriverWait closeAccommodation = new WebDriverWait(driver,30);
		 * closeAccommodation.until(ExpectedConditions.invisibilityOfElementLocated(By. cssSelector("div[class='xp__input-group xp__guests']")));
		 */
	}

	public void clickSearch() {
		getWebDriver().findElement(By.cssSelector("button[class='sb-searchbox__button ']")).click();
	}

	public void selectStarRating(String rating) {

		if (rating != "3" && rating != "4" && rating != "5" && rating != "Unrated") {
			throw new NoSuchElementException("Rating not present. Enter \"3\", \"4\", \"5\", or \"Unrated\".");
		} else {
			getWebDriver().findElement(By.cssSelector("a[data-value='" + rating + "']")).click();
		}
	}

	public void selectFunThingsToDoOption(String activity) {
		WebDriverWait waitForSearchResults = new WebDriverWait(getWebDriver(), 30);
		waitForSearchResults.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_results_table")));

		getWebDriver().findElement(By.xpath("//span[contains(text(), '" + activity + "')]")).click();
	}

	public void clickShowHotelsOnly() {
		WebDriverWait waitForSearchResults = new WebDriverWait(getWebDriver(), 10);
		waitForSearchResults.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Hotels')]")));

		getWebDriver().findElement(By.xpath("//span[contains(text(), 'Hotels')]")).click();
	}

	// ASSERTIONS

	public void assertSearchResultsAreDisplayed() {
		WebDriverWait waitForSearchResults = new WebDriverWait(getWebDriver(), 30);
		waitForSearchResults.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_results_table")));

		Assert.assertTrue(getWebDriver().findElement(By.id("search_results_table")).isDisplayed());
		System.out.print("\nARE SEARCH RESULTS DISPLAYED: " + getWebDriver().findElement(By.id("search_results_table")).isDisplayed());
	}

	public void assert3StarAccommodationOnlyVisible() {

		// List<WebElement> starRating =
		// driver.findElements(By.cssSelector("div[class='sr_property_block_main_row']
		// svg[class='bk-icon -sprite-ratings_stars_4']"));
		List<WebElement> numberFoundInSearch = getWebDriver().findElements(By.cssSelector("div[id=hotellist_inner]>div span[class=sr-hotel__title-badges]"));
		List<WebElement> stars = getWebDriver().findElements(By.cssSelector("div[id=hotellist_inner] div[data-class=\"3\"]"));
		Assert.assertTrue(numberFoundInSearch.size() == stars.size());
		System.out.print("\nNUMBER OF RESULTS: " + numberFoundInSearch.size());
		System.out.print("\nNUMBER 3 STARS: " + stars.size());
	}

	public void assert4StarAccommodationOnlyVisible() {

		// List<WebElement> starRating =
		// driver.findElements(By.cssSelector("div[class='sr_property_block_main_row']
		// svg[class='bk-icon -sprite-ratings_stars_4']"));
		List<WebElement> numberFoundInSearch = getWebDriver().findElements(By.cssSelector("div[id=hotellist_inner]>div span[class=sr-hotel__title-badges]"));
		List<WebElement> stars = getWebDriver().findElements(By.cssSelector("div[id=hotellist_inner] div[data-class=\"4\"]"));
		Assert.assertTrue(numberFoundInSearch.size() == stars.size());
		System.out.print("\nNUMBER OF RESULTS: " + numberFoundInSearch.size());
		System.out.print("\nNUMBER 4 STARS: " + stars.size());
	}

	public void assert5StarAccommodationOnlyVisible() {

		// List<WebElement> starRating =
		// driver.findElements(By.cssSelector("div[class='sr_property_block_main_row']
		// svg[class='bk-icon -sprite-ratings_stars_4']"));
		List<WebElement> numberFoundInSearch = getWebDriver().findElements(By.cssSelector("div[id=hotellist_inner]>div span[class=sr-hotel__title-badges]"));
		List<WebElement> stars = getWebDriver().findElements(By.cssSelector("div[id=hotellist_inner] div[data-class=\"5\"]"));
		Assert.assertTrue(numberFoundInSearch.size() == stars.size());
		System.out.print("\nNUMBER OF RESULTS: " + numberFoundInSearch.size());
		System.out.print("\nNUMBER 5 STARS: " + stars.size());
	}

	public void assertUnratedAccommodationOnlyVisible() {

		// List<WebElement> starRating =
		// driver.findElements(By.cssSelector("div[class='sr_property_block_main_row']
		// svg[class='bk-icon -sprite-ratings_stars_4']"));
		List<WebElement> numberFoundInSearch = getWebDriver().findElements(By.cssSelector("div[id=hotellist_inner]>div span[class=sr-hotel__title-badges]"));
		List<WebElement> stars = getWebDriver().findElements(By.cssSelector("div[id=hotellist_inner] div[data-class=\"0\"]"));
		Assert.assertTrue(numberFoundInSearch.size() == stars.size());
		System.out.print("\nNUMBER OF RESULTS: " + numberFoundInSearch.size());
		System.out.print("\nNUMBER Unrated STARS: " + stars.size());
	}
}
