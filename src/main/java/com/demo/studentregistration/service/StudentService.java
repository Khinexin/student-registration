package com.demo.studentregistration.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.demo.studentregistration.model.Student;
import com.demo.studentregistration.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository studentRepository;

	public Student create(Student student) {
		return studentRepository.save(student);
	}

	public Student update(Student student) {
		return studentRepository.saveAndFlush(student);
	}

	public void delete(int id) {
		studentRepository.deleteById(id);
	}

	public Student findById(int id) {
		return studentRepository.findById(id).orElse(null);
	}

	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	public long countStudent() {
		return studentRepository.count();
	}

	public Page<Student> findPaginated(final int page, final int size) {
		return studentRepository.findAll(PageRequest.of(page, size));
	}

}
