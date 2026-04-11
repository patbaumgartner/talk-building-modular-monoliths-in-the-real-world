package com.patbaumgartner.demo.modulith;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ArchitectureTests {

	@Test
	void verifyModularStructure() {
		ApplicationModules.of(ModulithApplication.class).verify();
	}

}
