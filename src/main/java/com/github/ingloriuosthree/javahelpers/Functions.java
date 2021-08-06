package com.github.ingloriuosthree.javahelpers;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class Functions {

   public static <T, R, A> Function<Collection<T>, A> mapAll(Function<T, R> map, Collector<R, ?, A> collector, boolean parallel) {
      return parallel ? t -> t.stream().map(map).collect(collector) : t -> t.stream().parallel().map(map).collect(collector);
   }

   public static  <T, R> Function<Collection<T>, List<R>> mapAll(Function<T, R> map) {
      return mapAll(map, Collectors.toList(), false);
   }

   public static  <T, R> Function<Collection<T>, Set<R>> mapAllToSet(Function<T, R> map) {
      return mapAll(map, Collectors.toSet(), false);
   }

   public static  <T, R> Function<Collection<T>, R> filterAll(Predicate<T> filter, Collector<T, ?, R> collector, boolean parallel) {
      return parallel ? t -> t.stream().filter(filter).collect(collector) : t -> t.stream().parallel().filter(filter).collect(collector);
   }

   public static  <T> Function<Collection<T>, List<T>> filterAll(Predicate<T> filter) {
      return filterAll(filter, Collectors.toList(), false);
   }

}
