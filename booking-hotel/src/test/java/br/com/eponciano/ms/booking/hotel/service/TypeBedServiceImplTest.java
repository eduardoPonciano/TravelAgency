package br.com.eponciano.ms.booking.hotel.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.eponciano.ms.booking.hotel.mapper.TypeBedMapper;
import br.com.eponciano.ms.booking.hotel.model.TypeBed;
import br.com.eponciano.ms.booking.hotel.model.TypeBedDTO;
import br.com.eponciano.ms.booking.hotel.repository.TypeBedRepository;
import br.com.eponciano.ms.booking.hotel.service.impl.TypeBedServiceImpl;

public class TypeBedServiceImplTest {

    @Mock
    private TypeBedRepository typeBedRepository;

    @InjectMocks
    private TypeBedServiceImpl typeBedService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveTypeBed() {
        TypeBedDTO typeBedDTO = createTypeBedDTO();
        TypeBed typeBedEntity = TypeBedMapper.dtoToEntity(typeBedDTO);
        TypeBed savedTypeBedEntity = typeBedEntity;
        savedTypeBedEntity.setId(1L); // Simulando que foi salvo com ID

        // Definindo o comportamento esperado do mock
        when(typeBedRepository.save(any(TypeBed.class))).thenReturn(savedTypeBedEntity);

        // Chamando o método a ser testado
        TypeBedDTO result = typeBedService.saveTypeBed(typeBedDTO);

        // Verificando o resultado
        assertEquals(savedTypeBedEntity.getId(), result.getId());
//        assertEquals(typeBedDTO.getName(), result.getName());
    }

    @Test
    public void testFindAll() {
        TypeBed typeBed1 = createTypeBed();
        TypeBed typeBed2 = createTypeBed();
        typeBed2.setId(2L);
        List<TypeBed> typeBedEntities = Arrays.asList(typeBed1, typeBed2);

        // Definindo o comportamento esperado do mock
        when(typeBedRepository.findAll()).thenReturn(typeBedEntities);

        // Chamando o método a ser testado
        List<TypeBedDTO> result = typeBedService.findAll();

        // Verificando o resultado
        assertEquals(typeBedEntities.size(), result.size());
//        assertEquals(typeBed1.getName(), result.get(0).getName());
//        assertEquals(typeBed2.getName(), result.get(1).getName());
    }

    private TypeBedDTO createTypeBedDTO() {
        return TypeBedDTO.builder()
//                         .name("Single")
                         .build();
    }

    private TypeBed createTypeBed() {
        return TypeBed.builder()
//                      .name("Single")
                      .build();
    }
}


