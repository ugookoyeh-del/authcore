# AuthCore
A Java 17 Spring Security authentication API demonstrating account registration, BCrypt password hashing and protected profile access.
## Run
`mvn spring-boot:run` — API listens on `http://localhost:8082`. H2 file database is created locally.
## Register
```http
POST /api/auth/register
Content-Type: application/json

{"name":"Ada Example","email":"ada@example.com","password":"a-long-demo-password"}
```
## Health and profile
`GET /api/auth/health` is public. `GET /api/auth/me` requires HTTP Basic authentication using the registered email and password.
## Security notes
Passwords are BCrypt-hashed and never returned. This learning API uses HTTP Basic for simplicity; use HTTPS in any hosted environment. Before production, add rate limiting, email verification, password reset, account lockout, secret management, CSRF/session design and a security review. Do not use it as-is for production identity infrastructure.