package pl.krzyssko.portfoliowebsite.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import pl.krzyssko.portfoliowebsite.components.widgets.*
import pl.krzyssko.portfoliowebsite.style.Style
import pl.krzyssko.portfoliowebsite.style.toColorPalette

val HeroStyle = CssStyle {
    base { Modifier.fillMaxWidth().padding(1.cssRem) }
    Breakpoint.MD { Modifier.fillMaxWidth(Style.Dimens.MAX_PAGE_WIDTH.px).height(Style.Dimens.MAX_HERO_HEIGHT.px) }
}

val HeroTitleStyle = CssStyle.base {
    Modifier.fontSize(54.px).fontWeight(FontWeight.ExtraBold)
}

@Composable
fun LeftSideHero(breakpoint: Breakpoint) {
    Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
        Div(HeroTitleStyle.toAttrs()) {
            SpanText(text = "Hi, I'm Krzysztof 💻")
        }
        SpanText(text = "I'm a Software Engineer experienced in many different areas of software engineering. My primary\n" +
                "but not limited to expertise are mobile applications. Worked with\n" +
                "different languages, platforms, cloud providers (mainly Azure) and technologies.\n" +
                "My secondary focus area is IoT technologies and Edge Computing. Participated in numerous projects in various roles\n" +
                "building, designing and presenting cutting-edge software as a senior or\n" +
                "leader. Constantly improving my skill set, recently expanding it on\n" +
                "Machine Learning.", modifier = Modifier.padding(top = 4.px).margin(bottom = 40.px))
        Column(Modifier.margin(bottom = 40.px)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                LocationIcon(Modifier.margin(right = 4.px))
                SpanText(text = "Poznań, Poland")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                StatusIcon(Modifier.margin(right = 4.px))
                SpanText(text = "Available for new project")
            }
        }
        Row(Modifier.fillMaxWidth().gap(16.px), horizontalArrangement = if (breakpoint <= Breakpoint.SM)
            Arrangement.Center else Arrangement.Start) {
            Link(path = "https://github.com/k-skor") {
                GitHubIcon()
            }
            Link(path = "https://www.linkedin.com/in/krzysztof-skorcz/") {
                LinkedInIcon()
            }
            Link(path = "https://www.facebook.com/krzysztof.skorcz") {
                FacebookIcon()
            }
        }
    }
}

@Composable
fun RightSideHero(colorMode: ColorMode) {
    val palette = colorMode.toColorPalette()
    Box(Modifier.padding(60.px), contentAlignment = Alignment.TopCenter) {
        Box {
            Surface(modifier = Modifier.fillMaxSize().background(palette.brand.accent).borderRadius(16.px).margin(top = 24.px, left = 24.px)) {  }
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .objectFit(ObjectFit.Contain).border(width = 8.px, style = LineStyle.Solid, color = palette.brand.primary).borderRadius(16.px),
                src = "Krzysztof_Skórcz_portret.jpg"
            )
        }
    }
}

@Composable
fun Hero() {
    val colorMode by ColorMode.currentState
    val breakpoint = rememberBreakpoint()
    SimpleGrid(numColumns(base = 1, md = 2), HeroStyle.toModifier()) {
        LeftSideHero(breakpoint)
        RightSideHero(colorMode)
    }
}

