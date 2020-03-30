package org.rodrigo.beauty.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.rodrigo.beauty.model.data.Vendor
import org.rodrigo.beauty.model.repository.VendorRepository

class MainViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)

    private val repository: VendorRepository = mock()

    private val mockData = listOf(
        Vendor("Fulano"),
        Vendor("Sicrano")
    )

    private lateinit var subject: MainViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        whenever(repository.getVendorsAsync()).thenReturn(CompletableDeferred(mockData))

        subject = MainViewModel(testCoroutineScope, repository)
    }

    @Test
    fun vendors_shouldHaveRepositoryValue() {
        var vendors: List<Vendor>? = null
        subject.vendors.observeForever {
            vendors = it
        }
        assertEquals(mockData, vendors)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }
}