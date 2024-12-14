package pl.krzyssko.portfoliowebsite

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.varabyte.kobweb.browser.http.http
import kotlinx.browser.window

enum class Language {
    PL,
    EN
}

suspend fun fetchLanguage(lang: Language): Map<String, String>? {
    window.http.logOnError = false
    val response = window.http.tryGet("strings_${lang.name.lowercase()}.txt")?.decodeToString() ?: return null
    val translation = mutableMapOf<String, String>()
    for (line in response.lines()) {
        if (line.isEmpty() || line.trim().first() == '#') continue
        val index = line.indexOfFirst { it == '=' }
        if (index != -1) {
            val key = line.substring(0, index).trim { it == '"' }.replace("\\n", "\n")
            val text = line.substring(index + 1, line.length - 1).trim { it == '"' }.replace("\\n", "\n")
            translation[key] = text
        }
    }
    return translation
}

fun fromBrowserDefault(): Language {
    val language = window.navigator.language
    if (language.startsWith("en")) {
        return Language.EN
    }
    return Language.PL
}

object Locale {
    lateinit var initialLanguage: Language
    val current: MutableState<Language?> = mutableStateOf(null)
    private val translations: MutableMap<Language, Map<String, String>?> = mutableMapOf()
    val translation: Map<String, String>?
        get() = translations[current.value]

    suspend fun init() {
        val language = initialLanguage
        if (!translations.contains(language)) {
            translations[language] = fetchLanguage(language)
        }
        current.value = language
    }
}

fun String.localized(): String {
    return Locale.translation?.get(this) ?: this
}
