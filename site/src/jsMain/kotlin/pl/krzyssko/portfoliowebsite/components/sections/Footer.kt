package pl.krzyssko.portfoliowebsite.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.color.ColorVar
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Span
import pl.krzyssko.portfoliowebsite.style.toColorPalette

val FooterStyle = CssStyle.base {
    Modifier
        .backgroundColor(ColorMode.DARK.toColorPalette().backgroundDim)
        .color(ColorMode.DARK.toColorPalette().tint)
        .padding(topBottom = 1.5.cssRem, leftRight = 10.percent)
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Box(FooterStyle.toModifier().then(modifier), contentAlignment = Alignment.Center) {
        Span(Modifier.fontSize(14.px).textAlign(TextAlign.Center).toAttrs()) {
            val sitePalette = ColorMode.current.toColorPalette()
            SpanText("built with ")
            Link(
                "https://github.com/varabyte/kobweb",
                "Kobweb",
                Modifier.setVariable(ColorVar, sitePalette.brand.primary),
                variant = UncoloredLinkVariant
            )
            SpanText(", designed by ")
            Link(
                "https://www.instagram.com/_buymyshelf/",
                "_buymyshelf",
                Modifier.setVariable(ColorVar, sitePalette.brand.primary),
                variant = UncoloredLinkVariant
            )
        }
    }
}
