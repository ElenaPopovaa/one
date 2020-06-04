package component

import react.*
import react.dom.*
import data.*
import hoc.withDisplayName
import react.router.dom.navLink


interface CurrentProps : RProps

fun currentFC(book:Book, linkTypeIsNav:Boolean, index:Int) =
		functionalComponent<CurrentProps> {
			if (book.visible) {
				if (linkTypeIsNav) {
					tr {
						td { +"$index" }
						td { +book.name }
						td { +book.author }
						td { +book.genre }
						td { +"${book.quantity}" }
						td { +"${book.price}" }
						td { navLink("/book$index") { +"Заказать" } }
					}
				} else {
					tr {
						td { +"$index" }
						td { +book.name }
						td { +book.author }
						td { +book.genre }
						td { +"${book.quantity}" }
						td { +"${book.price}" }
						td { a("#/book$index") { +"Заказать" } }
					}
				}
			}
		}


fun RBuilder.current(
		books: Book,
		index:Int,
		linkTypeIsNav:Boolean
) = child(withDisplayName("productList", currentFC(books,linkTypeIsNav,index))){
}
