package pl.krzyssko.portfoliowebsite.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.css.TextDecorationLine.Companion.Underline
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
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
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import pl.krzyssko.portfoliowebsite.components.widgets.*
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

@Composable
fun LeftSideTitle() {
    Div(HeadlineTextStyle.toAttrs()) {
        SpanText("Software Engineer ready for new challenges")
    }
}

@Composable
fun LeftSideHero(modifier: Modifier = Modifier, breakpoint: Breakpoint, colorMode: ColorMode) {
    Column(modifier.fillMaxSize().padding(right = 160.px), verticalArrangement = Arrangement.SpaceBetween) {
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
            Column(Modifier.gap(18.px).fillMaxWidth(60.percent)) {
                Row(Modifier.fillMaxWidth().gap(22.px)) {
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
                Row(Modifier.fillMaxWidth().gap(22.px)) {
                    Box(Modifier.fillMaxWidth(40.percent).align(Alignment.CenterVertically)) {
                        SpanText("Years of\nexperience", modifier = Modifier.fontSize(12.px).whiteSpace(WhiteSpace.PreLine))
                    }
                    SpanText("over 10 years of employment", modifier = Modifier.fillMaxWidth())
                }
                Row(Modifier.fillMaxWidth().gap(22.px)) {
                    Box(Modifier.fillMaxWidth(40.percent).align(Alignment.CenterVertically)) {
                        SpanText("Location", modifier = Modifier.fontSize(12.px))
                    }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        SpanText("Poznań, Poland")
                        SpanText("open to remote work")
                    }
                }
            }
            //Row(Modifier.fillMaxWidth(75.percent)) {
            //    Column(Modifier.weight(1).gap(18.px), verticalArrangement = Arrangement.SpaceEvenly) {
            //        SpanText("Programming\nLanguages", modifier = Modifier.fontSize(12.px).whiteSpace(WhiteSpace.PreLine))
            //        SpanText("Years of\nexperience", modifier = Modifier.fontSize(12.px).weight(1).whiteSpace(WhiteSpace.PreLine))
            //        SpanText("Location", modifier = Modifier.fontSize(12.px))
            //    }
            //    Column(Modifier.weight(3), verticalArrangement = Arrangement.SpaceEvenly) {
            //        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            //            val languages = listOf("Kotlin", "Java", "TypeScript", "C++")
            //            for (language in languages) {
            //                SpanText(language)
            //            }
            //        }
            //        SpanText("over 10 years of employment", Modifier.fillMaxWidth())
            //        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            //            SpanText("Poznań, Poland")
            //            SpanText("open to remote work")
            //        }
            //    }
            //}
            Spacer()
        }

        SocialIcons(modifier.color(colorMode.toColorPalette().tint), breakpoint, colorMode.toColorPalette().backgroundSecondary)
    }
}

@Composable
fun RightSideHero(modifier: Modifier = Modifier, colorMode: ColorMode) {
    val palette = colorMode.toColorPalette()
    Box(modifier, contentAlignment = Alignment.CenterEnd) {
        //Box {
        //    Surface(modifier = Modifier.fillMaxSize().background(palette.brand.accent).borderRadius(16.px).margin(top = 24.px, left = 24.px)) {  }
        //    Image(
        //        modifier = Modifier
        //            .fillMaxWidth()
        //            .objectFit(ObjectFit.Contain).border(width = 8.px, style = LineStyle.Solid, color = palette.brand.primary).borderRadius(16.px),
        //        src = "Krzysztof_Skórcz_portret.jpg"
        //    )
        //}
        Image(
            modifier = modifier
                .fillMaxWidth()
                .padding(left = 60.px)
                .objectFit(ObjectFit.Contain),
            src = "Krzysztof_Skórcz_portret.jpg"
        )
    }
}

@Composable
fun Hero() {
    val colorMode by ColorMode.currentState
    val breakpoint = rememberBreakpoint()
    //Column(HeroStyle.toModifier(), horizontalAlignment = Alignment.CenterHorizontally) {
    //    Row(HeroContentStyle.toModifier().fillMaxSize()) {
    //        LeftSideTitle(Modifier.weight(3))
    //        Box(Modifier.weight(1).fillMaxWidth()) {}
    //    }
    //    Row(HeroContentStyle.toModifier().fillMaxSize()) {
    //        LeftSideHero(Modifier.weight(3), breakpoint)
    //        RightSideHero(Modifier.weight(1), colorMode)
    //    }
    //}
    Box(HeroStyle.toModifier(), contentAlignment = Alignment.TopCenter) {
        Box(HeroContentStyle.toModifier().fillMaxSize().gridTemplateRows { size(1.fr); size(3.fr) }.gridTemplateColumns { size(3.fr); size(1.fr) }) {
            Box(Modifier.gridRow(1).gridColumn(1)) {
                LeftSideTitle()
            }
            //Box(Modifier.weight(1).fillMaxWidth()) {}
            LeftSideHero(Modifier.gridRow(2).gridColumn(1), breakpoint, colorMode)
            RightSideHero(Modifier.gridRow(2).gridColumn(2), colorMode)
        }
    }
}

