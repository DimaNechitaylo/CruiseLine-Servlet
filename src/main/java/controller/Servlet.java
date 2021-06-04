package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import controller.command.Command;
import controller.command.CommandCaller;
import controller.command.impl.SignUpCommand;

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
        if(Objects.isNull(command) || command.isBlank()) {
        	response.sendRedirect("/CruiseLine-Servlet/index.jsp");
        	return;
        }
        logger.info(request.getRequestURL());
        String path = commandCaller.call(command.toUpperCase()).execute(request);
        if(path.contains("redirect:")){
            response.sendRedirect(path.replace("redirect:", "/"));
        }else {
            request.getRequestDispatcher(path).forward(request, response);
        }
		
	}

}
