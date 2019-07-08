package com.acme.plugin

import groovy.transform.CompileStatic
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.workers.IsolationMode
import org.gradle.workers.WorkerExecutor

import javax.inject.Inject

// task is statically compiled but the task action is not
@CompileStatic
class DynamicWorkerApiGreetingTask extends DefaultTask {
    private final WorkerExecutor workerExecutor

    @Input
    Property<String> message = project.objects.property(String).convention ("Hello, Groovy")

    @Inject
    DynamicWorkerApiGreetingTask(WorkerExecutor workerExecutor) {
        this.workerExecutor = workerExecutor
    }

    @TaskAction
    void showsLength() {
        workerExecutor.submit(DynamicWorkerAction) {
            it.params(message.get())
            it.isolationMode = IsolationMode.PROCESS
        }
    }
}

// this is dynamic
class DynamicWorkerAction implements Runnable {

    private final String message

    @Inject
    DynamicWorkerAction(String message) {
        this.message = message
    }

    @Override
    void run() {
        println("Message: ${message} Taille: ${message.taille}")
    }
}