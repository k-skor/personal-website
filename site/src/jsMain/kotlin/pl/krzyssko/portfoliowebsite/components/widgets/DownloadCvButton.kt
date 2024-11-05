package pl.krzyssko.portfoliowebsite.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.icons.DownloadIcon
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.breakpoint.displayUntil
import pl.krzyssko.portfoliowebsite.style.PrimaryButtonVariant


@Composable
fun DownloadCvButton() {
    Button(onClick = { }, variant = PrimaryButtonVariant) {
        Link("Krzysztof_Skórcz_-CV.pdf", openInternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB, variant = UncoloredLinkVariant.then(
            UndecoratedLinkVariant
        )) {
            Box(Modifier.displayIfAtLeast(Breakpoint.MD)) {
                SpanText("Download CV")
            }
            Row(Modifier.displayUntil(Breakpoint.SM)) {
                DownloadIcon()
                SpanText(" CV")
            }
        }
    }
}