package pl.krzyssko.portfoliowebsite.style

import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.components.layout.HorizontalDividerStyle
import com.varabyte.kobweb.silk.components.navigation.LinkStyle
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.*
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.selectors.*
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import com.varabyte.kobweb.silk.theme.modifyStyleBase
import org.jetbrains.compose.web.css.*

@InitSilk
fun initSiteStyles(ctx: InitSilkContext) {
    // This site does not need scrolling itself, but this is a good demonstration for how you might enable this in your
    // own site. Note that we only enable smooth scrolling unless the user has requested reduced motion, which is
    // considered a best practice.
    ctx.stylesheet.registerStyle("html") {
        cssRule(CSSMediaQuery.MediaFeature("prefers-reduced-motion", StylePropertyValue("no-preference"))) {
            Modifier.scrollBehavior(ScrollBehavior.Smooth)
        }
    }

    ctx.stylesheet.registerStyleBase("body") {
        Modifier
            .fontFamily(
                "Space Grotesk", "Roboto", "Segoe UI", "Oxygen", "Ubuntu", "-apple-system", "BlinkMacSystemFont",
                "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", "sans-serif"
            )
            .fontWeight(400)
            .fontSize(18.px)
            .lineHeight(1.5)
    }

    // Silk dividers only extend 90% by default; we want full width dividers in our site
    ctx.theme.modifyStyleBase(HorizontalDividerStyle) {
        Modifier.fillMaxWidth()
    }

    ctx.theme.modifyStyleBase(ButtonStyle) {
        Modifier.fontFamily("Space Grotesk").fontSize(18.px)
    }
}

//val DecoratedLinkStyle = CssStyle {
//    base { Modifier.color(CSSColor.Inherit).fontWeight(FontWeight.Medium) }
//    link { Modifier.fontWeight(FontWeight.Bold) }
//    visited { Modifier.fontWeight(FontWeight.Medium) }
//}

//val regularTextModifier =
//    Modifier
//        .fontFamily("Space Grotesk")
//        .fontWeight(400)
//        .fontSize(18.px)
//        .lineHeight(1.5)
//
//val RegularTextStyle = CssStyle.base {
//    regularTextModifier
//}

val HeadlineTextStyle = CssStyle.base {
    Modifier
        .margin(0.px)
        .fontSize(3.cssRem)
        .fontWeight(FontWeight.Normal)
        .textAlign(TextAlign.Start)
        .lineHeight(1.2) //1.5x doesn't look as good on very large text
}

val SubheadlineTextStyle = CssStyle.base {
    Modifier
        .fontSize(1.cssRem)
        .textAlign(TextAlign.Start)
        .color(colorMode.toPalette().color.toRgb().copyf(alpha = 0.8f))
}

val CircleButtonVariant = ButtonStyle.addVariantBase {
    Modifier.padding(0.px).borderRadius(50.percent)
}

val StandardFontButtonVariant = ButtonStyle.addVariant {
    base {
        Modifier.fontWeight(400)
    }
}

val UncoloredButtonVariant = StandardFontButtonVariant.extendedBy {
    base {
        Modifier.background(Colors.Transparent)
    }
    hover {
        Modifier.textDecorationLine(TextDecorationLine.Underline).color(colorMode.toColorPalette().buttonFont.primary)
    }
    active {
        Modifier.textDecorationLine(TextDecorationLine.Underline).color(colorMode.toColorPalette().buttonFont.secondary)
    }
}

val ParenthesesStyle = CssStyle {
    base {
        Modifier.whiteSpace(WhiteSpace.NoWrap)
    }
    before {
        Modifier.content("{").textDecorationLine(TextDecorationLine.None)
    }
    after {
        Modifier.content("}").textDecorationLine(TextDecorationLine.None)
    }
}

val CodeBracketsStyle = CssStyle {
    before {
        Modifier.content("<").textDecorationLine(TextDecorationLine.None)
    }
    after {
        Modifier.content("/>").textDecorationLine(TextDecorationLine.None)
    }
}

