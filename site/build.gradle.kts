import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link
import kotlinx.html.script

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
}

group = "pl.krzyssko.portfoliowebsite"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            val configDomainName = "krzysztofskorcz.pl"
            val configGtag = "AW-11497412052"

            description.set("Powered by Kobweb")
            head.add {
                link(rel = "preconnect", href = "https://fonts.googleapis.com")
                link(rel = "preconnect", href = "https://fonts.gstatic.com") { attributes["crossorigin"] = "" }
                link(
                    href = "https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@300..700&display=swap",
                    rel = "stylesheet"
                )
                link(
                    rel = "alternate",
                    href = "https://${configDomainName}/pl"
                ) {
                    hrefLang = "pl"
                }
                link(
                    rel = "alternate",
                    href = "https://${configDomainName}/en"
                ) {
                    hrefLang = "en"
                }
                link(
                    rel = "alternate",
                    href = "https://${configDomainName}/"
                ) {
                    hrefLang = "x-default"
                }
                //<!-- Google tag (gtag.js) -->
                script(src = "https://www.googletagmanager.com/gtag/js?id=${configGtag}") {
                    async = true
                }
                script(src = "/gtag.js") { }
            }
        }
    }
}

kotlin {
    // This example is frontend only. However, for a fullstack app, you can uncomment the includeServer parameter
    // and the `jvmMain` source set below.
    configAsKobwebApplication("portfoliowebsite" /*, includeServer = true*/)

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
        }

        jsMain.dependencies {
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            // This default template uses built-in SVG icons, but what's available is limited.
            // Uncomment the following if you want access to a large set of font-awesome icons:
            // implementation(libs.silk.icons.fa)
            implementation(libs.kobwebx.markdown)
        }

        // Uncomment the following if you pass `includeServer = true` into the `configAsKobwebApplication` call.
//        jvmMain.dependencies {
//            compileOnly(libs.kobweb.api) // Provided by Kobweb backend at runtime
//        }
    }
}
