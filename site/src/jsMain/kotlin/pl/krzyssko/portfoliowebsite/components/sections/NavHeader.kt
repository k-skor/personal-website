package pl.krzyssko.portfoliowebsite.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.dom.ElementTarget
import com.varabyte.kobweb.compose.css.VerticalAlign
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.css.functions.clamp
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.icons.DownloadIcon
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.LinkKind
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.overlay.*
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.*
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.breakpoint.displayUntil
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import pl.krzyssko.portfoliowebsite.Language
import pl.krzyssko.portfoliowebsite.Locale
import pl.krzyssko.portfoliowebsite.components.widgets.CloseIcon
import pl.krzyssko.portfoliowebsite.components.widgets.HamburgerIcon
import pl.krzyssko.portfoliowebsite.components.widgets.IconButton
import pl.krzyssko.portfoliowebsite.localized
import pl.krzyssko.portfoliowebsite.style.*

val NavHeaderStyle = CssStyle {
    base { Modifier.fillMaxWidth().padding(1.cssRem).background(colorMode.toColorPalette().backgroundSecondary).color(colorMode.toColorPalette().font) }
    Breakpoint.MD { Modifier.padding(leftRight = 5.cssRem, topBottom = 1.5.cssRem) }
}

val HomeLinkVariant = ColoredLinkVariant.extendedByBase {
    var fontColor = colorMode.toColorPalette().font
    if (colorMode == ColorMode.LIGHT) {
        fontColor = colorMode.toColorPalette().backgroundPrimary
    }
    Modifier.color(fontColor)
}

val AlwaysLightParenthesesStyle = UncoloredButtonVariant.extendedByBase {
    Modifier.color(ColorMode.LIGHT.toColorPalette().font)
}

@Composable
private fun NavLink(path: String, text: String, modifier: Modifier = Modifier, variant: CssStyleVariant<LinkKind>? = null) {
    val variants = ColoredLinkVariant.then(AnimatedUnderlineLinkVariant)
    Link(path, text, modifier, variant = variant?.let {
        variants.then(it)
    } ?: variants)
}

@Composable
private fun MenuItems(modifier: Modifier = Modifier) {
    NavLink("#portfolio", "portfolio".localized(), modifier)
    NavLink("#contact", "contact".localized(), modifier)
}

@Composable
private fun LanguageButton(modifier: Modifier = Modifier) {
    val language by remember { mutableStateOf(Locale.initialLanguage) }
    val link = when (language) {
        Language.PL -> "/en/"
        Language.EN -> "/pl/"
    }
    val label = (when (language) {
        Language.PL -> Language.EN
        Language.EN -> Language.PL
    }).toString().lowercase()
    Div(ParenthesesStyle.toAttrs()) {
        NavLink(link, label, modifier.padding(leftRight = 0.5.cssRem))
    }
    Tooltip(
        ElementTarget.PreviousSibling,
        "Switch to PL or EN".localized(),
        modifier = Modifier.padding(leftRight = 4.px).fontSize(14.px).zIndex(3),
        placement = PopupPlacement.BottomRight,
        showDelayMs = 500,
        hideDelayMs = 500,
        keepOpenStrategy = KeepPopupOpenStrategy.onHover()
    )
}

@Composable
private fun ColorModeButton(modifier: Modifier = Modifier) {
    var colorMode by ColorMode.currentState
    Div(ParenthesesStyle.toAttrs()) {
        Button(onClick = { colorMode = colorMode.opposite }, variant = UncoloredButtonVariant.then(
            AnimatedUnderlineButtonVariant), modifier = modifier.verticalAlign(
            VerticalAlign.Baseline).padding(leftRight = 0.5.cssRem)) {
            if (colorMode.isLight) {
                SpanText("darkmode")
            } else {
                SpanText("lightmode")
            }
        }
    }
    Tooltip(
        ElementTarget.PreviousSibling,
        "Toggle color mode".localized(),
        modifier = Modifier.padding(leftRight = 4.px).fontSize(14.px).zIndex(3),
        placement = PopupPlacement.BottomRight,
        showDelayMs = 500,
        hideDelayMs = 500,
        keepOpenStrategy = KeepPopupOpenStrategy.onHover()
    )
}

