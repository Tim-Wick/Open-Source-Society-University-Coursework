fun is_older (date1 : int * int * int, date2 : int * int * int) =
    if date1 = date2
    then false
    else
	if #1 date1 < #1 date2
	then true
	else
	    if #2 date1 < #2 date2
	    then true
	    else
		if #3 date1 < #3 date2
		then true
		else false
			 
fun number_in_month (dates : (int * int * int) list, month : int) =
    if null dates then 0
    else if #2 (hd dates) = month
    then 1 + number_in_month(tl dates, month)
    else number_in_month(tl dates, month)

fun number_in_months (dates : (int * int * int) list, months : int list) =
    if null dates orelse null months
    then 0
    else number_in_month(dates, (hd months)) + number_in_months(dates, (tl months))

fun dates_in_month (dates : (int * int * int) list, month : int) =
    if null dates then []
    else if #2 (hd dates) = month
    then ((hd dates)::dates_in_month(tl dates, month))
    else dates_in_month(tl dates, month)
 		      
fun dates_in_months (dates : (int * int * int) list, months : int list) =
    if null dates orelse null months then []
    else dates_in_month(dates, (hd months)) @ dates_in_months(dates, (tl months))

fun get_nth (string : string list, n : int) = 
    if (n = 1)
    then (hd string)		   
    else get_nth((tl string), n-1)

fun date_to_string (date : (int * int * int)) =
    get_nth(["January","February","March","April","May","June","July","August","September","October","November","December"], (#2 date)) ^ " " ^ Int.toString(#3 date) ^ ", " ^ Int.toString(#1 date) 

fun number_before_reaching_sum (sum : int, list : int list) = 
    if sum > (hd list)
    then 1 + number_before_reaching_sum(sum - (hd list), (tl list))
    else 0

fun what_month (day : int) =
     number_before_reaching_sum(day, [31,28,31,30,31,30,31,31,30,31,30,31]) + 1
										  
fun month_range (day1 : int, day2 : int) = 
    if day1 > day2 then []
    else
	if day1 <= day2 then what_month(day1) :: month_range(day1 + 1, day2)
    else []	

fun oldest (dates : (int * int * int) list) = 
    if null dates
    then NONE
    else let
        fun oldest_nonempty (dates2 : (int * int * int) list) = 
            if null (tl dates2) then (hd dates2)
            else let val old = oldest_nonempty(tl dates2)
               in
		   if is_older(hd dates2, old)
		   then (hd dates2)
                   else old
	       end
    in
        SOME (oldest_nonempty(dates))
    end
