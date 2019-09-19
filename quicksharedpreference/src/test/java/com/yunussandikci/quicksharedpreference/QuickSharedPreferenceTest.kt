package com.yunussandikci.quicksharedpreference

import android.content.Context
import android.content.SharedPreferences
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Matchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.Matchers.anyString
import org.mockito.Mockito.`when`




class QuickSharedPreferenceTest {

    var context = mock(Context::class.java)

    var sharedPreferences = mock(SharedPreferences::class.java)

    var sharedPreferencesEditor = mock(SharedPreferences.Editor::class.java)

    val PACKAGE_NAME = "com.example.test.project"

    @Test
    fun it_should_get_value_without_cache() {
        //given
        val key = "key1"
        val value = "value1"
        QuickSharedPreference.setUseCache(false)
        `when`(context.packageName).thenReturn(PACKAGE_NAME)
        `when`(context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE)).thenReturn(sharedPreferences)
        `when`(context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE).getString(key, null)).thenReturn(value)

        //when
        val quickValue = QuickSharedPreference.get(context, key, String::class.java)

        //then
        assertEquals(quickValue, value)
    }

    @Test
    fun it_should_get_value_with_cache() {
        //given
        val key = "key1"
        val value = "value1"
        QuickSharedPreference.setUseCache(true)
        `when`(context.packageName).thenReturn(PACKAGE_NAME)
        `when`(context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE)).thenReturn(sharedPreferences)
        `when`(context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE).getString(key, null)).thenReturn(value)

        //when
        val quickValue = QuickSharedPreference.get(context, key, String::class.java)

        //then
        assertEquals(quickValue, value)
    }

}
