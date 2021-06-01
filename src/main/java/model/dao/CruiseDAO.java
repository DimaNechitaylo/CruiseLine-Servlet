package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import model.entity.Cruise;
import model.entity.Port;
import model.entity.User;

public interface CruiseDAO {
	Optional<Cruise> getCruise(Long id);
	boolean addCruise(Cruise user);
	boolean deleteCruise(Long id);
    boolean updateCruise(Cruise user);
    Optional<List<Cruise>> getAvailableCruises();
    Optional<List<Cruise>> findAllByStart(LocalDate start);
	Optional<List<Cruise>> findAllByStartAndFinishBetween(LocalDate start, LocalDate finish1,  LocalDate finish2);
	Optional<List<Cruise>> findAllByFinishMinusStartBetween(Long minDuration,  Long maxDuration);
	Optional<Cruise> findByIdNotBookined(Long cruiseId, Long userId);
	Optional<List<Cruise>> findUserCruisesByOrders(Long userId);
    Optional<List<Port>> getPortsById(Long id);
    List<User> getPassengersById(Long id);
    public Cruise extractEntity(ResultSet resultSet) throws SQLException;
}
