package component

import hoc.withDisplayName
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.functionalComponent
import kotlin.browser.document

interface SearchProps : RProps {
    var books: BookList
}

fun searchFC() =
    functionalComponent<SearchProps> { props ->
        div("container search") {

            h1("head") { +"Поиск" }
            div("searchContainer") {
                input(InputType.text) {
                    attrs.id = "inputSearch"
                    attrs.placeholder = "поиск по названию"
                }
                button {
                    +"Найти"
                    attrs.id = "searchButton"
                    attrs.onClickFunction = onClickSearched(props)
                }
            }
            div {
                attrs.id = "found"
            }
        }
    }

fun RBuilder.search(
        book: BookList
) = child(withDisplayName("Search", searchFC())){
    attrs.books = book
}

