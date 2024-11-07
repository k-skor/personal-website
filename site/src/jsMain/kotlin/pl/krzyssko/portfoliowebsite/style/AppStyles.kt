package pl.krzyssko.portfoliowebsite.style

import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import com.varabyte.kobweb.silk.components.layout.HorizontalDividerStyle
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.style.*
import com.varabyte.kobweb.silk.style.selectors.active
import com.varabyte.kobweb.silk.style.selectors.hover
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
        //Modifier
        //    .fontFamily(
        //        "Space Grotesk", "Roboto", "Segoe UI", "Oxygen", "Ubuntu", "-apple-system", "BlinkMacSystemFont",
        //        "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", "sans-serif"
        //    )
        //    .fontWeight(400)
        //    .fontSize(18.px)
        //    .lineHeight(1.5)
        regularTextModifier
    }

    // Silk dividers only extend 90% by default; we want full width dividers in our site
    ctx.theme.modifyStyleBase(HorizontalDividerStyle) {
        Modifier.fillMaxWidth()
    }
}

val regularTextModifier =
    Modifier
        .fontFamily("Space Grotesk")
        .fontWeight(400)
        .fontSize(18.px)
        .lineHeight(1.5)

val RegularTextStyle = CssStyle.base {
    regularTextModifier
}

val HeadlineTextStyle = CssStyle.base {
    Modifier
        .fontSize(3.cssRem)
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

val UncoloredButtonVariant = ButtonStyle.addVariantBase {
    //Modifier.setVariable(ButtonVars.BackgroundDefaultColor, Colors.Transparent)
    Modifier.background(Colors.Transparent)
}

val PrimaryButtonVariant = ButtonStyle.addVariant {
    val palette = colorMode.toColorPalette()
    base {
        Modifier.borderRadius(4.px).background(palette.backgroundPrimary).color(palette.tint)
    }
    hover {
        Modifier.background(palette.backgroundDim)
    }
    active {
        Modifier.background(palette.backgroundDim)
    }
}
