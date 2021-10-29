package com.joinsolutions.curso_demo_spring_boot.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.joinsolutions.curso_demo_spring_boot.entities.User;
import com.joinsolutions.curso_demo_spring_boot.repositories.UserRepository;
import com.joinsolutions.curso_demo_spring_boot.services.exceptions.DatabaseException;
import com.joinsolutions.curso_demo_spring_boot.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User obj) {
		return userRepository.save(obj);
	}

	public void deleteById(Long id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

//	@SuppressWarnings("deprecation")
	public User update(Long id, User obj) {
		try {
			/* getOne não vai no BD e somente prepara a entidade - melhor que o findById */
			// User entity = userRepository.getOne(id);
			User entity = findById(id);
			updateData(entity, obj);
			return userRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}

}
