package uk.co.bubblebearapps.pantry.addstock.ui

import androidx.lifecycle.Observer
import arrow.core.Either.*
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import uk.co.bubblebearapps.lib_test_base.ViewModelTestBase
import uk.co.bubblebearapps.pantry.addstock.domain.AddStockUseCase
import uk.co.bubblebearapps.pantry.addstock.ui.AddStockViewModel.*

@ExperimentalCoroutinesApi
class AddStockViewModelImplTest : ViewModelTestBase(

) {

    private val addStock: AddStockUseCase = mockk()

    private val viewStateObserver = mockk<Observer<ViewState>>{
        every { onChanged(any()) } returns Unit
    }

    private lateinit var subject : AddStockViewModel

    @Before
    fun setUp() {
        subject = AddStockViewModelImpl(
            addStock
        )

        subject.viewState.observeForever(viewStateObserver)
    }

    @After
    fun tearDown(){
        subject.viewState.removeObserver(viewStateObserver)
    }

    @Test
    fun `should start in Idle state`(){
        subject.viewState.value shouldBeEqualTo ViewState.Idle
    }

    @Test
    fun `onItemAdded happy path invokes usecase and completes`() = runTest {
        // GIVEN use case is happy
        val itemName = "item name"
        coEvery { addStock.invoke(itemName) } returns Right(Unit)

        // WHEN onItemAdded
        subject.onItemAdded(itemName)

        // THEN observer sees Loading then Complete
        verifyOrder {
            viewStateObserver.onChanged(ViewState.Loading)
            viewStateObserver.onChanged(ViewState.Complete)
        }
        // AND usecase was invoked
        coVerify(exactly = 1){ addStock.invoke(itemName) }
    }

    @Test
    fun `onItemAdded broken usecase emits error`() = runTest {
        val itemName = "item name"
        coEvery { addStock.invoke(itemName) } returns Left(mockk())

        subject.onItemAdded(itemName)

        verifyOrder {
            viewStateObserver.onChanged(ViewState.Loading)
            viewStateObserver.onChanged(ViewState.Error)
        }
        coVerify(exactly = 1){ addStock.invoke(itemName) }
    }
}
