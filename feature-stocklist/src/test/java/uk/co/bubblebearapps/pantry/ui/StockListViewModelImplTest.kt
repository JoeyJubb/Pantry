package uk.co.bubblebearapps.pantry.ui

import androidx.lifecycle.Observer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import uk.co.bubblebearapps.lib_test_base.ViewModelTestBase
import uk.co.bubblebearapps.pantry.domain.GetStockList
import uk.co.bubblebearapps.pantry.domain.model.StockListItem
import uk.co.bubblebearapps.pantry.ui.StockListViewModel.Event
import uk.co.bubblebearapps.pantry.ui.StockListViewModel.ViewState

@ExperimentalCoroutinesApi
class StockListViewModelImplTest : ViewModelTestBase() {

    private val getStockList: GetStockList = mockk()
    private val viewStateObserver: Observer<ViewState> = mockk()
    private val eventsObserver: Observer<Event> = mockk()

    private lateinit var subject: StockListViewModel

    private val stockListFlow = MutableSharedFlow<List<StockListItem>>()


    @Before
    fun setup() {
        every { getStockList.invoke(Unit) } returns stockListFlow
        every { viewStateObserver.onChanged(any()) } returns Unit
        every { eventsObserver.onChanged(any()) } returns Unit

        subject = StockListViewModelImpl(
            getStockList,
        )

        subject.viewState.observeForever(viewStateObserver)
        subject.events.observeForever(eventsObserver)
    }

    @After
    fun tearDown() {
        subject.viewState.removeObserver(viewStateObserver)
        subject.events.removeObserver(eventsObserver)
    }

    @Test
    fun `onAddButtonPress emits GoToAddStock event`() {
        subject.onAddButtonPress()

        verify { eventsObserver.onChanged(Event.GoToAddStock) }
    }

    @Test
    fun `starts with empty state`() {
        verify { viewStateObserver.onChanged(ViewState.Empty) }
    }

    @Test
    fun `empty list from GetStockList emits Empty State`() = runTest {
        stockListFlow.emit(emptyList())

        verify { viewStateObserver.onChanged(ViewState.Empty) }
    }

    @Test
    fun `non empty list from GetStockList emits Result State`() = runTest {
        val stockList = listOf<StockListItem>(mockk())
        stockListFlow.emit(stockList)

        verify { viewStateObserver.onChanged(ViewState.Result(stockList)) }
    }
}