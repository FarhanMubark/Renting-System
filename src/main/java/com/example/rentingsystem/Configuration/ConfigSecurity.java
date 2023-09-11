package com.example.rentingsystem.Configuration;

import com.example.rentingsystem.Api.ApiResponse;
import com.example.rentingsystem.Model.Ticket;
import com.example.rentingsystem.Model.User;
import com.example.rentingsystem.Service.ImageDataService;
import com.example.rentingsystem.Service.UserDetailsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigSecurity {
        private final UserDetailsService userDetailsService;
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/registers/add-renter").permitAll()
                .requestMatchers("/api/v1/registers/add-lessors").permitAll()
                .requestMatchers("/api/v1/registers/add-employee").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/registers/get").hasAnyAuthority("ADMIN","EMPLOYEE")
                .requestMatchers("/api/v1/registers/getinfo").hasAnyAuthority("ADMIN","RENTER","LESSOR")
                .requestMatchers("/api/v1/registers/delete/{userName}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/registers/block-renter/{renter_id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/registers/block-lessor/{lessor_id}").hasAuthority("ADMIN")

                .requestMatchers("/api/v1/tickets/get").hasAnyAuthority("ADMIN","EMPLOYEE")
                .requestMatchers("/api/v1/tickets/get-by-id/{ticket_id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/tickets/resolve-ticket/{ticket_id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/employees/**").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/tickets/add-l-ticket").hasAuthority("LESSOR")
                .requestMatchers("/api/v1/tickets/get-l-tickets").hasAuthority("LESSOR")
                .requestMatchers("/api/v1/tickets/update-l-ticket/{ticket_id}").hasAuthority("LESSOR")
                .requestMatchers("/api/v1/tickets/delete-l-ticket/{ticket_id}").hasAuthority("LESSOR")
                .requestMatchers("/api/v1/tickets/add-r-ticket").hasAuthority("RENTER")
                .requestMatchers("/api/v1/tickets/get-r-tickets").hasAuthority("RENTER")
                .requestMatchers("/api/v1/tickets/update-r-ticket/{ticket_id}").hasAuthority("RENTER")
                .requestMatchers("/api/v1/tickets/delete-r-ticket/{ticket_id}").hasAuthority("RENTER")

                .requestMatchers("/api/v1/warehouses/**").hasAuthority("ADMIN")

                .requestMatchers("/api/v1/subscriptions/").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/subscriptions/update/{subscriptionId}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/subscriptions/{subscriptionId}").hasAuthority("ADMIN")

                .requestMatchers("/api/v1/lessors/subscriber-{subscriberNumber}").hasAuthority("LESSOR")
                .requestMatchers("/api/v1/lessors/assing-{warehouseId}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/lessors/search").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/lessors/update").hasAnyAuthority("ADMIN","LESSOR")

                .requestMatchers("/api/v1/renters/buy-product/{product_id}/{typeOfDay}/{quantity}/{duration}").hasAuthority("RENTER")
                .requestMatchers("/api/v1/renters/search").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/renters/update").hasAnyAuthority("ADMIN","RENTER")




                .requestMatchers("/api/v1/employees/assing-{warehouseId}").hasAuthority("AMDIN")
                .requestMatchers("/api/v1/employees/search").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/employees/update").hasAnyAuthority("ADMIN","EMPLOYEE")
                .requestMatchers("/api/v1/employees/").hasAuthority("ADMIN")

                .requestMatchers("/api/v1/products/get").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/products/add/{typeOfDate}/{duration}").hasAuthority("LESSOR")
                .requestMatchers("/api/v1/update/{productId}/{typeOfDate}/{duration}").hasAuthority("LESSOR")
                .requestMatchers("/api/v1/products/delete/{productId}").hasAnyAuthority("ADMIN","LESSOR")
                .requestMatchers("/api/v1/products/expand-duration/{productId}/{typeOfDate}/{duration}").hasAuthority("LESSOR")
                .requestMatchers("/api/v1/products/get-products-by-lessor").hasAnyAuthority("ADMIN","LESSOR","RENTER")
                .requestMatchers("/api/v1/products/get-products-available/").hasAnyAuthority("ADMIN","LESSOR","RENTER")
                .requestMatchers("/api/v1/products/get-products-available-by-price").hasAnyAuthority("ADMIN","LESSOR","RENTER")
                .requestMatchers("/api/v1/products/get-products-available-by-price-desc").hasAnyAuthority("ADMIN","LESSOR","RENTER")
                .requestMatchers("/api/v1/products/get-products-by-name/{productName}").hasAnyAuthority("ADMIN","LESSOR","RENTER")

                .requestMatchers("/api/v1/orders/get").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/orders/is-return/{renter_id}/{order_id}/{rate}/{comment}").hasAuthority("RENTER")
                .requestMatchers("/api/v1/orders/show-my-orders").hasAuthority("RENTER")



                .requestMatchers("/api/v1/image/upload/{product_id}").hasAuthority("LESSOR")
                .requestMatchers("/api/v1/image/upload/download/{fileName}/{product_id}").hasAnyAuthority("ADMIN", "LESSOR")
                .requestMatchers("/api/v1/image/delete/{fileName}").hasAnyAuthority("ADMIN", "LESSOR")
                .anyRequest().permitAll()
                .and()
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }
}
