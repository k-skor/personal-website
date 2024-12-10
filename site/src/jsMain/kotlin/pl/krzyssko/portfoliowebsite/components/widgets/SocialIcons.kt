package pl.krzyssko.portfoliowebsite.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.CSSColor
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*

val UnderlineIconParentStyle = CssStyle {
    cssRule(":hover .underline-icon") {
        Modifier
            .background(Color.currentColor)
    }
}

val UnderlineIconStyle = CssStyle.base {
    Modifier
        .background(Colors.Transparent)
        .transition(Transition.of("background-color", 300.ms))
}

@Composable
fun HighlightIcon(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(UnderlineIconParentStyle.toModifier().then(modifier)) {
        content()
        Box(UnderlineIconStyle.toModifier().fillMaxWidth().height(0.1.em).align(Alignment.BottomCenter)) { }
    }
}

@Composable
fun SocialIcons(modifier: Modifier = Modifier, breakpoint: Breakpoint, background: CSSColorValue) {
    Row(modifier.gap(60.px), horizontalArrangement = if (breakpoint <= Breakpoint.SM)
        Arrangement.Center else Arrangement.Start) {
        HighlightIcon(modifier) {
            Link(path = "https://github.com/k-skor", Modifier.color(CSSColor.Inherit)) {
                GitHubIcon(modifier.size(32.px))
            }
        }
        HighlightIcon(modifier) {
            Link(path = "https://www.linkedin.com/in/krzysztof-skorcz/", Modifier.color(CSSColor.Inherit)) {
                LinkedInIcon(modifier.size(32.px), background)
            }
        }
        HighlightIcon(modifier) {
            Link(path = "https://www.facebook.com/krzysztof.skorcz", Modifier.color(CSSColor.Inherit)) {
                FacebookIcon(modifier.size(32.px), background)
            }
        }
    }
}