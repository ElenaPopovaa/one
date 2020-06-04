package component

import data.Book
import data.Order
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.dom.events.Event
import react.dom.*
import redux.AddBook
import redux.DeleteBook
import redux.MakeOrder
import store
import kotlin.browser.document

fun booleans(quantity: Int, book: Book, name: String, sName: String, address: String): Array<Boolean> {
	val check = Array(4) { false }
	if (quantity > book.quantity || quantity < 0) {
		(document.getElementById("orderQuantityInput") as HTMLInputElement).className = "wrongAdd"
		check[0] = false
	} else {
		(document.getElementById("orderQuantityInput") as HTMLInputElement).className = "correctAdd"
		check[0] = true
	}
	if (name.length < 2) {
		(document.getElementById("orderNameInput") as HTMLInputElement).className = "wrongAdd"
		check[1] = false
	} else {
		(document.getElementById("orderNameInput") as HTMLInputElement).className = "correctAdd"
		check[1] = true
	}
	if (sName.length < 2) {
		(document.getElementById("orderSNameInput") as HTMLInputElement).className = "wrongAdd"
		check[2] = false
	} else {
		(document.getElementById("orderSNameInput") as HTMLInputElement).className = "correctAdd"
		check[2] = true
	}
	if (address.length < 2) {
		(document.getElementById("orderAddressInput") as HTMLInputElement).className = "wrongAdd"
		check[3] = false
	} else {
		(document.getElementById("orderAddressInput") as HTMLInputElement).className = "correctAdd"
		check[3] = true
	}
	return check
}


fun onClickSubmit(book: Book, productIndex: Int): (Event) -> Unit {
	return {
		val quantity = inputIntValue("orderQuantityInput")
		val name = getValue("orderNameInput")
		val sName = getValue("orderSNameInput")
		val address = getValue("orderAddressInput")
		val check = booleans(quantity, book, name, sName, address)
		console.log(check)
		if (check[0] && check[1] && check[2] && check[3]) {
			render(document.getElementById("got")) {
				button {
					+"Сделать заказ. Итоговая стоимость: ${quantity * book.price}"
					attrs.onClickFunction = {
						store.dispatch(
							MakeOrder(
								Order(
									productIndex,
									quantity,
									name,
									sName,
									address
								)
							)
						)
					}
				}
			}
		} else {
			render(document.getElementById("got")) { +"" }
		}
	}
}


fun onClickSearched(props: SearchProps): (Event) -> Unit {
	var counter: Int = -1
	return {
		val value = getValue("inputSearch")
		render(document.getElementById("found")) {
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
						props.books.forEachIndexed { index, book ->
							if (book.name.toLowerCase().contains(value.toLowerCase())) {
								counter++
								current(book, index, false)
							}
						}
					}
				}
			}
		}
		if (counter == -1) {
			render(document.getElementById("found")) {
				+""
			}
		}
	}
}

fun onClickAddBook(): (Event) -> Unit {
	return {
		val name = getValue("nameInput")
		val author = getValue("authorInput")
		val genre = getValue("genreInput")
		val description = (document.getElementById("descriptionInput") as HTMLTextAreaElement).value
		val quantity = getValue("quantityInput")
		val price = getValue("priceInput")
		if (checkAddBook(quantity, name, author, genre, description, price)) {
			store.dispatch(AddBook(name, author, genre, description, quantity.toInt(), price.toInt()))
		}
	}
}

fun onClickDeleteSelected(): (Event) -> Unit {
	return {
		val value = getValueSelected("deleteSelected").substringBefore(' ').toInt()
		console.log("selectedValue = $value")
		store.dispatch(DeleteBook(value))
	}
}

fun onClickTestFill(): (Event) -> Unit {
	return {
		(document.getElementById("nameInput") as HTMLInputElement).value = "[TEST_NAME]"
		(document.getElementById("authorInput") as HTMLInputElement).value = "[TEST_AUTHOR]"
		(document.getElementById("genreInput") as HTMLInputElement).value = "[TEST_GENRE]"
		(document.getElementById("quantityInput") as HTMLInputElement).value = "111"
		(document.getElementById("priceInput") as HTMLInputElement).value = "777"
		(document.getElementById("descriptionInput") as HTMLTextAreaElement).value = "[TEST_DESCRIPTION]"
	}
}

fun checkAddBook(quantity: String, name: String, author: String, genre: String, description: String, price: String):Boolean {
	val check = Array(6) { false }
	if (quantity == "" || quantity.toInt() < 0 ) {
		(document.getElementById("quantityInput") as HTMLInputElement).className = "tableInput wrongAdd"
		check[0] = false
	} else {
		(document.getElementById("quantityInput") as HTMLInputElement).className = "tableInput correctAdd"
		check[0] = true
	}

	if (name.length < 2) {
		(document.getElementById("nameInput") as HTMLInputElement).className = "tableInput wrongAdd"
		check[1] = false
	} else {
		(document.getElementById("nameInput") as HTMLInputElement).className = "tableInput correctAdd"
		check[1] = true
	}

	if (author.length < 2) {
		(document.getElementById("authorInput") as HTMLInputElement).className = "tableInput wrongAdd"
		check[2] = false
	} else {
		(document.getElementById("authorInput") as HTMLInputElement).className = "tableInput correctAdd"
		check[2] = true
	}

	if (genre.length < 2) {
		(document.getElementById("genreInput") as HTMLInputElement).className = "tableInput wrongAdd"
		check[3] = false
	} else {
		(document.getElementById("genreInput") as HTMLInputElement).className = "tableInput correctAdd"
		check[3] = true
	}

	if (description.length < 2) {
		(document.getElementById("descriptionInput") as HTMLTextAreaElement).className = "tableInput wrongAdd"
		check[4] = false
	} else {
		(document.getElementById("descriptionInput") as HTMLTextAreaElement).className = "tableInput correctAdd"
		check[4] = true
	}

	if (price == "" || price.toInt() < 0 ) {
		(document.getElementById("priceInput") as HTMLInputElement).className = "tableInput wrongAdd"
		check[5] = false
	} else {
		(document.getElementById("priceInput") as HTMLInputElement).className = "tableInput correctAdd"
		check[5] = true
	}
	console.log(check)
	return check[0] && check[1] && check[2] && check[3] && check[4] && check[5]
}

fun getValue(str:String):String = (document.getElementById(str) as HTMLInputElement).value

fun inputIntValue(str:String):Int = (document.getElementById(str) as HTMLInputElement).value.toInt()

fun getValueSelected(str:String):String = (document.getElementById(str) as HTMLSelectElement).value

typealias BookList = MutableList<Book>

typealias OrderList = MutableList<Order>