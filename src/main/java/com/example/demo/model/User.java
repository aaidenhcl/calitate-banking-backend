package com.example.demo.model;

import java.util.Date;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyCollection;

import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import com.example.demo.model.CreditCardRequest;
import com.example.demo.utilities.EmailMessage;
import com.example.demo.utilities.EmailSender;

@Entity
public class User {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	@Column(unique = true)
	protected String username;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTwoFactorAuth() {
		return twoFactorAuth;
	}

	public void setTwoFactorAuth(String twoFactorAuth) {
		this.twoFactorAuth = twoFactorAuth;
	}

	protected byte[] salt;
	protected byte[] hash;

	protected Date DOB;
	protected String firstName;
	protected String lastName;
	protected String address;
	protected String region;
	protected Integer creditScore;
	protected String profession;
	protected String email;
	protected String twoFactorAuth = null;
	
	@CreatedDate
	protected Date dateCreated;
	
	@LastModifiedDate
	protected Date lastUpdated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="user", cascade = CascadeType.ALL)
	List<Account> accounts;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user",  cascade = CascadeType.ALL)
	List<CreditCard> creditCards;
	
	//One to Many Loans
	@OneToMany(mappedBy="user")
	List<Loan> loans;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="user",  cascade = CascadeType.ALL)
	List<CreditCardRequest> creditCardRequests;
	
	@OneToMany(mappedBy="user")
	List<LoanRequest> loanRequests;
	
	@JsonIgnoreProperties
	protected String password;//needed for configuration but do not use.
	
	public User() {
		System.out.println("Default Constructor HIT");
	}
	
	//I do not think this is ever hit.
	public User(String username, Date dOB, String firstName, String lastName, String address, String region,
			Integer creditScore, String profession, String password) {
		super();
		this.username = username;
		this.DOB = dOB;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.region = region;
		this.creditScore = creditScore;
		this.profession = profession;
		hashPassword(password);
		this.dateCreated = new Date();
		this.lastUpdated = new Date();
	}
	
	public User(String username, Date dOB, String firstName, String lastName, String address, String region,
			Integer creditScore, String profession, String password, String email) {
		super();
		this.username = username;
		this.DOB = dOB;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.region = region;
		this.creditScore = creditScore;
		this.profession = profession;
		hashPassword(password);
		this.dateCreated = new Date();
		this.lastUpdated = new Date();
		this.email = email;
	}
	
	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getPassword() {
		return password;
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}
	
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public List<CreditCard> getCreditCards() {
		return creditCards;
	}
	
	public void setCreditCards(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}
	
	public List<Loan> getLoans() {
		return loans;
	}
	
	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
	
	public List<CreditCardRequest> getCreditCardRequests() {
		return creditCardRequests;
	}
	
	public void setCreditCardRequests(List<CreditCardRequest> creditCardRequests) {
		this.creditCardRequests = creditCardRequests;
	}
	
	public List<LoanRequest> getLoanRequests() {
		return loanRequests;
	}
	
	public void setLoanRequests(List<LoanRequest> loanRequests) {
		this.loanRequests = loanRequests;
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
	
	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegion() {
		return region;
	}

	public void setCreditScore(Integer creditScore) {
		this.creditScore = creditScore;
	}

	public Integer getCreditScore() {
		return creditScore;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getProfession() {
		return profession;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateCreated() {
		return dateCreated;
	}
	

	//merely for setter injection. NOTICE how the password is not being set.
	public void setPassword(String password) {
		hashPassword(password);
	}
	




	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", salt=" + Arrays.toString(salt) + ", hash="
				+ Arrays.toString(hash) + ", DOB=" + DOB + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", region=" + region + ", creditScore=" + creditScore + ", profession="
				+ profession + ", email=" + email + ", twoFactorAuth=" + twoFactorAuth + ", dateCreated=" + dateCreated
				+ ", lastUpdated=" + lastUpdated + ", accounts=" + accounts + ", creditCards=" + creditCards
				+ ", loans=" + loans + ", creditCardRequests=" + creditCardRequests + ", loanRequests=" + loanRequests
				+ ", password=" + password + "]";
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
	public boolean authenticateLogin(String password, User user) {
		MessageDigest md;
		
		System.out.println("PASSWORD SENT TO MIDDLEWARE: " + password);
		
		boolean success = false;
		
		//alot of this will look similar to hashedPassword
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
				
				//2 factor auth
				//generate String
				
				this.twoFactorAuth = "";
				
				for (int i = 0; i < 8; i ++) {
					this.twoFactorAuth += (char)('a'+(int)(Math.random() * 26));
				}
				
				System.out.println("two factor::: " + this.twoFactorAuth);
				
				String to = this.email;
				String from = "TeamCalitate@gmail.com";
				String messageBody = "Your code is "+this.twoFactorAuth;
				EmailMessage message = new EmailMessage(to,from,"Two Factor Authorization Code", messageBody);
				System.out.println(message);
				if (!EmailSender.SendEmail(message)) {
						System.out.println("Email issue");
				}
				
				success = true;
							
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
		return success;
	}
	
	public String authenticate2FA(User user) {
		
		String token = null;
		Boolean validCredentials = false;
		
		token = this.generateToken(user);
		validCredentials = true;

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
					.setExpiration(Date.from(Instant.now().plus(500l, ChronoUnit.MINUTES)))
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
