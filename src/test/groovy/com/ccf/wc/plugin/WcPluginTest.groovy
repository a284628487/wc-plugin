package com.ccf.wc.plugin

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

class WcPluginTest {

    @Test
    public void buildTaskIsBuildTask() {
        Project project = ProjectBuilder.builder().build()

        project.pluginManager.apply(AppPlugin.class)
        // project.pluginManager.apply(LibraryPlugin.class)
        project.pluginManager.apply("wc-plugin")

        println(project.tasks.getByName("WcBuild") instanceof BuildTask)

        println(project.tasks.getByName("WcOutput"))

        println(project.tasks.getByName("printWcExtension"))
    }
}
