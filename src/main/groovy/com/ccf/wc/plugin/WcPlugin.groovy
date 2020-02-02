package com.ccf.wc.plugin

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class WcPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println("WcPlugin.apply......")

        // create extension
        WcExtension wcExtension = project.extensions.create("wcExtension", WcExtension.class)
        project.task("printWcExtension").doLast {
            wcExtension.printIt()
        }
        // Given a app/library project, config it as below:
        // wcExtension {
        //    needTransform = true
        // }

        // register Transform
        BaseExtension extension = project.extensions.getByType(BaseExtension.class)
        extension.registerTransform(new WcTransform(project))

        // create task by TaskClass
        project.tasks.create("WcBuild", BuildTask)

        // create task by Closure
        project.task("WcOutput") {
            doLast {
                println("WcOutput begin")
                println("WcOutput ...")
                println("WcOutput end")
            }
        }

        if (project.plugins.hasPlugin(LibraryPlugin.class)) {
            println("Library Project: ${project.android.libraryVariants}")
            project.afterEvaluate {
                project.android.libraryVariants.all {
                    // TODO
                }
            }
        } else if (project.plugins.hasPlugin(AppPlugin.class)) {
            println("App Project: ${project.android.applicationVariants}")
            project.afterEvaluate {
                project.android.applicationVariants.all { variant ->
                    println("Variant: ${variant}")
                }
            }
        }

        if (project.android) {
            println("Hi, Android Project")
        } else {
            println("UnKnown Project")
        }
    }
}
