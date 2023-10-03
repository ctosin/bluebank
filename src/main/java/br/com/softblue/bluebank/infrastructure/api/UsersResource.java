package br.com.softblue.bluebank.infrastructure.api;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import br.com.softblue.bluebank.domain.user.User;
import br.com.softblue.bluebank.domain.user.UserRepository;
import br.com.softblue.bluebank.infrastructure.api.dto.CredentialsDTO;
import br.com.softblue.bluebank.infrastructure.api.dto.SaveUserDTO;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("users")
public class UsersResource {
	
	@Inject
	private UserRepository userRepository;

	@GET
	@Produces(APPLICATION_JSON)
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	@GET
	@Path("{userId}")
	@Produces(APPLICATION_JSON)
	public User getById(@PathParam("userId") String userId) {
		return userRepository.findById(userId);
	}
	
	@DELETE
	@Path("{userId}")
	@Transactional
	public void deleteById(@PathParam("userId") String userId) {
		userRepository.deleteById(userId);
	}
	
	@PUT
	@Path("{userId}")
	@Transactional
	public void updateUser(@PathParam("userId") String userId, SaveUserDTO saveUserDTO) {
		User user = userRepository.findById(userId);
		populateFromDTO(user, saveUserDTO);
	}
	
	@POST
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	@Transactional
	public CredentialsDTO createUser(SaveUserDTO saveUserDTO) {
		User user = new User();
		populateFromDTO(user, saveUserDTO);
		
		userRepository.save(user);
		
		CredentialsDTO credentialsDTO = new CredentialsDTO();
		credentialsDTO.setId(user.getId());
		credentialsDTO.setPassword(saveUserDTO.getPassword());
		
		return credentialsDTO;
	}
	
	private void populateFromDTO(User user, SaveUserDTO saveUserDTO) {
		user.setName(saveUserDTO.getName());
		user.setPassword(saveUserDTO.getPassword());
		user.setEmail(saveUserDTO.getEmail());
		user.setCpf(saveUserDTO.getCpf());
	}
}
