package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    (c, r) match {
      case (0, _) => 1
      case (c, r) => if (c == r) 1 else pascal(c - 1, r - 1) + pascal(c, r - 1) 
    }
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def check(count: Int, chars: List[Char]): Boolean = {
      chars match {
        case Nil => count == 0
        case '(' :: rest => check(count + 1, rest)
        case ')' :: rest => if (count < 1) false else check(count - 1, rest)
        case _ :: rest => check(count, rest)
      }
    }
    
    check(0, chars)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def sortedCountChange(money: Int, coins: List[Int]): Int = {
      (money, coins) match {
        case (0, _) => 1
        case (_, Nil) => 0
        case (money, coins) => {
          (if (money >= coins.head) sortedCountChange(money, coins.init) else 0) +
          (if (money >= coins.last) sortedCountChange(money - coins.last, coins) else 0);
        } 
      }
    }

    sortedCountChange(money, coins.sorted)
  }
}
