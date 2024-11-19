package pl.krzyssko.portfoliowebsite.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import pl.krzyssko.portfoliowebsite.components.sections.Footer
import pl.krzyssko.portfoliowebsite.components.sections.NavHeader

val PageContentStyle = CssStyle {
    base { Modifier.fillMaxWidth() }
}

val NavPaddingStyle = CssStyle {
    base { Modifier.fillMaxSize().padding(top = (2.5.cssRem) + (2 * 16.px)) }
    Breakpoint.MD { Modifier.padding(top = (2.5.cssRem) + (2 * 24.px)) }
}

@Composable
fun PageLayout(title: String, content: @Composable ColumnScope.() -> Unit) {
    val colorMode by ColorMode.currentState

    LaunchedEffect(title) {
        document.title = title
    }

    Box(
        Modifier
            .fillMaxWidth()
            .minHeight(100.percent)
            .gridTemplateRows { size(minContent); size(1.fr); size(minContent) },
        contentAlignment = Alignment.TopCenter
    ) {
        NavHeader(Modifier.gridRow(1).position(Position.Fixed).overflow(Overflow.Hidden).top(0.px).zIndex(1), colorMode)
        Column(
            NavPaddingStyle.toModifier().gridRow(2).zIndex(0),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Div(Modifier.fillMaxSize().toAttrs()) {
                content()
            }
        }
        Footer(Modifier.fillMaxWidth().gridRow(3))
    }
}
