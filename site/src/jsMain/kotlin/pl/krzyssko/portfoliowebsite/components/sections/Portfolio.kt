package pl.krzyssko.portfoliowebsite.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.WhiteSpace
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
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import pl.krzyssko.portfoliowebsite.components.widgets.ArrowLinkIcon
import pl.krzyssko.portfoliowebsite.components.widgets.ExternalLinkButton
import pl.krzyssko.portfoliowebsite.style.HeadlineTextStyle
import pl.krzyssko.portfoliowebsite.style.Style
import pl.krzyssko.portfoliowebsite.style.toColorPalette

val PortfolioStyle = CssStyle {
    val palette = ColorMode.DARK.toColorPalette()
    base { Modifier.fillMaxWidth().padding(1.cssRem).background(palette.backgroundPrimary).color(palette.font) }
}

val PortfolioContentStyle = CssStyle {
    base { Modifier.fillMaxWidth() }
    Breakpoint.MD { Modifier.maxWidth(Style.Dimens.MAX_PAGE_WIDTH.px) }
}

@Composable
private fun Title(modifier: Modifier = Modifier) {
    Div(HeadlineTextStyle.toModifier().then(modifier).id("portfolio").toAttrs()) {
       SpanText("> portfolio", modifier.whiteSpace(WhiteSpace.NoWrap))
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

@Composable
fun PortfolioSection(modifier: Modifier = Modifier, breakpoint: Breakpoint, title: String, subtitle: String) {
    Box(modifier.fillMaxWidth()) {
        if (breakpoint >= Breakpoint.MD) {
            Row(modifier.fillMaxSize().gap(22.px), verticalAlignment = Alignment.CenterVertically) {
                SpanText(subtitle, Modifier.fontSize(15.px))
            }

            Box(modifier.fillMaxWidth().scaleX(140.percent)) {
                val scale = 100.toFloat() / 140
                val translate = ((1f - scale) * 100 / 2).percent
                SpanText(title, Modifier.fontSize(22.px).lineHeight(1.5).scaleX(scale).translateX(-translate))
            }
        } else {
            Column {
                SpanText(title, Modifier.fontSize(22.px).lineHeight(1.5))
                SpanText(subtitle, Modifier.fontSize(15.px))
            }
        }
    }
}

val PortfolioRowStyle = CssStyle {
    base { Modifier.fillMaxSize().padding(1.cssRem).zIndex(1).gap(60.px).flexWrap(FlexWrap.Wrap) }
    Breakpoint.MD { Modifier.padding(topBottom = 2.cssRem).flexWrap(FlexWrap.Nowrap)}
}

val PortfolioColumnStyle = CssStyle {
    base { Modifier.fillMaxWidth().zIndex(2) }
    Breakpoint.MD { Modifier.fillMaxSize() }
}

@Composable
fun PortfolioCard(modifier: Modifier = Modifier, colorMode: ColorMode, breakpoint: Breakpoint, title: String, content: String, icon: String? = null, link: String, stack: Map<String, Int>) {
    val palette = colorMode.toColorPalette()
    Box(modifier.fillMaxWidth()) {
        Surface(
            Modifier.fillMaxSize().zIndex(0).background(Color.argb(0.1f, 0xFF, 0xFF, 0xFF))
                .thenIf(breakpoint >= Breakpoint.MD, Modifier.scaleX(140.percent))
        ) { }
        Row(PortfolioRowStyle.toModifier(), horizontalArrangement = if (breakpoint < Breakpoint.MD) Arrangement.Center else Arrangement.Start) {
            Column(PortfolioColumnStyle.toModifier(), verticalArrangement = if (breakpoint >= Breakpoint.MD) Arrangement.SpaceBetween else Arrangement.Top) {
                Column(modifier.fillMaxWidth()) {
                    ExternalLinkButton(Modifier.padding(0.px).then(modifier), link) {
                        Row {
                            SpanText(title, modifier.fontSize(22.px))
                            ArrowLinkIcon(modifier.size(24.px).margin(left = 0.5.em).align(Alignment.CenterVertically))
                        }
                    }
                    Box(modifier.padding(top = 1.cssRem)) {
                        SpanText(
                            content,
                            Modifier.fontSize(12.px).lineHeight(1.25).whiteSpace(
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
                            //val color = rgb(r = (rgb shr 16) and 0xFF, g = (rgb shr 8) and 0xFF, b = rgb and 0xFF)
                            Surface(Modifier.weight(tech.value.toFloat() / sum).background(Color.rgb(rgb)).height(10.px)) {  }
                        }
                    }
                    StackLegend(modifier.color(ColorMode.LIGHT.toColorPalette().font), stack.keys.toList())
                }
            }
            Box(modifier.maxWidth(180.px).maxHeight(320.px).zIndex(2), contentAlignment = Alignment.Center) {
                if (icon != null) {
                    Image(
                        modifier = modifier
                            .fillMaxWidth()
                            .objectFit(ObjectFit.Contain),
                        src = icon
                    )
                } else {
                    Box(contentAlignment = Alignment.Center) {
                        Surface(Modifier.width(180.px).zIndex(4).color(Colors.DarkRed).background(Colors.Transparent).borderRadius(16.px).padding(0.5.em).border(width = 2.px, style = LineStyle.Dashed, Colors.DarkRed).rotate(1.rad)) {
                            SpanText("In progress...", Modifier.fontSize(18.px).align(Alignment.Center))
                        }
                        Surface(Modifier.size(width = 180.px, height = 320.px).zIndex(3).background(Color.rgb(0xEBE8E8))) { }
                    }
                }
                Surface(Modifier.fillMaxSize().zIndex(3).background(palette.backgroundPrimary.toRgb().copyf(alpha = 0.47f))) { }
            }
        }
    }
}

@Composable
fun PortfolioLayout(modifier: Modifier = Modifier, content: @Composable (colorMode: ColorMode) -> Unit) {
    val breakpoint = rememberBreakpoint()
    if (breakpoint >= Breakpoint.MD) {
        Box(PortfolioContentStyle.toModifier().fillMaxSize().gridTemplateRows { size(minContent); size(1.fr) }
            .gridTemplateColumns { size(minContent); size(1.fr) }) {
            Row(modifier.gridRow(1).gridColumn(1).padding(right = 60.px)) {
                Title(modifier.padding(top = 40.px))
            }
            Column(Modifier.fillMaxSize().gridRow(2).gridColumn(2).gap(36.px)) {
                content(ColorMode.DARK)
            }
        }
    } else {
        Column(PortfolioContentStyle.toModifier().fillMaxSize()) {
            Title(modifier.padding(top = 40.px))
            content(ColorMode.DARK)
        }
    }
}

@Composable
fun Portfolio() {
    Box(PortfolioStyle.toModifier(), contentAlignment = Alignment.TopCenter) {
        val colorMode = ColorMode.DARK
        val palette = colorMode.toColorPalette()
        val breakpoint = rememberBreakpoint()
        PortfolioLayout {
            Div {
                SpanText("Reference projects I've worked on. The last, 4th application is the Portfolio Browser which is a reference demo app for any professional portfolio. Two birds with one stone!")
            }

            PortfolioCard(
                Modifier.color(palette.font), colorMode,
                breakpoint,
                title = "McDonald's DE",
                link = "https://www.mcdonalds.com/de/de-de/mymcdonalds.html",
                content = "Android version of a B2C app for a leading global fast food company in the German market (7M+ users). The business goal was to reinforce user interaction and retention through time constrained marketing campaigns and global initiatives to boost sales.\n" +
                        "\n" +
                        "Worked on a daily basis with the customer and UI team to scope and plan the development.  My role as part of a complex environment was to lead the international team of developers essentially being responsible for the app’s technical side.\n" +
                        "\n" +
                        "App leveraged specific architecture to deliver frequent updates to keep strict customer deadlines. Introduced new technologies to the legacy code base i.e. modularization, dependency injection, Kotlin language, unit tests. All keeping the application highly available and meeting strict quality requirements.\n",
                icon = "mcd.webp",
                stack = mapOf("Java" to 80, "Kotlin" to 20)
            )
            PortfolioCard(
                Modifier.color(palette.font), colorMode,
                breakpoint,
                title = "Inverto SatPal™",
                link = "https://www.inverto.tv/what-is-satpal",
                content = "Application for controlling the core device in order to allow installers to correctly install and configure satellite installation. It used BLE connectivity, embedded Android features like camera, localization, multi-language support. As the result the app can export reports in standard text formats: PDF, XML, JSON. It is a multi-flavor app to support external OEM sales. As a side features it includes also: OTA updates, transponders DB updates, live QAM and FFT graph.\n",
                icon = "satpal.webp",
                stack = mapOf("Java" to 80, "C++" to 20)
            )
            PortfolioCard(
                Modifier.color(palette.font), colorMode,
                breakpoint,
                title = "Mersive Smart",
                link = "https://www.mersive.com/product/mersive-smart/",
                content = "Android TV app for remote sharing content to a Smart TV device like TV or stick. Uses WebRTC to video and audio transmission. Aimed for enterprise and education.\n",
                icon = "mersive.webp",
                stack = mapOf("TypeScript" to 100)
            )
            PortfolioSection(Modifier.color(palette.font), breakpoint, "> portfolio app", "Demo app presenting portfolio - coming soon!")
            PortfolioCard(
                Modifier.color(palette.font), colorMode,
                breakpoint,
                title = "Portfolio Browser",
                link = "https://github.com/k-skor/portfolio-browser",
                content = "Demo app presenting a projects portfolio.",
                stack = mapOf("Kotlin" to 55, "Java" to 25, "TypeScript" to 20, "C++" to 10)
            )
        }
    }
    //Box(PortfolioStyle.toModifier(), contentAlignment = Alignment.TopCenter) {
    //    Box(PortfolioContentStyle.toModifier().fillMaxSize().gridTemplateRows { size(minContent); size(1.fr) }.gridTemplateColumns { size(minContent); size(1.fr) }) {
    //        Row(Modifier.gridRow(1).gridColumn(1).padding(right = 60.px)) {
    //            Title()
    //        }
    //        Column(Modifier.fillMaxSize().gridRow(2).gridColumn(2).gap(36.px)) {
    //            Div {
    //                SpanText("opis, że te projekty to projekty referencyjne.  Ostatnia - czwarta - aplikacja to zaprojektowane przeze mnie Portfolio Browser, w którym znajdzie się dłuższa lista wykonanych przeze mnie projektów ")
    //            }

    //            PortfolioCard(
    //                Modifier.color(palette.tint), colorMode,
    //                title = "McDonald's DE",
    //                link = "https://www.mcdonalds.com/de/de-de/mymcdonalds.html",
    //                content = "Android version of a B2C app for a leading global fast food company in German market (7M+ users). The app's business goal is to reinforce user interaction and retention through time constrained in-app marketing campaigns to boost sales as well as to promote global initiatives.\nWorked in a complex environment leading the international team. Leveraged the app's specific architecture to constantly keep strict customer deadlines and deliver frequent updates. Introduced new technologies to the legacy code base i.e. modularization, dependency injection, Kotlin language, unit tests. All keeping the application highly available and meeting strict quality requirements. Worked with the customer and UI team on daily basis to scope and plan the development.",
    //                icon = "mcd.webp",
    //                stack = mapOf("Java" to 80, "Kotlin" to 20)
    //            )
    //            PortfolioCard(
    //                Modifier.color(palette.tint), colorMode,
    //                title = "Inverto SatPal™",
    //                link = "https://www.inverto.tv/what-is-satpal",
    //                content = "Application for controlling the core device in order to allow installers to correctly install and configure satellite installation. Used BLE connectivity, embedded Android features like camera, localization, multi-language support. As the result the app can export reports in standard text formats: PDF, XML, JSON. It is a multi-flavor app to support external OEM sales. As a side features it includes also: OTA updates, transponders DB updates, live QAM and FFT graph.\n",
    //                icon = "satpal.webp",
    //                stack = mapOf("Java" to 80, "C++" to 20)
    //            )
    //            PortfolioCard(
    //                Modifier.color(palette.tint), colorMode,
    //                title = "Mersive Smart",
    //                link = "https://www.mersive.com/product/mersive-smart/",
    //                content = "Android TV app for remote sharing content to a Smart TV device like TV or stick. Uses WebRTC to video and audio transmission. Aimed for enterprise and education.\n",
    //                icon = "mersive.webp",
    //                stack = mapOf("TypeScript" to 100)
    //            )
    //            PortfolioSection(Modifier.color(palette.tint), colorMode, "> portfolio app", "aplikacja zaprojektowana specjalnie, żeby pokazać całe portfolio")
    //            PortfolioCard(
    //                Modifier.color(palette.tint), colorMode,
    //                title = "Portfolio Browser",
    //                link = "https://github.com/k-skor/portfolio-browser",
    //                content = "Demo app presenting a projects portfolio.\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tristique dapibus porta. Quisque pretium pellentesque molestie. Proin vel enim a neque posuere mattis. Quisque aliquam tortor non augue faucibus, vitae lacinia lectus auctor. Praesent tortor erat, vehicula id tincidunt non, mollis non enim. Phasellus tincidunt nisi massa, id pulvinar ex accumsan in. Curabitur ligula tellus, maximus sed magna id, ullamcorper venenatis sapien. Sed quis porttitor velit, nec euismod neque. Curabitur ornare accumsan arcu, sed pharetra leo fringilla a. Quisque commodo nisi vel fringilla aliquam. Mauris nec ante id erat ultrices elementum. Morbi arcu est, consequat eget turpis at, fringilla porttitor neque. Vestibulum a eros lacinia, aliquam orci nec, posuere elit. Phasellus hendrerit nisi at euismod volutpat.",
    //                stack = mapOf("Kotlin" to 55, "Java" to 25, "TypeScript" to 20, "C++" to 10)
    //            )
    //        }
    //    }
    //}
}

