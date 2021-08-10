package com.github.ingloriuosthree.javahelpers;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

public class Option<T> {

   private static final Option<?> EMPTY = new Option<>();

   private final T value;

   private Option() {
      this.value = null;
   }

   private Option(T value) {
      this.value = value;
   }

   @SuppressWarnings("unchecked")
   public static <T> Option<T> empty() {
      return (Option<T>) EMPTY;
   }

   public static <T> Option<T> of(T value) {
      return new Option<>(value);
   }

   public static <T> Option<T> of(Optional<T> optional) {
      return nonNull(optional)
            ? of(optional.orElse(null))
            : empty();
   }

   public Optional<T> toOptional() {
      return Optional.ofNullable(value);
   }

   public <R> Option<R> map(Function<? super T, ? extends R> map) {
      requireNonNull(map);
      return isPresent()
            ? Option.of(map.apply(value))
            : empty();
   }

   public <R> R ifPresentOrElse(Function<? super T, ? extends R> ifPresent, Supplier<R> orElse) throws NullPointerException {
      return isPresent()
            ? requireNonNull(ifPresent).apply(value)
            : requireNonNull(orElse).get();
   }

   public boolean isPresent() {
      return value != null;
   }

   public boolean isEmpty() {
      return value == null;
   }
}
