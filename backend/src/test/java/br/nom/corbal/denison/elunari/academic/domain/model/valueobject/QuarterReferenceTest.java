package br.nom.corbal.denison.elunari.academic.domain.model.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class QuarterReferenceTest {
    @Test
    public void givenValidQuarterReference_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            QuarterReference quarterReference = new QuarterReference(
                    "FIRST");
            assertNotNull(quarterReference);
        });
    }

    @Test
    public void givenInvalidQuarterReference_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new QuarterReference(
                            null);
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new QuarterReference(
                            "");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new QuarterReference(
                            "ANYTHING");
                });
    }
}
