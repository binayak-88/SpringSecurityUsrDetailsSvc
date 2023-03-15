/**
 * 
 */
package edu.binayak.springsecurity.service;

import java.util.Optional;

import edu.binayak.springsecurity.model.User;

/**
 * @author HP
 *
 */
public interface UserService {
	User registerUser(User user);
	Optional<User> findUserByuserId(String userid);
}