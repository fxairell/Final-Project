package com.airell.bus.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airell.bus.models.User;
import com.airell.bus.repository.UserRepository;

/*
 * Ini adalah tag Service
 * Merupakan bagian yang didefinisikan interface dari berbagai kelas
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	/*
	 * Dihubungkan untuk security servicenya
	 * Dihuhungkan dengan repository User
	 */
	@Autowired
	UserRepository userRepository;

	/*
	 * Override melakukan implementasi dari kelas supernya
	 * Transactional agar method spesifik dapat berjalan
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}
}
