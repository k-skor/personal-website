package pl.krzyssko.portfoliowebsite.style

import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.color
import org.jetbrains.compose.web.dom.Col

/**
 * @property nearBackground A useful color to apply to a container that should differentiate itself from the background
 *   but just a little.
 */
class ColorPalette(
    val background: Color,
    val nearBackground: Color,
    val cobweb: Color,
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
        background = Color.rgb(Style.Colors.COLOR_BACKGROUND_PRIMARY_LIGHT),
        nearBackground = Color.rgb(Style.Colors.COLOR_BACKGROUND_SECONDARY_LIGHT),
        cobweb = Colors.LightGray,
        tint = Colors.LightGray.inverted()
    )
    val dark = ColorPalette(
        background = Color.rgb(Style.Colors.COLOR_BACKGROUND_PRIMARY_DARK),
        nearBackground = Color.rgb(Style.Colors.COLOR_BACKGROUND_SECONDARY_DARK),
        cobweb = Colors.LightGray.inverted(),
        tint = Colors.LightGray
    )
}

fun ColorMode.toColorPalette(): ColorPalette {
    return when (this) {
        ColorMode.LIGHT -> ColorPalettes.light
        ColorMode.DARK -> ColorPalettes.dark
    }
}

@InitSilk
fun initTheme(ctx: InitSilkContext) {
    ctx.theme.palettes.light.background = Color.rgb(Style.Colors.COLOR_BACKGROUND_PRIMARY_LIGHT)
    ctx.theme.palettes.light.color = Color.rgb(Style.Colors.COLOR_FONT_LIGHT)
    ctx.theme.palettes.dark.background = Color.rgb(Style.Colors.COLOR_BACKGROUND_PRIMARY_DARK)
    ctx.theme.palettes.dark.color = Color.rgb(Style.Colors.COLOR_FONT_DARK)
}
