package booking;

import java.time.LocalDate;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class BookingDotCom {
	LocalDate startDate = LocalDate.now().plusMonths(3);
	LocalDate endDate = startDate.plusDays(1);
	String webBrowser = "chrome";

	@Test
	public void Ensure_The_Limerick_Stand_Hotel_has_Sauna_option_and_is_present_in_Search_Results() {

		Booking bookingTest1 = new Booking(webBrowser);
		bookingTest1.enterLocation("Limerick");
		bookingTest1.selectStartandEndDates(startDate, endDate);
		bookingTest1.selectAdultsChildrenRooms(2, 0, 1);
		bookingTest1.clickSearch();
		bookingTest1.assertSearchResultsAreDisplayed();
		bookingTest1.selectStarRating("4");
		bookingTest1.clickShowHotelsOnly();
		bookingTest1.sleep(2000);
		bookingTest1.selectFunThingsToDoOption("Sauna");
		bookingTest1.assert4StarAccommodationOnlyVisible();
		bookingTest1.sleep(2000);
		Assert.assertTrue(bookingTest1.getWebDriver().findElement(By.xpath("//div[@id='search_results_table']//span[contains(text(), 'Limerick Strand Hotel')]")).isDisplayed());
		System.out.print("\nLimerick Strand Hotel visible: " + bookingTest1.getWebDriver().findElement(By.xpath("//div[@id='search_results_table']//span[contains(text(), 'Limerick Strand Hotel')]")).isDisplayed());
		bookingTest1.quit();
	}

	@Test
	void Ensure_The_George_Limerick_Hotel_has_Sauna_option_and_is_not_present_in_Search_Results() {
		Booking bookingTest2 = new Booking(webBrowser);
		bookingTest2.enterLocation("Limerick");
		bookingTest2.selectStartandEndDates(startDate, endDate);
		bookingTest2.selectAdultsChildrenRooms(2, 0, 1);
		bookingTest2.clickSearch();
		bookingTest2.assertSearchResultsAreDisplayed();
		bookingTest2.selectStarRating("4");
		bookingTest2.clickShowHotelsOnly();
		bookingTest2.sleep(2000);
		bookingTest2.selectFunThingsToDoOption("Sauna");
		bookingTest2.assert4StarAccommodationOnlyVisible();
		bookingTest2.sleep(2000);
		Assert.assertTrue(bookingTest2.getWebDriver().findElements(By.xpath("//div[@id='search_results_table']//span[contains(text(), 'George Limerick Hotel')]")).isEmpty());
		System.out.print("\nGeorge Limerick Hotel not in Sauna results: " + bookingTest2.getWebDriver().findElements(By.xpath("//div[@id='search_results_table']//span[contains(text(), 'George Limerick Hotel')]")).isEmpty());
		bookingTest2.quit();
	}

	@Test
	void Ensure_when_5_Star_is_selected_that_The_Savoy_Hotel_is_present_in_Search_Results() {
		Booking bookingTest3 = new Booking(webBrowser);
		bookingTest3.enterLocation("Limerick");
		bookingTest3.selectStartandEndDates(startDate, endDate);
		bookingTest3.selectAdultsChildrenRooms(2, 0, 1);
		bookingTest3.clickSearch();
		bookingTest3.assertSearchResultsAreDisplayed();
		bookingTest3.selectStarRating("5");
		bookingTest3.clickShowHotelsOnly();
		bookingTest3.sleep(5000);
		bookingTest3.assert5StarAccommodationOnlyVisible();

		Assert.assertTrue(bookingTest3.getWebDriver().findElement(By.xpath("//div[@id='search_results_table']//span[contains(text(), 'The Savoy Hotel')]")).isDisplayed());
		System.out.print("\nThe Savoy Hotel visible: " + bookingTest3.getWebDriver().findElement(By.xpath("//div[@id='search_results_table']//span[contains(text(), 'The Savoy Hotel')]")).isDisplayed());
		bookingTest3.quit();
	}

	@Test
	void Ensure_when_5_Star_is_selected_that_The_George_Limerick_Hotel_is_not_present_in_Search_Results() {
		Booking bookingTest4 = new Booking(webBrowser);
		bookingTest4.enterLocation("Limerick");
		bookingTest4.selectStartandEndDates(startDate, endDate);
		bookingTest4.selectAdultsChildrenRooms(2, 0, 1);
		bookingTest4.clickSearch();
		bookingTest4.assertSearchResultsAreDisplayed();
		bookingTest4.selectStarRating("5");
		bookingTest4.clickShowHotelsOnly();
		bookingTest4.sleep(5000);
		bookingTest4.assert5StarAccommodationOnlyVisible();

		Assert.assertTrue(bookingTest4.getWebDriver().findElements(By.xpath("//div[@id='search_results_table']//span[contains(text(), 'George Limerick Hotel')]")).isEmpty());
		System.out.print("\nGeorge Limerick Hotel not in 5 star results: " + bookingTest4.getWebDriver().findElements(By.xpath("//div[@id='search_results_table']//span[contains(text(), 'George Limerick Hotel')]")).isEmpty());
		bookingTest4.quit();
	}

}
