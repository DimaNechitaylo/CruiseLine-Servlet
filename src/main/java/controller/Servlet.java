package controller;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import controller.command.CommandCaller;
import util.exception.InvalidActionException;

@WebServlet("/main")
public class Servlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(Servlet.class.getName());

	private static final long serialVersionUID = 1L;
	
    private  final  CommandCaller commandCaller = new CommandCaller();

	@Override
	public void init(){
        logger.info("init");
    }

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
      logger.info("doGet " + request.getParameter("action"));
      processRequest(request, response);
	}



	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
        logger.info("doPost " + request.getParameter("action"));
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
        String command  = request.getParameter("action");
        String path = "";
        try {
            path = commandCaller.call((Objects.isNull(command) ? "" : command).toUpperCase())
            		.orElseThrow(() ->new InvalidActionException(command))
            		.execute(request);
		} catch (InvalidActionException e) {
			logger.info(e);
        	response.sendRedirect("/CruiseLine-Servlet/home.jsp");
        	return;
		}
        
        if(path.contains("redirect:")){
            response.sendRedirect(path.replace("redirect:", "/"));
        }else {
            request.getRequestDispatcher(path).forward(request, response);
        }
		
	}

}
