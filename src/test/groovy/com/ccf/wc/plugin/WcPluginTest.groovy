package com.ccf.wc.plugin

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

class WcPluginTest {

    @Test
    public void buildTaskIsBuildTask() {
        Project project = ProjectBuilder.builder().build()

        project.pluginManager.apply("wc-plugin")

        println(project.tasks.getByName("WcBuild") instanceof BuildTask)

        println(project.tasks.getByName("WcOutput"))
    }
}
