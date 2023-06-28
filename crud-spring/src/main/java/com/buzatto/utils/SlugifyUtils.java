package com.buzatto.utils;

import com.github.slugify.Slugify;

public class SlugifyUtils {

    private static final Slugify slugify = Slugify.builder().build();

    public static String slugify(String input) {
        return slugify.slugify(input);
    }
}
