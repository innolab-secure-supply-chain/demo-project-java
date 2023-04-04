package de.ziegler.demo;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("de.ziegler.demo");

        noClasses()
            .that()
                .resideInAnyPackage("de.ziegler.demo.service..")
            .or()
                .resideInAnyPackage("de.ziegler.demo.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..de.ziegler.demo.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
