package pl.krzyssko.portfoliowebsite.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.dom.ElementTarget
import com.varabyte.kobweb.compose.css.functions.clamp
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.icons.*
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.overlay.*
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.breakpoint.displayUntil
import com.varabyte.kobweb.silk.style.extendedByBase
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Text
import pl.krzyssko.portfoliowebsite.components.widgets.IconButton
import pl.krzyssko.portfoliowebsite.style.*

val NavHeaderStyle = CssStyle {
    base { Modifier.fillMaxWidth().padding(leftRight = 5.cssRem, topBottom = 2.cssRem).background(colorMode.toColorPalette().backgroundSecondary).color(colorMode.toColorPalette().tint) }
    //Breakpoint.MD { Modifier.fillMaxWidth(Style.Dimens.MAX_PAGE_WIDTH.px) }
}

val ButtonLinkStyle = UncoloredLinkVariant.extendedByBase {
    Modifier.color(colorMode.toColorPalette().tint)
}

@Composable
private fun NavLink(path: String, text: String, modifier: Modifier = Modifier) {
    Link(path, text, modifier, variant = UndecoratedLinkVariant.then(UncoloredLinkVariant))
}

@Composable
private fun MenuItems() {
    NavLink("/about", "portfolio")
    NavLink("/contact", "contact")
}

@Composable
private fun ColorModeButton() {
    var colorMode by ColorMode.currentState
    Button(onClick = { colorMode = colorMode.opposite }, variant = UncoloredButtonVariant, modifier = RegularTextStyle.toModifier()) {
        if (colorMode.isLight) {
            SpanText("{ darkmode }")
        } else {
            SpanText("{ lightmode }")
        }
    }
    Tooltip(
        ElementTarget.PreviousSibling,
        "Toggle color mode",
        modifier = Modifier.padding(leftRight = 4.px).fontSize(14.px)
            .background(Colors.Black.toRgb().copyf(alpha = 0.6f)),
        placement = PopupPlacement.BottomRight,
        showDelayMs = 500,
        hideDelayMs = 500,
        keepOpenStrategy = KeepPopupOpenStrategy.onHover()
    )
}

@Composable
private fun DownloadCvButton(colorMode: ColorMode) {
    Button(onClick = { }, variant = PrimaryButtonVariant, modifier = RegularTextStyle.toModifier().color(colorMode.toColorPalette().tint)) {
        Link("Krzysztof_Skórcz_-CV.pdf", openInternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB, variant = ButtonLinkStyle.then(
            UndecoratedLinkVariant
        )) {
            DownloadIcon(Modifier.color(colorMode.toColorPalette().tint))
            SpanText("download CV", Modifier.padding(left = 0.5.em).color(colorMode.toColorPalette().tint))
        }
    }
}

@Composable
private fun HamburgerButton(onClick: () -> Unit) {
    IconButton(onClick) {
        HamburgerIcon()
    }
}

@Composable
private fun CloseButton(onClick: () -> Unit) {
    IconButton(onClick) {
        CloseIcon()
    }
}

val SideMenuSlideInAnim = Keyframes {
    from {
        Modifier.translateX(100.percent)
    }

    to {
        Modifier
    }
}

// Note: When the user closes the side menu, we don't immediately stop rendering it (at which point it would disappear
// abruptly). Instead, we start animating it out and only stop rendering it when the animation is complete.
enum class SideMenuState {
    CLOSED,
    OPEN,
    CLOSING;

    fun close() = when (this) {
        CLOSED -> CLOSED
        OPEN -> CLOSING
        CLOSING -> CLOSING
    }
}

@Composable
fun NavHeader(modifier: Modifier = Modifier, colorMode: ColorMode) {
    val palette = colorMode.toColorPalette()
    Row(NavHeaderStyle.toModifier().then(modifier), verticalAlignment = Alignment.CenterVertically) {
        NavLink("/", "</ krzysztof skórcz >", Modifier.fontWeight(400).color(palette.backgroundPrimary))

        Spacer()

        Row(Modifier.gap(40.px).displayIfAtLeast(Breakpoint.MD), verticalAlignment = Alignment.CenterVertically) {
            ColorModeButton()
            MenuItems()
            DownloadCvButton(ColorMode.DARK)
        }

        Row(
            Modifier
                .fontSize(1.5.cssRem)
                .gap(1.cssRem)
                .displayUntil(Breakpoint.MD),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var menuState by remember { mutableStateOf(SideMenuState.CLOSED) }

            ColorModeButton()
            HamburgerButton(onClick =  { menuState = SideMenuState.OPEN })

            if (menuState != SideMenuState.CLOSED) {
                SideMenu(
                    menuState,
                    close = { menuState = menuState.close() },
                    onAnimationEnd = { if (menuState == SideMenuState.CLOSING) menuState = SideMenuState.CLOSED }
                )
            }
        }
    }
}

@Composable
private fun SideMenu(menuState: SideMenuState, close: () -> Unit, onAnimationEnd: () -> Unit) {
    Overlay(
        Modifier
            .setVariable(OverlayVars.BackgroundColor, Colors.Transparent)
            .onClick { close() }
    ) {
        key(menuState) { // Force recompute animation parameters when close button is clicked
            Column(
                Modifier
                    .fillMaxHeight()
                    .width(clamp(8.cssRem, 33.percent, 10.cssRem))
                    .align(Alignment.CenterEnd)
                    // Close button will appear roughly over the hamburger button, so the user can close
                    // things without moving their finger / cursor much.
                    .padding(top = 1.cssRem, leftRight = 1.cssRem)
                    .gap(1.5.cssRem)
                    .backgroundColor(ColorMode.current.toColorPalette().backgroundDim)
                    .animation(
                        SideMenuSlideInAnim.toAnimation(
                            duration = 200.ms,
                            timingFunction = if (menuState == SideMenuState.OPEN) AnimationTimingFunction.EaseOut else AnimationTimingFunction.EaseIn,
                            direction = if (menuState == SideMenuState.OPEN) AnimationDirection.Normal else AnimationDirection.Reverse,
                            fillMode = AnimationFillMode.Forwards
                        )
                    )
                    .borderRadius(topLeft = 2.cssRem)
                    .onClick { it.stopPropagation() }
                    .onAnimationEnd { onAnimationEnd() },
                horizontalAlignment = Alignment.End
            ) {
                CloseButton(onClick = { close() })
                Column(Modifier.padding(right = 0.75.cssRem).gap(1.5.cssRem).fontSize(1.4.cssRem), horizontalAlignment = Alignment.End) {
                    MenuItems()
                    DownloadCvButton(ColorMode.DARK)
                }
            }
        }
    }
}
