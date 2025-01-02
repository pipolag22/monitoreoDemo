package com.example.demo.Controller;

import com.example.demo.Security.jwt.JwtUtils;
import com.example.demo.persistence.Dto.LoginDto;
import com.example.demo.persistence.Dto.RegisterUserDto;
import com.example.demo.persistence.entities.User;
import com.example.demo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Operation(summary = "Registrar un usuario", description = "Registra un nuevo usuario ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "El nombre de usuario o correo ya esta en uso")
    })
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserDto registerUserDto) {
        if (!registerUserDto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El formato del correo es invalido");
        }
        if (registerUserDto.getPassword().length() < 8) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contraseña debe tener al menos 8 caracteres");
        }
        try {
            User user = new User();
            user.setEmail(registerUserDto.getEmail());
            user.setUsername(registerUserDto.getUsername());
            user.setPassword(registerUserDto.getPassword());

            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok("Usuario registrado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @Operation(summary = "Login", description = "Iniciar sesion ")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Optional<User> foundUser = userService.getUserEmail(loginDto.getEmail());

        if (foundUser.isEmpty() || !passwordEncoder.matches(loginDto.getPassword(), foundUser.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales invalidas");
        }

        String token = JwtUtils.generateToken(foundUser.get().getEmail());

        return ResponseEntity.ok(Map.of(
                "message", "Inicio de sesión exitoso",
                "token", token
        ));
    }
}
