
#lang racket

(provide (all-defined-out)) ;; so we can put tests in a second file
(require rackunit)

(define ones (lambda () (cons 1 ones)))
(define a 2)
;; put your code below

(define (sequence low high stride)
  (cond [(< high low) '()]
        [#t (cons low (sequence (+ low stride) high stride))])) 

(define (string-append-map xs suffix)
  (map (lambda (x) (string-append x suffix)) xs))

(define (list-nth-mod xs n)
  (cond [(< n 0) error "list-nth-mod: negative number"]
        [(= n 0) list-ref xs (0)]
        [(null? xs) error "list-nth-mod: empty list"]
        [(< (length xs) n) n]
        [list-ref xs (remainder n (length xs))]))

(define (stream-for-n-steps s n)
  (cond [(= n 0) null]
        [#t (cons (car (s)) (stream-for-n-steps (cdr (s)) (- n 1)))]))

(define funny-number-stream
  (letrec ([f (lambda (x) (if (= (remainder x 5) 0)
                             (cons (- x) (lambda () (f (+ x 1))))
                             (cons x (lambda () (f (+ x 1))))))])
  (lambda () (f 1))))

(define dan-then-dog
  (letrec ([f (lambda (x) (if (string=? x "dan.jpg")
                              (cons x (lambda () (f "dog.jpg")))
                              (cons x (lambda () (f "dan.jog")))))])
    (lambda () (f "dan.jpg"))))

(define (stream-add-zero s)
  (lambda () (cons (cons 0 (car (s))) (stream-add-zero (cdr (s))))))

(define (cycle-lists xs ys)
  (letrec ([f (lambda (n)
                (cons (cons (list-nth-mod xs n) (list-nth-mod ys n)) 
                      (lambda () (f (+ 1 n)))))])
    (lambda () (f 0))))

(define (vector-assoc v vec)
  (let ([len (vector-length vec)])
    (letrec ([f (lambda (pos)
      (cond ((= pos len) #f)
            ((pair? (vector-ref vec pos))
                    (if (equal? (car (vector-ref vec pos)) v)
                        (vector-ref vec pos)
                        (f (+ pos 1))))
            (#t (f (+ pos 1)))))])
      (f 0))))

(define (cached-assoc xs n)
  (let ([memo (make-vector n #f)]
        [pos 0])
    (lambda (v)
      (or (vector-assoc v memo)
          (let ([answer (assoc v xs)])
            (and answer
                 (begin (vector-set! memo pos answer)
                        (set! pos
                              (if (= (+ pos 1) n) 0
                                  (+ pos 1)))
                        answer)))))))
                
