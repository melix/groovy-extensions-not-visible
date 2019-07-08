package com.acme.plugin

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ASTTransformationCustomizer
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import groovy.transform.CompileStatic

class DynamicGroovyStaticShellGreetingTask extends DefaultTask {
    @Input
    Property<String> message = project.objects.property(String).convention ("Hello, Groovy")

    @TaskAction
    void showsLength() {
        def msg = message.get()
        def configuration = new CompilerConfiguration()
        configuration.addCompilationCustomizers(new ASTTransformationCustomizer(CompileStatic))
        def shell = new GroovyShell(DynamicGroovyStaticShellGreetingTask.classLoader, new Binding([msg: msg]), configuration)
        shell.evaluate("""
            String msg = binding.getVariable("msg")
            println "Message: \${msg} Taille: \${msg.taille}"
        """)
    }
}
