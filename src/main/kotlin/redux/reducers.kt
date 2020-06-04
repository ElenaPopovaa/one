package redux

import component.BookList
import component.OrderList
import data.*

fun changeReducer(state: State, action: RAction) =
    when (action) {
        is MakeOrder -> State(
                decreaseQuantity(state.books,
                        action.order.bookID,
                        action.order.quantity),
                addOrder(state.orders, action.order)
        )
        is AddBook -> State(
                addBook(state.books,
                        action.name,
                        action.author,
                        action.genre,
                        action.description,
                        action.quantity,
                        action.price),
                state.orders
        )
        is DeleteBook -> State(
                deleteBook(state.books,action.index),
                state.orders
        )
        else -> state
    }

fun deleteBook(books:BookList, index:Int):BookList{
    books[index].visible = !books[index].visible
    return books
}

fun addBook(books:BookList, name:String,author:String,genre:String,description:String,quantity:Int,price:Int) : BookList{
    books.add(
        Book(
            name,
            author,
            description,
            genre,
            quantity,
            price
        )
    )
    return books
}

fun decreaseQuantity(
        books:BookList,
        bookID:Int, orderQuantity:Int): BookList{
    books[bookID].quantity -= orderQuantity
    return books
}

fun addOrder(
     orders: OrderList,
     newOrder: Order): OrderList{
    val temp = orders
    temp.add(newOrder)
    return temp
}