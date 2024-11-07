package pl.krzyssko.portfoliowebsite.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import pl.krzyssko.portfoliowebsite.components.layouts.PageLayout
import pl.krzyssko.portfoliowebsite.components.sections.Contact
import pl.krzyssko.portfoliowebsite.components.sections.Hero
import pl.krzyssko.portfoliowebsite.components.sections.Portfolio

@Page
@Composable
fun HomePage() {

    PageLayout("Krzysztof Skórcz's professional page") {
        //Box(
        //    Modifier.fillMaxSize(),
        //    contentAlignment = Alignment.TopCenter
        //) {
        //    Column {
        //        Hero()
        //    }
        //}
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Hero()
            Portfolio()
            Contact()
        }
    }
}
