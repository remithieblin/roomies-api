package roomies.utils;

import roomies.api.contracts.FacebookIntrospectionData;

public interface FacebookIntrospector {

	public FacebookIntrospectionData introspect(String accessToken) throws Exception;

	public boolean vaidateTokenData(FacebookIntrospectionData data);

}
