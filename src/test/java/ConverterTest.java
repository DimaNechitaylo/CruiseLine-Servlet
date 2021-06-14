//
//import static org.junit.Assert.*;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//
//import model.entity.Cruise;
//import model.entity.Port;
//import model.entity.Ship;
//import model.entity.User;
//import util.exception.PortNotFoundException;
//
//public class ConverterTest {
//    static CruiseConvertor cruiseConvertor;
//    static Cruise cruise;
//
//  @BeforeClass
//  public static void initializeFields() {
//    cruiseConvertor = new CruiseConvertor();
//    cruise = Cruise.builder()
//        .id(1L)
//        .name("testUser")
//        .description("testDesc")
//        .ship(Ship.builder()
//            .id(1L)
//            .name("testShip")
//            .passengerСapacity(1L)
//            .build())
//        .ports(Arrays.asList(Port.builder().id(1L).name("testPort").build()))
//        .passengers(Arrays.asList(User.builder().id(1L).username("testUser")))
//        .start(LocalDate.MIN)
//        .finish(LocalDate.MAX)
//        .build();
//  }
//  
//    @Test
//    public void testConvertors() throws Exception{
//        assertEquals(user, user);
//    }
//
//}