package pl.krzyssko.portfoliowebsite.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.css.functions.clamp
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.*
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import pl.krzyssko.portfoliowebsite.components.widgets.ArrowLinkIcon
import pl.krzyssko.portfoliowebsite.components.widgets.ExternalLinkButton
import pl.krzyssko.portfoliowebsite.components.widgets.MovingSectionTitle
import pl.krzyssko.portfoliowebsite.localized
import pl.krzyssko.portfoliowebsite.style.*

val PortfolioStyle = CssStyle {
    val palette = ColorMode.DARK.toColorPalette()
    base { Modifier.fillMaxWidth().padding(1.cssRem).background(palette.backgroundPrimary).color(palette.font) }
}

val PortfolioContentStyle = CssStyle {
    base { Modifier.padding(1.cssRem).gap(2.cssRem) }
    Breakpoint.MD { Modifier.maxWidth(Style.Dimens.MAX_PAGE_WIDTH.px).padding(topBottom = 3.cssRem) }
}

@Composable
private fun Title(modifier: Modifier = Modifier, breakpoint: Breakpoint) {
    Div(HeadlineTextStyle.toModifier().then(modifier).toAttrs()) {
        MovingSectionTitle(modifier, breakpoint, ">", "Portfolio is my advantage".localized())
    }
}

val legend = mapOf("Kotlin" to 0x88C273, "Java" to 0xD4BDAC, "TypeScript" to 0x7FC7D9, "C++" to 0xB4B4B8)

@Composable
fun StackLegend(modifier: Modifier = Modifier, stack: List<String>) {
    Row(modifier.gap(20.px)) {
        for (tech in stack) {
            val rgb = legend[tech] ?: continue
            val color = rgb(r = (rgb shr 16) and 0xFF, g = (rgb shr 8) and 0xFF, b = rgb and 0xFF)
            Box(modifier.background(color).borderRadius(4.px).padding(leftRight = 10.px, topBottom = 2.px)) {
                SpanText(tech, modifier.lineHeight(1.25).fontSize(15.px))
            }
        }
    }
}

val PortfolioRowStyle = CssStyle {
    base { Modifier.fillMaxSize().padding(1.cssRem).borderRadius(4.px).zIndex(1).flexWrap(FlexWrap.Wrap).background(Color.argb(0.1f, 0xFF, 0xFF, 0xFF)) }
    Breakpoint.MD { Modifier.padding(leftRight = 4.cssRem, topBottom = 2.cssRem).flexWrap(FlexWrap.Nowrap)}
}

val PortfolioColumnStyle = CssStyle {
    base { Modifier.fillMaxWidth().padding(right = 3.cssRem).zIndex(2) }
    Breakpoint.MD { Modifier.fillMaxSize() }
}

val PortfolioCardContentStyle = CssStyle {
    cssRule(":hover .portfolio-image-mask") {
        Modifier.background(Colors.Transparent)
    }
    cssRule(":hover .transparent-image") {
        Modifier.background(Colors.Transparent)
    }
}

val PortfolioCardBackgroundStyle = CssStyle {
    hover { Modifier.background(Color.argb(0.22f, 0xFF, 0xFF, 0xFF)) }
}

val PortfolioImageMaskStyle = CssStyle.base {
    Modifier.fillMaxSize().zIndex(3).background(colorMode.toColorPalette().backgroundPrimary.toRgb().copyf(alpha = 0.50f))
}

val TransparentImageStyle = CssStyle {
    base {
        Modifier
            .width(180.px)
            .borderRadius(Style.Dimens.BORDER_RADIUS.px)
            .objectFit(ObjectFit.Contain)
    }
    Breakpoint.MD {
        Modifier.background(Color.argb(0.1f, 0xFF, 0xFF, 0xFF))
    }
}

val PortfolioImageBox = CssStyle {
    base { Modifier.minWidth(180.px).padding(top = 1.cssRem) }
    Breakpoint.MD { Modifier.padding(0.px) }
}

