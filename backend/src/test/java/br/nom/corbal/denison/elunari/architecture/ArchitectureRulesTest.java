package br.nom.corbal.denison.elunari.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

@AnalyzeClasses(packages = "br.nom.corbal.denison.elunari", importOptions = { ImportOption.DoNotIncludeTests.class })
public class ArchitectureRulesTest {
        @ArchTest
        static final ArchRule domain_should_not_depend_on_other_layers = noClasses()
                        .that().resideInAPackage("..domain..")
                        .should().dependOnClassesThat()
                        .resideInAnyPackage(
                                        "..application..",
                                        "..infrastructure..")
                        .allowEmptyShould(true);

        @ArchTest
        static final ArchRule application_should_only_depend_on_domain = noClasses()
                        .that().resideInAPackage("..application..")
                        .should().dependOnClassesThat()
                        .resideInAnyPackage(
                                        "..infrastructure..")
                        .allowEmptyShould(true);

        @ArchTest
        static final ArchRule infrastructure_should_implement_ports = classes()
                        .that().resideInAPackage("..infrastructure..")
                        .and().haveNameMatching(".*Repository|.*Client|.*Gateway|.*Adapter|.*Controller")
                        .should().dependOnClassesThat().areInterfaces()
                        .allowEmptyShould(true);

        @ArchTest
        static final ArchRule application_repository_should_be_interface = classes()
                        .that().resideInAPackage("..application.repository..")
                        .should().beInterfaces()
                        .allowEmptyShould(true);

        @ArchTest
        static final ArchRule application_event_publisher_should_be_interface = classes()
                        .that().resideInAPackage("..application.event..")
                        .and().haveNameMatching(".*Publisher")
                        .should().beInterfaces()
                        .allowEmptyShould(true);

        @ArchTest
        static final ArchRule usecases_should_be_named_properly = classes()
                        .that().resideInAPackage("..application.usecase..")
                        .should().haveSimpleNameEndingWith("UseCase")
                        .allowEmptyShould(true);

        @ArchTest
        static final ArchRule any_non_model_and_event_in_domain_should_be_interfaces = classes()
                        .that().resideInAPackage("..domain..")
                        .and().resideOutsideOfPackages("..domain.model..", "..domain.event..")
                        .should().beInterfaces()
                        .allowEmptyShould(true);

        @ArchTest
        static final ArchRule entities_should_be_only_in_domain = classes()
                        .that().haveSimpleNameEndingWith("Entity")
                        .should().resideInAPackage("..domain.model..")
                        .allowEmptyShould(true);

        @ArchTest
        static final ArchRule domain_should_not_use_spring = noClasses()
                        .that().resideInAPackage("..domain..")
                        .should().dependOnClassesThat()
                        .resideInAnyPackage("org.springframework..")
                        .allowEmptyShould(true);

        @ArchTest
        static final ArchRule layered_architecture_rule = layeredArchitecture().consideringOnlyDependenciesInLayers()

                        .layer("Domain").definedBy("..domain..")
                        .layer("Application").definedBy("..application..")
                        .layer("Infrastructure").definedBy("..infrastructure..")

                        .whereLayer("Domain").mayNotAccessAnyLayer()
                        .whereLayer("Domain").mayOnlyBeAccessedByLayers("Application")
                        .whereLayer("Application").mayOnlyBeAccessedByLayers("Infrastructure")
                        .whereLayer("Infrastructure").mayNotBeAccessedByAnyLayer()
                        .allowEmptyShould(true);

        @ArchTest
        static final ArchRule no_cycles = slices()
                        .matching("br.nom.corbal.denison.elunari.(*)..")
                        .should().beFreeOfCycles()
                        .allowEmptyShould(true);

        @ArchTest
        static final ArchRule repository_ports_should_be_in_domain = classes()
                        .that().haveSimpleNameEndingWith("Repository")
                        .and().areInterfaces()
                        .should().resideInAPackage("..domain.repository..")
                        .allowEmptyShould(true);

        @ArchTest
        static final ArchRule bounded_contexts_should_not_depend_on_each_other = noClasses()
                        .should(new ArchCondition<>("depend on another bounded context") {

                                @Override
                                public void check(JavaClass clazz, ConditionEvents events) {

                                        String sourceBc = extractBoundedContext(clazz.getPackageName());

                                        if ("shared".equals(sourceBc)) {
                                                return;
                                        }

                                        clazz.getDirectDependenciesFromSelf().forEach(dep -> {

                                                String targetBc = extractBoundedContext(
                                                                dep.getTargetClass().getPackageName());

                                                boolean violation = !sourceBc.equals(targetBc)
                                                                && !"shared".equals(targetBc);

                                                if (violation) {
                                                        events.add(SimpleConditionEvent.violated(
                                                                        dep,
                                                                        String.format(
                                                                                        "%s depends on %s",
                                                                                        clazz.getName(),
                                                                                        dep.getTargetClass()
                                                                                                        .getName())));
                                                }
                                        });
                                }
                        });

        private static String extractBoundedContext(String packageName) {

                String prefix = "br.nom.corbal.denison.elunari.";

                if (!packageName.startsWith(prefix))
                        return "shared";

                String remaining = packageName.substring(prefix.length());

                return remaining.split("\\.")[0];
        }

        @ArchTest
        static final ArchRule feature_or_module_should_not_depend_on_each_other = noClasses()
                        .should(new ArchCondition<>("depend on another feature or module") {

                                @Override
                                public void check(JavaClass clazz, ConditionEvents events) {

                                        String sourceFOrM = extractFeatureOrModule(clazz.getPackageName());

                                        if ("shared".equals(sourceFOrM)) {
                                                return;
                                        }

                                        clazz.getDirectDependenciesFromSelf().forEach(dep -> {

                                                String targetFOrM = extractFeatureOrModule(
                                                                dep.getTargetClass().getPackageName());

                                                boolean violation = !sourceFOrM.equals(targetFOrM)
                                                                && !"shared".equals(targetFOrM);

                                                if (violation) {
                                                        events.add(SimpleConditionEvent.violated(
                                                                        dep,
                                                                        String.format(
                                                                                        "%s depends on %s",
                                                                                        clazz.getName(),
                                                                                        dep.getTargetClass()
                                                                                                        .getName())));
                                                }
                                        });
                                }
                        });

        private static String extractFeatureOrModule(String packageName) {

                String prefix = "br.nom.corbal.denison.elunari.";

                if (!packageName.startsWith(prefix))
                        return "shared";

                String remaining = packageName.substring(prefix.length());

                return remaining.split("\\.")[1];
        }

}
