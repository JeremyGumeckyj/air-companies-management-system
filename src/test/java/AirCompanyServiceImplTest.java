import com.aircompanies.model.AirCompany;
import com.aircompanies.model.Flight;
import com.aircompanies.service.impl.AirCompanyServiceImpl;
import com.aircompanies.repository.AirCompanyRepository;
import com.aircompanies.service.util.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirCompanyServiceImplTest {

    @Mock
    private AirCompanyRepository airCompanyRepository;

    @InjectMocks
    private AirCompanyServiceImpl airCompanyService;

    private UUID nonExistingId;
    private AirCompany testAirCompany;
    private List<AirCompany> testAirCompanyList;

    @BeforeEach
    void setUp() {
        testAirCompany = new AirCompany(UUID.randomUUID(), "Test Air Company", "Test Company Type", LocalDate.now(), new ArrayList<>());
        testAirCompanyList = Arrays.asList(testAirCompany);
    }

    @Test
    void getAll_ReturnsAllAirCompanies() {
        when(airCompanyRepository.findAll()).thenReturn(testAirCompanyList);

        List<AirCompany> result = airCompanyService.getAll();

        assertEquals(testAirCompanyList, result);
    }

    @Test
    void getById_ExistingId_ReturnsAirCompany() {
        UUID id = UUID.randomUUID();
        when(airCompanyRepository.findById(id)).thenReturn(Optional.of(testAirCompany));

        AirCompany result = airCompanyService.getById(id);

        assertEquals(testAirCompany, result);
    }

    @Test
    void getById_NonExistingId_ThrowsNotFoundException() {
        UUID id = UUID.randomUUID();
        when(airCompanyRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> airCompanyService.getById(id));
    }

    @Test
    void createAirCompany_ValidAirCompany_CreatesAndReturnsAirCompany() {
        AirCompany newAirCompany = new AirCompany(null, "New Air Company", "New Company Type", LocalDate.now(), new ArrayList<>());
        when(airCompanyRepository.save(newAirCompany)).thenReturn(newAirCompany);

        AirCompany result = airCompanyService.createAirCompany(newAirCompany);

        assertNotNull(result.getId());
        assertEquals(newAirCompany.getName(), result.getName());
        assertEquals(newAirCompany.getCompanyType(), result.getCompanyType());
        assertEquals(newAirCompany.getFoundedAt(), result.getFoundedAt());
    }

    @Test
    void updateAirCompany_ExistingAirCompany_UpdatesAndReturnsAirCompany() {
        UUID companyId = UUID.randomUUID();
        AirCompany existingCompany = new AirCompany(companyId, "Test Air Company", "Type", null, null);
        when(airCompanyRepository.findById(companyId)).thenReturn(Optional.of(existingCompany));

        AirCompany updatedCompany = new AirCompany(companyId, "Updated Air Company", "Updated Type", null, null);
        when(airCompanyRepository.save(updatedCompany)).thenReturn(updatedCompany);
        AirCompany result = airCompanyService.updateAirCompany(updatedCompany);

        verify(airCompanyRepository, times(1)).findById(companyId);
        verify(airCompanyRepository, times(1)).save(updatedCompany);
        assertEquals(updatedCompany, result);
    }


    @Test
    void updateAirCompany_NonExistingAirCompany_ThrowsNotFoundException() {
        UUID companyId = UUID.randomUUID();
        AirCompany airCompanyToUpdate = new AirCompany();
        airCompanyToUpdate.setId(companyId);

        when(airCompanyRepository.findById(companyId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> airCompanyService.updateAirCompany(airCompanyToUpdate));
    }

    @Test
    public void deleteById_ExistingId_DeletesAirCompany() {
        UUID id = UUID.randomUUID();
        AirCompany airCompany = new AirCompany();
        airCompany.setId(id);
        when(airCompanyRepository.findById(id)).thenReturn(Optional.of(airCompany));

        airCompanyService.deleteById(id);

        verify(airCompanyRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteById_NonExistingId_ThrowsNotFoundException() {
        UUID nonExistingId = UUID.randomUUID();
        Mockito.when(airCompanyRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> airCompanyService.deleteById(nonExistingId));
        Mockito.verify(airCompanyRepository).findById(nonExistingId);
        Mockito.verifyNoMoreInteractions(airCompanyRepository);
    }

    @Test
    void validateIfAirCompanyExists() {
        UUID companyId = UUID.randomUUID();

        when(airCompanyRepository.findById(companyId)).thenReturn(Optional.of(new AirCompany()));

        assertDoesNotThrow(() -> airCompanyService.validateIfAirCompanyExists(companyId));
    }

    @Test
    void validateIfAirCompanyExists_NonExistingId_ThrowsNotFoundException() {
        nonExistingId = UUID.randomUUID();
        when(airCompanyRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> airCompanyService.validateIfAirCompanyExists(nonExistingId));
    }

    @Test
    void findFlightsByCompanyAndStatus_ValidCompanyAndStatus_ReturnsMatchingFlights() {
        Flight testFlight1 = new Flight(UUID.randomUUID(), null, testAirCompany, null, "Country A", "Country B", 1000, 120, null, null, null, null);
        Flight testFlight2 = new Flight(UUID.randomUUID(), null, testAirCompany, null, "Country B", "Country C", 1500, 180, null, null, null, null);
        testAirCompany.getFlights().addAll(Arrays.asList(testFlight1, testFlight2));

        when(airCompanyRepository.findByName("Test Air Company")).thenReturn(Optional.of(testAirCompany));

        List<Flight> result = airCompanyService.findFlightsByCompanyAndStatus("Test Air Company", null);

        assertEquals(testAirCompany.getFlights(), result);
    }
}
