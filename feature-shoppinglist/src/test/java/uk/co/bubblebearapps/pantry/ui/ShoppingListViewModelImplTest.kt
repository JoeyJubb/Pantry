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
import uk.co.bubblebearapps.pantry.shoppinglist.domain.GetShoppingList
import uk.co.bubblebearapps.pantry.shoppinglist.domain.model.ShoppingListItem
import uk.co.bubblebearapps.pantry.shoppinglist.ui.ShoppingListViewModel
import uk.co.bubblebearapps.pantry.shoppinglist.ui.ShoppingListViewModel.ViewState
import uk.co.bubblebearapps.pantry.shoppinglist.ui.ShoppingListViewModelImpl

@ExperimentalCoroutinesApi
class ShoppingListViewModelImplTest : ViewModelTestBase() {

    private val getShoppingList: GetShoppingList = mockk()
    private val viewStateObserver: Observer<ViewState> = mockk()

    private lateinit var subject: ShoppingListViewModel

    private val stockListFlow = MutableSharedFlow<List<ShoppingListItem>>()


    @Before
    fun setup() {
        every { getShoppingList.invoke(Unit) } returns stockListFlow
        every { viewStateObserver.onChanged(any()) } returns Unit

        subject = ShoppingListViewModelImpl(
            getShoppingList,
        )

        subject.viewState.observeForever(viewStateObserver)
    }

    @After
    fun tearDown() {
        subject.viewState.removeObserver(viewStateObserver)
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
        val stockList = listOf<ShoppingListItem>(mockk())
        stockListFlow.emit(stockList)

        verify { viewStateObserver.onChanged(ViewState.Result(stockList)) }
    }
}