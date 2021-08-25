//package dev.evertonsavio.app.logfileservice.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsConfigurationSource;
//import org.springframework.web.cors.reactive.CorsWebFilter;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//
//@EnableWebFluxSecurity
//@EnableReactiveMethodSecurity
//public class WebSecurity {
//
//    private static final String[] AUTH_WHITELIST = {
//            "/log-file-service/",
//            "/",
//            "/log-file-service/v1/log/**"
//    };
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//
//        http.cors().and().csrf().disable();
//
//        http.authorizeExchange()
//                .pathMatchers(AUTH_WHITELIST).permitAll()
//                .and().authorizeExchange()
//                .pathMatchers("/log-file-service/v1/file/**")
//                .hasAnyRole("developer")
//                .anyExchange().authenticated()
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
//
//        return http.build();
//    }
//
//    @Bean
//    public CorsWebFilter corsFilter() {
//        return new CorsWebFilter(corsConfigurationSource());
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        final org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
//        config.addAllowedMethod(HttpMethod.GET);
//        config.addAllowedMethod(HttpMethod.POST);
//        config.addAllowedMethod(HttpMethod.PUT);
//        config.addAllowedMethod(HttpMethod.DELETE);
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }
//}
