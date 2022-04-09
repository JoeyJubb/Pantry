package uk.co.bubblebearapps.pantry.data.exceptions

import java.lang.RuntimeException

class StockNotFoundException(reason: String) : RuntimeException(reason)