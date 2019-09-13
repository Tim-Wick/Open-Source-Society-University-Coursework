(* Dan Grossman, Coursera PL, HW2 Provided Code *)

(* if you use this function to compare two strings (returns true if the same
   string), then you avoid several of the functions in problem 1 having
   polymorphic types that may be confusing *)
fun same_string(s1 : string, s2 : string) =
    s1 = s2

(* put your solutions for problem 1 here *)

fun all_except_option (str, strList) =
    case strList of
    [] => NONE
     | head::tail => if same_string (head, str) then SOME tail
		     else case all_except_option (str, tail) of
			      NONE => NONE
			    | SOME other => SOME (head::other)

fun get_substitutions1 (substitutions, s) = 
    case substitutions of
	[] => []
      | head::tail => case all_except_option (s, head) of
			  SOME strlist => strlist @ get_substitutions1 (tail,s)
			| NONE => get_substitutions1 (tail,s)

fun get_substitutions2 (substitutions, s) = 
    case substitutions of
	[] => []
      | head::tail => let fun aux (strList, s, acc) =
			      case strList of
				  [] => acc
				| tempH::tempT => case all_except_option (s, tempH) of
						      SOME strList => aux (tempT, s, acc @ strList)
						    | NONE => aux (tempT, s, acc)
		      in
			  aux (substitutions, s, [])
		      end
			  
fun similar_names (strListList, name: {first:string,middle:string,last:string}) =
    let val {first=x,middle=y,last=z} = name
    in
	case get_substitutions2(strListList, x) of
	    [] => [name]
	  | head::tail => let fun append(name_list, acc) =
				  case name_list of
				      [] => acc
				    | tempH::tempT => append (tempT, acc@[{first=tempH, middle=y, last=z}])
			  in
			      append ((head::tail), [name])
			  end
    end

(* you may assume that Num is always used with values 2, 3, ..., 10
   though it will not really come up *)
datatype suit = Clubs | Diamonds | Hearts | Spades
datatype rank = Jack | Queen | King | Ace | Num of int 
type card = suit * rank

datatype color = Red | Black
datatype move = Discard of card | Draw 

exception IllegalMove

(* put your solutions for problem 2 here *)

fun card_color (card) =
    case card of
	(Clubs,_)  => Black
      | (Spades,_) => Black
      | (_,_) => Red

		    
fun card_value (card) = 
    case card of	
	(_,Num i) => i		 
      | (_,Ace) => 11	       
      | (_,_) => 10
		     
fun remove_card (cardList, card, ex) = 
    case cardList of
	card::rest => let fun check_card(remaining, acc) =
			      case remaining of
				  [] => raise ex
				| head::tail => if head=card then (acc@tail)
						else check_card(tail, acc@[head])
		      in check_card(cardList, [])
end
			 | _ => raise ex 

				     
fun all_same_color (cardList) = 
    case cardList of
	[] => true
      | _::[] => true
      | head::(neck::rest) => card_color(head) = card_color(neck)
			      andalso all_same_color(neck::rest)

fun sum_cards (cardList) =
    let fun aux(cardList,acc) =
	    case cardList of
		[] => acc
	      | x::cardList' => aux(cardList',card_value(x) + acc)
    in
	aux(cardList,0)
    end
	
fun score(cardList, int) = 
    let val sum = sum_cards(cardList)
    in
	case all_same_color(cardList) of
	    true => if sum>int then 3*(sum-int) div 2
		    else (int-sum) div 2
	  | false => if sum>int then 3*(sum-int)
		     else int-sum
    end
	
fun officiate (cardList, moveList, int) =
    let fun game (holdList, currentList, curMoveList) =
	    case curMoveList of
		Draw::otherMove => (case currentList of
					firstCard::remain => let val updateHoldList = (firstCard::holdList)
							     in
								 if (sum_cards(updateHoldList)>int)
								 then score((updateHoldList),int)
								 else game(updateHoldList, remain, otherMove)
									  end
					| [] => score(holdList, int)
									 )
	      | (Discard aCard)::otherMove => game(remove_card(holdList, aCard, IllegalMove), currentList, otherMove)
	      | [] => score(holdList,int)
    in
	game([],cardList,moveList)
    end
	
	   
fun officiate (cardlist, movelist, goal) =
    let fun run_game (held_list, current_card_list, current_move_list) =
	    case current_move_list of
		Draw::other_move => (case current_card_list of
					first_card::remain => let val update_held_list = (first_card::held_list)
							      in
								  if (sum_cards(update_held_list)>goal)
								  then score((update_held_list), goal)
								  else run_game(update_held_list, remain, other_move)
							      end
				      | [] => score(held_list, goal)
				    )
		| (Discard a_card)::other_move => run_game(remove_card(held_list, a_card,IllegalMove), current_card_list, other_move)
		| [] => score(held_list, goal)								 
    in
	run_game([], cardlist, movelist)
    end	
