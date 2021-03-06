package com.sramar.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {
    /* Permissions */
    String[] permissions();
    /* Rationales */
    int[] rationales() default {};
    /* Rejects */
    int[] rejects() default {};

    /* Rationales */
    String[] srationales() default {};
    /* Rejects */
    String[] srejects() default {};
}
