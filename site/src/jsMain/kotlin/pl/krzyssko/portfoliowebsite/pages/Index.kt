package pl.krzyssko.portfoliowebsite.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import kotlinx.coroutines.delay
import pl.krzyssko.portfoliowebsite.Language
import pl.krzyssko.portfoliowebsite.Locale
import pl.krzyssko.portfoliowebsite.components.layouts.PageLayout
import pl.krzyssko.portfoliowebsite.components.sections.Contact
import pl.krzyssko.portfoliowebsite.components.sections.Hero
import pl.krzyssko.portfoliowebsite.components.sections.Portfolio
import pl.krzyssko.portfoliowebsite.localized
import kotlin.time.Duration.Companion.seconds

@Page("/index")
@Composable
fun Index() {
    val ctx = rememberPageContext()
    if (Locale.initialLanguage == Language.EN) {
        ctx.router.navigateTo("/en/")
    }
    if (Locale.initialLanguage == Language.PL) {
        ctx.router.navigateTo("/pl/")
    }
}

@Composable
fun HomePage() {

    LaunchedEffect(Unit) {
        Locale.init()
    }

    PageLayout("Krzysztof Skórcz - mobile applications".localized()) {
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