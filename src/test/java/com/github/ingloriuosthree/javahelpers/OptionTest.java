package com.github.ingloriuosthree.javahelpers;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Function;

import static java.util.function.Function.identity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.*;

class OptionTest {

   @Test
   void ifOptionIsEmptyShouldReturnValueOfSupplier() {
      Option<Object> option = Option.empty();

      String actual = option.ifPresentOrElse(
            object -> "present",
            () -> "absent"
      );

      assertThat(actual).isEqualTo("absent");
   }

   @Test
   void ifSupplierReturnsNullAndifOptionIsEmptyShouldReturnNull() {
      Option<Object> option = Option.empty();

      String actual = option.ifPresentOrElse(
            object -> "present",
            () -> null
      );

      assertThat(actual).isNull();
   }

   @Test
   void ifOptionOfHansShouldReturnHans() {
      Option<String> option = Option.of("Hans");

      String actual = option.ifPresentOrElse(
            str -> str,
            () -> null
      );

      assertThat(actual).isEqualTo("Hans");
   }

   @Test
   void ifOptionOfHansAndSupplierIsNullShouldReturnHans() {
      Option<String> option = Option.of("Hans");

      String actual = option.ifPresentOrElse(
            str -> str,
            null
      );

      assertThat(actual).isEqualTo("Hans");
   }

   @Test
   void ifFunctionIsNullShouldThrowNullpointerException() {
      Option<String> option = Option.of("Hans");

      assertThatNullPointerException().isThrownBy(() -> option.ifPresentOrElse(null, () -> ""));
   }

   @Test
   void toOptional() {
      Option<String> option = Option.of("Hans");

      assertThat(option.toOptional())
            .isInstanceOf(Optional.class)
            .contains("Hans");
   }

   @Test
   void ifOptionEmptyShouldReturnTrue() {
      Option<String> option = Option.empty();

      boolean actual = option.isEmpty();

      assertThat(actual).isTrue();
   }

   @Test
   void ifOptionalEmptyShouldReturnTrue() {
      Option<String> option = Option.of(Optional.empty());

      boolean actual = option.isEmpty();

      assertThat(actual).isTrue();
   }

   @Test
   void ifOptionalOfNullShouldReturnTrue2() {
      Option<String> option = Option.of((String) null);

      boolean actual = option.isEmpty();

      assertThat(actual).isTrue();
   }

   @Test
   void ifPresentMapShouldBeApplied() {
      Option<Character> option = Option.of('H');

      Option<String> actual = option.map(character -> character.toString().concat("ans"));

      assertThat(actual.ifPresentOrElse(identity(), null)).contains("Hans");
   }

   @Test
   void ifNotPresentShouldReturnOptionEmpty() {
      Option<Character> option = Option.of(null);

      Option<String> actual = option.map(character -> character.toString().concat("ans"));

      assertThat(actual.isEmpty()).isTrue();
      assertThat(actual.ifPresentOrElse(identity(), () -> "orElse")).isEqualTo("orElse");
   }


   @Test
   void ifOptionFromOptionalEmptyShouldReturnOptionEmpty() {
      Option<String> option = Option.of(Optional.empty());

      assertThat(option.isEmpty()).isTrue();
   }

   @Test
   void ifOptionFromOptionalShouldReturnOption() {
      Option<String> option = Option.of(Optional.of("Hans"));

      assertThat(option.isEmpty()).isFalse();
      assertThat(option.ifPresentOrElse(identity(), null)).contains("Hans");
   }


}
