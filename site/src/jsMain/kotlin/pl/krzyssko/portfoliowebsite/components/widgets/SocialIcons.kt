package pl.krzyssko.portfoliowebsite.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.px

@Composable
fun SocialIcons(modifier: Modifier = Modifier, breakpoint: Breakpoint, background: CSSColorValue) {
    Row(modifier.fillMaxWidth().gap(60.px), horizontalArrangement = if (breakpoint <= Breakpoint.SM)
        Arrangement.Center else Arrangement.Start) {
        Link(path = "https://github.com/k-skor") {
            GitHubIcon(modifier.size(24.px))
        }
        Link(path = "https://www.linkedin.com/in/krzysztof-skorcz/") {
            LinkedInIcon(modifier.size(24.px), background)
        }
        Link(path = "https://www.facebook.com/krzysztof.skorcz") {
            FacebookIcon(modifier.size(24.px), background)
        }
    }
}