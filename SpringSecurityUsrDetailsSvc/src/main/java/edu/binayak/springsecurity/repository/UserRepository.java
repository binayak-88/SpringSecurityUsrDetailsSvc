/**
 * 
 */
package edu.binayak.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.binayak.springsecurity.model.User;

/**
 * @author HP
 *
 */
public interface UserRepository extends JpaRepository<User, String> {
	
}