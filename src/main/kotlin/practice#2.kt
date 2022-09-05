fun myFun (x: Int, y: Int,
           z: (Int, Int) -> Int =
               {x: Int, y: Int -> x + y}): Int {
    val result = z(x,y)
    return result
}
var addFive2: (Int) -> Int = { it + 5 }
val myLam: (Int) -> Int  = {
    println(it)
    it + 7
}

fun main(){
    println(myFun(1,2))
    myLam(5)
}