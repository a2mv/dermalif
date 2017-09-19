package com.pl10.dermalif.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pl10.dermalif.coverter.CityConverter;
import com.pl10.dermalif.coverter.PersonConverter;
import com.pl10.dermalif.entity.Person;
import com.pl10.dermalif.entity.UserRole;
import com.pl10.dermalif.model.PersonModel;
import com.pl10.dermalif.model.UserRolePersonModel;
import com.pl10.dermalif.repository.CityRepository;
import com.pl10.dermalif.repository.PersonRepository;
import com.pl10.dermalif.repository.UserRepository;
import com.pl10.dermalif.repository.UserRoleRepository;

@Service("userService")
public class UserService implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	@Autowired
	@Qualifier("userRoleRepository")
	private UserRoleRepository userRoleRepository;

	@Autowired
	@Qualifier("personRepository")
	private PersonRepository personRepository;

	@Autowired
	@Qualifier("personConverter")
	private PersonConverter personConverter;

	@Autowired
	@Qualifier("cityRepository")
	private CityRepository cityRepository;

	@Autowired
	@Qualifier("cityConverter")
	private CityConverter cityConverter;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.pl10.dermalif.entity.User user = userRepository.findByUsername(username);
		if (username.equals("admin") & user == null) {
			user = createAdmin();
		}
		List<GrantedAuthority> authorities = buildAuthority(user.getUserRole());
		return buildUser(user, authorities);
	}

	private User buildUser(com.pl10.dermalif.entity.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildAuthority(Set<UserRole> userRoles) {
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();

		for (UserRole userRole : userRoles) {
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<GrantedAuthority>(auths);
	}

	private com.pl10.dermalif.entity.User createAdmin() {
		com.pl10.dermalif.entity.User user = new com.pl10.dermalif.entity.User();
		user.setEnabled(true);
		user.setPassword(new BCryptPasswordEncoder().encode("admin"));
		user.setUsername("admin");
		user = userRepository.save(user);
		UserRole userRole = new UserRole();
		userRole.setRole("ROLE_ADMIN");
		userRole.setUser(user);
		userRoleRepository.save(userRole);
		return userRepository.findByUsername("admin");
	}

	public PersonModel userConverter(User user) {
		Person person = personRepository.findByEmail(user.getUsername());
		PersonModel personModel = new PersonModel();
		if (person != null) {
			personModel = personConverter.personToPersonModel(person);
		} else {
			personModel = new PersonModel();
			personModel.setFirstname("Administrador");
			personModel.setLastname("del sistema");
			personModel.setImageprofile("/dist/img/avatar5.png");
		}
		return personModel;
	}

	public UserRolePersonModel findUserRole(String username) {
		com.pl10.dermalif.entity.User user = userRepository.findByUsername(username);
		Set<UserRole> userRoles = userRoleRepository.findByUser(user);
		UserRolePersonModel userRolePersonModel = new UserRolePersonModel();	
		userRolePersonModel.setUsername(username);
		userRolePersonModel.setUsersystem(false);
		userRolePersonModel.setCitrole(false);
		userRolePersonModel.setDocrole(false);
		userRolePersonModel.setFacrole(false);
		userRolePersonModel.setHclrole(false);
		userRolePersonModel.setIngrole(false);
		userRolePersonModel.setPacrole(false);		
		if(null!=user){
			userRolePersonModel.setUsersystem(user.isEnabled());
		}		
		for (UserRole userRole : userRoles) {
			userRolePersonModel.setUsersystem(userRole.getUser().isEnabled());
			if(userRole.getRole().equals("ROLE_CITAS"))
				userRolePersonModel.setCitrole(true);
			if(userRole.getRole().equals("ROLE_DOCUMENTO"))
				userRolePersonModel.setDocrole(true);
			if(userRole.getRole().equals("ROLE_FACTURA"))
				userRolePersonModel.setFacrole(true);
			if(userRole.getRole().equals("ROLE_HISTORIA"))
				userRolePersonModel.setHclrole(true);
			if(userRole.getRole().equals("ROLE_INGRESO"))
				userRolePersonModel.setIngrole(true);
			if(userRole.getRole().equals("ROLE_USER"))
				userRolePersonModel.setPacrole(true);			
		}
		return userRolePersonModel;
	}
	
	public UserRolePersonModel addUserRole(UserRolePersonModel userRolePersonModel){
		com.pl10.dermalif.entity.User user = userRepository.findByUsername(userRolePersonModel.getUsername());
		if(null==user){
			user = new com.pl10.dermalif.entity.User();
			user.setUsername(userRolePersonModel.getUsername());			
			user.setPassword(new BCryptPasswordEncoder().encode("user"));
		}
		user.setEnabled(userRolePersonModel.isUsersystem());
		user = userRepository.save(user);
		gestionarRole(user, "ROLE_CITAS", userRolePersonModel.isCitrole());
		gestionarRole(user, "ROLE_DOCUMENTO", userRolePersonModel.isDocrole());
		gestionarRole(user, "ROLE_FACTURA", userRolePersonModel.isFacrole());
		gestionarRole(user, "ROLE_HISTORIA", userRolePersonModel.isHclrole());
		gestionarRole(user, "ROLE_USER", userRolePersonModel.isPacrole());
		gestionarRole(user, "ROLE_INGRESO", userRolePersonModel.isIngrole());
		if(userRolePersonModel.isUsersystem()==false){
			Set<UserRole> roles = userRoleRepository.findByUser(user);
			userRoleRepository.delete(roles);
		}
		return userRolePersonModel;
	}
	
	private void gestionarRole(com.pl10.dermalif.entity.User user, String role, boolean userRoleModel){
		UserRole userRole = userRoleRepository.findByUserAndRole(user, role);
		if(userRoleModel==true){			
			if(null==userRole){
				userRole=new UserRole();
				userRole.setUser(user);
				userRole.setRole(role);
				userRoleRepository.save(userRole);
			}
		}else{
			if(null!=userRole){
				userRoleRepository.delete(userRole);
			}
		}
	}
	
	public boolean validaPass(String username, String password, String password2) {
		com.pl10.dermalif.entity.User user = userRepository.findByUsername(username);
		if(!password.equals(password2)) {
			return false;
		}
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		user.setPassword(pe.encode(password));
		user = userRepository.save(user);
		return true;
	}
	


}
