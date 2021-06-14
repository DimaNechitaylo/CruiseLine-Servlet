package ua.training.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import ua.training.model.dto.PassengerDTO;
import ua.training.model.entity.Cruise;
import ua.training.model.entity.Port;

public interface CruiseDAO {
	Optional<Cruise> getCruise(Long id, Locale locale);
    Optional<List<Cruise>> getAvailableCruises(int start, int total, Locale locale);
    Long getAvailableCruisesCount();
    Optional<List<Cruise>> findAllByStart(LocalDate start, int startRow, int total, Locale locale);
	Optional<List<Cruise>> findAllByStartAndFinishBetween(LocalDate start, LocalDate finish1,  LocalDate finish2, int startRow, int total, Locale locale);
	Long getAllByStartAndFinishBetweenCount(LocalDate start, LocalDate finish1, LocalDate finish2);
	Optional<List<Cruise>> findAllByFinishMinusStartBetween(int minDuration,  int maxDuration, int startRow, int total, Locale locale);
	Long getAllByFinishMinusStartBetweenCount(int minDuration, int maxDuration);
	Optional<Cruise> findByIdNotBookined(Long cruiseId, Long userId, Locale locale);
	Optional<List<Cruise>> findUserCruisesByOrders(Long userId, Locale locale);
	Optional<List<Port>> getPortsByCruiseId(Long cruiseId, Locale locale);
    List<PassengerDTO> getPassengersById(Long id);
    public Cruise extractEntity(ResultSet resultSet, Locale locale) throws SQLException;
}
