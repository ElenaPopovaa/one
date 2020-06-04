package data

import component.OrderList

data class Order(
		var bookID:Int,
		var quantity:Int,
		var userName:String,
		var userSName:String,
		var adress:String
){ override fun toString(): String  = this.bookID.toString() }

var orders:OrderList = mutableListOf(
	Order(
		5,
		2,
		"Александр",
		"Шляпик",
		"Ул.Пушкина, кв 1"
	),
	Order(
		4,
		4,
		"Сергей",
		"Есенин",
		"Ул.Ленина, кв 5"
	),
	Order(
		3,
		1,
		"Алексей",
		"Лютик",
		"Ул.3-я Северная, кв 12"
	),
	Order(
		2,
		10,
		"Саша",
		"Белый",
		"Ул.Воровская, кв 10"
	),
	Order(
		1,
		5,
		"Данил",
		"Багров",
		"Ул.Ленина, дом 3, кв 22"
	)
)