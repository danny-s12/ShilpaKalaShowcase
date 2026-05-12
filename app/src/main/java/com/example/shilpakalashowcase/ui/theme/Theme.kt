package com.example.shilpakalashowcase.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// ─────────────────────────────────────────────
//  SHILPA-KALA — Always Dark Theme
//  We force dark theme always for the premium
//  gallery aesthetic. No light mode.
// ─────────────────────────────────────────────

private val ShilpaKalaDarkColorScheme = darkColorScheme(
    // Primary = the main gold accent (buttons, active icons, highlights)
    primary              = AncientGold,
    onPrimary            = DeepCharcoal,       // Text ON gold buttons = dark
    primaryContainer     = DeepGold,
    onPrimaryContainer   = WarmIvory,

    // Secondary = muted gold (secondary buttons, tags)
    secondary            = SoftCream,
    onSecondary          = DeepCharcoal,
    secondaryContainer   = SubtleSurface,
    onSecondaryContainer = WarmIvory,

    // Tertiary = pale gold accent
    tertiary             = PaleGold,
    onTertiary           = DeepCharcoal,

    // Backgrounds
    background           = DeepCharcoal,       // The main app background
    onBackground         = WarmIvory,          // Text on background

    // Surfaces (cards, bottom bars, top bars)
    surface              = RichBlack,
    onSurface            = WarmIvory,
    surfaceVariant       = ElevatedSurface,
    onSurfaceVariant     = SoftCream,

    // Errors
    error                = ErrorTerracotta,
    onError              = Color.White,

    // Outlines (text field borders, dividers)
    outline              = DeepGold,
    outlineVariant       = CardBorder,

    // Scrim (dialog overlays)
    scrim                = ScrimDark,
)

@Composable
fun ShilpaKalaShowcaseTheme(
    content: @Composable () -> Unit
) {
    // Always use our custom dark scheme
    // No dynamic color, no light mode — pure brand identity
    MaterialTheme(
        colorScheme = ShilpaKalaDarkColorScheme,
        typography  = Typography,
        content     = content
    )
}

