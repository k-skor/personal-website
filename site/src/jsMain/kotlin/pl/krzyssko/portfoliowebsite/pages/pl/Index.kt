package pl.krzyssko.portfoliowebsite.pages.pl

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import pl.krzyssko.portfoliowebsite.Language
import pl.krzyssko.portfoliowebsite.Locale
import pl.krzyssko.portfoliowebsite.pages.HomePage

@Page("/pl/index")
@Composable
fun IndexPl() {
    Locale.initialLanguage = Language.PL
    HomePage()
}
