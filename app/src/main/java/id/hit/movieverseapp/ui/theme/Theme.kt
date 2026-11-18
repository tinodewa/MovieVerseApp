package id.hit.movieverseapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkCinemaColorScheme = darkColorScheme(
    primary = ElectricCrimson,
    onPrimary = Color.White,
    primaryContainer = Color(0xFF8C0009),
    onPrimaryContainer = Color.White,
    secondary = CrimsonGold,
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF4A3800),
    onSecondaryContainer = CrimsonGold,
    tertiary = GoldAccent,
    onTertiary = Color.Black,
    background = ObsidianBlack,
    onBackground = OnSurfacePrimary,
    surface = DarkSurface,
    onSurface = OnSurfacePrimary,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = OnSurfaceSecondary,
    outline = DarkCardBorder
)

private val LightCinemaColorScheme = lightColorScheme(
    primary = ElectricCrimson,
    onPrimary = Color.White,
    secondary = CrimsonGold,
    onSecondary = Color.Black,
    background = Color(0xFFF8F9FA),
    surface = Color.White,
    onBackground = Color(0xFF121212),
    onSurface = Color(0xFF121212)
)

@Composable
fun MovieVerseAppTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkCinemaColorScheme
        else -> LightCinemaColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
