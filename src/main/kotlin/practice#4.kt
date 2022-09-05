fun search (list: List<Grocery>, criteria: (g: Grocery) -> Boolean){
    for (i in list){
        if (criteria (i)) {
            println(i.name)
        }
    }
}

data class Grocery (val name: String,
                    val category: String,
                    val unit: String,
                    val unitPrice: Double)

fun main(){
    val groceries = listOf(
        Grocery("Tomatoes", "Vegetable", "lb", 3.0),
        Grocery("Mushrooms", "Vegetable", "lb", 4.0),
        Grocery("Bagels", "Bakery", "Pack", 1.5),
        Grocery("Olive oil", "Pantry", "Bottle", 6.0),
        Grocery("Ice cream", "Frozen", "Pack", 3.0)
    )

    println("Expensive ingredients: ")
    search(groceries, {x: Grocery -> x.unitPrice > 5.0})
    println("All vegetables:")
    search(groceries) {y: Grocery -> y.category == "Vegetable"}
    println("All packs:")
    search(groceries) {i: Grocery -> i.unit == "Pack"}
}