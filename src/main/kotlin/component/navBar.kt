package component

import kotlinx.html.InputType
import kotlinx.html.id
import react.*
import react.dom.*
import react.router.dom.navLink

interface NavBarProps : RProps

val navBarFC =
	functionalComponent<NavBarProps> {
		nav("nav"){
			input( classes = "nav__trigger-input", type = InputType.checkBox ) {
				attrs.id = "trigger"
			}
			label( classes = "nav__trigger-finger" ) {
				attrs.htmlFor = "trigger"; span{+""}
			}
			ul("nav__list") {
				li("nav__item") {
					navLink(className = "nav__link", to = "") {
						span { +"HOME" } }
				}
				li("nav__item") {
					navLink(className = "nav__link", to = "/booklist") {
						span { +"Список книг" } }
				}
				li("nav__item") {
					navLink(className = "nav__link", to = "/search") {
						span { +"Поиск" } }
				}
				li("nav__item") {
					navLink(className = "nav__link", to = "/orders") {
						span { +"Заказы" } }
				}
				li("nav__item") {
					navLink(className = "nav__link", to = "/editbook") {
						span { +"Редактировать" } }
				}
			}
		}
	}

fun RBuilder.navBar() =
		child(navBarFC) { }