val SectionArrowStyle = CssStyle {
    before {
        Modifier.content(">")
    }
    hover {
        Modifier
    }
}

//val ParenthesesSpanTextVariant = SpanTextStyle.addVariant {
//    before {
//        Modifier.content("{ ").textDecorationLine(TextDecorationLine.None)
//    }
//    after {
//        Modifier.content(" }").textDecorationLine(TextDecorationLine.None)
//    }
//}

val ColoredLinkVariant = LinkStyle.addVariant {
    base {
        Modifier.color(colorMode.toColorPalette().font)
    }
    hover {
        Modifier.color(colorMode.toColorPalette().buttonFont.primary)
    }
    active {
        Modifier.color(colorMode.toColorPalette().buttonFont.secondary)
    }
}

val DarkExternalLinkVariant = LinkStyle.addVariant {
    base { Modifier.color(CSSColor.Inherit).fontWeight(FontWeight.Medium) }
    link { Modifier.fontWeight(FontWeight.Bold) }
    visited { Modifier.fontWeight(FontWeight.Medium) }
}

val AnimatedUnderlineLinkVariant = LinkStyle.addVariant {
    base {
        Modifier.transition(Transition.of("text-decoration-color", 300.ms)).styleModifier {
            property("text-decoration", "underline ${Colors.Transparent}")
        }
    }
    hover {
        Modifier.styleModifier {
            property("text-decoration-color", CSSColor.Inherit)
        }
    }
}

val AnimatedUnderlineButtonVariant = ButtonStyle.addVariant {
    base {
        Modifier.transition(Transition.of("text-decoration-color", 300.ms)).styleModifier {
            property("text-decoration", "underline ${Colors.Transparent}")
        }
    }
    hover {
        Modifier.styleModifier {
            property("text-decoration-color", CSSColor.Inherit)
        }
    }
}

val FilledButtonVariant = StandardFontButtonVariant.extendedBy {
    val palette = colorMode.opposite.toColorPalette()
    base {
        val backgroundColor = when (colorMode) {
            ColorMode.LIGHT -> palette.backgroundPrimary
            ColorMode.DARK -> palette.backgroundSecondary
        }
        Modifier.borderRadius(Style.Dimens.BORDER_RADIUS.px).background(backgroundColor).color(palette.font)
    }
    hover {
        Modifier.background(palette.backgroundLighter)
    }
    active {
        Modifier.background(palette.backgroundDim)
    }
}

val SectionTitleParentStyle = CssStyle {
    cssRule(":hover .fading-section-arrow") {
        Modifier.opacity(0)
    }
    cssRule(":hover .moving-section-title") {
        Modifier
            .transform { translateX((-1).em) }
            .transformOrigin(TransformOrigin.Right)
    }
}

val FadingSectionArrowStyle = CssStyle.base {
    Modifier
        .display(DisplayStyle.InlineBlock)
        .width(1.em)
        .opacity(1)
        .transition(Transition.of("opacity", 0.5.s))
}

val MovingSectionTitleStyle = CssStyle.base {
    Modifier
        .display(DisplayStyle.InlineBlock)
        .transition(Transition.of("all", 0.5.s))
}

val FadeInAnim = Keyframes {
    from {
        Modifier.opacity(0)
    }

    to {
        Modifier.opacity(1)
    }
}

val FadeInColorAnim = Keyframes {
    from {
        Modifier.color(Colors.Transparent)
    }

    to {
        Modifier.color(CSSColor.Inherit)
    }
}

val FadeInElementStyle = CssStyle.base {
    Modifier.animation(
        FadeInAnim.toAnimation(
            duration = 500.ms,
            timingFunction = AnimationTimingFunction.EaseIn,
            direction = AnimationDirection.Normal,
            fillMode = AnimationFillMode.Forwards
        )
    )
}

val FadeInColorElementStyle = CssStyle.base {
    Modifier.animation(
        FadeInColorAnim.toAnimation(
            duration = 500.ms,
            timingFunction = AnimationTimingFunction.EaseIn,
            direction = AnimationDirection.Normal,
            fillMode = AnimationFillMode.Forwards
        )
    )
}
