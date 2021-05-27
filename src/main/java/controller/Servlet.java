package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.Command;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map<String, Command> commands = new HashMap<>();

	@Override
	public void init(){
		log("vd");
        //init command
    }

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		processRequest(request, response);
	}



	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
        processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		String path = request.getRequestURI();
        path = path.replaceAll(".*/app/" , "");
        Command command = commands.getOrDefault(path ,
                (r)->"/index.jsp)");
        String page = command.execute(request);
        if(page.contains("redirect:")){
            response.sendRedirect(page.replace("redirect:", "/api"));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
		
	}

}
