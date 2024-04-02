import com.aircompanies.FlightStatus;
import com.aircompanies.model.Flight;
import com.aircompanies.repository.FlightRepository;
import com.aircompanies.service.impl.FlightServiceImpl;
import com.aircompanies.service.util.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    private UUID existingId;
    private UUID nonExistingId;
    private Flight existingFlight;
    private Flight testFlight;
    private List<Flight> testFlightList;

    @BeforeEach
    void setUp() {
        existingId = UUID.randomUUID();
        nonExistingId = UUID.randomUUID();
        testFlight = new Flight(UUID.randomUUID(), FlightStatus.PENDING, null, null, "Test Departure", "Test Destination", 1000.0, 120, null, null, null, LocalDateTime.now());
        testFlightList = new ArrayList<>();
        testFlightList.add(testFlight);
        existingFlight = new Flight(UUID.randomUUID(), FlightStatus.PENDING, null, null, "USA", "UK", 1000.0, 120, null, null, null, LocalDateTime.now());
    }

    @Test
    void getAll_ReturnsAllFlights() {
        when(flightRepository.findAll()).thenReturn(testFlightList);

        List<Flight> result = flightService.getAll();

        assertEquals(testFlightList, result);
    }

    @Test
    void getById_ExistingId_ReturnsFlight() {
        UUID id = UUID.randomUUID();
        when(flightRepository.findById(id)).thenReturn(Optional.of(testFlight));

        Flight result = flightService.getById(id);

        assertEquals(testFlight, result);
    }

    @Test
    void getById_NonExistingId_ThrowsNotFoundException() {
        UUID nonExistingId = UUID.randomUUID();
        when(flightRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> flightService.getById(nonExistingId));
    }

    @Test
    void createFlight_ReturnsCreatedFlight() {
        when(flightRepository.save(testFlight)).thenReturn(testFlight);

        Flight result = flightService.createFlight(testFlight);

        assertEquals(testFlight, result);
    }

    @Test
    void updateFlight_ExistingFlight_ReturnsUpdatedFlight() {
        UUID existingFlightId = existingFlight.getId();
        Flight updatedFlight = new Flight(existingFlightId, FlightStatus.ACTIVE, null, null, "USA", "UK", 1000.0, 120, null, null, null, LocalDateTime.now());

        when(flightRepository.findById(existingFlightId)).thenReturn(Optional.of(existingFlight));
        when(flightRepository.save(any(Flight.class))).thenReturn(updatedFlight);

        Flight result = flightService.updateFlight(updatedFlight);

        assertEquals(updatedFlight, result);
        verify(flightRepository, times(1)).findById(existingFlightId);
        verify(flightRepository, times(1)).save(updatedFlight);
    }

    @Test
    void updateFlight_NonExistingFlight_ThrowsNotFoundException() {
        UUID flightId = UUID.randomUUID();
        Flight flightToUpdate = new Flight();
        flightToUpdate.setId(flightId);

        when(flightRepository.findById(flightId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> flightService.updateFlight(flightToUpdate));
    }



    @Test
    void deleteById_ExistingId_DeletesFlight() {
        when(flightRepository.findById(existingId)).thenReturn(Optional.of(testFlight));

        flightService.deleteById(existingId);

        verify(flightRepository).deleteById(existingId);
    }

    @Test
    void deleteById_NonExistingId_ThrowsNotFoundException() {
        UUID nonExistingId = UUID.randomUUID();

        assertThrows(NotFoundException.class, () -> flightService.deleteById(nonExistingId));
    }

    @Test
    void changeFlightStatus_ExistingFlightAndValidStatus_UpdatesFlightStatus() {
        UUID flightId = UUID.randomUUID();;
        Flight testFlight = new Flight(flightId, FlightStatus.PENDING, null, null, "Test Departure", "Test Destination",1000.0, 120, null, null, null, LocalDateTime.now());
        FlightStatus newStatus = FlightStatus.ACTIVE;
        when(flightRepository.findById(flightId)).thenReturn(Optional.of(testFlight));

        flightService.changeFlightStatus(flightId, newStatus);

        assertEquals(newStatus, testFlight.getFlightStatus());
    }





    @Test
    void changeFlightStatus_NonExistingFlight_ThrowsNotFoundException() {
        assertThrows(NotFoundException.class, () -> flightService.changeFlightStatus(nonExistingId, FlightStatus.ACTIVE));
    }

    @Test
    void findFlightsInActiveStatusStartedMoreThan24HoursAgo_ReturnsFlights() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime twentyFourHoursAgo = now.minusHours(24);

        Flight activeFlight1 = new Flight(UUID.randomUUID(), FlightStatus.ACTIVE, null, null, "Departure Country", "Destination Country", 1000, 120, twentyFourHoursAgo, null, null, now.minusDays(1));
        Flight activeFlight2 = new Flight(UUID.randomUUID(), FlightStatus.ACTIVE, null, null, "Departure Country", "Destination Country", 1000, 120, twentyFourHoursAgo, null, null, now.minusDays(1));

        List<Flight> activeFlights = Arrays.asList(activeFlight1, activeFlight2);
        when(flightRepository.findAll()).thenReturn(activeFlights);

        List<Flight> result = flightService.findFlightsInActiveStatusStartedMoreThan24HoursAgo();

        assertEquals(2, result.size());
        assertTrue(result.contains(activeFlight1));
        assertTrue(result.contains(activeFlight2));
    }


    @Test
    void findFlightsInCompletedStatusWithDelay_ReturnsFlights() {
        testFlight.setFlightStatus(FlightStatus.COMPLETED);
        testFlight.setStartedAt(LocalDateTime.now().minusHours(3));
        testFlight.setEndedAt(LocalDateTime.now());
        testFlight.setEstimatedFlightTime(120);

        when(flightRepository.findAll()).thenReturn(testFlightList);

        List<Flight> result = flightService.findFlightsInCompletedStatusWithDelay();

        assertEquals(1, result.size());
        assertEquals(testFlight.getId(), result.get(0).getId());
    }
}
