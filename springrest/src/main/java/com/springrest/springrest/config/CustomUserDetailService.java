package com.springrest.springrest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

//The property source annotation is used to override the user.properties file with application.properties.. Hence the @Value annootation can be used to retrieve info
@Component
@PropertySource("classpath:user.properties")
public class CustomUserDetailService implements UserDetailsService {
	
	//Tried some ways to get all the users' properties in one List<String> but couldnt do it.. Hence stored each user's property in variables called "user1" and so..
    //@Value("#{systemProperties.entrySet().stream().filter(e -> e.getKey().startsWith('user')).map(e -> e.getKey() + '=' + e.getValue()).collect(java.util.stream.Collectors.toList())}")
	//@Value("#{'${user1}'.split(',')}")
	//private List<String> users;
	
	@Value("${user1}")
	String user1;
	
	@Value("${user2}")
	String user2;
	
	@Value("${user3}")
	String user3;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	    String[] users={user1,user2,user3};
		String[] parts = null;
		int flag = 0;
		
		for(String user: users)
		{
			parts = user.split(":");
	        if (username.equals(parts[0])) {
	        	
	        	flag = 1;
	        	return User.withUsername(parts[0])
	                    .password("{noop}" + parts[1]) // using {noop} for plaintext passwords
	                    .roles("ADMIN")
	                    .build();
	        } 
			
		}
		if (flag==0)
		{
	            throw new UsernameNotFoundException("User not found with username: " + username);
	    }
        
        return User.withUsername(parts[0])
                .password("{noop}" + parts[1]) // using {noop} for plaintext passwords
                .roles("ADMIN")
                .build();
	}   
}	