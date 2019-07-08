/*
 * This Groovy source file was generated by the Gradle 'init' task.
 */
package com.acme.plugin

import groovy.transform.CompileStatic
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A simple 'hello world' plugin.
 */
@CompileStatic
class GreetingPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.tasks.register("staticGreeting", StaticGreetingTask)
        project.tasks.register("dynamicGreeting", DynamicGreetingTask)
        project.tasks.register("dynamicWorkerApiGreeting", DynamicWorkerApiGreetingTask)
    }
}