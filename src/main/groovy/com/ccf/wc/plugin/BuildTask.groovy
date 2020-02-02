package com.ccf.wc.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class BuildTask extends DefaultTask {

    @TaskAction
    public void build() {
        println("WcBuildTask Begin")
        println("WcBuildTask ......")
        println("WcBuildTask End")
    }
}
