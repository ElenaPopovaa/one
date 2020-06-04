package component

import react.*
import react.dom.*
import hoc.withDisplayName


interface BookListProps : RProps

fun bookListFC(books: BookList) =
    functionalComponent<BookListProps> {
	    div("container"){
		    h1("head"){+"Список книг"}
		    div("table-wrapper") {
			    table("fl-table") {
				    thead {
					    tr {
						    th { +"#" }
						    th { +"Название" }
						    th { +"Автор" }
						    th { +"Жанр" }
						    th { +"Количество" }
						    th { +"Цена" }
						    th { +"Заказать" }
					    }
				    }
				    tbody {
						books.mapIndexed{ productIndex, product ->
							current(product,productIndex,false)
						}
					}
		        }
		    }
        }
	}

fun RBuilder.bookList(
		books: BookList
) = child(withDisplayName("bookList", bookListFC(books))){
}
