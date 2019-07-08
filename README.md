# Context

This project demonstrates an issue with Groovy extension modules not visible at plugin execution time.

The project is a composite build which consists of 3 modules:

- `groovy-ext-module`, a Groovy extension module declaring an additional method on `String`
- `plugin`, a Gradle plugin which declares tasks which use the extension method
- `consumer`, a Gradle project which uses the plugin above

The plugin defines different tasks:

- `dynamicGreeting`, a task which is using dynamic Groovy, and therefore requires the extension to be visible at runtime
- `dynamicGroovyShellGreeting`, a task which is using dynamic Groovy, and executes the code in a Groovy shell
- `dynamicGroovyStaticShellGreeting`, a task which is using dynamic Groovy, and executes the code in a Groovy shell with `@CompileStatic` applied
- `staticGreeting`, a task which is using statically compile Groovy
- `staticGroovyShellGreeting`, a task which is using statically compile Groovy and executes the code in a Groovy shell
- `dynamicWorkerApiGreeting`, a task which is again using dynamic Groovy, but executes the task using the Worker API out-of-process
- `javaExecGreeting`, a task written in Java which executes the script in a Groovy shell using Java Exec

## Testing

To test, run, at the top-level, either:

- `gradle dynamicGreeting`
- `gradle staticcGreeting`
- `gradle dynamicWorkerApiGreeting`

You will see that only the "static" version works, because the static compiler resolves the method at compile time, so doesn't rely on the initialization of the Groovy MOP in Gradle plugins.

The fact the worker API version doesn't work either is more surprising.

Conclusion: extensions methods are _only_ visible with `@CompileStatic`