import org.gradle.api.Plugin
import org.gradle.api.Project


/** Lesson3
 * Define Plugin in buildSrc
 */
class SleepPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.task("sleep") {

            println("I'm SleepTask in SleepPlugin defined in buildSrc dir")

            doLast {
                println("SleepTask done")
            }
        }
    }
}
