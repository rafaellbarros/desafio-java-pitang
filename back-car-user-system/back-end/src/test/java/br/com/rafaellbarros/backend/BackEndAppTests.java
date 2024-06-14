package br.com.rafaellbarros.backend;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;


class BackEndAppTests {

	private JavaClasses importedClasses;
	@BeforeEach
	public void setup() {
		importedClasses = new ClassFileImporter()
				.withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
				.importPackages("br.com.rafaellbarros");
	}

	@Test
	void servicesERepositoriesNaoDevemDependerDaCamadaWeb() {

		noClasses()
				.that().resideInAnyPackage("br.com.rafaellbarros.backend.endpoint.service..")
				.or().resideInAnyPackage("br.com.rafaellbarros.core.repository..")
				.should()
				.dependOnClassesThat()
				.resideInAnyPackage("br.com.rafaellbarros.backend.endpoint.controller..")
				.because("Services e repositories não devem depender da camada da web")
				.check(importedClasses);
	}

	@Test
	void serviceClassesDevemSerAcessadoApenasPelaController() {
		classes()
				.that().resideInAPackage("..service..")
				.should().onlyBeAccessed().byAnyPackage("..service..", "..controller..")
				.check(importedClasses);
	}
	@Test
	void repositoryClassesDevemSerChamadosXRepository() {
		classes()
				.that().resideInAPackage("..repository..")
				.should().haveSimpleNameEndingWith("Repository")
				.check(importedClasses);
	}

	@Test
	void repositoryClassesNaoDevemTerAnotacaoSpringRepository() {
		noClasses()
				.that().resideInAPackage("..repository..")
				.should().beAnnotatedWith(Repository.class)
				.check(importedClasses);
	}

	@Test
	void serviceClassessDevemTerAnotacaoSpringService() {
		classes()
				.that().resideInAPackage("..service..")
				.should().beAnnotatedWith(Service.class)
				.check(importedClasses);
	}

	@Test
	void arquiteturaEmCamadasDeveSerRespeitada() {
		layeredArchitecture()
				.layer("Controller").definedBy("..controller..")
				.layer("Service").definedBy("..service..")
				.layer("Repository").definedBy("..repository..")
				.layer("Validation").definedBy("..validation..")

				.whereLayer("Controller").mayNotBeAccessedByAnyLayer()
				.whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
				.whereLayer("Repository").mayOnlyBeAccessedByLayers("Service", "Validation")
				.check(importedClasses);
	}

	@Test
	void repositoriesDevemEstarPresenteNoPacoteRepository() {
		classes().that().haveNameMatching(".*Repository").should().resideInAPackage("..repository..")
				.as("Repositories devem estar em um pacote '..repository..'")
				.check(importedClasses);
	}

	@Test
	void servicesDevemEstarPresenteNoPacoteService() {
		classes().that().haveNameMatching(".*Service").should().resideInAPackage("..service..")
				.as("Services devem estar em um pacote '..service..'")
				.check(importedClasses);
	}

	@Test
	void controllersDevemEstarPresenteNoPacoteController() {
		classes().that().haveNameMatching(".*Controller").should().resideInAPackage("..controller..")
				.as("Controllers devem estar em um pacote '..controller..'")
				.check(importedClasses);
	}

	@Test
	void dtosDevemEstarPresenteNoPacoteModelDTO() {
		classes().that().haveNameMatching(".*DTO").should().resideInAPackage("..model.dto..")
				.as("DTOs devem estar em um pacote '..dto..'")
				.check(importedClasses);
	}

	@Test
	void enumsDevemEstarPresenteNoPacoteModelEnums() {
		classes()
				.that().resideInAPackage("..model.enums..")
				.should()
				.beEnums()
				.as("Enums devem estar em um pacote '..model.enums..'")
				.check(importedClasses);
	}

	@Test
	void interfacesNaoDevemTerNomesQueTerminemComAPalavraInterface() {
		noClasses().that().areInterfaces().should().haveNameMatching(".*Interface").check(importedClasses);
	}

	@Test
	void entityClassesDevemSerPublic() {
		classes()
				.that().resideInAPackage("..model.entity..")
				.should()
				.bePublic()
				.check(importedClasses);
	}

}
