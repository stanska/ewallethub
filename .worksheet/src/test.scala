case class TestClass(val name:String) {
	
}

object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(82); 
val t1 =TestClass("St");System.out.println("""t1  : TestClass = """ + $show(t1 ));$skip(25); 
val t2 = TestClass("St");System.out.println("""t2  : TestClass = """ + $show(t2 ));$skip(18); 
println(t1 == t2);$skip(19); 
println(t1.eq(t2));$skip(14); 
var t3 = "23";System.out.println("""t3  : String = """ + $show(t3 ));$skip(14); 
var t4 = "23";System.out.println("""t4  : String = """ + $show(t4 ));$skip(10); val res$0 = 
t3.eq(t4);System.out.println("""res0: Boolean = """ + $show(res$0));$skip(9); val res$1 = 
t3 == t4;System.out.println("""res1: Boolean = """ + $show(res$1))}
}
