package pl.krzyssko.portfoliowebsite.style

import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.color

class ColorPalette(
    val backgroundPrimary: Color,
    val backgroundSecondary: Color,
    val backgroundDim: Color = Color.rgb(Style.Colors.COLOR_BACKGROUND_DIM),
    val backgroundLighter: Color = Color.rgb(Style.Colors.COLOR_BACKGROUND_LIGHER),
    val font: Color,
    val brand: Brand = Brand(),
    val buttonFont: Button = Button(),
) {
    class Brand(
        val primary: Color = Color.rgb(Style.Colors.COLOR_PRIMARY),
        val accent: Color = Color.rgb(Style.Colors.COLOR_ACCENT),
    )
    class Button(
        val primary: Color = Color.rgb(Style.Colors.COLOR_BUTTON_FONT_PRIMARY_LIGHT),
        val secondary: Color = Color.rgb(Style.Colors.COLOR_BUTTON_FONT_SECONDARY_LIGHT)
    )
}

object ColorPalettes {
    val light = ColorPalette(
        backgroundPrimary = Color.rgb(Style.Colors.COLOR_BACKGROUND_PRIMARY_LIGHT),
        backgroundSecondary = Color.rgb(Style.Colors.COLOR_BACKGROUND_SECONDARY_LIGHT),
        font = Color.rgb(Style.Colors.COLOR_FONT_PRIMARY_LIGHT),
        buttonFont = ColorPalette.Button(Color.rgb(Style.Colors.COLOR_BUTTON_FONT_PRIMARY_LIGHT), Color.rgb(Style.Colors.COLOR_BUTTON_FONT_SECONDARY_LIGHT))
    )
    val dark = ColorPalette(
        backgroundPrimary = Color.rgb(Style.Colors.COLOR_BACKGROUND_PRIMARY_DARK),
        backgroundSecondary = Color.rgb(Style.Colors.COLOR_BACKGROUND_SECONDARY_DARK),
        font = Color.rgb(Style.Colors.COLOR_FONT_PRIMARY_DARK),
        buttonFont = ColorPalette.Button(Color.rgb(Style.Colors.COLOR_BUTTON_FONT_PRIMARY_DARK), Color.rgb(Style.Colors.COLOR_BUTTON_FONT_SECONDARY_DARK))
    )
}

fun ColorMode.toColorPalette(): ColorPalette {
    return when (this) {
        ColorMode.LIGHT -> ColorPalettes.light
        ColorMode.DARK -> ColorPalettes.dark
    }
}

fun ColorMode.toOppositePalette(): ColorPalette {
    return when (this) {
        ColorMode.LIGHT -> ColorPalettes.dark
        ColorMode.DARK -> ColorPalettes.light
    }
}

@InitSilk
fun initTheme(ctx: InitSilkContext) {
    ctx.theme.palettes.light.background = Color.rgb(Style.Colors.COLOR_BACKGROUND_PRIMARY_LIGHT)
    ctx.theme.palettes.light.color = Color.rgb(Style.Colors.COLOR_FONT_PRIMARY_LIGHT)
    ctx.theme.palettes.dark.background = Color.rgb(Style.Colors.COLOR_BACKGROUND_PRIMARY_DARK)
    ctx.theme.palettes.dark.color = Color.rgb(Style.Colors.COLOR_FONT_PRIMARY_DARK)
}
