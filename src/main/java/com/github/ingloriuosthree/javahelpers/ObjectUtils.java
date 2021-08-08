package com.github.ingloriuosthree.javahelpers;

import java.util.Arrays;
import java.util.Objects;

public final class ObjectUtils {

   /**
    * Checks whether or not all given params are non null.
    *
    * @param t objects that you want to check
    * @return true, if all given params are non null, false if any param is null
    */
   public static <T> boolean allNonNull(T... t) {
      return Arrays.stream(t)
            .allMatch(Objects::nonNull);
   }

   /**
    * Throws NullPointerException if any given param is null
    *
    * @param t objects that has to be non null
    * @throws NullPointerException if any object is null
    */
   public static <T> void allRequireNonNull(T... t) {
      Arrays.stream(t).forEach(Objects::requireNonNull);
   }
}
