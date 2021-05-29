package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import controller.command.Command;
import controller.command.CommandCaller;

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
        String command  = request.getParameter("action");
        logger.info("doGet "+command);
//        response.getWriter().print("Hello from servlet");
		processRequest(request, response);
	}



	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
        String command  = request.getParameter("action");
        logger.info("doPost "+command);
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
        String command  = request.getParameter("action");
        String path = commandCaller.call(command.toUpperCase()).execute(request, response);
        if(path.contains("redirect:")){
            response.sendRedirect(path.replace("redirect:", ""));
        }else {
            request.getRequestDispatcher(path).forward(request, response);
        }
		
	}

}
