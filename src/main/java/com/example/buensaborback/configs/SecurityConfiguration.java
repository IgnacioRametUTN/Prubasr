package com.example.buensaborback.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Value("${auth0.audience}")
    private String audience;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Value("${web.cors.allowed-origins}")
    private String corsAllowedOrigins;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .cors(withDefaults()) //por defecto spring va a buscar un bean con el nombre "corsConfigurationSource".
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
// TODO agregar rutas
                                // Definir reglas para ArticuloInsumoController
                                .requestMatchers(HttpMethod.GET, "/api/articulos/insumos").hasAuthority("SCOPE_read:articulos")
                                .requestMatchers(HttpMethod.GET, "/api/articulos/insumos/**").hasAuthority("SCOPE_read:articulos")
                                .requestMatchers(HttpMethod.POST, "/api/articulos/insumos").hasAuthority("SCOPE_write:articulos")
                                .requestMatchers(HttpMethod.POST, "/api/articulos/insumos/bulk").hasAuthority("SCOPE_write:articulos")
                                .requestMatchers(HttpMethod.POST, "/api/articulos/insumos/uploads").hasAuthority("SCOPE_write:articulos")
                                .requestMatchers(HttpMethod.PUT, "/api/articulos/insumos/**").hasAuthority("SCOPE_write:articulos")
                                .requestMatchers(HttpMethod.DELETE, "/api/articulos/insumos/**").hasAuthority("SCOPE_delete:articulos")
                                .requestMatchers(HttpMethod.GET, "/api/articulos/insumos/search").hasAuthority("SCOPE_read:articulos")

                                // Definir reglas para ArticuloManufacturadoController

                                .requestMatchers(HttpMethod.GET, "/api/articulos/manufacturados").hasAuthority("SCOPE_read:articulos")
                                .requestMatchers(HttpMethod.GET, "/api/articulos/manufacturados/**").hasAuthority("SCOPE_read:articulos")
                                .requestMatchers(HttpMethod.POST, "/api/articulos/manufacturados").hasAuthority("SCOPE_write:articulos")
                                .requestMatchers(HttpMethod.POST, "/api/articulos/manufacturados/bulk").hasAuthority("SCOPE_write:articulos")
                                .requestMatchers(HttpMethod.PUT, "/api/articulos/manufacturados/**").hasAuthority("SCOPE_write:articulos")
                                .requestMatchers(HttpMethod.DELETE, "/api/articulos/manufacturados/**").hasAuthority("SCOPE_delete:articulos")
                                .requestMatchers(HttpMethod.GET, "/api/articulos/manufacturados/search").hasAuthority("SCOPE_read:articulos")

                                // Categorias
                                .requestMatchers(HttpMethod.GET, "/api/categorias").hasAuthority("SCOPE_read:categorias")
                                .requestMatchers(HttpMethod.GET, "/api/categorias/padres").hasAuthority("SCOPE_read:categorias")
                                .requestMatchers(HttpMethod.GET, "/api/categorias/alta").hasAuthority("SCOPE_read:categorias")
                                .requestMatchers(HttpMethod.GET, "/api/categorias/**").hasAuthority("SCOPE_read:categorias")
                                .requestMatchers(HttpMethod.PUT, "/api/categorias/**").hasAuthority("SCOPE_write:categorias")
                                .requestMatchers(HttpMethod.POST, "/api/categorias/**").hasAuthority("SCOPE_write:categorias")
                                .requestMatchers(HttpMethod.POST, "/api/categorias/bulk").hasAuthority("SCOPE_write:categorias")
                                .requestMatchers(HttpMethod.DELETE, "/api/categorias/**").hasAuthority("SCOPE_delete:categorias")

                                //Clientes
                                .requestMatchers(HttpMethod.GET, "/api/clientes").hasAuthority("SCOPE_read:clientes")
                                .requestMatchers(HttpMethod.GET, "/api/clientes/**").hasAuthority("SCOPE_read:clientes")
                                .requestMatchers(HttpMethod.POST, "/api/clientes").hasAuthority("SCOPE_write:clientes")
                                .requestMatchers(HttpMethod.PUT, "/api/clientes/**").hasAuthority("SCOPE_write:clientes")
                                .requestMatchers(HttpMethod.DELETE, "/api/clientes/**").hasAuthority("SCOPE_delete:clientes")

                                //Domicilios
                                .requestMatchers(HttpMethod.GET, "/api/domicilios").hasAuthority("SCOPE_read:domicilios")
                                .requestMatchers(HttpMethod.GET, "/api/domicilios/alta").hasAuthority("SCOPE_read:domicilios")
                                .requestMatchers(HttpMethod.GET, "/api/domicilios/**").hasAuthority("SCOPE_read:domicilios")
                                .requestMatchers(HttpMethod.PUT, "/api/domicilios/**").hasAuthority("SCOPE_write:domicilios")
                                .requestMatchers(HttpMethod.POST, "/api/domicilios").hasAuthority("SCOPE_write:domicilios")
                                .requestMatchers(HttpMethod.DELETE, "/api/domicilios/**").hasAuthority("SCOPE_delete:domicilios")

                                //Empresa
                                .requestMatchers(HttpMethod.GET, "/api/empresas").hasAuthority("SCOPE_read:empresas")
                                .requestMatchers(HttpMethod.GET, "/api/empresas/**").hasAuthority("SCOPE_read:empresas")
                                .requestMatchers(HttpMethod.POST, "/api/empresas").hasAuthority("SCOPE_write:empresas")
                                .requestMatchers(HttpMethod.PUT, "/api/empresas/**").hasAuthority("SCOPE_write:empresas")
                                .requestMatchers(HttpMethod.DELETE, "/api/empresas/**").hasAuthority("SCOPE_delete:empresas")

                                //Mercado Pago
                                .requestMatchers(HttpMethod.POST, "/api/mercado-pago/crear_preference_mp").hasAuthority("SCOPE_write:mercado-pago")

                                //Pedido
                                .requestMatchers(HttpMethod.POST, "/api/pedidos").hasAuthority("SCOPE_write:pedidos")
                                .requestMatchers(HttpMethod.GET, "/api/pedidos").hasAuthority("SCOPE_read:pedidos")
                                .requestMatchers(HttpMethod.GET, "/api/pedidos/**").hasAuthority("SCOPE_read:pedidos")
                                .requestMatchers(HttpMethod.PUT, "/api/pedidos/actualizar/**").hasAuthority("SCOPE_write:pedidos")

                                //Promocion
                                .requestMatchers(HttpMethod.GET, "/api/promociones").hasAuthority("SCOPE_read:promociones")
                                .requestMatchers(HttpMethod.GET, "/api/promociones/**").hasAuthority("SCOPE_read:promociones")
                                .requestMatchers(HttpMethod.POST, "/api/promociones").hasAuthority("SCOPE_write:promociones")
                                .requestMatchers(HttpMethod.PUT, "/api/promociones/**").hasAuthority("SCOPE_write:promociones")
                                .requestMatchers(HttpMethod.DELETE, "/api/promociones/**").hasAuthority("SCOPE_delete:promociones")

                                //PromocionDetalle
                                .requestMatchers(HttpMethod.GET, "/api/promociones-detalles").hasAuthority("SCOPE_read:promociones-detalles")
                                .requestMatchers(HttpMethod.GET, "/api/promociones-detalles/**").hasAuthority("SCOPE_read:promociones-detalles")
                                .requestMatchers(HttpMethod.POST, "/api/promociones-detalles").hasAuthority("SCOPE_write:promociones-detalles")
                                .requestMatchers(HttpMethod.PUT, "/api/promociones-detalles/**").hasAuthority("SCOPE_write:promociones-detalles")
                                .requestMatchers(HttpMethod.DELETE, "/api/promociones-detalles/**").hasAuthority("SCOPE_delete:promociones-detalles")

                                //Reportes
                                .requestMatchers(HttpMethod.GET, "/api/reportes/top-products").hasAuthority("SCOPE_read:reportes")
                                .requestMatchers(HttpMethod.GET, "/api/reportes/ReporteTotales").hasAuthority("SCOPE_read:reportes")

                                //Sucursal
                                .requestMatchers(HttpMethod.POST, "/api/sucursales").hasAuthority("SCOPE_write:sucursales")
                                .requestMatchers(HttpMethod.GET, "/api/sucursales/{id}").hasAuthority("SCOPE_read:sucursales")
                                .requestMatchers(HttpMethod.GET, "/api/sucursales").hasAuthority("SCOPE_read:sucursales")
                                .requestMatchers(HttpMethod.PUT, "/api/sucursales/{id}").hasAuthority("SCOPE_write:sucursales")
                                .requestMatchers(HttpMethod.DELETE, "/api/sucursales/{id}").hasAuthority("SCOPE_write:sucursales")
                                .requestMatchers(HttpMethod.GET, "/api/sucursales/empresa/{empresaId}").hasAuthority("SCOPE_read:sucursales")

                                //Unidad Medida
                                .requestMatchers(HttpMethod.GET, "/api/unidades-medida").hasAuthority("SCOPE_read:unidades-medida")
                                .requestMatchers(HttpMethod.GET, "/api/unidades-medida/alta").hasAuthority("SCOPE_read:unidades-medida")
                                .requestMatchers(HttpMethod.GET, "/api/unidades-medida/{id}").hasAuthority("SCOPE_read:unidades-medida")
                                .requestMatchers(HttpMethod.PUT, "/api/unidades-medida/{id}").hasAuthority("SCOPE_write:unidades-medida")
                                .requestMatchers(HttpMethod.POST, "/api/unidades-medida").hasAuthority("SCOPE_write:unidades-medida")
                                .requestMatchers(HttpMethod.POST, "/api/unidades-medida/bulk").hasAuthority("SCOPE_write:unidades-medida")
                                .requestMatchers(HttpMethod.DELETE, "/api/unidades-medida/{id}").hasAuthority("SCOPE_write:unidades-medida")

                                //Usuario
                                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/auth/validar/{nombreUsuario}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/auth/cliente/{nombreUsuario}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/auth").hasAuthority("SCOPE_read:usuarios")


                                .anyRequest().permitAll()
                )
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer
                                .jwt(jwt ->
                                        jwt
                                                .decoder(jwtDecoder())
                                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                                )
                );
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(corsAllowedOrigins.split(",")));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setExposedHeaders(Arrays.asList("X-Get-Header"));
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer);

        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        String audience = System.getenv("auth0.audience");
        converter.setAuthoritiesClaimName(audience+"/roles");
        converter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(converter);
        return jwtConverter;
    }
}