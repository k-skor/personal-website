package pl.krzyssko.portfoliowebsite.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.TextDecorationLine.Companion.Underline
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import pl.krzyssko.portfoliowebsite.components.widgets.SocialIcons
import pl.krzyssko.portfoliowebsite.style.HeadlineTextStyle
import pl.krzyssko.portfoliowebsite.style.Style
import pl.krzyssko.portfoliowebsite.style.toColorPalette

val HeroStyle = CssStyle {
    base { Modifier.fillMaxWidth().padding(1.cssRem).background(colorMode.toColorPalette().backgroundSecondary) }
}

val HeroContentStyle = CssStyle {
    base { Modifier.fillMaxWidth() }
    Breakpoint.MD { Modifier.maxWidth(Style.Dimens.MAX_PAGE_WIDTH.px).minHeight(Style.Dimens.MAX_HERO_HEIGHT.px) }
}

val HeroRowStyle = CssStyle {
    base { Modifier.fillMaxWidth().gap(22.px).flexWrap(FlexWrap.Wrap) }
    Breakpoint.MD { Modifier.flexWrap(FlexWrap.Nowrap) }
}

@Composable
fun LeftSideTitle() {
    Div(HeadlineTextStyle.toAttrs()) {
        SpanText("Software Engineer ready for new challenges")
    }
}

@Composable
fun LeftSideHero(modifier: Modifier = Modifier, breakpoint: Breakpoint, colorMode: ColorMode) {
    Column(
        modifier.fillMaxSize().thenIf(breakpoint >= Breakpoint.MD, Modifier.padding(right = 160.px)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier.fillMaxSize()) {
            Row {
                Div(Modifier.fontSize(16.px).lineHeight(26.px).toAttrs()) {
                    SpanText("Krzysztof Skórcz is a Software Engineer experienced in many different areas of software engineering.\nHis primary but not limited to expertise is ", Modifier.whiteSpace(WhiteSpace.PreLine))
                    SpanText("mobile applications", modifier = Modifier.textDecorationLine(Underline))
                    Text(". He worked with different languages, platforms, cloud providers (mainly Azure) and technologies. Krzysztof's secondary focus area is ")
                    SpanText("IoT and Edge Computing", modifier = Modifier.textDecorationLine(Underline))
                    Text(". He participated in numerous projects in various roles building, designing and presenting cutting-edge software as a senior or leader. Constantly improving his skill set, recently expanding it on Machine Learning.")
                }
            }
            Spacer()
            Column(
                Modifier.gap(18.px).fillMaxWidth(if (breakpoint >= Breakpoint.MD) 60.percent else 100.percent)
                    .thenIf(breakpoint < Breakpoint.MD, Modifier.padding(top = 18.px))
            ) {
                Row(HeroRowStyle.toModifier()) {
                    Box(Modifier.fillMaxWidth(40.percent).align(Alignment.CenterVertically)) {
                        SpanText("Programming\nLanguages", modifier = Modifier.fontSize(12.px).whiteSpace(WhiteSpace.PreLine))
                    }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        val languages = listOf("Kotlin", "Java", "TypeScript", "C++")
                        for (language in languages) {
                            SpanText(language)
                        }
                    }
                }
                Row(HeroRowStyle.toModifier()) {
                    Box(Modifier.fillMaxWidth(40.percent).align(Alignment.CenterVertically)) {
                        SpanText("Years of\nexperience", modifier = Modifier.fontSize(12.px).whiteSpace(WhiteSpace.PreLine))
                    }
                    SpanText("over 10 years of employment", modifier = Modifier.fillMaxWidth())
                }
                Row(HeroRowStyle.toModifier()) {
                    Box(Modifier.fillMaxWidth(40.percent).align(Alignment.CenterVertically)) {
                        SpanText("Location", modifier = Modifier.fontSize(12.px))
                    }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        SpanText("Poznań, Poland")
                        SpanText("open to remote work")
                    }
                }
            }
            Spacer()
        }

        SocialIcons(
            modifier.color(colorMode.toColorPalette().tint).margin(bottom = 40.px)
                .thenIf(breakpoint < Breakpoint.MD, Modifier.margin(topBottom = 40.px)),
            breakpoint,
            colorMode.toColorPalette().backgroundSecondary
        )
    }
}

@Composable
fun RightSideHero(modifier: Modifier = Modifier, breakpoint: Breakpoint) {
    Box(modifier, contentAlignment = Alignment.CenterEnd) {
        Image(
            modifier = modifier
                .fillMaxWidth()
                .objectFit(ObjectFit.Contain)
                .thenIf(breakpoint >= Breakpoint.MD, Modifier.padding(left = 60.px)),
            src = "Krzysztof_Skórcz_portret.jpg"
        )
    }
}

@Composable
fun Hero() {
    val colorMode by ColorMode.currentState
    val breakpoint = rememberBreakpoint()
    Box(HeroStyle.toModifier(), contentAlignment = Alignment.TopCenter) {
        if (breakpoint >= Breakpoint.MD) {
            Box(HeroContentStyle.toModifier().fillMaxSize().gridTemplateRows { size(1.fr); size(3.fr) }.gridTemplateColumns { size(3.fr); size(1.fr) }) {
                Box(Modifier.gridRow(1).gridColumn(1)) {
                    LeftSideTitle()
                }
                LeftSideHero(Modifier.gridRow(2).gridColumn(1), breakpoint, colorMode)
                RightSideHero(Modifier.gridRow(2).gridColumn(2), breakpoint)
            }
        } else {
            Column(HeroContentStyle.toModifier().fillMaxSize()) {
                LeftSideTitle()
                LeftSideHero(breakpoint = breakpoint, colorMode = colorMode)
                RightSideHero(breakpoint = breakpoint)
            }
        }
    }
}

