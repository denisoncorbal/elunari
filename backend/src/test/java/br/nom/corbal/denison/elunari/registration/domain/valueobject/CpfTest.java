package br.nom.corbal.denison.elunari.registration.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.nom.corbal.denison.elunari.registration.domain.model.valueobject.Cpf;

public class CpfTest {
    @Test
    public void givenValidCpf_whenCreate_shouldReturn() {
        assertDoesNotThrow(() -> {
            Cpf cpf = new Cpf(
                    "111.111.111-11");
            assertNotNull(cpf);
        });

        assertDoesNotThrow(() -> {
            Cpf cpf = new Cpf(
                    "11111111111");
            assertNotNull(cpf);
        });
    }

    @Test
    public void givenInvalidCpf_whenCreate_shouldThrowError() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Cpf(
                            "1111.11.111-11");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Cpf(
                            "11.1111.111-11");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Cpf(
                            "111.1111.11-11");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Cpf(
                            "111.11.1111-11");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Cpf(
                            "111.111.1111-1");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Cpf(
                            "111.111.11-111");
                });

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Cpf(
                            "111111111111");
                });
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Cpf(
                            "A1111111111");
                });
    }
}
