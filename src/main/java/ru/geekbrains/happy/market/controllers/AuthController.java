package ru.geekbrains.happy.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.happy.market.beans.JwtTokenUtil;
import ru.geekbrains.happy.market.dto.JwtRequest;
import ru.geekbrains.happy.market.dto.JwtResponse;
import ru.geekbrains.happy.market.exceptions_handling.MarketError;
import ru.geekbrains.happy.market.model.Cart;
import ru.geekbrains.happy.market.model.User;
import ru.geekbrains.happy.market.services.CartService;
import ru.geekbrains.happy.market.services.UserService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final CartService cartService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new MarketError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);

        cartService.getCartForUser(authRequest.getUsername(), authRequest.getCartId());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
