import com.aircompanies.model.AirCompany;
import com.aircompanies.model.Airplane;
import com.aircompanies.repository.AirplaneRepository;
import com.aircompanies.service.AirCompanyService;
import com.aircompanies.service.impl.AirplaneServiceImpl;
import com.aircompanies.service.util.exception.IllegalArgumentException;
import com.aircompanies.service.util.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirplaneServiceImplTest {

    @Mock
    private AirplaneRepository airplaneRepository;

    @Mock
    private AirCompanyService airCompanyService;

    @InjectMocks
    private AirplaneServiceImpl airplaneService;

    private UUID nonExistingId;
    private Airplane testAirplane;
    private List<Airplane> testAirplaneList;

    @BeforeEach
    void setUp() {
        nonExistingId = UUID.randomUUID();
        testAirplane = new Airplane(UUID.randomUUID(), "Test Airplane", "Test Factory Serial Number", null, 0, 0, 0, "Test Type", null);
        testAirplaneList = new ArrayList<>();
        testAirplaneList.add(testAirplane);
    }

    @Test
    void getAll_ReturnsAllAirplanes() {
        when(airplaneRepository.findAll()).thenReturn(testAirplaneList);

        List<Airplane> result = airplaneService.getAll();

        assertEquals(testAirplaneList, result);
    }

    @Test
    void getById_ExistingId_ReturnsAirplane() {
        UUID id = testAirplane.getId();
        when(airplaneRepository.findById(id)).thenReturn(Optional.of(testAirplane));

        Airplane result = airplaneService.getById(id);

        assertEquals(testAirplane, result);
    }

    @Test
    void getById_NonExistingId_ThrowsNotFoundException() {
        when(airplaneRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> airplaneService.getById(nonExistingId));
    }

    @Test
    void createAirplane_ValidAirplane_ReturnsCreatedAirplane() {
        Airplane createdAirplane = new Airplane(UUID.randomUUID(), "Created Airplane", "Created Factory Serial Number", null, 0, 0, 0, "Created Type", null);
        when(airplaneRepository.save(any(Airplane.class))).thenReturn(createdAirplane);

        Airplane result = airplaneService.createAirplane(createdAirplane, null);

        assertEquals(createdAirplane, result);
    }

    @Test
    void updateAirplane_ValidAirplane_ReturnsUpdatedAirplane() {
        when(airplaneRepository.findById(testAirplane.getId())).thenReturn(Optional.of(testAirplane));
        when(airplaneRepository.save(any(Airplane.class))).thenReturn(testAirplane);

        Airplane result = airplaneService.updateAirplane(testAirplane);

        assertEquals(testAirplane, result);
    }

    @Test
    void updateAirplane_NotValidAirplane_ReturnsUpdatedAirplane() {
        Airplane invalidAirplane = new Airplane();

        assertThrows(IllegalArgumentException.class, () -> airplaneService.updateAirplane(invalidAirplane));
        verify(airplaneRepository, never()).save(any());
    }

    @Test
    void deleteById_ExistingId_DeletesAirplane() {
        UUID id = testAirplane.getId();
        when(airplaneRepository.findById(id)).thenReturn(Optional.of(testAirplane));
        doNothing().when(airplaneRepository).deleteById(id);

        airplaneService.deleteById(id);

        verify(airplaneRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteById_NotExistingId_DeletesAirplane() {
        UUID nonExistingId = UUID.randomUUID();
        when(airplaneRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> airplaneService.deleteById(nonExistingId));
        verify(airplaneRepository, never()).deleteById(any());
    }

    @Test
    void validateIfAirplaneExists_ExistingId_DoesNotThrowException() {
        UUID id = testAirplane.getId();
        when(airplaneRepository.findById(id)).thenReturn(Optional.of(testAirplane));

        assertDoesNotThrow(() -> airplaneService.validateIfAirplaneExists(id));
    }

    @Test
    void validateIfAirplaneExists_NonExistingId_ThrowsNotFoundException() {
        when(airplaneRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> airplaneService.validateIfAirplaneExists(nonExistingId));
    }

    @Test
    void moveAirplaneToAnotherCompany_ValidIds_ReturnsMovedAirplane() {
        AirCompany newCompany = new AirCompany(UUID.randomUUID(), "New Company", "New Company Type", null, null);
        UUID airplaneId = testAirplane.getId();
        UUID newCompanyId = newCompany.getId();

        when(airplaneRepository.findById(airplaneId)).thenReturn(Optional.of(testAirplane));
        when(airCompanyService.getById(newCompanyId)).thenReturn(newCompany);
        when(airplaneRepository.save(any(Airplane.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Airplane result = airplaneService.moveAirplaneToAnotherCompany(airplaneId, newCompanyId);

        assertEquals(newCompany, result.getAirCompany());
    }
}