/*
 * This Groovy source file was generated by the Gradle 'init' task.
 */
package com.acme.extensions

import groovy.transform.CompileStatic

@CompileStatic
class MyExtension {
    static int getTaille(String self) { self.length() }
}
