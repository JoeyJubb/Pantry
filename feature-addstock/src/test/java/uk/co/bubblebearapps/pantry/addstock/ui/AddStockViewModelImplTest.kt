package uk.co.bubblebearapps.pantry.addstock.ui

import androidx.lifecycle.Observer
import arrow.core.Either.Left
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import uk.co.bubblebearapps.lib_test_base.ViewModelTestBase
import uk.co.bubblebearapps.pantry.addstock.domain.AddStockUseCase
import uk.co.bubblebearapps.pantry.addstock.domain.GetStockSnapshotUseCase
import uk.co.bubblebearapps.pantry.addstock.domain.UpdateStockUseCase
import uk.co.bubblebearapps.pantry.addstock.ui.AddStockViewModel.ViewState

@ExperimentalCoroutinesApi
class AddStockViewModelImplTest : ViewModelTestBase(

) {

    private val addStock: AddStockUseCase = mockk()
    private val getStockSnapshot: GetStockSnapshotUseCase = mockk()
    private val updateStock: UpdateStockUseCase = mockk()

    private val viewStateObserver = mockk<Observer<ViewState>> {
        every { onChanged(any()) } returns Unit
    }

    private lateinit var subject: AddStockViewModel

    @Before
    fun setUp() {
        subject = AddStockViewModelImpl(
            addStock,
            getStockSnapshot,
            updateStock
        )

        subject.viewState.observeForever(viewStateObserver)
    }

    @After
    fun tearDown() {
        subject.viewState.removeObserver(viewStateObserver)
    }

    @Test
    fun `should start in Idle state`() {
        subject.viewState.value shouldBeEqualTo ViewState.NameEntry
    }

    @Test
    fun `onItemAdded broken usecase emits error`() = runTest {
        val itemName = "item name"
        coEvery { addStock.invoke(itemName) } returns Left(mockk())

        subject.onNameEntered(itemName)

        verifyOrder {
            viewStateObserver.onChanged(ViewState.Loading)
            viewStateObserver.onChanged(ViewState.Error)
        }
        coVerify(exactly = 1) { addStock.invoke(itemName) }
    }
}
