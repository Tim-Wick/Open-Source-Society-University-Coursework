def min_payment(balance, annualInterestRate):
    lower_payment = balance / 12
    upper_payment = (balance * (1 + annualInterestRate)**12) / 12
    end_balance = balance
    epsilon = .01
    payment = (lower_payment + upper_payment) / 2
    while abs(end_balance) > epsilon:
        end_balance = balance
        payment = (lower_payment + upper_payment) / 2
        for i in range(12):
            end_balance = (end_balance - payment) * (1 + (annualInterestRate/12))
        print(end_balance)
        if end_balance > 0:
            lower_payment = payment
        else:
            upper_payment = payment

    print("Lowest Payment: {}".format(round(payment, 2)))

min_payment(320000, .2)
min_payment(999999, .18)
