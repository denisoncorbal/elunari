package br.nom.corbal.denison.elunari.registration.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneNumberTest {
        @Test
        public void givenValidPhoneNumber_whenCreate_shouldReturn() {
                assertDoesNotThrow(() -> {
                        PhoneNumber phoneNumber = new PhoneNumber(
                                        "(11) 1111-1111");
                        assertNotNull(phoneNumber);
                });

                assertDoesNotThrow(() -> {
                        PhoneNumber phoneNumber = new PhoneNumber(
                                        "(11) 11111-1111");
                        assertNotNull(phoneNumber);
                });

                assertDoesNotThrow(() -> {
                        PhoneNumber phoneNumber = new PhoneNumber(
                                        "11111111111");
                        assertNotNull(phoneNumber);
                });
        }

        @Test
        public void givenInvalidStudentStatus_whenCreate_shouldThrowError() {
                assertThrows(
                                IllegalArgumentException.class,
                                () -> {
                                        new PhoneNumber(
                                                        "(111) 1111-1111");
                                });

                assertThrows(
                                IllegalArgumentException.class,
                                () -> {
                                        new PhoneNumber(
                                                        "(11) 1111-11111");
                                });

                assertThrows(
                                IllegalArgumentException.class,
                                () -> {
                                        new PhoneNumber(
                                                        "(11) 111111-111");
                                });

                assertThrows(
                                IllegalArgumentException.class,
                                () -> {
                                        new PhoneNumber(
                                                        "111111111111");
                                });

                assertThrows(
                                IllegalArgumentException.class,
                                () -> {
                                        new PhoneNumber(
                                                        "A1111111111");
                                });
        }
}
