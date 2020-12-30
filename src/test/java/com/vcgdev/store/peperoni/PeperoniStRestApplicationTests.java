package com.vcgdev.store.peperoni;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class PeperoniStRestApplicationTests {

	private RestTemplate restTemplate = new RestTemplate();
	private ObjectMapper mapper = new ObjectMapper();

	@Test
	void createAccessToken() throws Exception {
		String url = "http://localhost:8080/oauth/token";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("username", "testing");
		map.add("password", "my-pwd-test");
		map.add("grant_type", "password");
		map.add("client_secret", "my-pwd-test");
		map.add("client_id", "client-id-1");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		HttpEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
		assertTrue(responseEntity.hasBody());
		
		JsonNode root = mapper.readTree(responseEntity.getBody());
		assertNotNull(root.path("access_token"));
		assertNotNull(root.path("token_type"));
		assertNotNull(root.path("expires_in"));
		assertNotNull(root.path("scope"));
		assertNotNull(root.path("refresh_token"));


	}

}
