package com.ccf.wc.plugin

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class WcPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println("WcPlugin.apply......")

        BaseExtension extension = project.extensions.findByType(BaseExtension.class)
        extension.registerTransform(new WcTransform())

        project.tasks.create("WcBuild", BuildTask)

        project.task("WcOutput") {
            doLast {
                println("WcOutput begin")
                println("WcOutput ...")
                println("WcOutput end")
            }
        }

        if (project.android) {
            println("Hi, Android Project")
        } else {
            println("UnKnown Project")
        }
    }
}
