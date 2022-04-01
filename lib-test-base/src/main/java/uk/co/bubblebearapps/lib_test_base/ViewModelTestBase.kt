package uk.co.bubblebearapps.lib_test_base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Rule
import org.junit.rules.TestRule
import uk.co.bubblebearapps.lib_test_base.rules.CoroutineTestRule

@ExperimentalCoroutinesApi
abstract class ViewModelTestBase(
    dispatcher: TestDispatcher = UnconfinedTestDispatcher(TestCoroutineScheduler())
) {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule(dispatcher)
}
