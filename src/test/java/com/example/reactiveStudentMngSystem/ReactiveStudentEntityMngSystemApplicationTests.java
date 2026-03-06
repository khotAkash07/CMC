package com.example.reactiveStudentMngSystem;

import com.example.reactiveStudentMngSystem.infrastructure.persistence.adapter.StudentQueryAdapter;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReactiveStudentEntityMngSystemApplicationTests {

	@Autowired
	private StudentQueryAdapter  studentQueryAdapter;

	@Test
	void contextLoads() {
	}

	@Test
	public void getAllStuds(){
		studentQueryAdapter.findAllStudents()
				.subscribe(System.out::println);
	}

}
