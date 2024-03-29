/*
 * This Groovy source file was generated by the Gradle 'init' task.
 */
package com.acme.plugin

import groovy.transform.CompileStatic
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.tasks.JavaExec

/**
 * A simple 'hello world' plugin.
 */
@CompileStatic
class GreetingPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.tasks.register("staticGreeting", StaticGreetingTask)
        project.tasks.register("staticGroovyShellGreeting", StaticGroovyShellGreetingTask)
        project.tasks.register("dynamicGreeting", DynamicGreetingTask)
        project.tasks.register("dynamicGroovyShellGreeting", DynamicGroovyShellGreetingTask)
        project.tasks.register("dynamicGroovyStaticShellGreeting", DynamicGroovyStaticShellGreetingTask)
        project.tasks.register("dynamicWorkerApiGreeting", DynamicWorkerApiGreetingTask)
        project.tasks.register("javaExecGreeting", JavaExec) {
            def groovyRuntime = project.configurations.detachedConfiguration(
                    dependency(project, "org.codehaus.groovy:groovy:2.5.6"),
                    dependency(project, "com.acme:groovy-ext-module:1.0")
            )
            it.classpath(groovyRuntime, new File(GreetingPlugin.protectionDomain.codeSource.location.toURI()))
            it.main = "com.acme.plugin.GroovyShellAction"
            it.args("Hello, Groovy")
        }
    }

    private Dependency dependency(Project project, String coordinates) {
        project.dependencies.create(coordinates)
    }
}
