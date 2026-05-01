package br.nom.corbal.denison.elunari.registration.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {
    @Test
    public void givenValidAddress_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            Address address = new Address(
                    "DF",
                    "Brasilia",
                    "Asa Sul",
                    "Quadra x",
                    "11.111-111",
                    "1");
            assertNotNull(address);
        });

        assertDoesNotThrow(() -> {
            Address address = new Address(
                    "DF",
                    "Brasilia",
                    "Asa Sul",
                    "Quadra x",
                    "11111111",
                    "1");
            assertNotNull(address);
        });
    }

    @Test
    public void givenInvalidFederalUnity_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Address(
                            "BR",
                            "Brasilia",
                            "Asa Sul",
                            "Quadra x",
                            "11.111-111",
                            "1");
                });
    }

    @Test
    public void givenInvalidLocale_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Address(
                            "DF",
                            null,
                            "Asa Sul",
                            "Quadra x",
                            "11.111-111",
                            "1");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Address(
                            "DF",
                            "",
                            "Asa Sul",
                            "Quadra x",
                            "11.111-111",
                            "1");
                });
    }

    @Test
    public void givenInvalidNeighbourhood_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Address(
                            "DF",
                            "Brasilia",
                            null,
                            "Quadra x",
                            "11.111-111",
                            "1");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Address(
                            "DF",
                            "",
                            "",
                            "Quadra x",
                            "11.111-111",
                            "1");
                });
    }

    @Test
    public void givenInvalidZipCode_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Address(
                            "DF",
                            "Brasilia",
                            "Asa Sul",
                            "Quadra x",
                            "1.1111-111",
                            "1");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Address(
                            "DF",
                            "",
                            "Asa Sul",
                            "Quadra x",
                            "11.1111-11",
                            "1");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Address(
                            "DF",
                            "",
                            "Asa Sul",
                            "Quadra x",
                            "111.111-111",
                            "1");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Address(
                            "DF",
                            "",
                            "Asa Sul",
                            "Quadra x",
                            "A1.1111-11",
                            "1");
                });
    }
}
