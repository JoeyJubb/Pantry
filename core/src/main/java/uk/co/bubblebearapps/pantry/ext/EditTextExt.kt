package uk.co.bubblebearapps.pantry.ext

import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun EditText.setOnImeActionListener(
    action: Int = EditorInfo.IME_ACTION_DONE,
    function: () -> Unit
) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == action) {
            function()
            true
        } else {
            false
        }
    }
}
