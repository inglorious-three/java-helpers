package com.github.ingloriuosthree.javahelpers;

import java.util.Arrays;
import java.util.Objects;

public final class ObjectUtils {

   /**
    * Checks whether or not all given params are non null.
    *
    * @param objects objects that you want to check
    * @return true, if all given params are non null, false if any param is null
    */
   public static boolean allNonNull(Object... objects) {
      return Arrays.stream(objects).allMatch(Objects::nonNull);
   }

   /**
    * Throws NullPointerException if any given param is null
    *
    * @param objects objects that has to be non null
    * @throws NullPointerException if any object is null
    */
   public static void allRequireNonNull(Object... objects) {
      Arrays.stream(objects).forEach(Objects::requireNonNull);
   }
}
