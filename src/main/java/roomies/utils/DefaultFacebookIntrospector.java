package roomies.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import roomies.api.contracts.FacebookIntrospectionData;
import roomies.api.contracts.FacebookIntrospectionResult;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DefaultFacebookIntrospector implements FacebookIntrospector {

	private String appId = "facebook-app-id";

	public FacebookIntrospectionData introspect(String accessToken) throws URISyntaxException, ClientProtocolException,
			IOException {
		//		GET graph.facebook.com/debug_token?
		//		     input_token={token-to-inspect}
		//		     &access_token={app-token-or-admin-token}

		HttpClient client = HttpClientBuilder.create().build();

		URIBuilder builder = new URIBuilder();
		builder.setScheme("https").setHost("graph.facebook.com").setPath("/debug_token")
				.setParameter("input_token", accessToken)
				.setParameter("access_token", appId + "|secret");
		URI uri = builder.build();
		HttpGet httpget = new HttpGet(uri);

		HttpResponse response = client.execute(httpget);

		HttpEntity entity = response.getEntity();

		ObjectMapper mapper = new ObjectMapper();
		FacebookIntrospectionResult result = mapper.readValue(entity.getContent(), FacebookIntrospectionResult.class);

		return result.getData();
	}

	public boolean vaidateTokenData(FacebookIntrospectionData data) {
		return data.isValid() && data.getAppId().equals(appId);
	}

}
