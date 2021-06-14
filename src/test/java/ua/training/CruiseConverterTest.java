package ua.training;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import ua.training.converter.impl.CruiseConverter;
import ua.training.model.dto.CruiseDTO;
import ua.training.model.dto.PassengerDTO;
import ua.training.model.entity.Cruise;
import ua.training.model.entity.Port;
import ua.training.model.entity.Ship;

public class CruiseConverterTest {
    static CruiseConverter cruiseConvertor;
    static CruiseDTO cruiseDto;

  @BeforeClass
  public static void initializeFields() {
    cruiseConvertor = new CruiseConverter();
    cruiseDto = CruiseDTO.builder()
        .id(1L)
        .name("testUser")
        .description("testDesc")
        .ship(Ship.builder()
            .id(1L)
            .name("testShip")
            .passengerСapacity(1)
            .build())
        .portNames(Arrays.asList("testPort"))
        .passengersCount(1)
        .start(LocalDate.MIN)
        .finish(LocalDate.MAX)
        .build();
  }
  
    @Test
    public void testConvertCruiseEntityToCruiseDTO() throws Exception{
        Cruise cruise = Cruise.builder()
                .id(1L)
                .name("testUser")
                .description("testDesc")
                .ship(Ship.builder()
                    .id(1L)
                    .name("testShip")
                    .passengerСapacity(1)
                    .build())
                .ports(Arrays.asList(Port.builder().name("testPort").build()))
                .passengers(Arrays.asList(PassengerDTO.builder().build()))
                .start(LocalDate.MIN)
                .finish(LocalDate.MAX)
                .build();
        CruiseDTO cruiseDto = cruiseConvertor.toDto(cruise);
        assertEquals(CruiseConverterTest.cruiseDto, cruiseDto);
    }

}