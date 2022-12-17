package com.net.tmi.entity;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*This is just a  small java program to demonstrate on how to covert raw String into BCrypt encoded fomart
our DB had initially been storing our passwords in raw format before we had implemented Spring security,
so any time we logged in with the initially saved raw passwords b4 implementing the security feature we got an error like , Bad credentials
So to resolve this is when We just created this small java program to convert our raw password into Bcrypt format then copy the printed output and paste it into the DB manually to update the raw password into The BCrypt format, 
thus if we log in again we ain't getting the error 

This Small java program is not part of this application, twas for demonstration purpose alone,to resolve what is explained above.
*/
public class PasswordEncoder {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		String rawPassword="2022";
		String encodedPassword=encoder.encode(rawPassword);
		
		System.out.println(encodedPassword);
		
		Date now= new Date();
		String log=now.toLocaleString();
		System.out.println(log);

	}

}
