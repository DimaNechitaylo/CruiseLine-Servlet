package controller.listener;

import java.util.Locale;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import util.ResourceManager;

@WebListener
public class SessionListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		if (session.getAttribute("lang") == null) {
			Locale localeEN = new Locale("en");
			ResourceManager.getInstance().changeLocale(localeEN);
			session.setAttribute("lang", localeEN.getLanguage());
		}
	}
}
