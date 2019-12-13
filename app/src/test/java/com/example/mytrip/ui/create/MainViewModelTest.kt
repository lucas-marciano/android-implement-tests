package com.example.mytrip.ui.create

import com.example.mytrip.ui.create.MainViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private val mainViewModel = MainViewModel()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `is valid if the calculation was right`() {
        val distance = mainViewModel.distance
        distance.set("100")
        val price = mainViewModel.price
        price.set("30")
        val autonomy = mainViewModel.autonomy
        autonomy.set("15")
        assertTrue(mainViewModel.isValid())
    }

    @Test
    fun `is valid if the calculation was wrong`() {
        val distance = mainViewModel.distance
        distance.set("")
        val price = mainViewModel.price
        price.set("")
        val autonomy = mainViewModel.autonomy
        autonomy.set("")
        assertFalse(mainViewModel.isValid())
    }

    @Test
    fun `valid handle calculation`() {
        val distance = mainViewModel.distance
        distance.set("30")
        val price = mainViewModel.price
        price.set("15")
        val autonomy = mainViewModel.autonomy
        autonomy.set("250")
        val results = mainViewModel.results
        mainViewModel.handleCalculate()
        assertEquals("Total: R$ 1.8", results.get())
    }

    @Test
    fun `valid handle calculation wrong number`() {
        val distance = mainViewModel.distance
        distance.set("asadsaddsasdsdadssd")
        val price = mainViewModel.price
        price.set("15")
        val autonomy = mainViewModel.autonomy
        autonomy.set("250")
        mainViewModel.results
        val numberFormatException: Boolean = try {
            mainViewModel.handleCalculate()
            false
        } catch (e: Exception) {
            true
        }
        assertTrue(numberFormatException)
    }

    @Test
    fun `handle with calculate button click handle calculate`() {
        val distance = mainViewModel.distance
        distance.set("30")
        val price = mainViewModel.price
        price.set("15")
        val autonomy = mainViewModel.autonomy
        autonomy.set("250")
        val spy = spy(mainViewModel)
        spy.handleCalculateButtonClick()
        verify(spy, times(1)).handleCalculate()
    }

}
