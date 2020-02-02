import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.21"
    groovy
    java
    maven
    `maven-publish`
}

val pluginVersion = "0.0.10"
group = "com.ccf.wc"
version = pluginVersion

repositories {
    google()
    jcenter()
    mavenCentral()
    maven {
        credentials {
            username = "admin"
            password = "admin123"
        }
        url = uri("http://localhost:8081/repository/maven-releases/")
    }
}

dependencies {
    compile("org.codehaus.groovy:groovy-all:2.5.4")
    // implementation("com.android.tools.build:transform-api:1.5.0")
    implementation("com.android.tools.build:gradle:3.1.2")
    implementation(kotlin("stdlib-jdk8"))
    implementation(gradleApi())
    implementation(localGroovy())

    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


/** Lesson1 ----------------------------------------------------------
 * Define plugin in build.gradle file
 * GreetingPlugin
 */

open class GreetingPluginExtension {
    var message = "Hello, How are you?"
    var user = "Ccf"
}

class GreetingPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        // add the 'greeting' extension object
        val extension = target.extensions.create<GreetingPluginExtension>("greeting")
        // add a hello task
        target.task("hello") {

            println("I'm HelloTask in GreetingPlugin")

            doLast {
                println("Hello from HelloTask")

                println("${extension.message} from: ${extension.user}")
            }
        }
    }
}

apply<GreetingPlugin>()

// Configure the extension using a DSL block
configure<GreetingPluginExtension> {
    user = "ccf & yyn"
}

/** Configure the extension using groovy
// greeting {
//    user = "ccf & yyn"
// }
 */


/**
 * Define plugin in build.gradle file - end
 */


// ----------------------------------------------------------


/** Lesson2 ----------------------------------------------------------
 * Define a CustomTask to write text into file
 * WriteText2FileTask
 */

open class WriteText2FileTask : DefaultTask() {

    var filePath: String = ""

    @OutputFile
    fun getDestination(): File {
        return project.file(filePath)
    }

    @TaskAction
    fun greeting() {
        val file = getDestination()
        file.parentFile.mkdirs()
        file.writeText("Hello from WriteText2FileTask")
    }

}

// define task:writeTextFile, the task is WriteText2FileTask, and configure the filePath
tasks.register<WriteText2FileTask>("writeTextFile") {
    filePath = project.extra["filePath"]!! as String
}

// define task:testWriteFile
tasks.register("testWriteFile") {

    dependsOn("writeTextFile")

    doLast {
        println(project.file(project.extra["filePath"]!!).readText())
    }
}

extra["filePath"] = "$buildDir/greeting.txt"


// ----------------------------------------------------------


/** Lesson3 ----------------------------------------------------------
 * Define plugin in buildSrc
 * SleepPlugin
 */
apply<SleepPlugin>()


/** Lesson4 ----------------------------------------------------------
 * Publish to maven
 */
configure<PublishingExtension> {

    repositories {

        maven {
            credentials {
                username = "admin"
                password = "admin123"
            }
            url = uri("http://localhost:8081/repository/maven-releases/")
        }
    }

    publications {

        create<MavenPublication>("maven") {
            groupId = "com.ccf.wc"
            artifactId = "wc-plugin"
            version = pluginVersion
            artifact("$buildDir/libs/$artifactId-$version.jar")
            description = "Wtf?"
        }
    }
}

tasks.register("uploadArchives") {

    dependsOn("assemble")

    dependsOn("publish")

}

// Publish to maven end