fun sum_list (xs : int list) =
    if null xs
    then 0
    else hd xs + sum_list(tl xs)

fun list_product (i : int list) = 
    if null i
    then 1
    else hd i * list_product(tl i)

fun countdown (x : int) = 
    if x = 0
    then []
    else x :: countdown(x - 1)

fun append (xs : int list, ys : int list) =
    if null xs
    then ys
    else (hd xs) :: append((tl xs), ys)

fun sum_pair_list (xs : (int * int) list) =
    if null xs
    then 0
    else #1 (hd xs) + #2 (hd xs) + sum_pair_list(tl xs)

fun first (xs : (int * int) list) = 
    if null xs
    then []
    else (#1 (hd xs)) :: first(tl xs)

fun seconds (xs : (int * int) list) = 
    if null xs
    then []
    else (#2 (hd xs)) :: seconds(tl xs)

fun sum_pair_list2 (xs : (int * int) list) = 
    (sum_list (first xs)) + (sum_list (seconds xs))
