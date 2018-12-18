package com.codearms.maoqiqi.skin.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Doc.March
 * @date 2018/7/27
 */
@Retention(RUNTIME)
@Target({TYPE})
public @interface Skin {
}