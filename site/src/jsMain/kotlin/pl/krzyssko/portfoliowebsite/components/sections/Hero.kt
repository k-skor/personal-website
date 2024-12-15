package pl.krzyssko.portfoliowebsite.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.TextDecorationLine.Companion.Underline
import com.varabyte.kobweb.compose.css.TransformOrigin
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
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
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import pl.krzyssko.portfoliowebsite.components.widgets.SocialIcons
import pl.krzyssko.portfoliowebsite.localized
import pl.krzyssko.portfoliowebsite.style.*
import kotlin.time.Duration.Companion.milliseconds

val HeroStyle = CssStyle {
    base { Modifier.fillMaxWidth().padding(1.cssRem).background(colorMode.toColorPalette().backgroundSecondary).zIndex(0) }
}

val HeroContentStyle = CssStyle {
    base { Modifier.padding(1.cssRem) }
    Breakpoint.MD { Modifier.minWidth(Style.Dimens.MAX_PAGE_WIDTH.px).minHeight(Style.Dimens.MAX_HERO_HEIGHT.px).padding(topBottom = 3.cssRem) }
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
        SpanText("Software Engineer ready for new challenges".localized())
    }
}

@Composable
fun LeftSideHero(modifier: Modifier = Modifier, breakpoint: Breakpoint, colorMode: ColorMode, showBottomSection: MutableState<Boolean> = mutableStateOf(true), showDescription: MutableState<Boolean> = mutableStateOf(true)) {
    Column(
        modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        //if ("ab\ncd" != "ab\ncd".localized()) {
        //    console.log("WRONG!")
        //}
        //console.log("Test 1=\n${"ab\\ncd".encodeToByteArray().joinToString("-")}")
        //console.log("Test 2=\n${"ab\\ncd".localized().encodeToByteArray().joinToString("-")}")
        //console.log("Test 1=\n${"Years of\nexperience".encodeToByteArray().joinToString()}")
        //console.log("Test 2=\n${"Years of\nexperience".localized().encodeToByteArray()}")
        Column(HeroColumnStyle.toModifier()) {
            Div(Modifier.fontSize(15.px).lineHeight(25.px).color(Colors.Transparent).thenIf(showDescription.value, FadeInColorElementStyle.toModifier()).toAttrs()) {
                SpanText("Krzysztof Skórcz is a Software Engineer experienced in many different areas of software engineering.His primary but not limited to expertise is ".localized(), Modifier.whiteSpace(WhiteSpace.PreLine))
                SpanText("mobile applications".localized(), modifier = Modifier.textDecorationLine(Underline))
                Text(". He worked with different languages, platforms, cloud providers (mainly Azure) and technologies. Krzysztof's secondary focus area is ".localized())
                SpanText("IoT and Edge Computing".localized(), modifier = Modifier.textDecorationLine(Underline))
                Text(". He participated in numerous projects in various roles building, designing and presenting cutting-edge software as a senior or leader. Constantly improving his skill set, recently expanding it on Machine Learning.".localized())
            }
            Column(Modifier.gap(18.px).fillMaxWidth().color(Colors.Transparent).thenIf(showBottomSection.value, FadeInColorElementStyle.toModifier())) {
                Row(HeroRowStyle.toModifier()) {
                    Box(Modifier.fillMaxWidth(40.percent).align(Alignment.CenterVertically)) {
                        SpanText("Programming\nLanguages".localized(), modifier = Modifier.fontSize(12.px).whiteSpace(WhiteSpace.PreLine))
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
                        SpanText("Years of\nexperience".localized(), modifier = Modifier.fontSize(12.px).whiteSpace(WhiteSpace.PreLine))
                    }
                    SpanText("over 10 years of employment".localized(), modifier = Modifier.fillMaxWidth())
                }
                Row(HeroRowStyle.toModifier()) {
                    Box(Modifier.fillMaxWidth(40.percent).align(Alignment.CenterVertically)) {
                        SpanText("Location".localized(), modifier = Modifier.fontSize(12.px))
                    }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        SpanText("Poznań, Poland".localized())
                        SpanText("open to remote work".localized())
                    }
                }
            }
        }

        SocialIcons(
            modifier.color(Colors.Transparent).thenIf(showBottomSection.value, FadeInColorElementStyle.toModifier()),
            breakpoint,
            colorMode.toColorPalette().backgroundSecondary
        )
    }
}

@Composable
fun RightSideHero(modifier: Modifier = Modifier, breakpoint: Breakpoint) {
    Box(modifier.thenIf(breakpoint >= Breakpoint.MD, MovingPictureStyle.toModifier()), contentAlignment = Alignment.CenterEnd) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .borderRadius(Style.Dimens.BORDER_RADIUS.px)
                .objectFit(ObjectFit.Contain),
            src = "/Krzysztof_Skórcz_portret.jpg"
        )
    }
}

@Composable
fun Hero() {
    val colorMode by ColorMode.currentState
    val breakpoint = rememberBreakpoint()
    val showTitle = remember { mutableStateOf(false) }
    val showPhoto = remember { mutableStateOf(false) }
    val showBottomSection = remember { mutableStateOf(false) }
    val showDescription = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        // Loading sequence (motion): title, photo, bottom section, description
        showTitle.value = true
        delay(500.milliseconds)
        showPhoto.value = true
        delay(500.milliseconds)
        showBottomSection.value = true
        delay(500.milliseconds)
        showDescription.value = true
    }
    Box(HeroStyle.toModifier().id("home"), contentAlignment = Alignment.TopCenter) {
        if (breakpoint >= Breakpoint.MD) {
            Box(HeroContentStyle.toModifier().gridTemplateRows { size(minContent); size(1.fr) }.gridTemplateColumns { size((Style.Dimens.MAX_PAGE_WIDTH*3/4).px); size(minContent) }) {
                if (showTitle.value) {
                    LeftSideTitle(Modifier.gridRow(1).gridColumn(1).then(FadeInElementStyle.toModifier()), breakpoint)
                }
                LeftSideHero(Modifier.gridRow(2).gridColumn(1), breakpoint, colorMode, showBottomSection, showDescription)
                if (showPhoto.value) {
                    RightSideHero(Modifier.gridRow(2).gridColumn(2).fillMaxWidth((Style.Dimens.MAX_PAGE_WIDTH/4).px).then(FadeInElementStyle.toModifier()), breakpoint)
                }
            }
        } else {
            Column(HeroContentStyle.toModifier()) {
                if (showTitle.value) {
                    LeftSideTitle(Modifier.padding(bottom = 1.5.cssRem).then(FadeInElementStyle.toModifier()), breakpoint)
                }
                if (showPhoto.value) {
                    RightSideHero(Modifier.padding(bottom = 1.5.cssRem).maxWidth(10.cssRem).then(FadeInElementStyle.toModifier()), breakpoint = breakpoint)
                }
                LeftSideHero(breakpoint = breakpoint, colorMode = colorMode, showBottomSection = showBottomSection, showDescription = showDescription)
            }
        }
    }
}

