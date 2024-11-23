package pl.krzyssko.portfoliowebsite.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import kotlinx.coroutines.delay
import pl.krzyssko.portfoliowebsite.components.layouts.PageLayout
import pl.krzyssko.portfoliowebsite.components.sections.Contact
import pl.krzyssko.portfoliowebsite.components.sections.Hero
import pl.krzyssko.portfoliowebsite.components.sections.Portfolio
import kotlin.time.Duration.Companion.seconds

@Page
@Composable
fun HomePage() {

    PageLayout("Krzysztof Skórcz's professional page") {
        val readyToRender = mutableStateOf(false)
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Hero()
            LaunchedEffect(Unit) {
                delay(2.seconds)
                readyToRender.value = true
            }
            if (readyToRender.value) {
                Portfolio()
                Contact()
            }
        }
    }
}
