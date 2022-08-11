package ru.multicarta.shopping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.multicarta.shopping.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static ru.multicarta.shopping.entity.Role.USER;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    private static final String rememberMeKey = "somethingVerySecured";

    private static List<String> publicPaths = new ArrayList<String>(Arrays.asList(
            "/",
            "index",
            "/static/css/*",
            "/static/js/*",
            "login",
            "/login/login-error",
            "/register",
            "/api/v1/user/register",
            "/api/v1/user/register/**"
    ));

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers(publicPaths.toArray(new String[0]))
                        .permitAll()
                    .antMatchers("/dashboard","/dashboard/**")
                        .hasAnyAuthority(USER.name())
                        .anyRequest()
                        .authenticated()
                        .and()
                    .formLogin()
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl( "/dashboard", true)
                        .passwordParameter("password")
                        .usernameParameter("username")
                        .failureUrl("/login/login-error")
                        .and()
                    .rememberMe()
                        .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                        .key(rememberMeKey)
                        .rememberMeParameter("remember-me")
                        .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", HttpMethod.GET.name()))
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID", "remember-me")
                        .logoutSuccessUrl("/login");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}