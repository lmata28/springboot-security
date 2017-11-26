package pe.edu.tecsup.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/ventas/**").hasRole("VENTAS")
                .antMatchers("/index").fullyAuthenticated()
                .antMatchers("/**").permitAll();

        //Formulario de autenticacion
        http.formLogin()
                .loginPage("/login")
                .successForwardUrl("/index")
                .failureUrl("/login?error")
                .permitAll();

        http.logout().logoutSuccessUrl("/");
        http.sessionManagement().invalidSessionUrl("/");
        http.exceptionHandling().accessDeniedPage("/");//ventas ingresa a adm
        
        http.csrf().disable();//tema de seguridad

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

}
