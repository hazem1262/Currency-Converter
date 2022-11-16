package com.hazem.currency_converter.presentation.currency.converter

import com.hazem.currency_converter.data.remote.currency.CurrencyRepository
import com.hazem.currency_converter.presentation.currency.converter.mapper.CurrencyUiMapper
import com.hazem.currency_converter.presentation.currency.converter.model.CurrencyUiModel
import com.hazem.currency_converter.presentation.currency.converter.mvi.CurrencyConverterState
import com.hazem.currency_converter.utils.MainDispatcherRule
import com.hazem.currency_converter.utils.createConvertResponse
import com.hazem.currency_converter.utils.createCurrenciesResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class CurrencyConverterViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val currencyRepository : CurrencyRepository = mockk()
    private lateinit var viewModel: CurrencyConverterViewModel
    private val mockCurrenciesResponse = createCurrenciesResponse()
    private val mockConvertResponse = createConvertResponse()

    @Before
    fun setup() {
        viewModel = CurrencyConverterViewModel(
            currencyRepository = currencyRepository
        )
    }

    // getAvailableCurrencies test cases
    @Test
    fun `when getAvailableCurrencies is called, verify the ui state has the currencies as expected`() {
        runTest {
            // given
            coEvery { currencyRepository.getAvailableCurrencies() }.coAnswers { mockCurrenciesResponse }

            // when
            viewModel.getAvailableCurrencies()

            // Assert
            val expectedAvailableCurrencies = CurrencyUiMapper.toCurrencyUiModel(mockCurrenciesResponse)
            val currentState = viewModel.uiState.value
            assert(currentState.currencies == expectedAvailableCurrencies)
        }
    }

    // onNewFromCurrencySelected test cases
    @Test
    fun `when onNewFromCurrencySelected is called, given there is an input in from text field, convertCurrency called and the ui state has the toTextFieldString as expected`() {
        runTest {
            // given
            coEvery { currencyRepository.covertCurrency(any(), any(), any()) }.coAnswers { mockConvertResponse }
            viewModel.uiState.value = CurrencyConverterState(fromTextFieldString = "1")

            // when
            viewModel.onNewFromCurrencySelected(1)

            // Assert
            val expectedConversionResults = mockConvertResponse.result
            val currentState = viewModel.uiState.value
            assert(currentState.toTextFieldString == expectedConversionResults)
        }
    }

    @Test
    fun `when onNewFromCurrencySelected is called, given there is no input in from text field and to field has data, convertCurrencyRevered called and the ui state has the fromTextFieldString as expected`() {
        runTest {
            // given
            coEvery { currencyRepository.covertCurrency(any(), any(), any()) }.coAnswers { mockConvertResponse }
            viewModel.uiState.value = CurrencyConverterState(toTextFieldString = "1", fromTextFieldString = "")

            // when
            viewModel.onNewFromCurrencySelected(1)

            // Assert
            val expectedConversionResults = mockConvertResponse.result
            val currentState = viewModel.uiState.value
            assert(currentState.fromTextFieldString == expectedConversionResults)
        }
    }

    @Test
    fun `when onNewFromCurrencySelected is called, given there is no input in from text field and no input field at to , the ui state has no updates as expected`() {
        runTest {
            // given
            coEvery { currencyRepository.covertCurrency(any(), any(), any()) }.coAnswers { mockConvertResponse }
            viewModel.uiState.value = CurrencyConverterState(toTextFieldString = "", fromTextFieldString = "")

            // when
            viewModel.onNewFromCurrencySelected(1)

            // Assert
            val expectedConversionResults = mockConvertResponse.result
            val currentState = viewModel.uiState.value
            assert(currentState.fromTextFieldString == "")
            assert(currentState.toTextFieldString == "")
        }
    }

    // onNewToCurrencySelected test cases
    @Test
    fun `when onNewToCurrencySelected is called, given there is an input in from text field, convertCurrency called and the ui state has the toTextFieldString as expected`() {
        runTest {
            // given
            coEvery { currencyRepository.covertCurrency(any(), any(), any()) }.coAnswers { mockConvertResponse }
            viewModel.uiState.value = CurrencyConverterState(fromTextFieldString = "1")

            // when
            viewModel.onNewFromCurrencySelected(1)

            // Assert
            val expectedConversionResults = mockConvertResponse.result
            val currentState = viewModel.uiState.value
            assert(currentState.toTextFieldString == expectedConversionResults)
        }
    }

    @Test
    fun `when onNewToCurrencySelected is called, given there is no input in from text field and to field has data, convertCurrencyRevered called and the ui state has the fromTextFieldString as expected`() {
        runTest {
            // given
            coEvery { currencyRepository.covertCurrency(any(), any(), any()) }.coAnswers { mockConvertResponse }
            viewModel.uiState.value = CurrencyConverterState(toTextFieldString = "1", fromTextFieldString = "")

            // when
            viewModel.onNewFromCurrencySelected(1)

            // Assert
            val expectedConversionResults = mockConvertResponse.result
            val currentState = viewModel.uiState.value
            assert(currentState.fromTextFieldString == expectedConversionResults)
        }
    }

    @Test
    fun `when onNewToCurrencySelected is called, given there is no input in from text field and no input field at to , the ui state has no updates as expected`() {
        runTest {
            // given
            coEvery { currencyRepository.covertCurrency(any(), any(), any()) }.coAnswers { mockConvertResponse }
            viewModel.uiState.value = CurrencyConverterState(toTextFieldString = "", fromTextFieldString = "")

            // when
            viewModel.onNewFromCurrencySelected(1)

            // Assert
            val expectedConversionResults = mockConvertResponse.result
            val currentState = viewModel.uiState.value
            assert(currentState.fromTextFieldString == "")
            assert(currentState.toTextFieldString == "")
        }
    }

    // onValueFromChanged test cases
    @Test
    fun `when onValueFromChanged is called with empty text, fromTextFieldString is the only ui state property affected`() {
        runTest {
            // given
            coEvery { currencyRepository.covertCurrency(any(), any(), any()) }.coAnswers { mockConvertResponse }
            viewModel.uiState.value = CurrencyConverterState()

            // when
            viewModel.onValueFromChanged("")

            // Assert
            val currentState = viewModel.uiState.value
            assert(currentState.fromTextFieldString == "")
        }
    }

    @Test
    fun `when onValueFromChanged is called, convertCurrency called and the ui state has the toTextFieldString as expected`() {
        runTest {
            // given
            coEvery { currencyRepository.covertCurrency(any(), any(), any()) }.coAnswers { mockConvertResponse }
            viewModel.uiState.value = CurrencyConverterState(fromTextFieldString = "1")

            // when
            viewModel.onValueFromChanged("2")

            // Assert
            val expectedConversionResults = mockConvertResponse.result
            val currentState = viewModel.uiState.value
            assert(currentState.toTextFieldString == expectedConversionResults)
        }
    }

    // onValueFromChanged test cases
    @Test
    fun `when onValueToChanged is called with empty text, toTextFieldString is the only ui state property affected`() {
        runTest {
            // given
            coEvery { currencyRepository.covertCurrency(any(), any(), any()) }.coAnswers { mockConvertResponse }
            viewModel.uiState.value = CurrencyConverterState()

            // when
            viewModel.onValueToChanged("")

            // Assert
            val currentState = viewModel.uiState.value
            assert(currentState.toTextFieldString == "")
        }
    }

    @Test
    fun `when onValueToChanged is called, convertCurrency called and the ui state has the toTextFieldString as expected`() {
        runTest {
            // given
            coEvery { currencyRepository.covertCurrency(any(), any(), any()) }.coAnswers { mockConvertResponse }
            viewModel.uiState.value = CurrencyConverterState(fromTextFieldString = "1")

            // when
            viewModel.onValueToChanged("2")

            // Assert
            val expectedConversionResults = mockConvertResponse.result
            val currentState = viewModel.uiState.value
            assert(currentState.fromTextFieldString == expectedConversionResults)
        }
    }

    // swapValues test cases
    @Test
    fun `when swapValues is called, from and to currencies are swapped and convertCurrency called`() {
        runTest {
            // given
            val oldFromCurrency = CurrencyUiModel("EGP", "Egyptian Pound")
            val oldToCurrency = CurrencyUiModel("AED", "United Arab Emirates Dirham")
            coEvery { currencyRepository.covertCurrency(any(), any(), any()) }.coAnswers { mockConvertResponse }
            viewModel.uiState.value = CurrencyConverterState(selectedFromCurrency = oldFromCurrency, selectedToCurrency = oldToCurrency)

            // when
            viewModel.swapValues()

            // Assert
            val expectedConversionResults = mockConvertResponse.result
            val currentState = viewModel.uiState.value
            assert(currentState.toTextFieldString == expectedConversionResults)
            assert(currentState.selectedToCurrency == oldFromCurrency)
            assert(currentState.selectedFromCurrency == oldToCurrency)
        }
    }
}