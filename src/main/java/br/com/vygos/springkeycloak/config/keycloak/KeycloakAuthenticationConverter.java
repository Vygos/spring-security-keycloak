package br.com.vygos.springkeycloak.config.keycloak;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;
import java.util.stream.Collectors;

public class KeycloakAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
  @Override
  public AbstractAuthenticationToken convert(Jwt jwt) {
    JSONObject realmAccess = jwt.getClaim("realm_access");
    JSONArray roles = (JSONArray) realmAccess.get("roles");
    List<SimpleGrantedAuthority> authorities = roles.
        stream().map((Object role) -> new SimpleGrantedAuthority((String) role))
        .collect(Collectors.toList());
    return new JwtAuthenticationToken(jwt, authorities);
  }
}
