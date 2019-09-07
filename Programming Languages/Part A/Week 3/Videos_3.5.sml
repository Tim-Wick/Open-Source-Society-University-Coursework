datatype suit = Club | Diamond | Heart | Spade

datatype rank = Jack | Queen | King | Ace | Num of int

datatype id = StudentNum of int
            | Name of string
		      * (string option)
		      * string
			    
datatype exp = Constant of int
	     | Negate of exp
	     | Add of exp * exp
	     | Multiply of exp * exp
				    
fun eval e =
    case e of
	Constant i => i
      | Negate e2 => ~ (eval e2)
      | Add(e1,e2) => (eval e1) + (eval e2)
      | Multiply(e1,e2)  => (eval e1) * (eval e2) 

val x : exp = Add(Constant 19, Negate (Constant 4))
val y : int = eval x
