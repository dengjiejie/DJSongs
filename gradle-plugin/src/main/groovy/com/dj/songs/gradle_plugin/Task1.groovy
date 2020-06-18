package com.dj.songs.gradle_plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class Task1 extends DefaultTask {

    @TaskAction
    void action() {

        println("my task")

    }








}