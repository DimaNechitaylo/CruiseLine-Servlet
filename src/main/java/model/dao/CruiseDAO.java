package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import model.dto.PassengerDTO;
import model.entity.Cruise;
import model.entity.Port;

public interface CruiseDAO {
	Optional<Cruise> getCruise(Long id);
	boolean addCruise(Cruise user);
	boolean deleteCruise(Long id);
    boolean updateCruise(Cruise user);
    Optional<List<Cruise>> getAvailableCruises(int start, int total);
    Long getAvailableCruisesCount();
    Optional<List<Cruise>> findAllByStart(LocalDate start, int startRow, int total);
	Optional<List<Cruise>> findAllByStartAndFinishBetween(LocalDate start, LocalDate finish1,  LocalDate finish2, int startRow, int total);
	Long getAllByStartAndFinishBetweenCount(LocalDate start, LocalDate finish1, LocalDate finish2);
	Optional<List<Cruise>> findAllByFinishMinusStartBetween(int minDuration,  int maxDuration, int startRow, int total);
	Long getAllByFinishMinusStartBetweenCount(int minDuration, int maxDuration);
	Optional<Cruise> findByIdNotBookined(Long cruiseId, Long userId);
	Optional<List<Cruise>> findUserCruisesByOrders(Long userId);
    Optional<List<Port>> getPortsById(Long id);
    List<PassengerDTO> getPassengersById(Long id);
    public Cruise extractEntity(ResultSet resultSet) throws SQLException;
}
