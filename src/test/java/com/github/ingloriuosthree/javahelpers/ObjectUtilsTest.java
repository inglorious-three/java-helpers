package com.github.ingloriuosthree.javahelpers;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

import static com.github.ingloriuosthree.javahelpers.ObjectUtils.allNonNull;
import static com.github.ingloriuosthree.javahelpers.ObjectUtils.allRequireNonNull;
import static com.github.ingloriuosthree.javahelpers.ObjectUtils.anyNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class ObjectUtilsTest {

   @Test
   void givenNonNullShouldReturnTrue() {
      // given
      Object[] objects = {2, -1, 0, "Test", 's'};

      // when
      boolean actual = allNonNull(objects);

      // then
      assertThat(actual).isTrue();
   }

   @Test
   void givenOneNullShouldReturnFalse() {
      // given
      Object[] objects = {2, -1, null, "Test", 's'};

      // when
      boolean actual = allNonNull(objects);

      // then
      assertThat(actual).isFalse();
   }

   @Test
   void givenMultipleNullShouldReturnFalse() {
      // given
      Object[] objects = {null, null, null, "Test", 's'};

      // when
      boolean actual = allNonNull(objects);

      // then
      assertThat(actual).isFalse();
   }

   @Test
   void givenNonNullShouldReturnFalse() {
      // given
      Object[] objects = {2, -1, 0, "Test", 's'};

      // when
      boolean actual = anyNull(objects);

      // then
      assertThat(actual).isFalse();
   }

   @Test
   void givenNullShouldReturnTrue() {
      // given
      Object[] objects = {2, -1, null, "Test", 's'};

      // when
      boolean actual = anyNull(objects);

      // then
      assertThat(actual).isTrue();
   }

   @Test
   void givenNonNullValuesDoNothing() {
      // given
      Object[] objects = {2, -1, 0, "Test", 's'};

      // when
      ThrowingCallable callable = () -> allRequireNonNull(objects);

      // then
      assertThatNoException().isThrownBy(callable);
   }

   @Test
   void givenNullValuesDoNothing() {
      // given
      Object[] objects = {2, -1, null, "Test", 's'};

      // when
      ThrowingCallable callable = () -> allRequireNonNull(objects);

      // then
      assertThatNullPointerException().isThrownBy(callable);
   }
}
