package pl.krzyssko.portfoliowebsite.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.navigation.Link
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
import pl.krzyssko.portfoliowebsite.components.widgets.MovingSectionTitle
import pl.krzyssko.portfoliowebsite.components.widgets.SocialIcons
import pl.krzyssko.portfoliowebsite.style.*

val ContactStyle = CssStyle {
    val palette = ColorMode.DARK.toColorPalette()
    base { Modifier.fillMaxWidth().padding(1.cssRem).background(palette.backgroundDim).color(palette.font) }
}

val ContactContentStyle = CssStyle {
    base { Modifier.padding(topBottom = 2.cssRem).gap(3.cssRem) }
    Breakpoint.MD { Modifier.maxWidth(Style.Dimens.MAX_PAGE_WIDTH.px).minHeight(Style.Dimens.MAX_CONTACT_HEIGHT.px) }
}

@Composable
private fun Title(modifier: Modifier = Modifier, breakpoint: Breakpoint) {
    Div(HeadlineTextStyle.toModifier().then(modifier).id("contact").toAttrs()) {
        MovingSectionTitle(breakpoint = breakpoint, decorator = ">", title = "contact")
    }
}

@Composable
fun ContactInfo(modifier: Modifier = Modifier, breakpoint: Breakpoint) {
    Column(modifier.fillMaxWidth(if (breakpoint < Breakpoint.MD) 100.percent else 50.percent).gap(1.cssRem), verticalArrangement = Arrangement.SpaceBetween) {
        Row(Modifier.fillMaxWidth().gap(2.cssRem)) {
            Box(Modifier.fillMaxWidth(8.cssRem).align(Alignment.CenterVertically)) {
                SpanText("e-mail", modifier = Modifier.fontSize(14.px).whiteSpace(WhiteSpace.NoWrap))
            }
            Link(path = "mailto:krzy.skorcz@gmail.com", text = "krzy.skorcz@gmail.com", modifier = Modifier.fontWeight(400), variant = DarkExternalLinkVariant)
        }
        Row(Modifier.fillMaxWidth().gap(22.px)) {
            Box(Modifier.fillMaxWidth(8.cssRem).align(Alignment.CenterVertically)) {
                SpanText("phone number", modifier = Modifier.fontSize(14.px).whiteSpace(WhiteSpace.PreLine))
            }
            Link(path = "tel:+48886331190", text = "+48 886 331 190", modifier = Modifier.fontWeight(400), variant = DarkExternalLinkVariant)
        }
    }
}

@Composable
fun ContactLinks(modifier: Modifier = Modifier, colorMode: ColorMode, breakpoint: Breakpoint) {

    val palette = colorMode.toColorPalette()
    if (breakpoint < Breakpoint.MD) {
        Column(modifier.gap(3.cssRem)) {
            ExternalLinkButton(modifier, "https://github.com/k-skor/portfolio-browser") {
                Row {
                    SpanText("Portfolio Browser", modifier.fontSize(22.px))
                    ArrowLinkIcon(modifier.size(24.px).margin(left = 0.5.em))
                }
            }
            SocialIcons(modifier, breakpoint, palette.backgroundDim)
        }
    } else {
        Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            SocialIcons(modifier, breakpoint, palette.backgroundDim)
            ExternalLinkButton(modifier, "https://github.com/k-skor/portfolio-browser") {
                Row {
                    SpanText("Portfolio Browser", modifier.fontSize(22.px))
                    ArrowLinkIcon(modifier.size(24.px).margin(left = 0.5.em))
                }
            }
        }
    }
}

@Composable
fun Contact() {
    val colorMode = ColorMode.DARK
    val breakpoint = rememberBreakpoint()
    Box(ContactStyle.toModifier(), contentAlignment = Alignment.TopCenter) {
        Column(ContactContentStyle.toModifier().fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            Title(breakpoint = breakpoint)
            SpanText("I'm willing to cooperate in form of freelance work for a single project or job but I'm also open to full-time job offers. Let's stay in touch! (PL or EN)", Modifier.fontSize(15.px).lineHeight(1.25))

            ContactInfo(breakpoint = breakpoint)
            ContactLinks(Modifier.color(colorMode.toColorPalette().font), colorMode, breakpoint)
        }
    }
}

