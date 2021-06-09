package util;

import model.dao.DAOFactory;
import model.dao.impl.DAOFactoryImpl;

public class Scheduler implements Runnable {

	@Override
	public void run() {
		DAOFactory daoFactory = new DAOFactoryImpl();
		while(true) {
			try {
				daoFactory.getOrderDAO().startCruises();
				daoFactory.getOrderDAO().finishCruises();
				System.out.println("Scheduler");
				Thread.sleep(1000 * 60 * 60 * 5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
