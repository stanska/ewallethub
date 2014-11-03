case class TestClass(val name:String) {
	
}

object test {
val t1 =TestClass("St")                           //> t1  : TestClass = TestClass(St)
val t2 = TestClass("St")                          //> t2  : TestClass = TestClass(St)
println(t1 == t2)                                 //> true
println(t1.eq(t2))                                //> false
var t3 = "23"                                     //> t3  : String = 23
var t4 = "23"                                     //> t4  : String = 23
t3.eq(t4)                                         //> res0: Boolean = true
t3 == t4                                          //> res1: Boolean = true
}