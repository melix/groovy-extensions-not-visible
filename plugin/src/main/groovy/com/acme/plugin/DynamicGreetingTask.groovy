package com.acme.plugin


import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class DynamicGreetingTask extends DefaultTask {
    @Input
    Property<String> message = project.objects.property(String).convention ("Hello, Groovy")

    @TaskAction
    void showsLength() {
        def msg = message.get()
        logger.lifecycle("Message: ${msg} Taille: ${msg.taille}")
    }
}
