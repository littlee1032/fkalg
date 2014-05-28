object test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(57); 
  println("Welcome to the Scala worksheet");$skip(80); val res$0 = 

	List('a', 'b', 'a').groupBy(w => w).map{
	case (k,v) => (k, v.size)
	}.toList;System.out.println("""res0: List[(Char, Int)] = """ + $show(res$0))}

}
