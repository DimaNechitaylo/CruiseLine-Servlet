package model.dao;

public interface DAOFactory {
    UserDAO getUserDAO();
    CruiseDAO getCruiseDAO();
    ShipDAO getShipDAO();
    OrderDAO getOrderDAO();
    PortDAO getPortDAO();
}
