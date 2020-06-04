package component

import hoc.withDisplayName
import react.*
import react.dom.*
import react.functionalComponent


interface OrdersPageProps : RProps {
    var orders: OrderList
    var books:BookList
}

fun ordersPageFC() =
    functionalComponent<OrdersPageProps> { props ->
        div("container") {
            h1("head"){+"Заказы"}
            div("table-wrapper"){
                table("fl-table"){
                    thead{
                        tr {
                            th { +"#" }
                            th { +"ID книги" }
                            th { +"Название книги" }
                            th { +"Количество" }
                            th { +"Адресс" }
                            th { +"Имя" }
                            th { +"Фамилия" }
                            th { +"Итоговая стоимость" }
                        }
                    }
                    tbody {
                        props.orders.forEachIndexed { orderIndex, order ->
                            if(props.books[order.bookID].visible){ //!!!!!!!!!!!!!!!
                                tr {
                                    td { +"$orderIndex" }
                                    td { +"${order.bookID}" }
                                    td { +"${props.books[order.bookID]}" }
                                    td { +"${order.quantity}" }
                                    td { +order.adress }
                                    td { +order.userName }
                                    td { +order.userSName }
                                    td { +"${(order.quantity * props.books[order.bookID].price)}" }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

fun RBuilder.ordersPage(
        orders : OrderList,
        books : BookList
) = child(
    withDisplayName("OrdersPage", ordersPageFC())
){
    attrs.orders = orders
    attrs.books = books
}
