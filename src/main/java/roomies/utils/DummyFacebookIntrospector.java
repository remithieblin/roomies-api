package roomies.utils;

import roomies.api.contracts.FacebookIntrospectionData;

public class DummyFacebookIntrospector implements FacebookIntrospector {

	public FacebookIntrospectionData introspect(String accessToken) throws Exception {
		FacebookIntrospectionData data = new FacebookIntrospectionData();
		data.setUserId("10153309213502866");
		return data;
	}

	public boolean vaidateTokenData(FacebookIntrospectionData data) {
		return true;
	}

}