@Composable
private fun HomeButton(modifier: Modifier = Modifier) {
    Span(attrs = CodeBracketsStyle.toModifier().then(modifier).toAttrs()) {
        NavLink("#home", "krzysztof skórcz", modifier.padding(leftRight = 0.5.em).whiteSpace(WhiteSpace.NoWrap))
    }
}

@Composable
private fun DownloadCvButton(modifier: Modifier = Modifier, colorMode: ColorMode) {
    Button(onClick = { }, variant = FilledButtonVariant) {
        Link("/Krzysztof_Skórcz_-CV.pdf", openInternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB, variant = UndecoratedLinkVariant) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val buttonModifier = modifier.color(colorMode.toOppositePalette().font)
                DownloadIcon(buttonModifier)
                SpanText("download CV".localized(), buttonModifier.padding(left = 0.5.em))
            }
        }
    }
}

@Composable
private fun HamburgerButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(onClick) {
        HamburgerIcon(modifier)
    }
}

@Composable
private fun CloseButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(onClick) {
        CloseIcon(modifier)
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
    Row(NavHeaderStyle.toModifier().then(modifier), verticalAlignment = Alignment.CenterVertically) {
        HomeButton(Modifier.fontWeight(400).color(colorMode.toColorPalette().brand.primary))

        Spacer()

        Row(Modifier.gap(40.px).displayIfAtLeast(Breakpoint.MD), verticalAlignment = Alignment.CenterVertically) {
            ColorModeButton()
            MenuItems()
            LanguageButton()
            DownloadCvButton(colorMode = colorMode)
        }

        Row(
            Modifier
                .fontSize(1.5.cssRem)
                .gap(1.cssRem)
                .displayUntil(Breakpoint.MD),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var menuState by remember { mutableStateOf(SideMenuState.CLOSED) }

            HamburgerButton(Modifier.color(colorMode.toColorPalette().brand.primary), onClick =  { menuState = SideMenuState.OPEN })

            if (menuState != SideMenuState.CLOSED) {
                SideMenu(
                    menuState,
                    colorMode,
                    close = { menuState = menuState.close() },
                    onAnimationEnd = { if (menuState == SideMenuState.CLOSING) menuState = SideMenuState.CLOSED }
                )
            }
        }
    }
}

@Composable
private fun SideMenu(menuState: SideMenuState, colorMode: ColorMode, close: () -> Unit, onAnimationEnd: () -> Unit) {
    Overlay(
        Modifier
            .zIndex(2)
            .setVariable(OverlayVars.BackgroundColor, Colors.Transparent)
            .onClick { close() }
    ) {
        key(menuState) { // Force recompute animation parameters when close button is clicked
            Column(
                Modifier
                    .fillMaxHeight()
                    .width(clamp(10.percent, 12.cssRem, 50.percent))
                    .align(Alignment.CenterEnd)
                    // Close button will appear roughly over the hamburger button, so the user can close
                    // things without moving their finger / cursor much.
                    .padding(top = 1.cssRem, leftRight = 1.cssRem)
                    .backgroundColor(colorMode.toColorPalette().backgroundSideMenu)
                    .animation(
                        SideMenuSlideInAnim.toAnimation(
                            duration = 200.ms,
                            timingFunction = if (menuState == SideMenuState.OPEN) AnimationTimingFunction.EaseOut else AnimationTimingFunction.EaseIn,
                            direction = if (menuState == SideMenuState.OPEN) AnimationDirection.Normal else AnimationDirection.Reverse,
                            fillMode = AnimationFillMode.Forwards
                        )
                    )
                    .onClick { it.stopPropagation() }
                    .onAnimationEnd { onAnimationEnd() },
                horizontalAlignment = Alignment.End
            ) {
                CloseButton(Modifier.color(colorMode.toColorPalette().brand.primary).size(28.px), onClick = { close() })
                Column(Modifier.padding(top = 3.cssRem).gap(3.cssRem).fontSize(14.px), horizontalAlignment = Alignment.End) {
                    ColorModeButton(Modifier.fontSize(14.px))
                    MenuItems()
                    LanguageButton()
                    DownloadCvButton(Modifier.fontSize(14.px), colorMode = colorMode)
                }
            }
        }
    }
}
