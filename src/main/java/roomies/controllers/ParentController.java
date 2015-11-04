package roomies.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ParentController {

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass().getName());

	@ExceptionHandler
	void exception(Exception e, HttpServletResponse response) throws IOException {
		LOG.debug(e.getMessage(), e);
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

}
