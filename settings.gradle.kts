rootProject.name = "vulpes-backend"

dependencyResolutionManagement {
    if (System.getenv("CI") != null) {
        repositoriesMode = RepositoriesMode.PREFER_SETTINGS
        repositories {
            maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            maven("https://repo.htl-md.schule/repository/Gitlab-Runner/")
            maven {
                val groupdId = 28 // Gitlab Group
                val ciApiv4Url = System.getenv("CI_API_V4_URL")
                url = uri("$ciApiv4Url/groups/$groupdId/-/packages/maven")
                name = "GitLab"
                credentials(HttpHeaderCredentials::class.java) {
                    name = "Job-Token"
                    value = System.getenv("CI_JOB_TOKEN")
                }
                authentication {
                    create<HttpHeaderAuthentication>("header")
                }
            }
        }
    } else {
        repositories {
            mavenCentral()
            maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            maven {
                val groupdId = 28 // Gitlab Group
                url = uri("https://gitlab.onelitefeather.dev/api/v4/groups/$groupdId/-/packages/maven")
                name = "GitLab"
                credentials(HttpHeaderCredentials::class.java) {
                    name =  "Private-Token"
                    value = providers.gradleProperty("gitLabPrivateToken").get()
                }
                authentication {
                    create<HttpHeaderAuthentication>("header")
                }
            }
        }
    }
    versionCatalogs {
        create("libs") {
            version("annotation", "26.0.1")
            version("jackson", "2.18.2")
            version("embed.mongo", "4.11.0")

            library("jackson", "com.fasterxml.jackson.module", "jackson-module-kotlin").versionRef("jackson")
            library("annotation", "org.jetbrains", "annotations").versionRef("annotation")
            library("vulpes.api", "net.theevilreaper.vulpes.api", "vulpes-spring-api").version("1.0.0-SNAPSHOT")
            library("embed.mongo", "de.flapdoodle.embed", "de.flapdoodle.embed.mongo.spring30x").versionRef("embed.mongo")

        }
    }
}