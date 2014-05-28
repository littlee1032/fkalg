object test {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

	List('a', 'b', 'a').groupBy(w => w).map{
	case (k,v) => (k, v.size)
	}.toList                                  //> res0: List[(Char, Int)] = List((b,1), (a,2))

}