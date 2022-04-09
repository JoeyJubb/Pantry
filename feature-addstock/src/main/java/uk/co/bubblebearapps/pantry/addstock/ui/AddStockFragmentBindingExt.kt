package uk.co.bubblebearapps.pantry.addstock.ui

import android.widget.Toast
import androidx.core.view.isVisible
import arrow.core.Invalid
import arrow.core.Valid
import arrow.core.Validated
import timber.log.Timber
import uk.co.bubblebearapps.pantry.addstock.R
import uk.co.bubblebearapps.pantry.addstock.databinding.AddStockFragmentBinding
import uk.co.bubblebearapps.pantry.domain.model.UnitOfMeasure
import uk.co.bubblebearapps.pantry.ext.setOnImeActionListener
import uk.co.bubblebearapps.pantry.ext.showKeyboard

private const val INDEX_NAME = 0
private const val INDEX_QUANTITY = 1
private const val INDEX_LOADING = 2
private const val INDEX_ERROR = 3

internal fun AddStockFragmentBinding.showError() {
    includeError.btnRetry.isVisible = false
    root.displayedChild = INDEX_ERROR
}

internal fun AddStockFragmentBinding.showNameEntry() {
    root.displayedChild = INDEX_NAME

    includeName.etItemName.showKeyboard()
}

internal fun AddStockFragmentBinding.showQuantityEntry(viewState: AddStockViewModel.ViewState.QuantityEntry) {
    includeQuantity.etItemName.text = viewState.itemName

    includeQuantity.etItemQuantity.setText(
        when (val quantity = viewState.quantity) {
            0 -> null
            else -> quantity.toString()
        }
    )
    includeQuantity.etItemQuantity.selectAll()

    includeQuantity.groupUnitOfMeasure.check(
        when (viewState.unitOfMeasure) {
            UnitOfMeasure.UNITS -> R.id.btn_units
            UnitOfMeasure.MASS_GRAMS -> R.id.btn_mass
            UnitOfMeasure.VOLUME_MILLILITERS -> R.id.btn_volume
            UnitOfMeasure.LENGTH_MILLIMETERS -> R.id.btn_length
        }
    )

    setQuantityAddedListener(viewState.onConfirmedAction)

    root.displayedChild = INDEX_QUANTITY

    includeQuantity.etItemQuantity.showKeyboard()
}

internal fun AddStockFragmentBinding.showLoading() {

    root.displayedChild = INDEX_LOADING
}

internal fun AddStockFragmentBinding.setOnNameEnteredListener(function: (String) -> Unit) =
    with(root.context) {

        val checkFunction = {
            val itemName = includeName.etItemName.text.toString()
            if (itemName.isBlank()) {
                includeName.etItemName.error = getString(R.string.err_required)
            } else {
                function(itemName)
            }
        }

        includeName.etItemName.setOnImeActionListener { checkFunction() }
        includeName.btnAdd.setOnClickListener { checkFunction() }
    }

fun AddStockFragmentBinding.setQuantityAddedListener(onConfirmedAction: (UnitOfMeasure, Int) -> Unit) {
    includeQuantity.btnAdd.setOnClickListener {
        validateQuantity(onConfirmedAction)
    }
}

private fun AddStockFragmentBinding.validateQuantity(
    onConfirmedAction: (UnitOfMeasure, Int) -> Unit
) {
    val quantity = Validated.catch { getQuantity() }
    val unitOfMeasure = Validated.catch { getUnitOfMeasure() }

    if (unitOfMeasure is Valid && quantity is Valid) {
        onConfirmedAction(unitOfMeasure.value, quantity.value)
    }

    if (unitOfMeasure is Invalid) {
        Timber.w(unitOfMeasure.value)
        Toast.makeText(
            root.context,
            root.context.getString(R.string.error_unit_of_measure),
            Toast.LENGTH_SHORT
        ).show()
    }

    if (quantity is Invalid) {
        Timber.w(quantity.value)
        includeQuantity.etItemQuantity.error = root.context.getString(R.string.error_quantity)
    }
}


@Throws(NumberFormatException::class)
private fun AddStockFragmentBinding.getQuantity(): Int =
    includeQuantity.etItemQuantity.text.toString().toInt()

@Throws(IllegalStateException::class)
private fun AddStockFragmentBinding.getUnitOfMeasure(): UnitOfMeasure =
    when (includeQuantity.groupUnitOfMeasure.checkedButtonId) {
        R.id.btn_units -> UnitOfMeasure.UNITS
        R.id.btn_mass -> UnitOfMeasure.MASS_GRAMS
        R.id.btn_length -> UnitOfMeasure.LENGTH_MILLIMETERS
        R.id.btn_volume -> UnitOfMeasure.VOLUME_MILLILITERS
        else -> throw IllegalStateException("No checked button ID")
    }