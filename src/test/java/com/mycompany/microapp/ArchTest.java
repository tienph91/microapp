package com.mycompany.microapp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.mycompany.microapp");

        noClasses()
            .that()
            .resideInAnyPackage("com.mycompany.microapp.service..")
            .or()
            .resideInAnyPackage("com.mycompany.microapp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.mycompany.microapp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
