package com.dj.songs.gradle_plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class Gradle_plugins implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println("gradle_plugins")

        project.tasks.create("mytask", Task1.class)
        android.registerTransform(new Transm())

    }
}