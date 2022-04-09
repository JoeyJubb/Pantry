package uk.co.bubblebearapps.pantry.domain

import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import uk.co.bubblebearapps.pantry.data.PantryRepository
import uk.co.bubblebearapps.pantry.domain.model.Stock
import uk.co.bubblebearapps.pantry.domain.model.StockListItem
import uk.co.bubblebearapps.pantry.domain.model.UnitOfMeasure

@ExperimentalCoroutinesApi
class GetStockListTest {

    private val repository = mockk<PantryRepository>()

    private lateinit var subject: GetStockList

    @Before
    fun setup() {
        subject = GetStockList(
            repository,
        )
    }

    @Test
    fun `use case correctly maps and reverses list from repository`() = runTest {
        every { repository.getStock() } returns flowOf(
            listOf(
                Stock(id = "1", name = "Name1", unitOfMeasure = UnitOfMeasure.MASS_GRAMS, quantity = 4),
                Stock(id = "2", name = "Name2", unitOfMeasure = UnitOfMeasure.UNITS, quantity = 5),
                Stock(id = "3", name = "Name3", unitOfMeasure = UnitOfMeasure.VOLUME_MILLILITERS, quantity = 6),
                Stock(id = "4", name = "Name4", unitOfMeasure = UnitOfMeasure.LENGTH_MILLIMETERS, quantity = 7),
            ),
        )

        val results = subject.invoke(Unit).toList()

        results[0] shouldBeEqualTo listOf(
            StockListItem(id = "4", name = "Name4", unitOfMeasure = UnitOfMeasure.LENGTH_MILLIMETERS, quantity = 7),
            StockListItem(id = "3", name = "Name3", unitOfMeasure = UnitOfMeasure.VOLUME_MILLILITERS, quantity = 6),
            StockListItem(id = "2", name = "Name2", unitOfMeasure = UnitOfMeasure.UNITS, quantity = 5),
            StockListItem(id = "1", name = "Name1", unitOfMeasure = UnitOfMeasure.MASS_GRAMS, quantity = 4),
        )
    }
}
