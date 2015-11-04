package roomies.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Component
public class TokenBuilder {

	@Value("${secretFile}")
	private String filePath;

	private MACSigner macSigner;

	private JWSVerifier verifier;

	static final Logger LOG = LoggerFactory.getLogger(TokenBuilder.class);

	public String buildToken(long accountId, int expiration) throws IOException, JOSEException {
		JWTClaimsSet claimsSet = new JWTClaimsSet();
		claimsSet.setSubject(new Long(accountId).toString());
		claimsSet.setExpirationTime(new Date(expiration));

		SignedJWT jwsObject = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

		MACSigner signer = getSigner();
		jwsObject.sign(signer);

		String jwt = jwsObject.serialize();
		return jwt;
	}

	public boolean verifyToken(String token) throws ParseException, IOException {
		SignedJWT signedJWT = SignedJWT.parse(token);

		JWSVerifier verifier = getVerifier();

		try {
			return signedJWT.verify(verifier);
		} catch (JOSEException e) {
			LOG.warn("verifyToken", e);
			return false;
		}
	}

	private MACSigner getSigner() throws IOException {

		if (macSigner == null) {
			Path path = Paths.get(filePath);
			String secret = new String(Files.readAllBytes(path), Charset.defaultCharset());
			macSigner = new MACSigner(secret);
		}
		return macSigner;
	}

	private JWSVerifier getVerifier() throws IOException {

		if (verifier == null) {
			Path path = Paths.get(filePath);
			String secret = new String(Files.readAllBytes(path), Charset.defaultCharset());
			verifier = new MACVerifier(secret);
		}
		return verifier;
	}

}
