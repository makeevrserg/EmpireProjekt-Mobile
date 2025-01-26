package com.makeevrserg.empireprojekt.mobile.services.core.settings

import com.russhwolf.settings.Settings
import kotlin.enums.EnumEntries

object SettingsExt {
    fun <E : Enum<E>> Settings.getEnumByOrdinalOrDefault(entries: EnumEntries<E>, key: String, default: E): E {
        val ordinal = getIntOrNull(key) ?: return default
        return entries.getOrNull(ordinal) ?: default
    }

    fun <E : Enum<E>> Settings.putEnumByOrdinal(key: String, value: E?) {
        if (value == null) {
            remove(key)
        } else {
            putInt(key, value.ordinal)
        }
    }
}
