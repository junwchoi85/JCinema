package com.jcinema.users;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ContextConfiguration;

import com.jcinema.users.audit.AuditAwareImpl;

@SpringBootTest
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@ContextConfiguration(classes = {AuditAwareImpl.class})
class UsersApplicationTests {

	@Test
	void contextLoads() {
	}

}
