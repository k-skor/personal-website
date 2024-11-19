package pl.krzyssko.portfoliowebsite.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.TextDecorationLine.Companion.Underline
import com.varabyte.kobweb.compose.css.TransformOrigin
import com.varabyte.kobweb.compose.css.Transition
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
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import pl.krzyssko.portfoliowebsite.components.widgets.SocialIcons
import pl.krzyssko.portfoliowebsite.style.*

val HeroStyle = CssStyle {
    base { Modifier.fillMaxWidth().padding(1.cssRem).background(colorMode.toColorPalette().backgroundSecondary).zIndex(0) }
}

val HeroContentStyle = CssStyle {
    base { Modifier.padding(topBottom = 3.cssRem) }
    Breakpoint.MD { Modifier.minWidth(Style.Dimens.MAX_PAGE_WIDTH.px).minHeight(Style.Dimens.MAX_HERO_HEIGHT.px) }
}

val HeroColumnStyle = CssStyle {
    base { Modifier.fillMaxSize().gap(4.cssRem).padding(bottom = 3.cssRem) }
    Breakpoint.MD { Modifier.padding(bottom = 4.cssRem, right = 7.cssRem) }
}

val HeroRowStyle = CssStyle {
    base { Modifier.fillMaxWidth().gap(22.px).flexWrap(FlexWrap.Wrap) }
    Breakpoint.MD { Modifier.flexWrap(FlexWrap.Nowrap) }
}

val MovingHeadlineStyle = CssStyle {
    base {
        Modifier.scale(1).transition(
            Transition.of("scale", 300.ms, delay = 100.ms),
            Transition.of("padding", 300.ms, delay = 100.ms)
        ).transformOrigin(TransformOrigin.TopLeft).padding(bottom = 2.cssRem)
    }
    hover {
        Modifier.scale(1.2).padding(bottom = (2 + (2 * 1.2)).cssRem)
    }
}

val MovingPictureStyle = CssStyle {
    base {
        Modifier.scale(1).transition(
            Transition.of("scale", 300.ms, delay = 100.ms),
            Transition.of("margin", 300.ms, delay = 100.ms)
        ).transformOrigin(TransformOrigin.Center)
    }
    hover {
        Modifier.scale(1.4).margin(leftRight = (32 + 32).px)
    }
}

@Composable
fun LeftSideTitle(modifier: Modifier = Modifier, breakpoint: Breakpoint) {
    Div(HeadlineTextStyle.toModifier().then(modifier).thenIf(breakpoint >= Breakpoint.MD, MovingHeadlineStyle.toModifier()).toAttrs()) {
        SpanText("Software Engineer ready for new challenges")
    }
}

@Composable
fun LeftSideHero(modifier: Modifier = Modifier, breakpoint: Breakpoint, colorMode: ColorMode) {
    Column(
        modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(HeroColumnStyle.toModifier()) {
            Row {
                Div(Modifier.fontSize(16.px).lineHeight(26.px).toAttrs()) {
                    SpanText("Krzysztof Skórcz is a Software Engineer experienced in many different areas of software engineering.\nHis primary but not limited to expertise is ", Modifier.whiteSpace(WhiteSpace.PreLine))
                    SpanText("mobile applications", modifier = Modifier.textDecorationLine(Underline))
                    Text(". He worked with different languages, platforms, cloud providers (mainly Azure) and technologies. Krzysztof's secondary focus area is ")
                    SpanText("IoT and Edge Computing", modifier = Modifier.textDecorationLine(Underline))
                    Text(". He participated in numerous projects in various roles building, designing and presenting cutting-edge software as a senior or leader. Constantly improving his skill set, recently expanding it on Machine Learning.")
                }
            }
            Column(Modifier.gap(18.px).fillMaxWidth()) {
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
        }

        SocialIcons(
            modifier.color(colorMode.toColorPalette().font),
            breakpoint,
            colorMode.toColorPalette().backgroundSecondary
        )
    }
}

@Composable
fun RightSideHero(modifier: Modifier = Modifier, breakpoint: Breakpoint) {
    Box(modifier.thenIf(breakpoint >= Breakpoint.MD, MovingPictureStyle.toModifier()), contentAlignment = Alignment.CenterEnd) {
        Image(
            modifier = modifier
                .fillMaxWidth()
                .borderRadius(Style.Dimens.BORDER_RADIUS.px)
                .objectFit(ObjectFit.Contain),
            src = "Krzysztof_Skórcz_portret.jpg"
        )
    }
}

@Composable
fun Hero() {
    val colorMode by ColorMode.currentState
    val breakpoint = rememberBreakpoint()
    Box(HeroStyle.toModifier().id("home"), contentAlignment = Alignment.TopCenter) {
        if (breakpoint >= Breakpoint.MD) {
            Box(HeroContentStyle.toModifier().gridTemplateRows { size(minContent); size(1.fr) }.gridTemplateColumns { size((Style.Dimens.MAX_PAGE_WIDTH*3/4).px); size(minContent) }) {
                LeftSideTitle(Modifier.gridRow(1).gridColumn(1), breakpoint)
                LeftSideHero(Modifier.gridRow(2).gridColumn(1), breakpoint, colorMode)
                RightSideHero(Modifier.gridRow(2).gridColumn(2).fillMaxWidth((Style.Dimens.MAX_PAGE_WIDTH/4).px), breakpoint)
            }
        } else {
            Column(HeroContentStyle.toModifier()) {
                LeftSideTitle(Modifier.padding(bottom = 2.cssRem), breakpoint)
                RightSideHero(Modifier.maxWidth(10.cssRem), breakpoint = breakpoint)
                LeftSideHero(breakpoint = breakpoint, colorMode = colorMode)
            }
        }
    }
}

