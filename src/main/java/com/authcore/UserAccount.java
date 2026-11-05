package com.authcore;
import jakarta.persistence.*;import java.time.Instant;
@Entity @Table(name="user_accounts",uniqueConstraints=@UniqueConstraint(columnNames="email"))
public class UserAccount{
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=80) private String name;
 @Column(nullable=false,unique=true,length=120) private String email;
 @Column(nullable=false) private String passwordHash;
 @Column(nullable=false,updatable=false) private Instant createdAt=Instant.now();
 protected UserAccount(){} public UserAccount(String name,String email,String passwordHash){this.name=name;this.email=email;this.passwordHash=passwordHash;}
 public Long getId(){return id;}public String getName(){return name;}public String getEmail(){return email;}public String getPasswordHash(){return passwordHash;}public Instant getCreatedAt(){return createdAt;}
}