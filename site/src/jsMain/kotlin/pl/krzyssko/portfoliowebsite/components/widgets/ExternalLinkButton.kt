package pl.krzyssko.portfoliowebsite.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.silk.components.navigation.Link
import pl.krzyssko.portfoliowebsite.style.DarkExternalLinkVariant


@Composable
fun ExternalLinkButton(modifier: Modifier = Modifier, link: String, content: @Composable () -> Unit) {
    Link(link, openInternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB, modifier = modifier, variant = DarkExternalLinkVariant) {
        content()
    }
}
