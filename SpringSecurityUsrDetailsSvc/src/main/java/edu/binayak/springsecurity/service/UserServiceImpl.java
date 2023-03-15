/**
 * 
 */
package edu.binayak.springsecurity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.binayak.springsecurity.model.User;
import edu.binayak.springsecurity.repository.UserRepository;

/**
 * @author HP
 *
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

	@Override
	public Optional<User> findUserByuserId(String userid) {
		// TODO Auto-generated method stub
		return repository.findById(userid);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user = findUserByuserId(username);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("Invalid username");
		}
		SimpleGrantedAuthority authorities = new SimpleGrantedAuthority(user.get().getRole());
		List<SimpleGrantedAuthority> list = new ArrayList<>();
		list.add(authorities);
		org.springframework.security.core.userdetails.User usr = new org.springframework.security.core.userdetails.User(
				username, user.get().getPassword(), list);
		return usr;
	}

}
