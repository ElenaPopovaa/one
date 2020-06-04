package redux
import data.Order


class MakeOrder(var order: Order) : RAction

class DeleteBook(val index:Int) : RAction

class AddBook(val name:String,
              val author:String,
              val genre:String,
              val description : String,
              val quantity:Int,
              val price:Int) : RAction