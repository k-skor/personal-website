package pl.krzyssko.portfoliowebsite.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.css.TextDecorationLine.Companion.Underline
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.*
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import pl.krzyssko.portfoliowebsite.components.widgets.*
import pl.krzyssko.portfoliowebsite.style.HeadlineTextStyle
import pl.krzyssko.portfoliowebsite.style.RegularTextStyle
import pl.krzyssko.portfoliowebsite.style.Style
import pl.krzyssko.portfoliowebsite.style.toColorPalette

val PortfolioStyle = CssStyle {
    val palette = ColorMode.DARK.toColorPalette()
    base { Modifier.fillMaxWidth().padding(1.cssRem).background(palette.backgroundPrimary).color(palette.tint) }
}

val PortfolioContentStyle = CssStyle {
    base { Modifier.fillMaxWidth() }
    Breakpoint.MD { Modifier.maxWidth(Style.Dimens.MAX_PAGE_WIDTH.px) }
}

@Composable
private fun Title() {
    Div(HeadlineTextStyle.toAttrs()) {
       SpanText("> portfolio", Modifier.whiteSpace(WhiteSpace.NoWrap))
    }
}

@Composable
fun PortfolioCard(modifier: Modifier = Modifier, colorMode: ColorMode, title: String, content: String, icon: String, link: String) {
    val palette = colorMode.toColorPalette()
    Box {
        Surface(Modifier.fillMaxSize().background(Color.argb(0.1f, 0xFF, 0xFF, 0xFF)).scaleX(130.percent)) { }
        Row(modifier.padding(topBottom = 2.cssRem)) {
            Column(modifier.weight(3)) {
                ExternalLinkButton(RegularTextStyle.toModifier().padding(0.px).then(modifier), link) {
                    Row {
                        SpanText(title, modifier.fontSize(22.px))
                        ArrowLinkIcon(modifier.size(24.px).margin(left = 0.5.em))
                    }
                }
                SpanText(
                    content,
                    Modifier.fontSize(12.px).lineHeight(1.25).whiteSpace(
                        WhiteSpace.PreLine
                    ).padding(top = 1.cssRem)
                )
            }
            Box(modifier.weight(1)) {
                Image(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(left = 60.px)
                        .objectFit(ObjectFit.Contain),
                    src = icon
                )
                Surface(Modifier.fillMaxSize().padding(left = 60.px).background(palette.backgroundPrimary.toRgb().copyf(alpha = 0.47f))) { }
            }
        }
    }
}

@Composable
fun Portfolio() {
    val colorMode = ColorMode.DARK
    val palette = colorMode.toColorPalette()
    Box(PortfolioStyle.toModifier(), contentAlignment = Alignment.TopCenter) {
        Box(PortfolioContentStyle.toModifier().fillMaxSize().gridTemplateRows { size(minContent); size(1.fr) }.gridTemplateColumns { size(minContent); size(1.fr) }) {
            Row(Modifier.gridRow(1).gridColumn(1).padding(right = 60.px)) {
                Title()
            }
            Column(Modifier.fillMaxSize().gridRow(2).gridColumn(2).gap(22.px)) {
                Div {
                    SpanText("opis, że te projekty to projekty referencyjne.  Ostatnia - czwarta - aplikacja to zaprojektowane przeze mnie Portfolio Browser, w którym znajdzie się dłuższa lista wykonanych przeze mnie projektów ")
                }

                PortfolioCard(
                    Modifier.color(palette.tint), colorMode,
                    title = "McDonald's DE",
                    link = "https://www.mcdonalds.com/de/de-de/mymcdonalds.html",
                    content = "Android version of a B2C app for a leading global fast food company in German market (7M+ users). The app's business goal is to reinforce user interaction and retention through time constrained in-app marketing campaigns to boost sales as well as to promote global initiatives.\nWorked in a complex environment leading the international team. Leveraged the app's specific architecture to constantly keep strict customer deadlines and deliver frequent updates. Introduced new technologies to the legacy code base i.e. modularization, dependency injection, Kotlin language, unit tests. All keeping the application highly available and meeting strict quality requirements. Worked with the customer and UI team on daily basis to scope and plan the development.",
                    icon = "mcd.webp"
                )
                PortfolioCard(
                    Modifier.color(palette.tint), colorMode,
                    title = "Inverto SatPal™",
                    link = "https://www.inverto.tv/what-is-satpal",
                    content = "Application for controlling the core device in order to allow installers to correctly install and configure satellite installation. Used BLE connectivity, embedded Android features like camera, localization, multi-language support. As the result the app can export reports in standard text formats: PDF, XML, JSON. It is a multi-flavor app to support external OEM sales. As a side features it includes also: OTA updates, transponders DB updates, live QAM and FFT graph.\n",
                    icon = "satpal.webp"
                )
                PortfolioCard(
                    Modifier.color(palette.tint), colorMode,
                    title = "Mersive Smart",
                    link = "https://www.mersive.com/product/mersive-smart/",
                    content = "Android TV app for remote sharing content to a Smart TV device like TV or stick. Uses WebRTC to video and audio transmission. Aimed for enterprise and education.\n",
                    icon = "mersive.webp"
                )
            }
        }
    }
}

