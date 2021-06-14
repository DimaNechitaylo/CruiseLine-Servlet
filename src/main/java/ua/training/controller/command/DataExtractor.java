package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface DataExtractor<T> {
	T extractData(HttpServletRequest request);
}
