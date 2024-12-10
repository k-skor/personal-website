package pl.krzyssko.portfoliowebsite.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import pl.krzyssko.portfoliowebsite.style.FadingSectionArrowStyle
import pl.krzyssko.portfoliowebsite.style.MovingSectionTitleStyle
import pl.krzyssko.portfoliowebsite.style.SectionTitleParentStyle

@Composable
fun MovingSectionTitle(modifier: Modifier = Modifier, breakpoint: Breakpoint, decorator: String, title: String) {
    Div(modifier.thenIf(breakpoint >= Breakpoint.MD, SectionTitleParentStyle.toModifier()).toAttrs()) {
        Div(FadingSectionArrowStyle.toAttrs()) {
            Text(decorator)
        }
        Div(MovingSectionTitleStyle.toAttrs()) {
            Text(title)
        }
    }
}