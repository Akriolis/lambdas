var addFive = {x: Int -> x + 5}
// this similar to the fun addFive(x: Int) = x + 5
// all lambdas are defined with curly braces
// -> separate any parameters from the lambda body
// lambda can have single parameters, multiple parameters or none at all
// the body can have multiple lines and the last evaluated body is used as the lambda's return value

val tryLambda = {x: Int, y: Int -> x + y}
val goofyLambda = {"Pow!"}

// a function that uses a lambda as a parameter or return value is known as a higher-order function

fun convert (x: Double, converter: (Double) -> Double): Double{
    val result = converter(x)
    println("$x is converted to $result")
    return result
}

// if you have just one parameter and that parameter is a lambda, you can omit the parentheses.
fun convertFive (converter2: (Int) -> Double): Double{
    val result = converter2(5)
    println("5 is converted to $result")
    return result
}

// in general, lambdas is a type of function but with no name

// a function can return a lambda

fun getConversionLambda (str: String): (Double) -> Double{
    if (str == "CentigradetoFahrenheit"){
        return { it * 1.8 + 32}
    } else if (str == "KgsToPounds"){
        return { it * 2.204623}
    } else if (str == "PoundsToUSTons") {
        return { it / 2000.0 }
    } else{
        return { it }
    }

    /*
    similar to
    when (str) {
        "CentigradetoFahrenheit" -> {
            return { it * 1.8 + 32}
        }
        "KgsToPounds" -> {
            return { it * 2.204623}
        }
        "PoundsToUSTons" -> {
            return { it / 2000.0 }
        }
        else -> {
            return { it }
        }
    }     */
}

fun combine(lambda1: DoubleConversion, lambda2: DoubleConversion): DoubleConversion{
    return { x: Double -> lambda2(lambda1(x))}
}

//type alias
typealias DoubleConversion = (Double) -> Double
// it's just a placeholder for (Double) -> Double

typealias DuckArray = Array<Int>


fun main(){
    println(goofyLambda) // when you assign a lambda to a variable you are assigning
    // a block of code to it, not the result of code being run
    println(goofyLambda.invoke())
    val result = tryLambda(5,6)
    // it is similar to val result = tryLambda.invoke(5,6)
    println(result)

    // a lambda's type is also known as a function type
    // (parameters) -> return_type
    // val msg = { x: Int -> "The value is $x" }
    // (Int) -> String

    val myLambda: (Int, Int) -> Int
    myLambda = {x: Int, y: Int -> x + y} // how explicitly define the variable's type
    val greetings: () -> String = { "Hello" }

    // with a single parameter you can use "it" keyword for referring to it
    // works only if compiler can infer its type
    var addFive2: (Int) -> Int = { it + 5 }
    // val myAdd = { it + 5 } this code won't compile

    // use Unit to say a lambda has no return value
    val lalaLambda: () -> Unit = { println("Hi there!")}
    lalaLambda.invoke()
    val calculation: (Int, Int) -> Unit = { x, y -> x + y }
    val myResult = calculation(5,5)
    println(myResult)

    // convert(20.0, {y: Double -> y * 1.8 + 32})
    convert (20.0) { y: Double -> y * 1.8 + 32}
    // you can move lambda outside the function call's parentheses

    convertFive { it * 1.8 + 32 }

    val testLambda = {
        c: Double -> println(c)
        c * 1.8 + 32
    }
    print(testLambda.invoke(1.1))

    val pounds = getConversionLambda("KgsToPounds") (2.5)
    val fahrenheit = getConversionLambda("CentigradeToFahrenheit") (31.5)
    val usTons = getConversionLambda("PoundsToUSTons") (10.0)
    val goofyConvert = getConversionLambda("Auf") (0.1)
    println(pounds)
    println(fahrenheit)
    println(usTons)
    println(goofyConvert)

    convert(20.0, getConversionLambda("PoundsToUSTons"))

    // Convert 2.5 kg to Pounds
    println("Convert 2.5kg to Pounds: ${getConversionLambda("KgsToPounds") (2.5)}")

    // Define two conversion lambdas
    val kgsToPoundsLambda = getConversionLambda("KgsToPounds")
    val poundsToUSTonsLambda = getConversionLambda("PoundsToUSTons")

    // Combine the two lambdas to create a new one
    val kgsToUSTonsLambda = combine (kgsToPoundsLambda, poundsToUSTonsLambda)

    // Use the new lambda to convert 17.4 to US tons
    val value = 17.4
    println("$value kgs if ${convert(value, kgsToUSTonsLambda)} US tons")


}

