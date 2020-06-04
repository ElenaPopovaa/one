package component

import data.*
import hoc.withDisplayName
import react.*
import react.router.dom.*
import redux.*
import react.RBuilder
import store

interface AppProps : RProps {
    var store: Store<State, RAction, WrapperAction>
}
fun appFC() =
    functionalComponent<AppProps> {
        navBar()
        switch{
            store.getState().books.mapIndexed { bookIndex, book ->
                route("/book$bookIndex",
                    exact = true,
                    render = {
                        bookOrderPage(book, bookIndex)
                    }
                )
            }
            route("/editbook",
                exact = true,
                render = {
                    editBookList(store.getState().books)
                }
            )
            route("/orders",
                exact = true,
                render = {
                    ordersPage(store.getState().orders, store.getState().books )
                }
            )
            route("/booklist",
                exact = true,
                render = {
                    bookList(store.getState().books)
                }
            )
            route("/search",
                exact = true,
                render = {
                    search(store.getState().books)
                }
            )
        }
    }


fun RBuilder.app( store: Store<State, RAction, WrapperAction> ) =
    child( withDisplayName( "App", appFC() ) ){ attrs.store = store }






