package com.github.ingloriuosthree.javahelpers;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.github.ingloriuosthree.javahelpers.Functions.filterAll;
import static com.github.ingloriuosthree.javahelpers.Functions.mapAll;
import static com.github.ingloriuosthree.javahelpers.Functions.mapAllToSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.util.Lists.list;

public final class FunctionsTest {

   @Test
   void givenListshouldMapEachEntry() {
      //      given
      TestWrapper<Collection<Integer>> given = TestWrapper.of(list(1, 2, 3, 4));

      //      when
      TestWrapper<List<Integer>> actual = given.map(mapAll(integer -> integer + 1));

      //      then
      assertThat(actual.getRaw()).containsExactly(2,3,4,5);
   }

   @Test
   void givenNullShouldThrowNullPointerException1() {
      //      given
      TestWrapper<Collection<Integer>> given = TestWrapper.of(list(1, 2, 3, 4));

      //      when/then
      assertThatNullPointerException().isThrownBy(() -> given.map(mapAll(null)));
   }

   @Test
   void givenNullShouldThrowNullPointerException2() {
      //      given
      TestWrapper<Collection<Integer>> given = TestWrapper.of(list(1, 2, 3, 4));

      //      when/then
      assertThatNullPointerException().isThrownBy(() -> given.map(mapAll(integer -> integer, null, false)));
   }

   @Test
   void givenNullShouldThrowNullPointerException3() {
      //      given
      TestWrapper<Collection<Integer>> given = TestWrapper.of(list(1, 2, 3, 4));

      //      when/then
      assertThatNullPointerException().isThrownBy(() -> given.map(mapAllToSet(null)));
   }

   @Test
   void givenNullShouldThrowNullPointerException4() {
      //      given
      TestWrapper<Collection<Integer>> given = TestWrapper.of(list(1, 2, 3, 4));

      //      when/then
      assertThatNullPointerException().isThrownBy(() -> given.map(filterAll(null)));
   }

   @Test
   void givenNullShouldThrowNullPointerException5() {
      //      given
      TestWrapper<Collection<Integer>> given = TestWrapper.of(list(1, 2, 3, 4));

      //      when/then
      assertThatNullPointerException().isThrownBy(() -> given.map(filterAll(integer -> true, null, true)));
   }

   @Test
   void givenListshouldMapEachEntryToSet() {
      //      given
      TestWrapper<Collection<Integer>> given = TestWrapper.of(list(1, 2, 2, 3, 4));

      //      when
      TestWrapper<Set<Integer>> actual = given.map(mapAll(integer -> integer - 2, Collectors.toSet(), false));

      //      then
      assertThat(actual.getRaw()).containsExactlyInAnyOrder(-1,0,1,2);
   }

   @Test
   void givenListShouldFilterEachEntry() {
      //      given
      TestWrapper<Collection<Integer>> given = TestWrapper.of(list(1, 2, 3, 4));

      //      when
      TestWrapper<List<Integer>> actual = given.map(filterAll(integer -> integer % 2 != 0));

      //      then
      assertThat(actual.getRaw()).containsExactly(1,3);
   }

   @Test
   void givenListShouldFilterEachEntryToSet() {
      //      given
      TestWrapper<Collection<Integer>> given = TestWrapper.of(list(1, 2, 2, 3, 4));

      //      when
      TestWrapper<Set<Integer>> actual = given.map(filterAll(integer -> integer % 2 == 0, Collectors.toSet(), false));

      //      then
      assertThat(actual.getRaw()).containsExactlyInAnyOrder(2,4);
   }


   static class TestWrapper<T> {

      T t;

      private TestWrapper(T t) {
         this.t = t;
      }

      public static <T> TestWrapper<T> of(T t) {
         return new TestWrapper<>(t);
      }

      public <R> TestWrapper<R> map(Function<T, R> map) {
         return of(map.apply(this.t));
      }

      public T getRaw() {
         return t;
      }
   }
}
