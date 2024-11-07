package pl.krzyssko.portfoliowebsite.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import pl.krzyssko.portfoliowebsite.style.UncoloredButtonVariant


@Composable
fun ExternalLinkButton(modifier: Modifier = Modifier, link: String, content: @Composable () -> Unit) {
    Button(modifier = modifier, onClick = { }, variant = UncoloredButtonVariant) {
        Link(link, openInternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB, variant = UncoloredLinkVariant.then(
            UndecoratedLinkVariant
        )) {
            content()
        }
    }
}
