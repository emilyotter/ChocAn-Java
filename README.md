# Project4 - Java Project with Ant Build Script

## Description
This project, named "ChocAn," is a Java Project (JAR) with an Ant build script. It allows you to compile and package a Java application, including external library dependencies. The build script uses Apache Ivy for dependency management.

## Prerequisites
Before using this project, make sure you have the following prerequisites:
- Apache Ant installed
- Apache Ivy Jar file in the `lib` directory under user home directory (e.g. `~/.ant/lib`) (Run ant ivy to do this automatically)

## Usage
You can use the following targets to perform various tasks:

- `resolve`: Retrieves project dependencies using Ivy.
- `ivy`: Installs Ivy if it's not already installed.
- `init`: Creates the necessary build directory.
- `compile`: Compiles the Java source code.
- `copy-dependencies`: Copies project dependencies to the `dist/lib` directory.
- `jar`: Packages the application into a JAR file with dependencies.
- `run`: Executes the application.
- `clean`: Cleans up build and distribution directories.

## Getting Started
1. Ensure you have Ant installed.
2. Run the `ivy` target to install Ivy if not already installed.
3. Run the `resolve` target to retrieve project dependencies.
4. Use the `compile` target to compile the Java source code.
5. Run the `jar` target to package the application into a JAR file.
6. Execute the application with the `run` target.

## Customization
You can customize the project by modifying the following properties in the build script:

- `projectName`: The name of your project.
- `src.dir`: The source code directory.
- `build.dir`: The directory for compiled classes.
- `dist.dir`: The distribution directory.
- `dist.lib.dir`: The directory for copied dependencies.
- `lib.dir`: The directory containing external libraries.
- `main-class`: The main class to be executed when using the `run` target.

## Example Usage
1. Clone this project.
2. Modify the properties in the build script as needed.
3. Run the `main` target to resolve dependencies, clean the build directories, compile, package, and run the application.

```shell
ant main
```

## Clean Up
You can use the `clean` target to remove the build and distribution directories.
```shell
ant clean
```