package br.nom.corbal.denison.elunari.academic.assignement.domain.model.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;
import java.util.random.RandomGenerator;

import org.junit.jupiter.api.Test;

public class AssignementGradeTest {
    @Test
    public void givenValidAssignementGrade_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            AssignementGrade assignementGrade = new AssignementGrade(
                    Random.from(RandomGenerator.getDefault()).nextDouble(
                            AssignementGrade.ASSIGNEMENT_GRADE_MINIMUM_VALUE,
                            AssignementGrade.ASSIGNEMENT_GRADE_MAXIMUM_VALUE));
            assertNotNull(assignementGrade);
        });
    }

    @Test
    public void givenInvalidAssignementGrade_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new AssignementGrade(
                            AssignementGrade.ASSIGNEMENT_GRADE_MINIMUM_VALUE - 1);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new AssignementGrade(
                            AssignementGrade.ASSIGNEMENT_GRADE_MAXIMUM_VALUE + 1);
                });
    }
}
