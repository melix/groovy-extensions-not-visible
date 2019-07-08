package com.acme.plugin


import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class DynamicGroovyShellGreetingTask extends DefaultTask {
    @Input
    Property<String> message = project.objects.property(String).convention ("Hello, Groovy")

    @TaskAction
    void showsLength() {
        def msg = message.get()
        def shell = new GroovyShell(DynamicGroovyShellGreetingTask.classLoader)
        shell.context.variables.msg = msg
        shell.evaluate("""
            println "Message: ${msg} Taille: ${msg.taille}"
        """)
    }
}
