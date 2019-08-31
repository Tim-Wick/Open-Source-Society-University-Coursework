(* This is a comment and the first program. *)

val x = 34;
(* static environment (before program is run): x : int *)
(* dynamic environment: x -> 34 *)

val y = 17;
(* dynamic environment: x-> 34, t -> 17 *)
(* static environment: x : int, y : int *)

val z = (x + y) + (y + 2);
(* dynamic environment: x -> 34, y -> 17, z -> 70 *)
(* static environment: x : int, y : int, z : int *)

val q = z + 1;
(* dynamic environment: x -> 34, y -> 17, z -> 70, w-> 71 *)
(* static environment: x : int, y : int, z : int, w : int *)

val abs_of_z = if z < 0 then 0 - z else z; (* bool *)(* int *)
(* dynamic environment : ....., abs_of_z -> 70 *)

val abs_of_z_simpler = abs z;
