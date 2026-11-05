package com.authcore;
import jakarta.validation.Valid;import jakarta.validation.constraints.*;import org.springframework.http.HttpStatus;import org.springframework.security.core.Authentication;import org.springframework.security.crypto.password.PasswordEncoder;import org.springframework.web.bind.annotation.*;import java.util.Map;
@RestController @RequestMapping("/api/auth") public class AuthController{
 private final UserRepository users;private final PasswordEncoder encoder;public AuthController(UserRepository users,PasswordEncoder encoder){this.users=users;this.encoder=encoder;}
 public record RegisterRequest(@NotBlank @Size(max=80) String name,@NotBlank @Email @Size(max=120) String email,@NotBlank @Size(min=10,max=72) String password){}
 @PostMapping("/register") @ResponseStatus(HttpStatus.CREATED) public Map<String,Object> register(@Valid @RequestBody RegisterRequest r){String email=r.email().toLowerCase().trim();if(users.existsByEmail(email))throw new IllegalArgumentException("Email already registered");UserAccount u=users.save(new UserAccount(r.name().trim(),email,encoder.encode(r.password())));return Map.of("id",u.getId(),"name",u.getName(),"email",u.getEmail(),"createdAt",u.getCreatedAt());}
 @GetMapping("/me") public Map<String,Object> me(Authentication auth){UserAccount u=users.findByEmail(auth.getName()).orElseThrow();return Map.of("id",u.getId(),"name",u.getName(),"email",u.getEmail(),"createdAt",u.getCreatedAt());}
 @GetMapping("/health") public Map<String,String> health(){return Map.of("status","ok","service","AuthCore");}
}