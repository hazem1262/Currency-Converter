package com.hazem.currency_converter.presentation.currency.converter.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.hazem.currency_converter.presentation.currency.converter.model.CurrencyUiModel
import com.hazem.currency_converter.utils.components.TEST_TAG_LOADING
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(
    sdk = [28]
)
internal class ConvertCurrencyScreenKtTest {
    @get: Rule
    val composeRule = createComposeRule()

    private val onFromSelected: (index:Int) -> Unit = mockk(relaxed = true)
    private val onToSelected: (index:Int) -> Unit = mockk(relaxed = true)
    private val onValueFromChanged: (String) -> Unit = mockk(relaxed = true)
    private val onValueToChanged: (String) -> Unit = mockk(relaxed = true)
    private val onDetailsClicked: () -> Unit = mockk(relaxed = true)
    private val onSwapClicked: () -> Unit = mockk(relaxed = true)

    @Composable
    private fun ScreenComposable(
        isLoading: Boolean = false,
        availableCurrencies:List<CurrencyUiModel> = arrayListOf(),
        fromString:String = "",
        toString:String = "",
        initialSelectedFromIndex:Int = 0,
        initialSelectedToIndex:Int = 0
    ) {
        ConvertCurrencyScreen(
            isLoading = isLoading,
            availableCurrencies = availableCurrencies,
            onFromSelected = onFromSelected,
            onToSelected = onToSelected,
            fromString = fromString,
            onValueFromChanged = onValueFromChanged,
            toString = toString,
            onValueToChanged = onValueToChanged,
            onDetailsClicked = onDetailsClicked,
            onSwapClicked = onSwapClicked,
            initialSelectedFromIndex = initialSelectedFromIndex,
            initialSelectedToIndex = initialSelectedToIndex
        )
    }

    @Test
    fun `given ConvertCurrencyScreen with loading state, loading is visible and other views not visible`() {
        composeRule.setContent { ScreenComposable(isLoading = true) }
        composeRule.onNodeWithTag(TEST_TAG_LOADING).assertExists()
        composeRule.onNodeWithTag(CONVERT_CURRENCY_SCREEN_TAG).assertDoesNotExist()
    }

    @Test
    fun `given ConvertCurrencyScreen with state not loading, loading is not visible and other views are visible`() {
        composeRule.setContent { ScreenComposable(isLoading = false, availableCurrencies = arrayListOf(CurrencyUiModel(acronym = "EGP", name = "Egyptian Pound"))) }
        composeRule.onNodeWithTag(TEST_TAG_LOADING).assertDoesNotExist()
        composeRule.onNodeWithTag(CONVERT_CURRENCY_SCREEN_TAG).assertExists()
    }
}