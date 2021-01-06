package com.example.demo.model;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Entity
public class User {
		
	@Id
	@GeneratedValue
	protected Long id;
	@Column(unique = true)
	protected String username;
	protected byte[] salt;
	protected byte[] hash;
	
	@OneToMany(mappedBy="user")
	List<Account> accounts;
	
	@OneToMany(mappedBy = "user")
	List<CreditCard> creditCards;
	
	//One to Many Loans
	@OneToMany(mappedBy="user")
	List<Loan> loans;
	
	@OneToMany(mappedBy="user")
	List<creditCardRequest> creditCardRequests;
	
	@OneToMany(mappedBy="user")
	List<loanRequest> loanRequests;
	
	@JsonIgnoreProperties
	protected String password;//needed for configuration but do not use.
	
	public User() {
		System.out.println("Default Constructor HIT");
	}
	
	//I do not think this is ever hit.
	public User(String username, String password) {
		System.out.println("Constructor w/ fields HIT");
		this.username = username;
		System.out.println("PASSWORD:: " + password);
		this.hash = this.hashPassword(password);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		System.out.println("HITTING SETTERS");
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		System.out.println("HITTING SETTERS");
		this.username = username;
	}
	
	public byte[] getSalt() {
		return salt;
	}
	public void setSalt(byte[] salt) {
		System.out.println("HITTING SETTERS");
		this.salt = salt;
	}
	public byte[] getHash() {
		return hash;
	}
	public void setHash(byte[] hash) {
		this.hash = hash;
	}
	
	//merely for setter injection. NOTICE how the password is not being set.
	public void setPassword(String password) {
		hashPassword(password);
	}
	
	//for debugging
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", salt=" + Arrays.toString(salt) + ", hash="
				+ Arrays.toString(hash) + ", password=" + password + "]";
	}
	
   /*
	*this function is hit on user creation
	*the client end sends a password as a string
	*this method is hit which encrypts the password and stores as hash and string
	**/
	public byte[] hashPassword(String password) {
		System.out.println("INITIAL PASSWORD" + password);
		System.out.println("HASHED PASSWORDS FUNCTION HIT");
		MessageDigest md;
		byte[] hashedPassword = null;
		
		try {
			//select message digest version for hash computation. 
			//selected sha-256 beacuase of ease and familiarity 
			md = MessageDigest.getInstance("SHA-256");
			
			//Generate the random salt
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			
			//passing the salt into the digest for computation
			md.update(salt);
			
			//storing salt to the DB
			this.salt = salt;
			
			//for debugging purposes
			System.out.println("SALT ONE::: " + this.salt);
			StringBuilder sb2 = new StringBuilder();
            System.out.println("iterated salt ONE::: ");
            for (byte b : salt)
                sb2.append(String.format("%02x", b));
            System.out.println(sb2);
			
			
			//Generate the salted hash
			hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));			
			
			//storing the hash in the DB
			this.hash = hashedPassword;
		}catch(Exception e) {
			System.err.println("Error in hashPassword method: " + e);
		}
		return hashedPassword;
	}
	
	/*
	 * This is the login method. When the login route is hit this middleware will
	 * validate that the password is correct. 
	 * */
	public String authenticateLogin(String password, User user) {
		MessageDigest md;
		Boolean validCredentials = false;
		System.out.println("PASSWORD SENT TO MIDDLEWARE: " + password);
		String token = null;

		
		//alot of this wil look similar to hashedPassword
		try {
			
			//Making sure to use the same algorithm as we did when creating the users
			md = MessageDigest.getInstance("SHA-256");
			
			//updating the digest with the proper salt that was stored on this instance
			//for digest computation when hashing
			md.update(this.salt);			
			
			//using the digest, with updated salt and proper algorithm to generate the hashedPassword
			byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

			//VERY IMPORTANT part that checks if the newly generated hashedPassword matches 
			//the hashedPassword saved to this user instance
			if(Arrays.equals(this.hash, hashedPassword)) {
				System.out.println("HASHES ARE EQUAL");				
				token = this.generateToken(user);
				validCredentials = true;
			}
			
			
			//Debugging purposes
			StringBuilder sb1 = new StringBuilder();
			System.out.println("THIS.HASH::: ");
            for (byte b : this.hash)
                sb1.append(String.format("%02x", b));

            System.out.println(sb1);
            
            StringBuilder sb3 = new StringBuilder();
            System.out.println("iterated SALT TWO::: ");
            for (byte b : this.salt)
                sb3.append(String.format("%02x", b));

            System.out.println(sb3);
            
            StringBuilder sb2 = new StringBuilder();
            System.out.println("HASHEDPASSWORD::: ");
            for (byte b : hashedPassword)
                sb2.append(String.format("%02x", b));

            System.out.println(sb2);
			
		}catch(Exception e) {
			System.err.println("Error in authenticateLogin ConsumerUser class: " + e);
		}
		if(!validCredentials) {
			return null;
		}
		return token;
	}
	
	/*
	 * This method is responsible for generating a JWT token using HMAC 
	 * to sign. Note that the expiration date is for 5 minutes after assigned.
	 */
	private String generateToken(User user) {
//		Instant instant = new Instant();
		//HMAC secret key that I found on the internet. 
		//Not sure how to get my own unique one. 
		//Should look into that.
		String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
		String jwtToken = null;
		
		try {
			//Creating a key for signing and authorization
			Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret), 
					SignatureAlgorithm.HS256.getJcaName());
			
			//Creating the JWT token for data authorization
			//I am not certain what most of the claim, subject, and Id 
			//functions are doing. Need to research that.
			jwtToken = Jwts.builder()
					.claim("user", user)
					.setSubject(user.getUsername())
					.setId(UUID.randomUUID().toString())
					.setIssuedAt(Date.from(Instant.now()))
					.setExpiration(Date.from(Instant.now().plus(5l, ChronoUnit.MINUTES)))
					.signWith(hmacKey)
					.compact(); 
			
		}catch (Exception e) {
			System.err.println("Error in generateToken" + e);
		}
		return jwtToken;
	}
	
	/*
	 * validates that the token is not expired and is a valid token.
	*/
	public static Boolean validateUserToken(String token) {
		String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
		Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret), 
                SignatureAlgorithm.HS256.getJcaName());
		Jws<Claims> jwt = null;
		try {			
			jwt = Jwts.parserBuilder()
					.setSigningKey(hmacKey)
					.build()
					.parseClaimsJws(token);
		}catch(Exception e){
			System.err.println("Invalid Token please login(Most likely due to expiration: " + e);
			return false;
		}
		return true;
	}
}
