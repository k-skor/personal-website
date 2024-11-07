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
    val backgroundDim: Color,
    val tint: Color,
    val brand: Brand = Brand(),
) {
    class Brand(
        val primary: Color = Color.rgb(Style.Colors.COLOR_PRIMARY),
        val accent: Color = Color.rgb(Style.Colors.COLOR_ACCENT),
    )
}

object ColorPalettes {
    val light = ColorPalette(
        backgroundPrimary = Color.rgb(Style.Colors.COLOR_BACKGROUND_PRIMARY_LIGHT),
        backgroundSecondary = Color.rgb(Style.Colors.COLOR_BACKGROUND_SECONDARY_LIGHT),
        backgroundDim = Color.rgb(Style.Colors.COLOR_BACKGROUND_DIM),
        tint = Color.rgb(Style.Colors.COLOR_FONT_LIGHT)
    )
    val dark = ColorPalette(
        backgroundPrimary = Color.rgb(Style.Colors.COLOR_BACKGROUND_PRIMARY_DARK),
        backgroundSecondary = Color.rgb(Style.Colors.COLOR_BACKGROUND_SECONDARY_DARK),
        backgroundDim = Color.rgb(Style.Colors.COLOR_BACKGROUND_DIM),
        tint = Color.rgb(Style.Colors.COLOR_FONT_DARK)
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
    ctx.theme.palettes.light.color = Color.rgb(Style.Colors.COLOR_FONT_LIGHT)
    ctx.theme.palettes.dark.background = Color.rgb(Style.Colors.COLOR_BACKGROUND_PRIMARY_DARK)
    ctx.theme.palettes.dark.color = Color.rgb(Style.Colors.COLOR_FONT_DARK)
}
