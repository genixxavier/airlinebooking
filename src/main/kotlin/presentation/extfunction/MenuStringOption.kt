package presentation.extfunction

import domain.model.Flight

fun String.isBlankOrEmpty(): Boolean {
    return this.isBlank() || this.isEmpty()
}

fun String.isNotBlankOrEmpety(): Boolean{
    return this.isNotBlank() || this.isNotEmpty()
}

fun String.isNumber(): Boolean{
     return this.all { it.isDigit() }
}

fun <T> String.isMenuOptionValid(mapObjects: Map<Int, T>): Boolean {
    return if(isNotBlankOrEmpety()) {
        return isNumber() && mapObjects.containsKey(toInt())
    } else false
}