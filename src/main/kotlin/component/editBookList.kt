package component

import hoc.withDisplayName
import kotlinx.html.*
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.functionalComponent
import redux.AddBook
import redux.DeleteBook
import store
import kotlin.browser.document

interface EditBookListProps : RProps

fun editBookListFC(books:BookList) =
	functionalComponent<EditBookListProps> {
		div("container") {
			h1("head"){+"Страница редактирования"}
			div("table-wrapper") {
				table("fl-table") {
					thead {
						tr {
							th { +"Название" }
							th { +"Автор" }
							th { +"Жанр" }
							th { +"Количество" }
							th { +"Цена" }
							th { +"Добавить" }
						}
					}
					tbody {
						tr{
							td {
								input(InputType.text, classes = "tableInput") {
									attrs.id = "nameInput"
									attrs.placeholder = "Введите название"
								}
							}
							td {
								input(InputType.text, classes = "tableInput") {
									attrs.id = "authorInput"
									attrs.placeholder = "Введите автора"
								}
							}
							td {
								input(InputType.text, classes = "tableInput") {
									attrs.id = "genreInput"
									attrs.placeholder = "Введите жанр"
								}
							}
							td {
								input(InputType.number, classes = "tableInput") {
									attrs.id = "quantityInput"
									attrs.placeholder = "Введите количество"
									attrs.defaultValue = "1"
								}
							}
							td {
								input(InputType.number, classes = "tableInput") {
									attrs.id = "priceInput"
									attrs.placeholder = "Введите цену"
									attrs.defaultValue = "1"
								}
							}
							td {
								button {
									+"Добавить"
									attrs.onClickFunction = onClickAddBook()
								}
							}
						}
					}
				}
				textArea ( classes = "tableInput area"){
					attrs.id = "descriptionInput"
					attrs.placeholder = "Введите описание"
				}
				button(classes = "tableInput"){
					+"[ТЕСТОВОЕ ЗАПОЛНЕНИЕ]"
					attrs.onClickFunction = onClickTestFill()
				}
				select{
					attrs.id = "deleteSelected"
					books.mapIndexed{index, book ->
						if(book.visible){
							option {
								+"$index $book"
							}
						}
					}
				}
				button{
					+"удалить"
					attrs.onClickFunction = onClickDeleteSelected()
				}
			}
		}
	}


fun RBuilder.editBookList(
		books:BookList
) = child(withDisplayName("editBookList", editBookListFC(books))){}

