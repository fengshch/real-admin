package cc.realtec.real.bffmvc.security;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.oauth2.core.http.converter.OAuth2ErrorHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class TokenRevocationService {
    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientService authorizedClientService;

    // URL of the Authorization Server's revocation endpoint
    private static final String REVOCATION_URL = "http://localhost:9000/oauth2/revoke";

    public TokenRevocationService(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientService authorizedClientService) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.authorizedClientService = authorizedClientService;
    }

    // Method to revoke an access token
    public void revokeToken(String clientRegistrationId, String principalName) {
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(clientRegistrationId);
        if (clientRegistration == null) {
            throw new IllegalArgumentException("Client registration not found for id: " + clientRegistrationId);
        }

        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(clientRegistrationId, principalName);

        if (authorizedClient == null) {
            throw new IllegalArgumentException("Authorized client not found for client registration id: " + clientRegistrationId + " and principal name: " + principalName);
        }

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        OAuth2RefreshToken refreshToken = authorizedClient.getRefreshToken();

        if (accessToken == null && refreshToken == null) {
            throw new IllegalArgumentException("No tokens found for client registration id: " + clientRegistrationId + " and principal name: " + principalName);
        }

        authorizedClientService.removeAuthorizedClient(clientRegistrationId, principalName);

//        String tokenRevocationEndpoint = clientRegistration.getProviderDetails().getTokenUri() + "/revoke";
        String revocationEndpoint = clientRegistration.getProviderDetails().getConfigurationMetadata().get("revocation_endpoint").toString();

        // Create a RestTemplate to send the HTTP request
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(List.of(
            new OAuth2AccessTokenResponseHttpMessageConverter(),
            new OAuth2ErrorHttpMessageConverter(),
            new FormHttpMessageConverter()
        ));

        String clientId = clientRegistration.getClientId();
        String clientSecret = clientRegistration.getClientSecret();

        if(accessToken ==null){
            throw new IllegalArgumentException("No access token found for client registration id: " + clientRegistrationId + " and principal name: " + principalName);
        }

        revokeSpecificToken(restTemplate, revocationEndpoint, clientId, clientSecret, accessToken.getTokenValue(), "access_token");

        if(refreshToken ==null){
            throw new IllegalArgumentException("No refresh token found for client registration id: " + clientRegistrationId + " and principal name: " + principalName);
        }

        revokeSpecificToken(restTemplate, revocationEndpoint, clientId, clientSecret, refreshToken.getTokenValue(), "refresh_token");
    }

    private void revokeSpecificToken(RestTemplate restTemplate, String tokenRevocationEndpoint, String clientId, String clientSecret, String tokenValue, String tokenTypeHint) {
        if(tokenTypeHint == null || tokenTypeHint.isBlank()) {
            throw new IllegalArgumentException("Token type hint must not be empty");
        }

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add(OAuth2ParameterNames.TOKEN, tokenValue);
        body.add(OAuth2ParameterNames.TOKEN_TYPE_HINT, tokenTypeHint);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(tokenRevocationEndpoint);

        try {
            restTemplate.postForEntity(uriBuilder.toUriString(), new HttpEntity<>(body, createBasicAuthHeaders(clientId, clientSecret)), Void.class);
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to revoke " + tokenTypeHint + ": " + ex.getMessage(), ex);
        }
    }

    private HttpHeaders createBasicAuthHeaders(String clientId, String clientSecret) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String basicAuth = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8));
        headers.set(HttpHeaders.AUTHORIZATION, "Basic " + basicAuth);
        return headers;
    }

}
