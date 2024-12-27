package pl.krzyssko.portfoliowebsite.pages.en

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import pl.krzyssko.portfoliowebsite.Language
import pl.krzyssko.portfoliowebsite.Locale
import pl.krzyssko.portfoliowebsite.pages.HomePage

@Page("/en/index")
@Composable
fun IndexEn() {
    Locale.initialLanguage = Language.EN
    HomePage()
}
