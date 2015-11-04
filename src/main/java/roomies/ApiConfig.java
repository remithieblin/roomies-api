package roomies;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;

import roomies.api.exceptions.InvalidTokenException;
import roomies.utils.DefaultFacebookIntrospector;
import roomies.utils.FacebookIntrospector;
import roomies.utils.TokenBuilder;

@Configuration
public class ApiConfig {

	@Autowired
	private TokenBuilder tokenBuilder;

	private final Logger LOG = LoggerFactory.getLogger(this.getClass().getName());

	@Bean
	public ErrorAttributes errorAttributes() {
		return new DefaultErrorAttributes() {

			@Override
			public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
				//              Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
				//              Object errorMessage = requestAttributes.getAttribute(RequestDispatcher.ERROR_MESSAGE, RequestAttributes.SCOPE_REQUEST);
				//              if (errorMessage != null) {
				//                  errorAttributes.put("message",  errorMessage);
				//              }
				return null;
			}

		};
	}

	@Bean
	public FacebookIntrospector introspector() {
		return new DefaultFacebookIntrospector();
	}

	@Bean
	public Filter authFilter() {
		Filter filter = new Filter() {
			public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
					throws IOException, ServletException {

				HttpServletRequest httpRequest = (HttpServletRequest) request;

				String path = httpRequest.getRequestURI();

				if (path.startsWith("/auth/token")) {
					filterChain.doFilter(request, response);
				} else {

					String header = httpRequest.getHeader("token");

					try {

						if (!tokenBuilder.verifyToken(header))
							throw new InvalidTokenException();

					} catch (InvalidTokenException e) {
						LOG.error("invalid token:", e);
						((HttpServletResponse) response).sendError(HttpStatus.UNAUTHORIZED.value());
					} catch (Exception e) {
						LOG.warn("auth filter:", e);
						((HttpServletResponse) response).sendError(HttpStatus.BAD_REQUEST.value());
					}

					filterChain.doFilter(request, response);
				}
			}

			public void destroy() {
			}

			public void init(FilterConfig arg0) throws ServletException {
			}
		};
		return filter;
	}

	@Bean
	public FilterRegistrationBean filterRegistration() {
		FilterRegistrationBean filter = new FilterRegistrationBean();
		filter.setFilter(authFilter());

		return filter;
	}

}
