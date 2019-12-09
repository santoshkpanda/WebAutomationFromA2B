package testCases;

import com.cloud.core.Utils;
import com.cloud.fileHandler.PropertyFileHandler;
import com.cloud.pageActions.Dashboard;
import com.cloud.pageActions.DeutscheBahnPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TripsTesting extends BaseTest{

    private Dashboard dashboard;
    private DeutscheBahnPage deutscheBahnPage;

    @Test
    public void validateShortestDurationInDeutscheBahn() throws IOException {
        initTestCase("TC_3");
        dashboard = new Dashboard(getDriver());
        deutscheBahnPage = new DeutscheBahnPage(getDriver());
        String departureCity = PropertyFileHandler.getPropertyValue("DEPARTURE");
        String destinationCity= PropertyFileHandler.getPropertyValue("DESTINATION");
        String noOfDaysJourneyPlanned = PropertyFileHandler.getPropertyValue("NO_DAYS_TRIP_PLANNED");

        dashboard.searchShortestDurationTrip(departureCity, destinationCity);
        deutscheBahnPage.searchForTripsFromDeutscheBahnPage(departureCity,
                                                             destinationCity,
                                                             Utils.dateAfterCurrentDate(Integer.parseInt(noOfDaysJourneyPlanned)));

        int fastestTripIndex = deutscheBahnPage.getFastestTripIndex();
        deutscheBahnPage.selectFastestOffer(fastestTripIndex);
    }
}