@Composable
fun PortfolioCard(modifier: Modifier = Modifier, breakpoint: Breakpoint, title: String, content: String, icon: String? = null, link: String, stack: Map<String, Int>) {
    Box(modifier.fillMaxWidth()) {
        Row(PortfolioRowStyle.toModifier().thenIf(breakpoint >= Breakpoint.MD, PortfolioCardContentStyle.toModifier().then(
            PortfolioCardBackgroundStyle.toModifier())), horizontalArrangement = if (breakpoint < Breakpoint.MD) Arrangement.Center else Arrangement.Start) {
            Column(PortfolioColumnStyle.toModifier(), verticalArrangement = if (breakpoint >= Breakpoint.MD) Arrangement.SpaceBetween else Arrangement.Top) {
                Column(modifier.fillMaxWidth()) {
                    ExternalLinkButton(modifier, link) {
                        Row {
                            SpanText(title, modifier.fontSize(22.px))
                            ArrowLinkIcon(modifier.size(24.px).margin(left = 0.5.em).align(Alignment.CenterVertically))
                        }
                    }
                    Box(modifier.padding(top = 1.cssRem)) {
                        SpanText(
                            content,
                            Modifier.fontSize(14.px).lineHeight(16.px).whiteSpace(
                                WhiteSpace.PreLine
                            )
                        )
                    }
                }
                Column(modifier.fillMaxWidth().gap(20.px).thenIf(breakpoint < Breakpoint.MD, Modifier.margin(top = 20.px)), verticalArrangement = Arrangement.Bottom) {
                    Row(modifier.fillMaxWidth()) {
                        val sum =
                            stack.map { it.value }.reduce { sum, lines -> sum + lines }
                        for (tech in stack) {
                            val rgb = legend[tech.key] ?: continue
                            Surface(Modifier.weight(tech.value.toFloat() / sum).background(Color.rgb(rgb)).height(10.px)) {  }
                        }
                    }
                    StackLegend(modifier.color(ColorMode.LIGHT.toColorPalette().font), stack.keys.toList())
                }
            }
            Box(PortfolioImageBox.toModifier().zIndex(2), contentAlignment = Alignment.Center) {
                if (icon != null) {
                    Image(
                        modifier = TransparentImageStyle.toModifier()
                            .width(180.px)
                            .objectFit(ObjectFit.Contain),
                        src = icon
                    )
                } else {
                    Box(contentAlignment = Alignment.Center) {
                        Surface(Modifier.width(180.px).zIndex(4).color(Colors.DarkRed).background(Colors.Transparent).borderRadius(16.px).padding(0.5.em).border(width = 2.px, style = LineStyle.Dashed, Colors.DarkRed).rotate(1.rad)) {
                            SpanText("In progress...", Modifier.fontSize(18.px).align(Alignment.Center))
                        }
                        Surface(Modifier.size(width = 180.px, height = 320.px).zIndex(3).background(Color.rgb(0x73768C))) { }
                    }
                }
                Surface(PortfolioImageMaskStyle.toModifier().displayIfAtLeast(Breakpoint.MD)) { }
            }
        }
    }
}

@Composable
fun PortfolioLayout(modifier: Modifier = Modifier, content: @Composable (colorMode: ColorMode) -> Unit) {
    Column(PortfolioContentStyle.toModifier().then(modifier)) {
        content(ColorMode.DARK)
    }
}

@Composable
fun Portfolio() {
    Box(PortfolioStyle.toModifier().then(FadeInElementStyle.toModifier()), contentAlignment = Alignment.TopCenter) {
        val colorMode = ColorMode.DARK
        val palette = colorMode.toColorPalette()
        val breakpoint = rememberBreakpoint()
        PortfolioLayout(Modifier.id("portfolio")) {
            Title(breakpoint = breakpoint)
            Box(Modifier.fontSize(15.px).lineHeight(19.px).fillMaxWidth(clamp(20.cssRem, 65.percent, 40.cssRem))) {
                SpanText("Mobile applications I've worked on. The last, 4th application is the Portfolio Browser which is a reference demo app for any professional portfolio. Two birds with one stone!".localized())
            }

            PortfolioCard(
                Modifier.color(palette.font),
                breakpoint,
                title = "Global fast food company".localized(),
                link = "https://www.google.pl/search?q=global+fast+food+company",
                content = ("Android version of a B2C app for a leading global fast food company in the German market. The business goal was to reinforce user interaction and retention through time constrained marketing campaigns and global initiatives to boost sales.\n" +
                        "Worked on a daily basis with the customer and UI team to scope and plan the development. My role as part of a complex environment was to lead the international team of developers essentially being responsible for the app’s technical side.\n" +
                        "App leveraged specific architecture to deliver frequent updates to keep strict customer deadlines. Introduced new technologies to the legacy code base i.e. modularization, Dependency Injection, Kotlin language, unit tests. All keeping the application highly available and meeting strict quality requirements.").localized(),
                icon = "/fast_food.png",
                stack = mapOf("Java" to 80, "Kotlin" to 20)
            )
            PortfolioCard(
                Modifier.color(palette.font),
                breakpoint,
                title = "Inverto SatPal™",
                link = "https://www.inverto.tv/what-is-satpal",
                content = "Application for controlling the remote device in order to allow installers to correctly install and configure satellite installation. It used BLE connectivity, embedded Android features like camera, position, internationalization. As the result the app can export reports of installation process in standard text formats: PDF, XML, JSON. It is a multi-flavor app to support external OEM sales. As a side features it includes also: OTA updates, transponders DB updates, QAM signal and FFT graph in real time.".localized(),
                icon = "/satpal.webp",
                stack = mapOf("Java" to 80, "C++" to 20)
            )
            PortfolioCard(
                Modifier.color(palette.font),
                breakpoint,
                title = "Mersive Smart",
                link = "https://www.mersive.com/product/mersive-smart/",
                content = "Android TV app for remote sharing content to a Smart TV device like TV or stick. Uses WebRTC to video and audio transmission. Aimed for enterprise and education industry.".localized(),
                icon = "/mersive.jpg",
                stack = mapOf("TypeScript" to 100)
            )
            Column(Modifier.gap(1.cssRem)) {
                MovingSectionTitle(Modifier.fontSize(22.px), breakpoint, ">", "Portfolio Browser - demo app presenting portfolio is coming soon!".localized())
            }
            PortfolioCard(
                Modifier.color(palette.font),
                breakpoint,
                title = "Portfolio Browser",
                link = "https://github.com/k-skor/portfolio-browser",
                content = "Demo app presenting a projects portfolio.".localized(),
                icon = "/placeholder.jpg",
                stack = mapOf("Kotlin" to 55, "Java" to 25, "TypeScript" to 20, "C++" to 10)
            )
        }
    }
}

