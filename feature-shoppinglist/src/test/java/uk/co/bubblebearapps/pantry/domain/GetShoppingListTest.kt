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
import uk.co.bubblebearapps.pantry.shoppinglist.domain.model.ShoppingListItem
import uk.co.bubblebearapps.pantry.shoppinglist.domain.GetShoppingList

@ExperimentalCoroutinesApi
class GetShoppingListTest {

    private val repository = mockk<PantryRepository>()

    private lateinit var subject: GetShoppingList

    @Before
    fun setup() {
        subject = GetShoppingList(
            repository,
        )
    }

    @Test
    fun `use case correctly maps and reverses list from repository`() = runTest {
        every { repository.getShoppingList() } returns flowOf(
            listOf(
                Item(id = "1", name = "Name1", unitOfMeasure = UnitOfMeasure.MASS_GRAMS, quantity = 4),
                Item(id = "2", name = "Name2", unitOfMeasure = UnitOfMeasure.UNITS, quantity = 5),
                Item(id = "3", name = "Name3", unitOfMeasure = UnitOfMeasure.VOLUME_MILLILITERS, quantity = 6),
                Item(id = "4", name = "Name4", unitOfMeasure = UnitOfMeasure.LENGTH_MILLIMETERS, quantity = 7),
            ),
        )

        val results = subject.invoke(Unit).toList()

        results[0] shouldBeEqualTo listOf(
            ShoppingListItem(id = "4", name = "Name4", unitOfMeasure = UnitOfMeasure.LENGTH_MILLIMETERS, quantity = 7),
            ShoppingListItem(id = "3", name = "Name3", unitOfMeasure = UnitOfMeasure.VOLUME_MILLILITERS, quantity = 6),
            ShoppingListItem(id = "2", name = "Name2", unitOfMeasure = UnitOfMeasure.UNITS, quantity = 5),
            ShoppingListItem(id = "1", name = "Name1", unitOfMeasure = UnitOfMeasure.MASS_GRAMS, quantity = 4),
        )
    }
}
