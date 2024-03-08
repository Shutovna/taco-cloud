package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        User.UserBuilder builder = User.builder().passwordEncoder(passwordEncoder::encode);
        UserDetails user = builder
                .username("nikitos")
                .password("her")
                .roles("USER")
                .build();
        UserDetails admin = builder
                .username("admin")
                .password("her")
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            /*http.csrf(Customizer.withDefaults())
                    .authorizeRequests()
                    .antMatchers("/admin/**")
                    .hasRole("ADMIN")
                    .antMatchers("/anonymous*")
                    .anonymous()
                    .antMatchers("/login*")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and();
*/
        http.csrf(Customizer.withDefaults())
                .authorizeHttpRequests(
                        (requests) -> requests
                                .requestMatchers("/design", "/orders").hasRole("USER")
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .requestMatchers("/", "/**").permitAll()
                )
                .formLogin(login -> login.loginPage("/login"));
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/ignore1", "/ignore2");
    }

}
