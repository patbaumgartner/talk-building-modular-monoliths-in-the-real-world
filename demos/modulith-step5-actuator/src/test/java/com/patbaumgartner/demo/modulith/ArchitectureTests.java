package com.patbaumgartner.demo.modulith;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ArchitectureTests {

	@Test
	void verifyAndDocument() {
		ApplicationModules modules = ApplicationModules.of(ModulithApplication.class);
		modules.verify();

		new Documenter(modules).writeDocumentation();
	}

}
