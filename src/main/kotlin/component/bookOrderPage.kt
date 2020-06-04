package component

import data.Order
import data.Book
import hoc.withDisplayName
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.functionalComponent
import react.router.dom.navLink
import redux.MakeOrder
import store
import kotlin.browser.document

interface BookOrderPageProps : RProps

fun bookOrderPageFC(book:Book, productIndex:Int) =
    functionalComponent<BookOrderPageProps> {
        div("orderContainer") {
            navLink("/booklist"){
                button(classes = "button first") {
                    +"Назад"
                }
            }
            div("textHolder"){
                h2 { +book.name }
                p{ +"Автор: ${book.author}" }
                p{ +"Жанр: ${book.genre}" }
                p{ +"Описание: ${book.description}" }
                p { +"Цена: ${book.price}" }
                p { +"Количество: ${book.quantity}" }
            }
            div("container order"){
                h3("head"){+"Сделать заказ"}
                div{
                    input(InputType.number, classes = "correctAdd") {
                        attrs.id = "orderQuantityInput"
                        attrs.placeholder = "Укажите количество"
                        attrs.defaultValue = "1"
                    }
                }
                div{
                    input(InputType.text, classes = "correctAdd") {
                        attrs.id = "orderNameInput"
                        attrs.placeholder = "Введите имя"
                    }
                }
                div{
                    input(InputType.text, classes = "correctAdd") {
                        attrs.id = "orderSNameInput"
                        attrs.placeholder = "Введите фамилию"
                    }
                }
                div{
                    input(InputType.text, classes = "correctAdd") {
                        attrs.id = "orderAddressInput"
                        attrs.placeholder = "адрес"
                    }
                }
                button {
                    +"Поддтвердить"
                    attrs.onClickFunction = onClickSubmit(book, productIndex)
                }
                div { attrs.id = "got" }
            }
        }
    }



fun RBuilder.bookOrderPage(
        book:Book,
        productIndex: Int
) = child(
    withDisplayName("bookOrderPage",  bookOrderPageFC(book,productIndex))
){
}
