package mobile;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MobileServiceTest {

    @Test
    void testSetGps() {
        // Arrange
        MobileService service = new MobileService();
        MobileGpsService mockGps = mock(MobileGpsService.class);

        // Act
        service.setGps(mockGps);

        // Assert indirectly by calling getMobileDetails
        // So we also need to mock the GPS and location fetch behavior
        when(mockGps.fetchGPS()).thenReturn("Mock GPS");
        
        // Mock a location service too
        MobileLocationService mockLocation = mock(MobileLocationService.class);
        when(mockLocation.fetchLocation()).thenReturn("Mock Location");
        service.setLocation(mockLocation);

        MobileDetailsDTO dto = service.getMobileDetails();

        assertEquals("Mock GPS", dto.getGpsCoordinates());
        assertEquals("Mock Location", dto.getLocation());
    }
}
