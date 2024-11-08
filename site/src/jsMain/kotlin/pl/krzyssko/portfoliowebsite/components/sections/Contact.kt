package pl.krzyssko.portfoliowebsite.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import pl.krzyssko.portfoliowebsite.components.widgets.ArrowLinkIcon
import pl.krzyssko.portfoliowebsite.components.widgets.ExternalLinkButton
import pl.krzyssko.portfoliowebsite.components.widgets.SocialIcons
import pl.krzyssko.portfoliowebsite.style.HeadlineTextStyle
import pl.krzyssko.portfoliowebsite.style.RegularTextStyle
import pl.krzyssko.portfoliowebsite.style.Style
import pl.krzyssko.portfoliowebsite.style.toColorPalette

val ContactStyle = CssStyle {
    val palette = ColorMode.DARK.toColorPalette()
    base { Modifier.fillMaxWidth().padding(1.cssRem).background(palette.backgroundDim).color(palette.tint) }
}

val ContactContentStyle = CssStyle {
    base { Modifier.fillMaxWidth() }
    Breakpoint.MD { Modifier.maxWidth(Style.Dimens.MAX_PAGE_WIDTH.px).minHeight(Style.Dimens.MAX_CONTACT_HEIGHT.px) }
}

@Composable
private fun Title(modifier: Modifier = Modifier) {
    Div(HeadlineTextStyle.toModifier().then(modifier).id("contact").toAttrs()) {
       SpanText("> contact", modifier.whiteSpace(WhiteSpace.NoWrap))
    }
}

@Composable
fun ContactInfo(breakpoint: Breakpoint) {
    Column(Modifier.gap(18.px).fillMaxWidth(50.percent).thenIf(breakpoint < Breakpoint.MD, Modifier.margin(topBottom = 18.px)), verticalArrangement = Arrangement.SpaceBetween) {
        Row(Modifier.fillMaxWidth().gap(22.px)) {
            Box(Modifier.fillMaxWidth(40.percent).align(Alignment.CenterVertically)) {
                SpanText("e-mail", modifier = Modifier.fontSize(14.px).whiteSpace(WhiteSpace.PreLine))
            }
            Link(path = "mailto:krzy.skorcz@gmail.com", text = "krzy.skorcz@gmail.com", modifier = Modifier.fillMaxWidth(), variant = UncoloredLinkVariant)
        }
        Row(Modifier.fillMaxWidth().gap(22.px)) {
            Box(Modifier.fillMaxWidth(40.percent).align(Alignment.CenterVertically)) {
                SpanText("phone number", modifier = Modifier.fontSize(14.px).whiteSpace(WhiteSpace.PreLine))
            }
            //SpanText("+", modifier = Modifier.fillMaxWidth())
            Link(path = "tel:+48886331190", text = "+48 886 331 190", modifier = Modifier.fillMaxWidth(), variant = UncoloredLinkVariant)
        }
    }
}

@Composable
fun Contact() {
    val colorMode = ColorMode.DARK
    val palette = colorMode.toColorPalette()
    val breakpoint = rememberBreakpoint()
    Box(ContactStyle.toModifier(), contentAlignment = Alignment.TopCenter) {
        Column(ContactContentStyle.toModifier().fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            Box(Modifier.gridTemplateRows { size(minContent); size(1.fr) }.gridTemplateColumns { size(minContent); size(1.fr) }) {
                Row(Modifier.gridRow(1).gridColumn(1).padding(right = 60.px)) {
                    Title(Modifier.padding(top = 40.px))
                }
                Column(Modifier.fillMaxSize().gridRow(2).gridColumn(2).gap(22.px)) {
                    Div {
                        SpanText("opis, że możesz współpracować na zasadzie freelance do jednorazlwego projektu lub zlecenia ale też jesteś otwarty na oferty pracy na pełen etat . Bądźmy w kontakcie.")
                    }
                }
            }

            ContactInfo(breakpoint)

            Row(Modifier.fillMaxWidth(if (breakpoint >= Breakpoint.MD) 40.percent else 100.percent).thenIf(breakpoint < Breakpoint.MD, Modifier.flexWrap(
                FlexWrap.Wrap)), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                val modifier = Modifier.color(palette.tint)
                SocialIcons(modifier, breakpoint, palette.backgroundDim)
                ExternalLinkButton(RegularTextStyle.toModifier().then(modifier), "https://github.com/k-skor/portfolio-browser") {
                    Row {
                        SpanText("Portfolio Browser", modifier)
                        ArrowLinkIcon(modifier.size(24.px).margin(left = 0.5.em))
                    }
                }
            }
        }
    }
}

