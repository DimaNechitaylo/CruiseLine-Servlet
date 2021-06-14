package ua.training.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import ua.training.util.Scheduler;

@WebListener
public class ContextListener  implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		 Thread thread = new Thread(new Scheduler());
		 thread.start();
	}
}
